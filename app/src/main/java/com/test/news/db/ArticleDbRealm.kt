package com.test.news.db

import com.test.news.realmmodel.ArticleModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where

class ArticleDbRealm(private val realmConfiguration: RealmConfiguration) : ArticlesDao{

    override fun getArticleModels(): RealmResults<ArticleModel> {
        val realm = Realm.getInstance(realmConfiguration)
        return realm.where<ArticleModel>().findAll()
    }

    override fun insertArticleModel(articleModel: ArticleModel) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { transaction ->
            transaction.insert(articleModel)
        }
    }

    override fun insertArticleModels(articleModels: List<ArticleModel>) {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { transaction ->
            transaction.insert(articleModels)
        }
    }

    override fun deleteAllArticleModels() {
        val realm = Realm.getInstance(realmConfiguration)
        realm.executeTransaction { transaction ->
            getArticleModels().deleteAllFromRealm()
        }
    }
}