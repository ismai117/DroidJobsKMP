package jobs


import kotlinx.coroutines.flow.Flow
import utils.ResultState


interface JobsRepository {
    suspend fun getAllJobs(): Flow<ResultState<List<Jobs>>>
    suspend fun getJob(jobID: String): Flow<ResultState<Jobs>>
}