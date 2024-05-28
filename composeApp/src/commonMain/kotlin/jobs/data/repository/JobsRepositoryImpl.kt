package data.repository


import jobs.domain.repository.JobsRepository
import jobs.data.service.JobsService
import jobs.domain.model.JobRole


class JobsRepositoryImpl(
    private val jobsService: JobsService
) : JobsRepository {

    override suspend fun getJobRoles(): List<JobRole> {
        return jobsService.getAllJobs()
    }

    override suspend fun getJobById(jobId: String): JobRole? {
        return jobsService.getJob(jobId)
    }

}


