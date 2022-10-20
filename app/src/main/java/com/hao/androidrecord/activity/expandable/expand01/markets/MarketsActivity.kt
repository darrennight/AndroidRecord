package com.hao.androidrecord.activity.expandable.expand01.markets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.activity.expandable.expand01.ExpandMenuActivity
import com.hao.androidrecord.databinding.MarketsActivityBinding
import pokercc.android.expandablerecyclerview.ExpandableItemAnimator

class MarketsActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MarketsActivity::class.java))
        }
    }

    private val binding by lazy { MarketsActivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.more.setOnClickListener {
            ExpandMenuActivity.start(it.context)
        }
        with(binding.recyclerView) {
            adapter = MarketsAdapter()
            itemAnimator = ExpandableItemAnimator(this, animChildrenItem = true)
            addItemDecoration(MarketsItemDecoration())
            layoutManager = LinearLayoutManager(context)
        }

    }
}
