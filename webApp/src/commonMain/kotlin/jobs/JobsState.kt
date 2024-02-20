package jobs

import domain.model.Jobs

data class JobsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val allJobs: List<Jobs> = emptyList(),
    val job: Jobs? = null
)
