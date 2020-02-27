package org.buffer.android.boilerplate.presentation.browse

import org.buffer.android.boilerplate.domain.model.Article
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.presentation.base.BaseResult
import org.buffer.android.boilerplate.presentation.base.model.TaskStatus

sealed class BrowseResult : BaseResult {

    class LoadBufferoosTask(val status: TaskStatus,
                            val bufferoos: List<Bufferoo>? = null) :
            BrowseResult() {

        companion object {

            internal fun success(conversations: List<Bufferoo>?): LoadBufferoosTask {
                return LoadBufferoosTask(TaskStatus.SUCCESS, conversations)
            }

            internal fun failure(): LoadBufferoosTask {
                return LoadBufferoosTask(TaskStatus.FAILURE, null)
            }

            internal fun inFlight(): LoadBufferoosTask {
                return LoadBufferoosTask(TaskStatus.IN_FLIGHT)
            }
        }
    }

    class LoadArticleTask(val status: TaskStatus,
                          val articles: List<Article>? = null) :
            BrowseResult() {

        companion object {

            internal fun success(conversations: List<Article>?): LoadArticleTask {
                return LoadArticleTask(TaskStatus.SUCCESS, conversations)
            }

            internal fun failure(): LoadArticleTask {
                return LoadArticleTask(TaskStatus.FAILURE, null)
            }

            internal fun inFlight(): LoadArticleTask {
                return LoadArticleTask(TaskStatus.IN_FLIGHT)
            }
        }
    }

}