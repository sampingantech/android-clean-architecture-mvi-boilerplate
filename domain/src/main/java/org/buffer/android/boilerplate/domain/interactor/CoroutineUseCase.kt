package org.buffer.android.boilerplate.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.buffer.android.boilerplate.domain.executor.CoroutinePostExecutionThread
import org.buffer.android.boilerplate.domain.executor.CoroutineThreadExecutor

abstract class CoroutineUseCase<T, in Params> constructor(
        val threadExecutor: CoroutineThreadExecutor,
        val postThreadExecutor: CoroutinePostExecutionThread
) {
    protected abstract fun buildUseCaseObservable(params: Params? = null): Flow<T>

    open suspend fun invoke(params: Params? = null): Flow<T> {
        return withContext(postThreadExecutor.scheduler) {
            this@CoroutineUseCase.buildUseCaseObservable(params)
                    .flowOn(threadExecutor)
        }
    }
}