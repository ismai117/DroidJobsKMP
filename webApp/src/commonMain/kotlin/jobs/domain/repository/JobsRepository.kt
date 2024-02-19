package jobs.domain.repository


import jobs.domain.model.Jobs
import com.ncgroup.droidjobs.utils.ResultState
import kotlinx.coroutines.flow.Flow


interface JobsRepository {
    suspend fun getAllJobs(): Flow<ResultState<List<Jobs>>>
    suspend fun getJob(jobID: String): Flow<ResultState<Jobs>>
}