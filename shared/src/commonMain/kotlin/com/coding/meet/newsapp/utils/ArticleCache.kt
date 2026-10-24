package com.coding.meet.newsapp.utils

import com.coding.meet.newsapp.domain.model.Article

/**
 * In-memory cache for the article currently being viewed.
 * Populated when the user navigates to the detail screen from any list.
 * The detail screen checks this first, then falls back to the local DB
 * (which covers bookmarked articles that are reopened).
 */
class ArticleCache {
    private var cached: Article? = null

    fun put(article: Article) {
        cached = article
    }

    fun get(publishedAt: String): Article? =
        cached?.takeIf { it.publishedAt == publishedAt }
}
