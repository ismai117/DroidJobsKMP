import data.repository.JobsRepositoryImpl
import data.service.JobsService
import domain.repository.JobsRepository


object DroidJobsKMP {

    private val jobsService: JobsService by lazy {
        JobsService()
    }

    val jobsRepository: JobsRepository by lazy {
        JobsRepositoryImpl(jobsService = jobsService)
    }

}