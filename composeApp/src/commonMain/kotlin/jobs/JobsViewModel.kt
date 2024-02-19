package jobs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JobsScreenModeL(
    private val jobsRepository: JobsRepository
) : ScreenModel {

    var state by mutableStateOf(JobsState())

    init {
        getAllJobs()
    }

    private fun getAllJobs() {
        screenModelScope.launch {
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

    fun getJob(id: String) {
        screenModelScope.launch{
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

}