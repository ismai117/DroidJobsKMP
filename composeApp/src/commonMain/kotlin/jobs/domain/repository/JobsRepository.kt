package jobs.domain.repository



import jobs.domain.model.JobRole


interface JobsRepository {
    suspend fun getJobRoles(): List<JobRole>
    suspend fun getJobById(jobId: String): JobRole?
}