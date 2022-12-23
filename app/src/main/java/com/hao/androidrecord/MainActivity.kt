package com.hao.androidrecord

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hao.androidrecord.activity.*
import com.hao.androidrecord.activity.AdvancedTextView.SelectMainActivity
import com.hao.androidrecord.activity.ArcSelectList.StartActivity
import com.hao.androidrecord.activity.CardScrool.MainImpActivity
import com.hao.androidrecord.activity.DashedLineDivider.DashedLineMainActivity
import com.hao.androidrecord.activity.DragPanelLayout.DragMainActivity
import com.hao.androidrecord.activity.KeyboardLayout.KeyboardActivity
import com.hao.androidrecord.activity.LuckyWheel.LuckWheelActivity
import com.hao.androidrecord.activity.NotchTools.NotchToolsMainActivity
import com.hao.androidrecord.activity.SelectableTextHelper.SelectHelpMainActivity
import com.hao.androidrecord.activity.SelectableTextView.SelectTextActivity
import com.hao.androidrecord.activity.StickyHeaderViewPager.TestActivity
import com.hao.androidrecord.activity.TableView.TableViewActivity
import com.hao.androidrecord.activity.ZoomRecylerLayout.ZoomRecycleActivity
import com.hao.androidrecord.activity.alihome.AliHomeMainActivity
import com.hao.androidrecord.activity.animatorx.AnimatorXActivity
import com.hao.androidrecord.activity.arclayout.ArcLayoutMainActivity
import com.hao.androidrecord.activity.autoscroll.AutoScrollRecycleActivity
import com.hao.androidrecord.activity.autoscrollview.AutoScrollActivity
import com.hao.androidrecord.activity.blur.BlurActivity
import com.hao.androidrecord.activity.blur.BlurTitleActivity
import com.hao.androidrecord.activity.bookpage.BookPageActivity
import com.hao.androidrecord.activity.bookpage.BookPageActivity00
import com.hao.androidrecord.activity.bottom.MainBottomActivity
import com.hao.androidrecord.activity.bottom.MainBottomActivity01
import com.hao.androidrecord.activity.bottom.MainBottomActivity02
import com.hao.androidrecord.activity.bottomsheet.BottomBarActivity
import com.hao.androidrecord.activity.bottomsheet.BottomSheetActivity
import com.hao.androidrecord.activity.bottomsheet.CoordinatorActivity
import com.hao.androidrecord.activity.bottomtab.RVBottomActivity
import com.hao.androidrecord.activity.candle.CandleActivity
import com.hao.androidrecord.activity.chatview.SimpleChatViewActivity
import com.hao.androidrecord.activity.chooser.ChooseMainActivity
import com.hao.androidrecord.activity.coffeeView.CoffeeView
import com.hao.androidrecord.activity.coffeeView.CoffeeViewActivity
import com.hao.androidrecord.activity.consecutivescroller.MainScrollerActivity
import com.hao.androidrecord.activity.coroutine.*
import com.hao.androidrecord.activity.cusTab.TabMainActivity
import com.hao.androidrecord.activity.datetime.DateTimePicerActivity
import com.hao.androidrecord.activity.dfilp.MainDialogActivity
import com.hao.androidrecord.activity.dialog.DialogActivity
import com.hao.androidrecord.activity.drag.DragCardActivity
import com.hao.androidrecord.activity.dyindex.DYIndexMainActivity
import com.hao.androidrecord.activity.elasticviews.ElasticViewMainActivity
import com.hao.androidrecord.activity.expandable.ExpandableRecycleActivity
import com.hao.androidrecord.activity.expandable.two.TwoExpandableActivity
import com.hao.androidrecord.activity.expandablecardview.ExpandCardMainActivity
import com.hao.androidrecord.activity.expandflow.ExpandFlowActivity
import com.hao.androidrecord.activity.expandrv.ExpandMenuActivity
import com.hao.androidrecord.activity.fabpop.FabPopMainActivity
import com.hao.androidrecord.activity.flexbox.FlexBoxRecycleActivity
import com.hao.androidrecord.activity.flip.FlipMainActivity
import com.hao.androidrecord.activity.flowRecycle.LanuchActivity
import com.hao.androidrecord.activity.halfScreen.HalfMainActivity
import com.hao.androidrecord.activity.halfScreen.MainHalf2Activity
import com.hao.androidrecord.activity.hilt.TestHiltActivity
import com.hao.androidrecord.activity.indexBar.CountryChooseActivity
import com.hao.androidrecord.activity.indexbar2.LauncherActivity
import com.hao.androidrecord.activity.interval.IntervalActivity
import com.hao.androidrecord.activity.keyboard.MainKeyboardActivity
import com.hao.androidrecord.activity.keyboardbottom.KeyboardMainActivity
import com.hao.androidrecord.activity.liveMessageHelper.RootActivity
import com.hao.androidrecord.activity.lunarPick.LunarDialogActivity
import com.hao.androidrecord.activity.nestedscrolling.NestedMainActivity
import com.hao.androidrecord.activity.nine.CustomGirdActivity
import com.hao.androidrecord.activity.nine.NineGridActivity
import com.hao.androidrecord.activity.nine.NineGridNewActivity
import com.hao.androidrecord.activity.paing3.TestPaging3Activity
import com.hao.androidrecord.activity.parallaxdecoration.ParallaxMainActivity
import com.hao.androidrecord.activity.physicslayout.MainPhysicActivity
import com.hao.androidrecord.activity.pinned.StockActivity
import com.hao.androidrecord.activity.pinned2.MainPinned2Activity
import com.hao.androidrecord.activity.pinned3.ui.DemoActivity
import com.hao.androidrecord.activity.progress.RoundProgressActivity
import com.hao.androidrecord.activity.recycleState.RecycleStateActivity
import com.hao.androidrecord.activity.recycleState.custom.RecycleStateActivity01
import com.hao.androidrecord.activity.recyclegroup.MainGroupStickyActivity
import com.hao.androidrecord.activity.richeditor.RichEditorActivity
import com.hao.androidrecord.activity.robustwebview.WebViewActivity
import com.hao.androidrecord.activity.rv_vp.Rv2VpMainActivity
import com.hao.androidrecord.activity.rvnotify.RVNotifyActivity
import com.hao.androidrecord.activity.scalingLayout.ScalingLayoutActivity
import com.hao.androidrecord.activity.scrollMove.MainScrollActivity
import com.hao.androidrecord.activity.scrollable.HeaderAndTablayoutRV
import com.hao.androidrecord.activity.scrollable.HeaderAndTablayoutRV02
import com.hao.androidrecord.activity.scrollable01.ScrollableMainActivity
import com.hao.androidrecord.activity.scrollable02.MainActivity
import com.hao.androidrecord.activity.scrollable03.MainActivityScrollable03
import com.hao.androidrecord.activity.scrollable04.Demo4Activity
import com.hao.androidrecord.activity.scrollable06.Scroll06MainActivity
import com.hao.androidrecord.activity.scrollable07.Scroll07MainActivity
import com.hao.androidrecord.activity.scrool.ScrollChangeTitleActivity
import com.hao.androidrecord.activity.search.MainSearch2Activity
import com.hao.androidrecord.activity.searchview.MainSearchActivity
import com.hao.androidrecord.activity.seemore.MainSeeMoreActivity
import com.hao.androidrecord.activity.separatededittext.PayPsdViewActivity
import com.hao.androidrecord.activity.separatededittext.PayPwdMainActivity
import com.hao.androidrecord.activity.shadow.MainShadowActivity
import com.hao.androidrecord.activity.sheet.MainGaodeSheetActivity
import com.hao.androidrecord.activity.slidingTab.MainSlidingActivity
import com.hao.androidrecord.activity.snaphelper.SnapHelperActivity
import com.hao.androidrecord.activity.stickscroll.MainStickScrollActivity
import com.hao.androidrecord.activity.swipehorizontalscrollview.HorizontalMainActivity
import com.hao.androidrecord.activity.switchButton.SwitchButtonMainActivity
import com.hao.androidrecord.activity.tabScrollAttacher.TabScrollActivity
import com.hao.androidrecord.activity.table.ChangeTableColorActivity
import com.hao.androidrecord.activity.tableview01.TableView01Activity
import com.hao.androidrecord.activity.taskaffinity.TaskAffinityActivity
import com.hao.androidrecord.activity.testviewvgroup.PieMainActivity
import com.hao.androidrecord.activity.testviewvgroup.TestViewVGroupActivity
import com.hao.androidrecord.activity.ticket.TicketViewActivity
import com.hao.androidrecord.activity.tiger.SlotMachineActivity
import com.hao.androidrecord.activity.tiger2.LuckyMainActivity
import com.hao.androidrecord.activity.tiger3.FortuneActivity
import com.hao.androidrecord.activity.tiktokComments.CommentMultiActivity
import com.hao.androidrecord.activity.timeline.line1.MainTimeLineActivity
import com.hao.androidrecord.activity.timeline.line2.DotTimeLineActivity
import com.hao.androidrecord.activity.viewcacheext.DemoRvActivity
import com.hao.androidrecord.activity.webviewOptimize.H5WebViewActivity
import com.hao.androidrecord.adapter.CityData
import com.hao.androidrecord.adapter.DemoAdapter
import com.hao.androidrecord.adapter.JsonBean
import com.hao.androidrecord.animtest.AnimationTestMainActivity
import com.hao.androidrecord.calendar.activity.CalendarMainActivity
import com.hao.androidrecord.custom.drapmenu.DropMenuActivity
import com.hao.androidrecord.custom.pickerads.AddressDialog
import com.hao.androidrecord.custom.pickerads.BaseDialog
import com.hao.androidrecord.custom.selector.Matisse
import com.hao.androidrecord.custom.selector.MimeType
import com.hao.androidrecord.custom.selector.engine.impl.GlideEngine
import com.hao.androidrecord.custom.selector.ui.MatisseCustomActivity
import com.hao.androidrecord.indexable.IndexableCityActivity
import com.hao.androidrecord.leonids.ExampleListActivity
import com.hao.androidrecord.service.MusicPlayerService
import com.hao.androidrecord.table.table01.TableActivity
import com.hao.androidrecord.table.tablelunar.LunaActivity
import com.hao.androidrecord.threestateswitch.ThreeStateActivity
import com.hao.androidrecord.util.AliBase64
import com.hao.androidrecord.util.MBase64
import com.hjq.toast.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
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
        ToastUtils.show("toast框架")
        initCity()

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
                    49 -> {
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.RichEdit.MainActivity::class.java))
                    }
                    50 -> {
                        startActivity(Intent(this@MainActivity, RichEditorActivity::class.java))
                    }
                    51 -> {
                        startActivity(Intent(this@MainActivity, SwitchActivity::class.java))
                    }
                    52 -> {
                        startActivity(Intent(this@MainActivity, MainBottomActivity::class.java))
                    }
                    53 -> {
                        startActivity(Intent(this@MainActivity, MainBottomActivity01::class.java))
                    }

                    54 -> {
                        startActivity(Intent(this@MainActivity, MainBottomActivity02::class.java))
                    }
                    55 -> {
                        startActivity(Intent(this@MainActivity, StartActivity::class.java))
                    }
                    56 -> {
                        startActivity(Intent(this@MainActivity, RelationActivity::class.java))
                    }
                    57 -> {
                        startActivity(Intent(this@MainActivity, MainImpActivity::class.java))
                    }
                    58 -> {
                        startActivity(Intent(this@MainActivity, MainScrollActivity::class.java))
                    }
                    59 -> {
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.live.MainActivity::class.java))
                    }
                    60 -> {
                        startActivity(Intent(this@MainActivity, SlotMachineActivity::class.java))
                    }
                    61 -> {
                        startActivity(Intent(this@MainActivity, LuckyMainActivity::class.java))
                    }
                    62 -> {
                        startActivity(Intent(this@MainActivity, FortuneActivity::class.java))
                    }
                    63 -> {
                        startActivity(Intent(this@MainActivity, SelectMainActivity::class.java))
                    }
                    64 -> {
                        startActivity(Intent(this@MainActivity, SelectTextActivity::class.java))
                    }
                    65 -> {
                        startActivity(Intent(this@MainActivity, SelectHelpMainActivity::class.java))
                    }
                    66 -> {
                        startActivity(Intent(this@MainActivity, HalfMainActivity::class.java))
                    }

                    67 -> {
                        startActivity(Intent(this@MainActivity, MainHalf2Activity::class.java))
                    }
                    68 -> {
                        startActivity(Intent(this@MainActivity, AutoScrollRecycleActivity::class.java))
                    }
                    69 -> {
                        startActivity(Intent(this@MainActivity, MeasureTextActivity::class.java))
                    }
                    70 -> {
                        startActivity(Intent(this@MainActivity, MainSearchActivity::class.java))
                    }
                    71 -> {
                        startActivity(Intent(this@MainActivity, MainSearch2Activity::class.java))
                    }
                    72 -> {
                        startActivity(Intent(this@MainActivity, ArcLayoutMainActivity::class.java))
                    }
                    73 -> {
                        startActivity(Intent(this@MainActivity, AutoScrollActivity::class.java))
                    }

                    74 -> {
                        startActivity(Intent(this@MainActivity, MainKeyboardActivity::class.java))
                    }
                    75 -> {
                        startActivity(Intent(this@MainActivity, CheckNotchActivity::class.java))
                    }
                    76 -> {
                        startActivity(Intent(this@MainActivity, TestFLowActivity::class.java))
                    }
                    77 -> {
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.bnavigationbar.MainBottomActivity::class.java))
                    }
                    78 -> {
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.bnavigationbar.MainViewPagerActivity::class.java))
                    }
                    79 -> {
                        startActivity(Intent(this@MainActivity, CoilLoadActivity::class.java))
                    }
                    80 -> {
                        startActivity(Intent(this@MainActivity, KeyboardMainActivity::class.java))
                    }
                    81 -> {
                        startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
                    }
                    82 -> {
                        startActivity(Intent(this@MainActivity, RVBottomActivity::class.java))
                    }
                    83 -> {
                        startActivity(Intent(this@MainActivity, ShapeActivity::class.java))
                    }
                    84 -> {
                        startActivity(Intent(this@MainActivity, CuSwitchButtonActivity::class.java))
                    }
                    85 -> {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                    86 -> {
                        startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                    }
                    87 -> {
                        AddressDialog.Builder(this@MainActivity)
                            //.setTitle("选择地区")
                            // 设置默认省份
//                            .setProvince(province) // 设置默认城市（必须要先设置默认省份）
//                            .setCity(city) // 不选择县级区域
                            //.setIgnoreArea()
                            .setListener(object : AddressDialog.OnListener {

                                override fun onSelected(dialog: BaseDialog?, province: String, city: String, area: String) {

                                }
                            })
                            .show()
                    }

                    88 -> {
                        startActivity(Intent(this@MainActivity, GuideActivity::class.java))
                    }
                    89 -> {
                        startActivity(Intent(this@MainActivity, DialogActivity::class.java))
                    }
                    90 -> {
                        startActivity(Intent(this@MainActivity, TextViewPressAlphaActivity::class.java))
                    }
                    91 -> {
                        startActivity(Intent(this@MainActivity, EditTextCusorActivity::class.java))
                    }
                    92 -> {
                        startActivity(Intent(this@MainActivity, BlurTitleActivity::class.java))
                    }

                    93 -> {
                        startActivity(Intent(this@MainActivity, NiftyDialogActivity::class.java))
                    }

                    94 -> {
                        startActivity(Intent(this@MainActivity, MainDialogActivity::class.java))
                    }

                    95 -> {
                        startActivity(Intent(this@MainActivity, MainTimeLineActivity::class.java))
                    }
                    96 -> {
                        startActivity(Intent(this@MainActivity, DotTimeLineActivity::class.java))
                    }
                    97 -> {
                        startActivity(Intent(this@MainActivity, MainSeeMoreActivity::class.java))
                    }
                    98 -> {
                        startActivity(Intent(this@MainActivity, MainSlidingActivity::class.java))
                    }
                    99 -> {
                        startActivity(Intent(this@MainActivity, BottomSheetActivity::class.java))
                    }

                    100 -> {
                        startActivity(Intent(this@MainActivity, CoordinatorActivity::class.java))
                    }
                    101 -> {
                        startActivity(Intent(this@MainActivity, BottomBarActivity::class.java))
                    }

                    102 -> {
                        startActivity(Intent(this@MainActivity, LauncherActivity::class.java))
                    }
                    103 -> {
                        startActivity(Intent(this@MainActivity, MainGroupStickyActivity::class.java))
                    }
                    104 -> {
                        startActivity(Intent(this@MainActivity, MainScrollerActivity::class.java))
                    }
                    105 -> {
                        startActivity(Intent(this@MainActivity, StockActivity::class.java))
                    }
                    106 -> {
                        startActivity(Intent(this@MainActivity, MainPinned2Activity::class.java))
                    }
                    107 -> {
                        startActivity(Intent(this@MainActivity, DemoActivity::class.java))
                    }
                    108 -> {
                        startActivity(Intent(this@MainActivity, MainGaodeSheetActivity::class.java))
                    }
                    109 -> {
                        startActivity(Intent(this@MainActivity, DropMenuActivity::class.java))
                    }
                    110 -> {
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.observable.MainActivity::class.java))
                    }
                    111 -> {
                        startActivity(Intent(this@MainActivity, ExpandFlowActivity::class.java))
                    }
                    112 -> {
                        startActivity(Intent(this@MainActivity, OAIDActivity::class.java))
                    }
                    113 -> {
                        startActivity(Intent(this@MainActivity, ExpandMenuActivity::class.java))
                    }
                    114 -> {
                        startActivity(Intent(this@MainActivity, FlipMainActivity::class.java))
                    }
                    115 -> {
                        startActivity(Intent(this@MainActivity, ExpandableRecycleActivity::class.java))
                    }
                    116 -> {
                        startActivity(Intent(this@MainActivity, TwoExpandableActivity::class.java))
                    }
                    117 -> {
                        startActivity(Intent(this@MainActivity, SimpleChatViewActivity::class.java))
                    }

                    118 -> {
                        startActivity(Intent(this@MainActivity, ScrollTextViewActivity::class.java))
                    }
                    119 -> {
                        startActivity(Intent(this@MainActivity, com.hao.androidrecord.activity.expandable.expand01.ExpandMenuActivity::class.java))
                    }

                    120 -> {
                        startActivity(Intent(this@MainActivity,AnimatorXActivity::class.java))
                    }
                    121 -> {
                        startActivity(Intent(this@MainActivity,FabPopMainActivity::class.java))
                    }
                    122 -> {
                        startActivity(Intent(this@MainActivity,SparkButtonActivity::class.java))
                    }
                    123 -> {
                        startActivity(Intent(this@MainActivity,DYIndexMainActivity::class.java))
                    }
                    124 -> {
                        startActivity(Intent(this@MainActivity,MainPhysicActivity::class.java))
                    }
                    125 -> {
                        startActivity(Intent(this@MainActivity,AnimationTestMainActivity::class.java))
                    }
                    126 -> {
                        startActivity(Intent(this@MainActivity,PayPwdMainActivity::class.java))
                    }
                    127 -> {
                        startActivity(Intent(this@MainActivity,PayPsdViewActivity::class.java))
                    }
                    128 -> {
                        startActivity(Intent(this@MainActivity,IntervalActivity::class.java))
                    }
                    129 -> {
                        startActivity(Intent(this@MainActivity,MainStickScrollActivity::class.java))
                    }
                    130 -> {
                        startActivity(Intent(this@MainActivity,ChooseMainActivity::class.java))
                    }
                    131 -> {
                        startActivity(Intent(this@MainActivity,ThreeStateActivity::class.java))
                    }
                    132 -> {
                        startActivity(Intent(this@MainActivity,ExpandCardMainActivity::class.java))
                    }
                    133 -> {
                        startActivity(Intent(this@MainActivity,TestViewVGroupActivity::class.java))
                    }
                    134 -> {
                        startActivity(Intent(this@MainActivity,DemoRvActivity::class.java))
                    }
                    135 -> {
                        startActivity(Intent(this@MainActivity,RVNotifyActivity::class.java))
                    }
                    136 -> {
                        startActivity(Intent(this@MainActivity,TaskAffinityActivity::class.java))
                    }

                    137 -> {
                        startForegroundService(Intent(this@MainActivity,MusicPlayerService::class.java))
                    }
                    138 -> {
                        startActivity(Intent(this@MainActivity,com.hao.androidrecord.activity.WebViewActivity::class.java))
                    }
                    139 -> {
                        startActivity(Intent(this@MainActivity,SnapHelperActivity::class.java))
                    }
                    140 -> {
                        startActivity(Intent(this@MainActivity,RecycleStateActivity::class.java))
                    }
                    141 -> {
                        startActivity(Intent(this@MainActivity,RecycleStateActivity01::class.java))
                    }
                    142 -> {
                        startActivity(Intent(this@MainActivity,
                            PieMainActivity::class.java))
                    }
                    143 -> {
                        startActivity(Intent(this@MainActivity,
                            AlphaActivity::class.java))
                    }
                    144 -> {
                        startActivity(Intent(this@MainActivity,
                            H5WebViewActivity::class.java))
                    }
                    145 -> {
                        startActivity(Intent(this@MainActivity,
                            Rv2VpMainActivity::class.java))
                    }
                    146 -> {
                        startActivity(Intent(this@MainActivity,
                            TableViewActivity::class.java))
                    }
                    147 -> {
                        startActivity(Intent(this@MainActivity,
                            ZoomRecycleActivity::class.java))
                    }
                    148 -> {
                        startActivity(Intent(this@MainActivity,
                            TableView01Activity::class.java))
                    }
                    149 -> {
                        startActivity(Intent(this@MainActivity,
                            RoundProgressActivity::class.java))
                    }
                    150 -> {
                        startActivity(Intent(this@MainActivity,
                            FlexBoxRecycleActivity::class.java))
                    }
                    151 -> {
                        startActivity(Intent(this@MainActivity,
                            DragCardActivity::class.java))
                    }
                    152 -> {
                        startActivity(Intent(this@MainActivity,
                            TicketViewActivity::class.java))
                    }
                    153 -> {
                        startActivity(Intent(this@MainActivity,
                            LuckWheelActivity::class.java))
                    }
                    154 -> {
                        startActivity(Intent(this@MainActivity,
                            KeyboardActivity::class.java))
                    }
                    155 -> {
                        startActivity(Intent(this@MainActivity,
                            ScalingLayoutActivity::class.java))
                    }
                    156 -> {
                        startActivity(Intent(this@MainActivity,
                            TabScrollActivity::class.java))
                    }

                    157 -> {
                        startActivity(Intent(this@MainActivity,
                            CalendarMainActivity::class.java))
                    }
                    158 -> {
                        startActivity(Intent(this@MainActivity,
                            ElasticViewMainActivity::class.java))

                    }

                    159 -> {
                        startActivity(Intent(this@MainActivity,
                            HorizontalMainActivity::class.java))
                    }
                    160 -> {
                        startActivity(Intent(this@MainActivity,
                            LunaActivity::class.java))
                    }

                      161 -> {
                        startActivity(Intent(this@MainActivity,
                            TableActivity::class.java))
                    }
                     162 -> {
                        startActivity(Intent(this@MainActivity,
                            RootActivity::class.java))
                    }
                    163 -> {
                        startActivity(Intent(this@MainActivity,
                            ExampleListActivity::class.java))
                    }
                    164 -> {
                        startActivity(Intent(this@MainActivity,
                            CoffeeViewActivity::class.java))
                    }
                    165 -> {
                        startActivity(Intent(this@MainActivity,
                            CandleActivity::class.java))
                    }
                    166 -> {
                        startActivity(Intent(this@MainActivity,
                            LunarDialogActivity::class.java))
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
        list.add("20滑动按钮&tab单选")
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
        list.add("49edittext话题@")
        list.add("50edittext话题@")
        list.add("51滑动按钮自定义")
        list.add("52底部凹陷导航")
        list.add("53底部凹陷导航")
        list.add("54底部凹陷导航")
        list.add("55选择器弧形")
        list.add("56亲属关系")
        list.add("57卡片滑到详情，详情退出回到卡片")
        list.add("58高仿药房网商品详情页面（滑动联动）。效果流畅到你难以想象")
        list.add("59直播打赏榜，顶部、底部渐变效果")
        list.add("60老虎机")
        list.add("61老虎机")
        list.add("62老虎机")
        list.add("63TextView 内容对齐 滑动选择内容 弹出复制等操作")
        list.add("64TextView 选择内容 弹出复制等操作")
        list.add("65TextView 选择内容 弹出复制等操作")
        list.add("66半屏类似sheet webview")
        list.add("67半屏类似sheet webview")
        list.add("68RecyclerView实现垂直自动无限滚动，类似于中奖信息，跑马灯")
        list.add("69 测量textView设置文字后的宽度--测量文字宽度")
        list.add("70 material效果展示搜索")
        list.add("71 material效果展示搜索 仿哔哩哔哩")
        list.add("72 View进行弧度处理")
        list.add("73跑马灯 可以嵌套viewgroup作为整体移动")
        list.add("74 键盘输入数字移动到输入框")
        list.add("75 检测是否刘海屏水滴屏")
        list.add("76 协程flow相关")
        list.add("77 底部tab导航 中间凸出")
        list.add("78 底部tab导航 中间凸出")
        list.add("79 coil图片加载")
        list.add("80解决软键盘弹出和收缩时表情面板切换跳闪的问题")
        list.add("81Android WebView H5 秒开方案")
        list.add("82RecycleView作为bottomtab")
        list.add("83shape的一些")
        list.add("84滑动按钮")
        list.add("85登陆界面整体item动画layoutAnimation")
        list.add("86注册界面整体item动画layoutAnimation")
        list.add("87城市选择器")
        list.add("88引导界面")
        list.add("89dialog的一些")
        list.add("90textview长按透明度")
        list.add("91EditText首次点击光标最右边显示，其他次正常点击位置")
        list.add("92title毛玻璃模糊底部内容")
        list.add("93dialog动画弹出动画")
        list.add("94dialog_3D旋转切换")
        list.add("95timeline时间线")
        list.add("96timeline时间线")
        list.add("97recycle横向滑动加载更多")
        list.add("98可以交叉tab和文字的SlidingTab，支持设置渐变色")
        list.add("99bottomsheet固定底部拖动")
        list.add("100bottomsheet固定底部拖动")
        list.add("101bottomsheet固定底部拖动")
        list.add("102 recycleIndexbar,分组 section sticky pinned 悬浮吸顶")
        list.add("103 recycleIndexbar,分组 展开 expand section sticky pinned 悬浮吸顶")
        list.add("104 滑动布局 连贯滑动的容器,scroll")
        list.add("105 不用太关注 -- 重复绘制header-recycleIndexbar,分组 section sticky pinned 悬浮吸顶")
        list.add("106recycleIndexbar,分组 section sticky pinned 悬浮吸顶")
        list.add("107recycleIndexbar,分组 section sticky pinned 悬浮吸顶")
        list.add("108bottomsheet固定底部拖动 两层移动")
        list.add("109DropMenu筛选菜单")
        list.add("110滚动模式滚动监听")
        list.add("111搜索历史记录设置显示行数 折叠展开")
        list.add("112开源的oaid")
        list.add("113RecycleExpand")
        list.add("114flip卡片翻转")
        list.add("115RecycleExpand")
        list.add("116两级expand---RecycleExpand")
        list.add("117直播公屏---左边滚动的留言")
        list.add("118跑马灯--滚动一次都某位暂停")
        list.add("119expandRecycle 展开")
        list.add("120协程+属性动画==power")
        list.add("121fab 弹窗fab 面包旅行弹窗的游记故事集按钮")
        list.add("122sparkBUtton")
        list.add("123抖音首页垂直滚动")
        list.add("124 2D物理引擎 碰撞")
        list.add("125动画相关testdemo")
        list.add("126微信支付宝密码界面自定义")
        list.add("127微信支付宝密码界面自定义")
        list.add("128定时器")
        list.add("129stick_scroll")
        list.add("130 自定义单选")
        list.add("131 三种状态滑动按钮")
        list.add("132 expand--收缩cardview")
        list.add("133自定义view viewgroup")
        list.add("134RecycleView自定义缓存")
        list.add("135RecycleView刷新Notif相关")
        list.add("136taskAffinity")
        list.add("137前台service")
        list.add("138webview--settings相关设置")
        list.add("139RecycleView--snaphelp相关--滑动设置item位置--可以设置成viewpager滑动模式")
        list.add("140Recycleview--嵌套水平滚动Recycleview--回收保持位置")
        list.add("141Recycleview--嵌套水平滚动Recycleview--回收保持位置")
        list.add("142自定义饼状图pie")
        list.add("143activity窗口变暗 类似弹出dialog")
        list.add("144Android WebView H5 秒开方案")
        list.add("145RecycleView 类似 viewpager效果")
        list.add("146tableview 表格")
        list.add("147滑动是 recycleview item 缩放")
        list.add("148tableview 表格")
        list.add("149roundprogress 圆角进度条")
        list.add("150Flexbox+Recyclerview----flowlayout")
        list.add("151drag--拖动卡片--拖动答题")
        list.add("152ticket_票据--电影票")
        list.add("153幸运转盘，luck wheel")
        list.add("154弹出键盘自动上移")
        list.add("155 滚动 动态缩放 layout")
        list.add("156 列表滚动，上面tab切换")
        list.add("157 日历相关")
        list.add("158 点击缩放效果")
        list.add("159 表格水平滚动")
        list.add("160 表格 table-layout-manager--黄历")
        list.add("161 表格")
        list.add("162 直播间聊天消息列表")
        list.add("163粒子爆炸效果")
        list.add("164咖啡冒烟效果")
        list.add("165draw 烛光 test")
        list.add("166农历lunar选择器")
        adapter.notifyDataSetChanged()

        rv_demo_list.scrollToPosition(list.size-1)


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


  private  fun getJson(context: Context, fileName: String?): String {
        val stringBuilder = StringBuilder()
        try {
            val assetManager: AssetManager = context.getAssets()
            val bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName!!)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
    fun parseData(result: String): ArrayList<JsonBean> { //Gson 解析
        val detail: ArrayList<JsonBean> = ArrayList()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity: JsonBean =
                    gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detail
    }
    private fun initCity(){
        val listPri = ArrayList<CityData>()
        val json = getJson(this,"province_city_county.json")

        val list = parseData(json)

        list.forEach {

            val listCity = ArrayList<String>()
            it.cityList.forEach { cityBean ->
                if(cityBean.name != "其他" && cityBean.name != "其他市"){
                    listCity.add(cityBean.name)
                }

            }
            val data = CityData(it.name,listCity)
            listPri.add(data)
        }


        Log.e("=======city","$listPri")
        val gson = Gson()
        val jscity = gson.toJson(listPri)
        Log.e("=======city","$jscity")






    }

}