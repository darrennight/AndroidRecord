package com.hao.androidrecord

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.activity.AnimlogoActivity
import com.hao.androidrecord.activity.DashedLineDivider.DashedLineMainActivity
import com.hao.androidrecord.activity.DragPanelLayout.DragMainActivity
import com.hao.androidrecord.activity.KeyBoardPageScroll
import com.hao.androidrecord.activity.KeyBoardPopDialog
import com.hao.androidrecord.activity.NotchTools.NotchToolsMainActivity
import com.hao.androidrecord.activity.StickyHeaderViewPager.TestActivity
import com.hao.androidrecord.activity.WhellViewActivity
import com.hao.androidrecord.activity.alihome.AliHomeMainActivity
import com.hao.androidrecord.activity.blur.BlurActivity
import com.hao.androidrecord.activity.datetime.DateTimePicerActivity
import com.hao.androidrecord.activity.nestedscrolling.NestedMainActivity
import com.hao.androidrecord.activity.parallaxdecoration.ParallaxMainActivity
import com.hao.androidrecord.activity.scrollable.HeaderAndTablayoutRV
import com.hao.androidrecord.activity.scrollable.HeaderAndTablayoutRV02
import com.hao.androidrecord.activity.scrollable01.ScrollableMainActivity
import com.hao.androidrecord.activity.scrollable02.MainActivity
import com.hao.androidrecord.activity.scrollable03.MainActivityScrollable03
import com.hao.androidrecord.activity.scrollable04.Demo4Activity
import com.hao.androidrecord.activity.scrollable06.Scroll06MainActivity
import com.hao.androidrecord.activity.scrollable07.Scroll07MainActivity
import com.hao.androidrecord.activity.shadow.MainShadowActivity
import com.hao.androidrecord.activity.switchButton.SwitchButtonMainActivity
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
//                        startActivity(Intent(this@MainActivity,ChangeTableColorActivity::class.java))
                        testMeiShi()
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
                    7->{
                        startActivity(Intent(this@MainActivity, NestedMainActivity::class.java))
                    }
                    8->{
                        startActivity(Intent(this@MainActivity, HeaderAndTablayoutRV::class.java))
                    }
                    9->{
                        startActivity(Intent(this@MainActivity, HeaderAndTablayoutRV02::class.java))
                    }
                    10->{
                        startActivity(Intent(this@MainActivity, ScrollableMainActivity::class.java))
                    }
                    11->{
                        startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    }
                    12->{
                        startActivity(Intent(this@MainActivity, MainShadowActivity::class.java))
                    }
                    13->{
                        startActivity(Intent(this@MainActivity, TestActivity::class.java))
                    }
                    14->{
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.MultiScroll.activity.MainActivity::class.java))
                    }

                    15->{
                        startActivity(Intent(this@MainActivity, DateTimePicerActivity::class.java))
                    }
                    16->{
                        startActivity(Intent(this@MainActivity, MainActivityScrollable03::class.java))
                    }

                    17->{
                        startActivity(Intent(this@MainActivity, Demo4Activity::class.java))
                    }
                    18->{
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.scrollable05.MainActivity::class.java))
                    }
                    19->{
                        startActivity(Intent(this@MainActivity, AliHomeMainActivity::class.java))
                    }
                    20->{
                        startActivity(Intent(this@MainActivity, SwitchButtonMainActivity::class.java))
                    }

                    21->{
                        startActivity(Intent(this@MainActivity, Scroll06MainActivity::class.java))
                    }

                    22->{
                        startActivity(Intent(this@MainActivity, Scroll07MainActivity::class.java))
                    }

                    23->{
                        startActivity(Intent(this@MainActivity, DashedLineMainActivity::class.java))
                    }

                    24->{
                        startActivity(Intent(this@MainActivity, DragMainActivity::class.java))
                    }
                    25->{
                        startActivity(Intent(this@MainActivity, NotchToolsMainActivity::class.java))
                    }
                    26->{
                        startActivity(Intent(this@MainActivity, WhellViewActivity::class.java))
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
        list.add("7RecyclerView和头部layout交互集合")
        list.add("8RecyclerView和头部layout+顶部下拉刷新")
        list.add("9RecyclerView和头部layout")
        list.add("10RecyclerView和头部layout")
        list.add("11RecyclerView和头部layout")
        list.add("12阴影")
        list.add("13RecyclerView和头部layout")
        list.add("14RecyclerView和头部layout")
        list.add("15时间选择器")
        list.add("16RecyclerView和头部layout仿京东,淘宝商品展示")
        list.add("17RecyclerView和头部layout仿支付宝首页")
        list.add("18RecyclerView和头部layout即刻出品")
        list.add("19RecyclerView和头部layout支付宝首页")
        list.add("20滑动按钮")
        list.add("21RecyclerView和头部layout仿京东,淘宝商品展示")
        list.add("22吸顶效果实现的五种方式")
        list.add("23虚线")
        list.add("24RecyclerView和头部layout")
        list.add("25刘海适配全屏没有状态栏")
        list.add("26wheelview3D选择")
        adapter.notifyDataSetChanged()


//        testWebView()
        wv_webview.visibility = View.GONE
    }



    private fun testMeiShi(){
        val intent = Intent()
        intent.setClassName("com.meishi.app","com.taishimei.video.ui.other.SplashActivity")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("testMeiShi")
        startActivity(intent);
    }

    private fun testWebView(){

        webiewSetting()
        wv_webview.loadUrl("http://s.taishimei.com/dist1/index.html?index=1")
        wv_webview.visibility = View.GONE
    }

    private fun webiewSetting() {
        //声明WebSettings子类
        val webSettings: WebSettings = wv_webview.getSettings()
        webSettings.setJavaScriptEnabled(true);



        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件

        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8" //设置编码格式
    }
}