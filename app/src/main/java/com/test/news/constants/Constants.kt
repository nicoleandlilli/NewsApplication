package com.test.news.constants

interface Constants {

    companion object{
        //Retrofit
        const val BASE_URL = "https://newsapi.org/"
        const val NEWS_KEY = "9f5c8408c37248b484e9146fb6e47f5c"
        const val COUNTRY = "us"

        //Realm
        const val REALM_NAME = "News Project"
        const val REALM_VERSION = 1L
    }
}