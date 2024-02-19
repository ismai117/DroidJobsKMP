import jobs.JobsRepository
import jobs.JobsRepositoryImpl
import jobs.JobsService


object DroidJobsKMP {

    private val jobsService: JobsService by lazy {
        JobsService()
    }

    val jobsRepository: JobsRepository by lazy {
        JobsRepositoryImpl(jobsService = jobsService)
    }

}