package jobs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.Jobs
import domain.repository.JobsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utils.ResultState

@OptIn(FlowPreview::class)
class JobsScreenModeL(
    private val jobsRepository: JobsRepository
) : ScreenModel {

    var state by mutableStateOf(JobsState())

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searching = MutableStateFlow(false)
    val searching = _searching.asStateFlow()

    private val allJobs = mutableListOf<Jobs>()

    private val _jobs = MutableStateFlow(allJobs)
    val jobs = query
        .debounce(500L)
        .onEach { _searching.update { true } }
        .combine(_jobs) { text, jobs ->
            if (text.isBlank()){
                jobs
            }else {
                jobs.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _searching.update { false } }
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _jobs.value
        )

    init {
        getAllJobs()
    }

    private fun getAllJobs() {
        screenModelScope.launch {
            jobsRepository.getAllJobs().collect { result ->
                withContext(Dispatchers.Main){
                    when(result) {
                        is ResultState.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                        is ResultState.Success -> {
                            state = state.copy(isLoading = false)
                            result.data.orEmpty().forEach {
                                allJobs.add(it)
                            }
                        }
                        is ResultState.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message.orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }

    fun getJob(id: String) {
        screenModelScope.launch {
            jobsRepository.getJob(
                jobID = id
            ).collect { result ->
                when(result) {
                    is ResultState.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        state = state.copy(
                            isLoading = false,
                            job = result.data
                        )
                    }
                    is ResultState.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message.orEmpty()
                        )
                    }
                }
            }
        }
    }

    fun onQueryChange(query: String){
       _query.value = query
    }



}

