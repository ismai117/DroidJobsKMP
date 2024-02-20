

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import domain.repository.JobsRepository
import jobs.JobsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import utils.ResultState


class JobsViewModel(
    private val jobsRepository: JobsRepository,
): ViewModel(){

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
                withContext(Dispatchers.Main) {
                    when (result) {
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
        viewModelScope.launch(Dispatchers.Main) {
            jobsRepository.getJob(
                jobID = _jobId.value
            ).collect { result ->
                when (result) {
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