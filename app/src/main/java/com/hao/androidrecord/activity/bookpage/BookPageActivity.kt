package com.hao.androidrecord.activity.bookpage

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.custom.bookpage.BookPageView
import kotlinx.android.synthetic.main.activity_book_page.*

class BookPageActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)
        view_book_page.setOnTouchListener { v, event ->

            Log.e("=======move","ACTION_MOVEACTION_MOVE${event.getAction()}")
            when(event.getAction()){
                MotionEvent.ACTION_DOWN->{
                    if(event.getY() < view_book_page.getViewHeight()/2){
                        view_book_page.setTouchPoint(event.getX(),event.getY(),BookPageView.STYLE_TOP_RIGHT);
                    }else if(event.getY() >= view_book_page.getViewHeight()/2) {
                        view_book_page.setTouchPoint(event.getX(),event.getY(),BookPageView.STYLE_LOWER_RIGHT);
                    }
                }
                MotionEvent.ACTION_MOVE->{

                    view_book_page.setTouchPoint(event.getX(),event.getY(),"")
                }
                MotionEvent.ACTION_UP->{
                    view_book_page.setDefaultPath()
                }
            }

            true }
    }
}