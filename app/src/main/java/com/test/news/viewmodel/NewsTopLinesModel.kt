package com.test.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.news.constants.Constants
import com.test.news.db.ArticlesDao
import com.test.news.model.ArticleList
import com.test.news.realmmodel.ArticleModel
import com.test.news.retrofit.NewsTopHeadLinesRequest
import com.test.news.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsTopLinesModel(): ViewModel() {

    private lateinit var db:ArticlesDao

    fun setDb(db:ArticlesDao){
        this.db = db
    }

    fun getTopLinesNewsList(){
        var newsTopHeadLineReq: NewsTopHeadLinesRequest = RetrofitInstance.service.create(NewsTopHeadLinesRequest::class.java)
        newsTopHeadLineReq.getTopHeadLines(Constants.COUNTRY,Constants.NEWS_KEY).enqueue(object : Callback<ArticleList> {

            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                response?.body()?.let {

                    viewModelScope.launch(Dispatchers.IO) {
                        var articles = response?.body()!!.articles

                        var articleModels:ArrayList<ArticleModel> = ArrayList<ArticleModel>()

                        articles.forEach { article ->
                            var articleModel = ArticleModel()
                            articleModel.title = article.title
                            articleModel.urlToImage = article.urlToImage

                            articleModels.add(articleModel)

                        }

                        db.deleteAllArticleModels()
                        db.insertArticleModels(articleModels)
                    }

                }
            }

            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                Log.d("TEST", t.message.toString())
            }

        })
    }

}