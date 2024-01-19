package com.test.news.model

data class ArticleList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)