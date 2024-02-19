import jobs.data.repository.JobsRepositoryImpl
import jobs.data.service.JobsService
import jobs.domain.repository.JobsRepository


object DroidJobsKMP {

    private val jobsService: JobsService by lazy {
        JobsService()
    }

    val jobsRepository: JobsRepository by lazy {
        JobsRepositoryImpl(jobsService = jobsService)
    }

}