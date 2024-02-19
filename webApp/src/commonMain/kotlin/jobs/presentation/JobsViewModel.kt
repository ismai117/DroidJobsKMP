package jobs.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ncgroup.droidjobs.jobs.presentation.JobsState
import jobs.domain.repository.JobsRepository
import com.ncgroup.droidjobs.utils.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JobsViewModel(
    coroutineScope: CoroutineScope? = null,
    private val jobsRepository: JobsRepository,

) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    var state by mutableStateOf(JobsState())

    private val _jobId = mutableStateOf("")

    fun setJobID(jobID: String) {
        _jobId.value = jobID
    }

    init {
        getAllJobs()
    }

    private fun getAllJobs() {
        viewModelScope.launch {
            jobsRepository.getAllJobs().collect { result ->
                withContext(Dispatchers.Main){
                    when(result) {
                        is ResultState.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                        is ResultState.Success -> {
                            state = state.copy(
                                isLoading = false,
                                allJobs = result.data.orEmpty()
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
    }

    fun getJob() {
        viewModelScope.launch {
            jobsRepository.getJob(
                jobID = _jobId.value
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

}