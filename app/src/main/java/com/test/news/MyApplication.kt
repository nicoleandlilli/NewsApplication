package com.test.news

import android.app.Application
import com.test.news.constants.Constants
import com.test.news.db.ArticleDbRealm
import com.test.news.db.ArticlesDao
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {

    private var db:RealmConfiguration?=null

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }


    private fun getDb():RealmConfiguration{
        return if(db!=null){
            db!!
        }else{
            db=RealmConfiguration
                .Builder()
                .name(Constants.REALM_NAME)
                .schemaVersion(Constants.REALM_VERSION)
                .build()
            db!!
        }
    }

    companion object{
        private var instance:MyApplication?=null

        fun applicationContext(): MyApplication? {
            return instance
        }

        fun getDao(): ArticlesDao {

            return ArticleDbRealm(instance!!.getDb())
        }

    }
}