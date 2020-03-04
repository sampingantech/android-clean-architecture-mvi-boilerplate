package org.buffer.android.boilerplate.domain.interactor.browse

import kotlinx.coroutines.flow.Flow
import org.buffer.android.boilerplate.domain.executor.CoroutinePostExecutionThread
import org.buffer.android.boilerplate.domain.executor.CoroutineThreadExecutor
import org.buffer.android.boilerplate.domain.interactor.CoroutineUseCase
import org.buffer.android.boilerplate.domain.model.Article
import org.buffer.android.boilerplate.domain.repository.ArticleRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Article] instances from the [ArticleRepository]
 */
open class GetArticles @Inject constructor(val articleRepository: ArticleRepository,
                                           threadExecutor: CoroutineThreadExecutor,
                                           postExecutionThread: CoroutinePostExecutionThread) :
        CoroutineUseCase<List<Article>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flow<List<Article>> {
        return articleRepository.getArticles()
    }

}