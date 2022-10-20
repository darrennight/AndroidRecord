package com.hao.androidrecord.activity.expandable.expand01.childsticky

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.databinding.ChildStickyHeaderActivityBinding
import pokercc.android.expandablerecyclerview.ExpandableItemAnimator

class ChildStickyHeaderActivity : AppCompatActivity() {

    private val binding by lazy { ChildStickyHeaderActivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChildStickyHeaderAdapter()
            itemAnimator = ExpandableItemAnimator(this, animChildrenItem = true)
        }
    }

    companion object {

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ChildStickyHeaderActivity::class.java)
            context.startActivity(starter)
        }
    }
}
