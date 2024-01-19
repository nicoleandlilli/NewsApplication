package com.test.news.realmmodel

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class ArticleModel(): RealmObject() {
    @PrimaryKey
    var id: ObjectId =ObjectId()
    var title: String? = ""
    var urlToImage: String? = ""
}