package jobs.data.repository


import jobs.data.service.JobsService
import jobs.domain.model.Jobs
import jobs.domain.repository.JobsRepository
import com.ncgroup.droidjobs.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class JobsRepositoryImpl(
    private val jobsService: JobsService
) : JobsRepository {

    override suspend fun getAllJobs(): Flow<ResultState<List<Jobs>>> = callbackFlow {
        runCatching {
            trySend(ResultState.Loading())
            jobsService.getAllJobs()
        }.onSuccess {
            trySend(ResultState.Success(it))
        }.onFailure {
            trySend(ResultState.Error(message = it.message))
        }
        awaitClose {
            close()
        }
    }

    override suspend fun getJob(jobID: String): Flow<ResultState<Jobs>> = callbackFlow {
        runCatching {
            trySend(ResultState.Loading())
            jobsService.getJob(jobID)
        }.onSuccess {
            trySend(ResultState.Success(it))
        }.onFailure {
            trySend(ResultState.Error(message = it.message))
        }
        awaitClose {
            close()
        }
    }

}