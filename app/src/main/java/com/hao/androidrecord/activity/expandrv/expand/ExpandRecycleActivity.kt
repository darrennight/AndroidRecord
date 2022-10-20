package com.hao.androidrecord.activity.expandrv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.expandrv.GenreDataFactory.makeGenres
import kotlinx.android.synthetic.main.activity_expand_re.*

//https://github.com/thoughtbot/expandable-recycler-view
class ExpandRecycleActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_re)
        val layoutManager = LinearLayoutManager(this)

        recycler_view.layoutManager = layoutManager
        val animator: ItemAnimator? = recycler_view.getItemAnimator()
        if (animator is DefaultItemAnimator) {
            (animator).supportsChangeAnimations = false
        }

       val  adapter = GenreAdapter(makeGenres());
        recycler_view.adapter = adapter
    }
}