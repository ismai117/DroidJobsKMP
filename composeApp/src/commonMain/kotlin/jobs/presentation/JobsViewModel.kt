package jobs.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jobs.domain.model.JobRole
import jobs.domain.repository.JobsRepository
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

@OptIn(FlowPreview::class)
class JobsViewModel(
    private val jobsRepository: JobsRepository
) : ViewModel() {

    var state by mutableStateOf(JobsState())

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searching = MutableStateFlow(false)
    val searching = _searching.asStateFlow()

    private val allJobs = mutableListOf<JobRole>()

    private val _jobs = MutableStateFlow(allJobs)
    val jobs = query
        .debounce(500L)
        .onEach { _searching.update { true } }
        .combine(_jobs) { text, jobs ->
            if (text.isBlank()) {
                jobs
            } else {
                jobs.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _searching.update { false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _jobs.value
        )


    fun getAllJobs() {
        viewModelScope.launch(Dispatchers.Default) {
            jobsRepository.getJobRoles()
                .forEach {
                    allJobs.add(it)
                }
        }
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }


}

