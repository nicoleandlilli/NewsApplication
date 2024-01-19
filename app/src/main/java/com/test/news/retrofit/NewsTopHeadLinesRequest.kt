package com.test.news.retrofit

import com.test.news.model.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsTopHeadLinesRequest {

    @GET("v2/top-headlines")
    fun getTopHeadLines(@Query("country") country:String,@Query("apiKey") apiKey:String): Call<ArticleList>

}