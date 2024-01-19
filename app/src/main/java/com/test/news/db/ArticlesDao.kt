package com.test.news.db

import com.test.news.realmmodel.ArticleModel
import io.realm.RealmResults

interface ArticlesDao {

    fun getArticleModels():RealmResults<ArticleModel>

    fun insertArticleModel(articleModel: ArticleModel)

    fun insertArticleModels(articleModels: List<ArticleModel>)

    fun deleteAllArticleModels()
}