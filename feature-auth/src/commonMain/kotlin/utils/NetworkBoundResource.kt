package utils.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {

    val data = query().firstOrNull()

    val flow = if (shouldFetch(data)){
        emit(UIState.Loading(data))
        delay(1_000)
        try {
            saveFetchResult(fetch())
            query().map { UIState.Success(it) }
        }catch (e: Exception){
            e.printStackTrace()
            query().map { UIState.Error(e.message.toString(), it) }
        }
    } else {
        emit(UIState.Loading())
        delay(1_000)
        query().map { UIState.Success(it) }
    }

    emitAll(flow)

}