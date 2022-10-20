package com.hao.androidrecord.activity.expandable.expand01.textbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hao.androidrecord.databinding.ActivityTextBookListBinding

/**
 * 教材列表页面
 * @author pokercc
 * @date 2020-6-30 16:25:44
 */
class TextBookListActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, TextBookListActivity::class.java))
        }
    }

    private lateinit var binding: ActivityTextBookListBinding
    private lateinit var viewModel: TextBookListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelProvider = ViewModelProvider(
            this,
            SavedStateViewModelFactory(application, this)
        )
        val spanCount = 3
        val gridLayoutManager = GridLayoutManager(this, spanCount)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.addItemDecoration(TextBookDecorator(spanCount))
        viewModel = viewModelProvider.get(TextBookListViewModel::class.java)
        viewModel.textBookLists.observe(this, Observer {
            val textBookAdapter = TextBookAdapter(it)
            binding.recyclerView.adapter = textBookAdapter
            gridLayoutManager.spanSizeLookup = TextBookSpanLookup(spanCount, textBookAdapter)
        })
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.textBookLists.value?.isNotEmpty() != true) {
            viewModel.loadData()
        }
    }

}

