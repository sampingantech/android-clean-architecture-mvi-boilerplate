package org.buffer.android.boilerplate.presentation.browse

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import org.buffer.android.boilerplate.domain.interactor.browse.GetArticles
import javax.inject.Inject

class BrowseProcessor @Inject constructor(private val getArticles: GetArticles) {

    private fun conversationsProcessor() = runBlocking {
        getArticles.invoke().map {
            BrowseResult.LoadArticleTask.success(it)
        }.catch {
            BrowseResult.LoadArticleTask.failure()
        }.onStart { emit(BrowseResult.LoadArticleTask.inFlight()) }
    }

    var actionProcessor: Flow<BrowseResult.LoadArticleTask>

    init {
        this.actionProcessor = conversationsProcessor()
        }
    }
