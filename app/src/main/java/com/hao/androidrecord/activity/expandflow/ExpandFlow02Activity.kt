package com.hao.androidrecord.activity.expandflow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.expandflow.BlobItem.OnItemCheckListener
import kotlinx.android.synthetic.main.activity_expand_flow02.*

//https://github.com/orcchg/AnimatedFlowLayout
//改版为 moreview在列表item跟随
class ExpandFlow02Activity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_expand_flow02);

        val texts = resources.getStringArray(R.array.texts)
        for (i in 0..29){
           val  itemView = BlobItem(this,i)
            itemView.setText(texts[i]);
            if (i == 0){
                itemView.setSelectItem()
            }
            itemView.setOnItemCheckListener(object :OnItemCheckListener{
                override fun onChecked(txt: String) {
                    Log.e("======txtonChecked",txt)
                }

                override fun onUnchecked(txt: String) {
                    Log.e("======txtonUnchecked",txt)
                }
            })
            interests_container.addView(itemView)
        }

        interests_container.enableLayoutTransition(true);
    }
}