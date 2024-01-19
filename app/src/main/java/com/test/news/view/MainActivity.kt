package com.test.news.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.news.MyApplication
import com.test.news.adapter.NewsTopHeadLineAdapter
import com.test.news.databinding.ActivityMainBinding
import com.test.news.db.ArticlesDao
import com.test.news.viewmodel.NewsTopLinesModel

class MainActivity: AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val mNewsTopLinesModel: NewsTopLinesModel by viewModels()
    private lateinit var mNewsTopLineAdapter:NewsTopHeadLineAdapter
    private lateinit var mDb:ArticlesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initData() {
        mDb = MyApplication.getDao()

        mNewsTopLineAdapter = NewsTopHeadLineAdapter(mDb.getArticleModels())
        mBinding.recyclerview.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager

            val dividerDecoration = DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerDecoration)

            adapter = mNewsTopLineAdapter
        }

        mNewsTopLinesModel.setDb(MyApplication.getDao())
        mNewsTopLinesModel.getTopLinesNewsList()

    }


}