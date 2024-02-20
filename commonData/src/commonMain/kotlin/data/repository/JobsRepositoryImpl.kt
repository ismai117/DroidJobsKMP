package data.repository


import data.service.JobsService
import domain.model.Jobs
import domain.repository.JobsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import utils.ResultState


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