package com.hao.androidrecord.activity.expandable.expand01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.activity.expandable.expand01.changeadapter.ChangeAdapterActivity
import com.hao.androidrecord.activity.expandable.expand01.childsticky.ChildStickyHeaderActivity
import com.hao.androidrecord.activity.expandable.expand01.college.CollegeActivity
import com.hao.androidrecord.activity.expandable.expand01.slection.SelectionActivity
import com.hao.androidrecord.activity.expandable.expand01.textbook.TextBookListActivity
import com.hao.androidrecord.activity.expandable.expand01.yuanfudao.YuanfudaoActivity
import com.hao.androidrecord.databinding.ActivityExpandRvMenuBinding

//https://github.com/Xigong93/ExpandableRecyclerView
class ExpandMenuActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ExpandMenuActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityExpandRvMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.collegeLongListButton.setOnClickListener {
            CollegeActivity.start(it.context, false)
        }
        binding.collegeShortListButton.setOnClickListener {
            CollegeActivity.start(it.context, true)
        }
        binding.textBookListButton.setOnClickListener {
            TextBookListActivity.start(it.context)
        }
        binding.yuanfudaoButton.setOnClickListener {
            YuanfudaoActivity.start(it.context)
        }
        binding.changeAdapter.setOnClickListener {
            ChangeAdapterActivity.start(it.context)
        }
        binding.selection.setOnClickListener {
            SelectionActivity.start(it.context)
        }
        binding.childSticky.setOnClickListener {
            ChildStickyHeaderActivity.start(it.context)
        }
    }
}
