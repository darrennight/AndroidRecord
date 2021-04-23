package com.hao.androidrecord.activity.paing3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.paing3.adapter.RecommendAdapter
import com.hao.androidrecord.activity.paing3.paging.FeedRecommendViewModel
import kotlinx.android.synthetic.main.activity_test_paging.*


class TestPaging3Activity:AppCompatActivity() {

    private val viewModel: FeedRecommendViewModel by viewModels()
    private lateinit var adapter: RecommendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_paging)
        adapter = RecommendAdapter()

        tv_feed_list.layoutManager = LinearLayoutManager(this)
        tv_feed_list.adapter = adapter

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error -> {

                    Toast.makeText(this, "Refresh Error", Toast.LENGTH_SHORT).show()
                }
                is LoadState.Loading -> {
                    Toast.makeText(this, "Refresh Loading", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    
                }
            }
        }
        viewModel.refreshFeed().observe(this,{
            adapter.submitData(this.lifecycle,it)
            Log.e("========refresh","refreshrefreshrefreshrefresh")
        })


        btn_refresh.setOnClickListener {
            adapter.refresh()
        }
    }
}