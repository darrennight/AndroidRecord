package com.hao.androidrecord.activity.dialog

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.hao.androidrecord.R
import com.hao.androidrecord.activity.dialog.dialog.InputDialog
import com.hao.androidrecord.activity.dialog.dialog.MenuDialog
import com.hao.androidrecord.activity.dialog.dialog.MessageDialog
import com.hao.androidrecord.activity.dialog.dialog.TipsDialog
import com.hao.androidrecord.custom.pickerads.BaseDialog
import kotlinx.android.synthetic.main.dialog_activity.*
import java.util.ArrayList

class DialogActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_activity)
        btn_dialog_message.setOnClickListener {
            // 消息对话框
            MessageDialog.Builder(this)
                // 标题可以不用填写
                .setTitle("我是标题")
                // 内容必须要填写
                .setMessage("我是内容")
                // 确定按钮文本
                .setConfirm("确定")
                // 设置 null 表示不显示取消按钮
                .setCancel("取消")
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(object : MessageDialog.OnListener {

                    override fun onConfirm(dialog: BaseDialog?) {
                        //toast("确定了")
                    }

                    override fun onCancel(dialog: BaseDialog?) {
                        //toast("取消了")
                    }
                })
                .show()
        }

        btn_dialog_input.setOnClickListener {
            // 输入对话框
            InputDialog.Builder(this)
                // 标题可以不用填写
                .setTitle("我是标题")
                // 内容可以不用填写
                .setContent("我是内容")
                // 提示可以不用填写
                .setHint("我是提示")
                // 确定按钮文本
                .setConfirm(getString(R.string.common_confirm))
                // 设置 null 表示不显示取消按钮
                .setCancel(getString(R.string.common_cancel))
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(object : InputDialog.OnListener {

                    override fun onConfirm(dialog: BaseDialog?, content: String) {

                    }

                    override fun onCancel(dialog: BaseDialog?) {

                    }
                })
                .show()
        }

        btn_dialog_bottom_menu.setOnClickListener {
            val data = ArrayList<String>()
            for (i in 0..9) {
                data.add("我是数据" + (i + 1))
            }

            // 底部选择框
            MenuDialog.Builder(this)
                // 设置 null 表示不显示取消按钮
                //.setCancel(getString(R.string.common_cancel))
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setList(data)
                .setListener(object : MenuDialog.OnListener<String> {

                    override fun onSelected(dialog: BaseDialog?, position: Int, data: String) {

                    }

                    override fun onCancel(dialog: BaseDialog?) {

                    }
                })
                .show()
        }

        btn_dialog_center_menu.setOnClickListener {
            val data = ArrayList<String>()
            for (i in 0..9) {
                data.add("我是数据" + (i + 1))
            }
            // 居中选择框
            MenuDialog.Builder(this)
                .setGravity(Gravity.CENTER)
                // 设置 null 表示不显示取消按钮
                //.setCancel(null)
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setList(data)
                .setListener(object : MenuDialog.OnListener<String> {

                    override fun onSelected(dialog: BaseDialog?, position: Int, data: String) {

                    }

                    override fun onCancel(dialog: BaseDialog?) {

                    }
                })
                .show()
        }

        btn_dialog_succeed_toast.setOnClickListener {

            // 成功对话框
            TipsDialog.Builder(this)
                .setIcon(TipsDialog.ICON_FINISH)
                .setMessage("完成")
                .show()
        }
    }
}