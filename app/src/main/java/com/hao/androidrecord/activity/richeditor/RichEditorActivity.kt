package com.hao.androidrecord.activity.richeditor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import kotlinx.android.synthetic.main.activity_rich_editor.*

/**
 * https://github.com/change9326/RichEditor
 */
class RichEditorActivity:AppCompatActivity() {
    val REQUEST_USER_CODE_CLICK = 2222
    val REQUEST_STOCK_CODE_CLICK = 3333

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rich_editor)

        btn_at.setOnClickListener {
            startActivityForResult(
                Intent(this@RichEditorActivity, UserListActivity::class.java),
                REQUEST_USER_CODE_CLICK
            )
        }
        btn_topic.setOnClickListener {
            startActivityForResult(
                Intent(this@RichEditorActivity, StockListActivity::class.java),
                REQUEST_STOCK_CODE_CLICK
            )
        }
    }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         data?.let {
             if (resultCode == RESULT_OK) {
                 when (requestCode) {
                     REQUEST_USER_CODE_CLICK -> {
                         val userModel = data.getSerializableExtra(UserListActivity.DATA) as UserModel
                         richEditor.insertSpecialStr(InsertModel("@", userModel.user_name, "#f77500"))
                     }
                     REQUEST_STOCK_CODE_CLICK -> {
                         val stockModel = data.getSerializableExtra(StockListActivity.DATA) as UserModel
                         richEditor.insertSpecialStr(InsertModel("#", stockModel.user_name, "#f77500"))
                     }
                 }
             }
         }

    }

}