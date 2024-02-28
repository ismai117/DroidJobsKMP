package presentation

import domain.model.Jobs


data class JobsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val job: Jobs? = null,
    val allJobs: List<Jobs> = emptyList()
)
