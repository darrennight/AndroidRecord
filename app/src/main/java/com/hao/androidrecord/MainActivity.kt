package com.hao.androidrecord

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.activity.AnimlogoActivity
import com.hao.androidrecord.activity.KeyBoardPageScroll
import com.hao.androidrecord.activity.KeyBoardPopDialog
import com.hao.androidrecord.activity.blur.BlurActivity
import com.hao.androidrecord.activity.parallaxdecoration.ParallaxMainActivity
import com.hao.androidrecord.activity.table.ChangeTableColorActivity
import com.hao.androidrecord.adapter.DemoAdapter
import com.hao.androidrecord.custom.selector.Matisse
import com.hao.androidrecord.custom.selector.MimeType
import com.hao.androidrecord.custom.selector.engine.impl.GlideEngine
import com.hao.androidrecord.custom.selector.ui.MatisseCustomActivity
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity() {
    private val list:MutableList<String> by lazy {
        ArrayList<String>()
    }
    private lateinit var adapter:DemoAdapter
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun openAlbum(){
        //允许打开相册
        Matisse.from(this@MainActivity)
            .choose(
                MimeType.ofVideo())
            .theme(R.style.Matisse_Dracula)
            .showSingleMediaType(true)
            .countable(false)
            .maxSelectable(1)
            .originalEnable(true)
            .maxOriginalSize(1)
            .imageEngine(GlideEngine())
            .launch(MatisseCustomActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        rv_demo_list.layoutManager = manager
        adapter = DemoAdapter(this,list)
        adapter.clickListener = object :DemoAdapter.DemoItemClickListener{
            override fun onDemoItemClick(position: Int) {
                when(position){
                    0->{
                        startActivity(Intent(this@MainActivity,ChangeTableColorActivity::class.java))
                    }
                    1->{
                        startActivity(Intent(this@MainActivity,BlurActivity::class.java))
                    }
                    2->{
                        startActivity(Intent(this@MainActivity,KeyBoardPageScroll::class.java))
                    }
                    3->{
                        startActivity(Intent(this@MainActivity, KeyBoardPopDialog::class.java))
                    }
                    4->{
                        openAlbumWithPermissionCheck()
                    }

                    5->{
                        startActivity(Intent(this@MainActivity, ParallaxMainActivity::class.java))
                    }
                    6->{
                        startActivity(Intent(this@MainActivity, AnimlogoActivity::class.java))
                    }
                }
            }
        }
        rv_demo_list.adapter = adapter
        initData()
    }

    private fun initData(){
        list.add("0viewpager滑动切换Tab颜色渐变")
        list.add("1自定义形状高斯模糊")
        list.add("2键盘弹出页面滚动")
        list.add("3键盘弹出--弹出对话框输入--类似添加评论")
        list.add("4相册列出视频")
        list.add("5RecycleView背景视差decoration")
        list.add("6启动页logo文字动画")
        adapter.notifyDataSetChanged()
    }
}