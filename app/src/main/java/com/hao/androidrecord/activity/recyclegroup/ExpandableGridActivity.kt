package com.hao.androidrecord.activity.recyclegroup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.recyclegroup.adapter.GroupedGridNoFooterListAdapter
import com.hao.androidrecord.activity.recyclegroup.model.GroupModel

class ExpandableGridActivity:AppCompatActivity() {

    private var rvList: RecyclerView? = null
    private var itemDecoration: RecyclerView.ItemDecoration? = null
    private var adapter: GroupedGridNoFooterListAdapter? = null

    companion object{
        fun openActivity(context: Context) {
            val intent = Intent(context, ExpandableGridActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_list)
        rvList = findViewById<View>(R.id.rv_list) as RecyclerView

        adapter = GroupedGridNoFooterListAdapter(this, GroupModel.getExpandableGroups(10, 10))
        adapter!!.setOnHeaderClickListener { adapter, holder, groupPosition ->

//                Toast.makeText(ExpandableActivity.this, "组头：groupPosition = " + groupPosition,
//                        Toast.LENGTH_LONG).show();
            val expandableAdapter = adapter as GroupedGridNoFooterListAdapter
            if (expandableAdapter.isExpand(groupPosition)) {
                expandableAdapter.collapseGroup(groupPosition)
            } else {
                expandableAdapter.expandGroup(groupPosition)
            }

            Toast.makeText(
                this@ExpandableGridActivity, "组头：groupPosition = $groupPosition",
                Toast.LENGTH_LONG
            ).show()
        }
        adapter!!.setOnFooterClickListener { adapter, holder, groupPosition ->
            Toast.makeText(
                this@ExpandableGridActivity, "组尾：groupPosition = $groupPosition",
                Toast.LENGTH_LONG
            ).show()
        }
        adapter!!.setOnChildClickListener { adapter, holder, groupPosition, childPosition ->
            Toast.makeText(
                this@ExpandableGridActivity, "子项：groupPosition = " + groupPosition
                        + ", childPosition = " + childPosition,
                Toast.LENGTH_LONG
            ).show()
        }
        rvList!!.adapter = adapter


        //直接使用GroupedGridLayoutManager实现子项的Grid效果
        val gridLayoutManager = GroupedGridLayoutManager(this, 2, adapter)
        rvList!!.layoutManager = gridLayoutManager
    }
}