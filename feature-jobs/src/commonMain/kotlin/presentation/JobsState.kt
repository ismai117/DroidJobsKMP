package presentation


import jobs.Jobs



data class JobsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val job: Jobs? = null,
    val allJobs: List<Jobs> = emptyList(),

    val isInsertBookmarkLoading: Boolean = false,
    val statusInsertBookmark: Boolean= false,
    val errorInsertBookmark: String = ""
)
