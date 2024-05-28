package jobs.presentation

import jobs.domain.model.JobRole

data class JobsState(
    val isLoading: Boolean = false,
    val jobRole: JobRole? = null,
    val allJobRoles: List<JobRole> = emptyList(),
    val error: String = "",
)
