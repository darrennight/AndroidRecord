package com.hao.androidrecord

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hao.androidrecord.activity.*
import com.hao.androidrecord.activity.DashedLineDivider.DashedLineMainActivity
import com.hao.androidrecord.activity.DragPanelLayout.DragMainActivity
import com.hao.androidrecord.activity.NotchTools.NotchToolsMainActivity
import com.hao.androidrecord.activity.StickyHeaderViewPager.TestActivity
import com.hao.androidrecord.activity.alihome.AliHomeMainActivity
import com.hao.androidrecord.activity.blur.BlurActivity
import com.hao.androidrecord.activity.bookpage.BookPageActivity
import com.hao.androidrecord.activity.bookpage.BookPageActivity00
import com.hao.androidrecord.activity.cusTab.TabMainActivity
import com.hao.androidrecord.activity.datetime.DateTimePicerActivity
import com.hao.androidrecord.activity.flowRecycle.LanuchActivity
import com.hao.androidrecord.activity.hilt.TestHiltActivity
import com.hao.androidrecord.activity.indexBar.CountryChooseActivity
import com.hao.androidrecord.activity.nestedscrolling.NestedMainActivity
import com.hao.androidrecord.activity.nine.CustomGirdActivity
import com.hao.androidrecord.activity.nine.NineGridActivity
import com.hao.androidrecord.activity.nine.NineGridNewActivity
import com.hao.androidrecord.activity.paing3.TestPaging3Activity
import com.hao.androidrecord.activity.parallaxdecoration.ParallaxMainActivity
import com.hao.androidrecord.activity.scrollable.HeaderAndTablayoutRV
import com.hao.androidrecord.activity.scrollable.HeaderAndTablayoutRV02
import com.hao.androidrecord.activity.scrollable01.ScrollableMainActivity
import com.hao.androidrecord.activity.scrollable02.MainActivity
import com.hao.androidrecord.activity.scrollable03.MainActivityScrollable03
import com.hao.androidrecord.activity.scrollable04.Demo4Activity
import com.hao.androidrecord.activity.scrollable06.Scroll06MainActivity
import com.hao.androidrecord.activity.scrollable07.Scroll07MainActivity
import com.hao.androidrecord.activity.scrool.ScrollChangeTitleActivity
import com.hao.androidrecord.activity.shadow.MainShadowActivity
import com.hao.androidrecord.activity.switchButton.SwitchButtonMainActivity
import com.hao.androidrecord.activity.table.ChangeTableColorActivity
import com.hao.androidrecord.activity.tiktokComments.CommentMultiActivity
import com.hao.androidrecord.adapter.DemoAdapter
import com.hao.androidrecord.custom.selector.Matisse
import com.hao.androidrecord.custom.selector.MimeType
import com.hao.androidrecord.custom.selector.engine.impl.GlideEngine
import com.hao.androidrecord.custom.selector.ui.MatisseCustomActivity
import com.hao.androidrecord.indexable.IndexableCityActivity
import com.hao.androidrecord.util.AliBase64
import com.hao.androidrecord.util.LocalBase64
import com.hao.androidrecord.util.MBase64
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


@RuntimePermissions
class MainActivity : AppCompatActivity() {
    private val list:MutableList<String> by lazy {
        ArrayList<String>()
    }
    private lateinit var adapter:DemoAdapter
    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun openAlbum(){
        //允许打开相册
        Matisse.from(this@MainActivity)
            .choose(
                MimeType.ofVideo()
            )
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
        adapter = DemoAdapter(this, list)
        adapter.clickListener = object :DemoAdapter.DemoItemClickListener{
            override fun onDemoItemClick(position: Int) {
                when(position){
                    0 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                ChangeTableColorActivity::class.java
                            )
                        )
//                        testMeiShi()
                    }
                    1 -> {
                        startActivity(Intent(this@MainActivity, BlurActivity::class.java))
                    }
                    2 -> {
                        startActivity(Intent(this@MainActivity, KeyBoardPageScroll::class.java))
                    }
                    3 -> {
                        startActivity(Intent(this@MainActivity, KeyBoardPopDialog::class.java))
                    }
                    4 -> {
                        openAlbumWithPermissionCheck()
                    }

                    5 -> {
                        startActivity(Intent(this@MainActivity, ParallaxMainActivity::class.java))
                    }
                    6 -> {
                        startActivity(Intent(this@MainActivity, AnimlogoActivity::class.java))
                    }
                    7 -> {
                        startActivity(Intent(this@MainActivity, NestedMainActivity::class.java))
                    }
                    8 -> {
                        startActivity(Intent(this@MainActivity, HeaderAndTablayoutRV::class.java))
                    }
                    9 -> {
                        startActivity(Intent(this@MainActivity, HeaderAndTablayoutRV02::class.java))
                    }
                    10 -> {
                        startActivity(Intent(this@MainActivity, ScrollableMainActivity::class.java))
                    }
                    11 -> {
                        startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    }
                    12 -> {
                        startActivity(Intent(this@MainActivity, MainShadowActivity::class.java))
                    }
                    13 -> {
                        startActivity(Intent(this@MainActivity, TestActivity::class.java))
                    }
                    14 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                com.hao.androidrecord.activity.MultiScroll.activity.MainActivity::class.java
                            )
                        )
                    }

                    15 -> {
                        startActivity(Intent(this@MainActivity, DateTimePicerActivity::class.java))
                    }
                    16 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                MainActivityScrollable03::class.java
                            )
                        )
                    }

                    17 -> {
                        startActivity(Intent(this@MainActivity, Demo4Activity::class.java))
                    }
                    18 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                com.hao.androidrecord.activity.scrollable05.MainActivity::class.java
                            )
                        )
                    }
                    19 -> {
                        startActivity(Intent(this@MainActivity, AliHomeMainActivity::class.java))
                    }
                    20 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                SwitchButtonMainActivity::class.java
                            )
                        )
                    }

                    21 -> {
                        startActivity(Intent(this@MainActivity, Scroll06MainActivity::class.java))
                    }

                    22 -> {
                        startActivity(Intent(this@MainActivity, Scroll07MainActivity::class.java))
                    }

                    23 -> {
                        startActivity(Intent(this@MainActivity, DashedLineMainActivity::class.java))
                    }

                    24 -> {
                        startActivity(Intent(this@MainActivity, DragMainActivity::class.java))
                    }
                    25 -> {
                        startActivity(Intent(this@MainActivity, NotchToolsMainActivity::class.java))
                    }
                    26 -> {
                        startActivity(Intent(this@MainActivity, WhellViewActivity::class.java))
                    }
                    27 -> {
                        startActivity(Intent(this@MainActivity, TestCoroutineActivity::class.java))
                    }
                    28 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity01::class.java
                            )
                        )
                    }
                    29 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity02::class.java
                            )
                        )
                    }
                    30 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity03::class.java
                            )
                        )
                    }
                    31 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity04::class.java
                            )
                        )
                    }
                    32 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity05::class.java
                            )
                        )
                    }
                    33 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity06::class.java
                            )
                        )
                    }
                    34 -> {
                        startActivity(Intent(this@MainActivity, TestHiltActivity::class.java))
                    }
                    35 -> {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TestCoroutineActivity07::class.java
                            )
                        )
                    }
                    36 -> {
                        startActivity(Intent(this@MainActivity, BookPageActivity::class.java))
                    }

                    37 -> {
                        startActivity(Intent(this@MainActivity, BookPageActivity00::class.java))
                    }
                    38 -> {
                        startActivity(Intent(this@MainActivity, TestPaging3Activity::class.java))
                    }
                    39 -> {
                        startActivity(Intent(this@MainActivity, CommentMultiActivity::class.java))
                    }
                    40 -> {
                        startActivity(Intent(this@MainActivity, NineGridActivity::class.java))
                    }
                    41 -> {
                        startActivity(Intent(this@MainActivity, CustomGirdActivity::class.java))
                    }
                    42 -> {
                        startActivity(Intent(this@MainActivity, NineGridNewActivity::class.java))
                    }
                    43 -> {
                        startActivity(Intent(this@MainActivity, LanuchActivity::class.java))
                    }
                    44 -> {
                        startActivity(Intent(this@MainActivity, CountryChooseActivity::class.java))
                    }
                    45 -> {
                        startActivity(Intent(this@MainActivity, IndexableCityActivity::class.java))
                    }
                    46 -> {
                        startActivity(Intent(this@MainActivity, ClickSpanActivity::class.java))
                    }
                    47 -> {
                        startActivity(Intent(this@MainActivity, TabMainActivity::class.java))
                    }

                    48 -> {
                        startActivity(Intent(this@MainActivity, ScrollChangeTitleActivity::class.java))
                    }
                }
            }
        }
        rv_demo_list.adapter = adapter
        initData()

        val en = encryptByPublicKey("${System.currentTimeMillis()}")
//        val en = encryptByPublicKey("1618824407630")
        val des = decryptByPrivateKey(en)

//        val des = decryptByPrivateKey("Lr0JNhdKJVAGycPCYHYsiv0Pmm1BoymMI9XHImmhlAy9/yanaeJPZhGNjKa8rpSHNZ9mO/2VASu/9k2oh3Gmzg==")
        Log.e("=======des", des)

//        val test = encryptByPublicKeyTest("1618824407630")
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
        list.add("27kotlin协程测试使用")
        list.add("28kotlin协程测试使用")
        list.add("29kotlin协程测试使用")
        list.add("30kotlin协程测试使用")
        list.add("31kotlin协程测试使用")
        list.add("32kotlin协程测试使用withContext")
        list.add("33协程并发执行")
        list.add("34测试Hilt")
        list.add("35kotlin协程测试使用lifecycleScope")
        list.add("36bookpage")
        list.add("37bookpage00")
        list.add("38测试Paging3")
        list.add("39评论多类型")
        list.add("40动态九宫格")
        list.add("41动态九宫格")
        list.add("42动态九宫格--网络图片")
        list.add("43recycleview实现的flow")
        list.add("44国家城市index索引")
        list.add("45城市index索引")
        list.add("46textview click span")
        list.add("47tab")
        list.add("48滚动标题变化")
        adapter.notifyDataSetChanged()


        testWebView()
    }



    private fun testMeiShi(){
        val intent = Intent()
        intent.setClassName("com.meishi.app", "com.taishimei.video.ui.other.SplashActivity")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("testMeiShi")
        startActivity(intent);
    }

    private fun testWebView(){

//        webiewSetting()
//        wv_webview.loadUrl("http://s.taishimei.com/dist1/index.html?index=1")
//        wv_webview.loadUrl("http://s.taishimei.com/ms_ks/?videoId=11288&source=ks&ms=1")
//        wv_webview.loadUrl("http://192.168.1.211:8080/deeplink.html")
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


        wv_webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.d("=========smsg", "url=$url")
//                val uri: Uri = Uri.parse(url)
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)

                /*val uri: Uri = Uri.parse(url)
                val host = resources.getString(R.string.host_)
                val scheme = resources.getString(R.string.scheme_)
                if (uri.getScheme().equals(scheme)
                    && uri.getHost().equals(host)
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    return false
                } else {
                    view.loadUrl(url)
                }*/
                return true
            }
        })

    }



    private val TASK_PUBLICK_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL3Wg/zb8F+jcM6GwM1d9LkUhq4ly4dw/q0sQXgwGF80mND47ADxCvmoDNrpI1k7zx8N1VXO0GpvxWOjBXfVhb8CAwEAAQ=="

    private fun encryptByPublicKey(timestamp: String): String {


        Log.e("========time", "$timestamp")
        val NO_WRAP = MBase64.decode(TASK_PUBLICK_KEY)



        val x509EncodedKeySpec = X509EncodedKeySpec(NO_WRAP)
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
        val publicKey: PublicKey = keyFactory.generatePublic(x509EncodedKeySpec)

//        val cipher: Cipher = Cipher.getInstance("RSA")
        val cipher: Cipher = Cipher.getInstance("RSA/None/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val result: ByteArray = cipher.doFinal(timestamp.toByteArray())
        val ss = MBase64.encode(result)
        Log.e("=======ss", ss)

        return ss
    }

    val PrivateKey =
        "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAvdaD/NvwX6NwzobAzV30uRSGriXLh3D+rSxBeDAYXzSY0PjsAPEK+agM2ukjWTvPHw3VVc7Qam/FY6MFd9WFvwIDAQABAkBxCfst5gkL6daSI8tKflfqnT5VFExNKgt8Mo4JcxoQFcr9JtcEB9Y0HEgkd9F8aZckj2Ofuk/U/A1rmRHKPhEBAiEA45Vtk3ItAW5aE1bB+iB4O5iENZPH82COQhv7SD4f8ekCIQDVipFoBUY6EBBjGiR/eNKP+BAGhUSVWtU3cqWNV2oJZwIgV6GLHtJA+CMYtgebC4gDI9d3WPX9cP5F6LjDii65uDkCIBvaKwUKSxKfrcN/UWte8vfcPxranwtsAYtt5LH+yNHZAiEAwo8MhymEKP7ACawdIZOaEfYQ8Vaf6k71JVOwV0utT1s="
    fun decryptByPrivateKey(text: String): String {
        val pkcs8EncodedKeySpec5 = PKCS8EncodedKeySpec(Base64.decode(PrivateKey, Base64.NO_WRAP))
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey: PrivateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5)
//        val cipher = Cipher.getInstance("RSA")
        val cipher: Cipher = Cipher.getInstance("RSA/None/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val result = cipher.doFinal(Base64.decode(text, Base64.NO_WRAP))
        return String(result)
    }


    private fun encryptByPublicKeyTest(timestamp: String): String {


        Log.e("========timetest", "$timestamp")



        val de = AliBase64.decode(TASK_PUBLICK_KEY)

        val x509EncodedKeySpec = X509EncodedKeySpec(de)
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
        val publicKey: PublicKey = keyFactory.generatePublic(x509EncodedKeySpec)
        val cipher: Cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val result: ByteArray = cipher.doFinal(timestamp.toByteArray())
        val ss = AliBase64.encode(result)
        Log.e("=======sstest", ss)

        return ss
    }


}