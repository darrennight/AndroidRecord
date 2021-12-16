package com.hao.androidrecord.activity.scrollMove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.scrollMove.adapter.CommentsAdapter;
import com.hao.androidrecord.activity.scrollMove.fragment.ExplainFragment;
import com.hao.androidrecord.activity.scrollMove.fragment.MyFragment;
import com.hao.androidrecord.activity.scrollMove.utils.DensityUtils;
import com.yuruiyin.appbarlayoutbehavior.AppBarLayoutBehavior;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

//https://github.com/lihangleo2/HightCopyDetail
public class MainScrollActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {
    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;
    float comments_height;

    private boolean isScrolling = false;
    private boolean isCommentClick = false;


    private ArrayList<TextView> textViews = new ArrayList<>();

    //关于下方fragment的切换
    private static final int HOME_ONE = 0;
    private static final int HOME_TWO = 1;
    private static final int HOME_THREE = 2;
    private int index;
    private int currentTabIndex = 0;

    MyFragment fragment_one;
    ExplainFragment fragment_two;
    ExplainFragment fragment_three;

    private TextView[] mTabs;
    private TextView[] mTabs_second;
    private FragmentManager manager;
    private ArrayList<Fragment> list_fragment = new ArrayList<Fragment>();

    private TextView txtStatus;
    private TextView txtComment;
    private TextView txtProduct;
    private TextView txtDetail;
    private AppBarLayout appBar;
    private RelativeLayout relativeTitle;
    private NestedScrollView nestedScrollView;
    private CoordinatorLayout coordinator;
    private LinearLayout linearTherother;
    private RecyclerView recyclerViewComment;

    private TextView txtTopBase;
    private TextView txtTopExplain;
    private TextView txtTopFuwu;

    private TextView txtBottomBase;
    private TextView txtBottomExplain;
    private TextView txtBottomFuwu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scroll);
        txtStatus = findViewById(R.id.txt_status);
        txtComment = findViewById(R.id.txt_comment);
        appBar = findViewById(R.id.appBar);
        relativeTitle = findViewById(R.id.relative_title);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        coordinator = findViewById(R.id.coordinator);
        linearTherother = findViewById(R.id.linear_therother);
        txtProduct = findViewById(R.id.txt_product);
        txtDetail = findViewById(R.id.txt_detail);
        recyclerViewComment = findViewById(R.id.recyclerViewComment);

        txtTopBase = findViewById(R.id.txt_top_base);
        txtTopExplain = findViewById(R.id.txt_top_explain);
        txtTopFuwu = findViewById(R.id.txt_top_fuwu);

        txtBottomBase = findViewById(R.id.txt_bottom_base);
        txtBottomExplain = findViewById(R.id.txt_bottom_explain);
        txtBottomFuwu = findViewById(R.id.txt_bottom_fuwu);

        txtTopBase.setOnClickListener(this);
        txtTopExplain.setOnClickListener(this);
        txtTopFuwu.setOnClickListener(this);
        txtBottomBase.setOnClickListener(this);
        txtBottomExplain.setOnClickListener(this);
        txtBottomFuwu.setOnClickListener(this);


        setListener();
        initFragment();
        initArray();

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) txtStatus.getLayoutParams();
        layoutParams.height = DensityUtils.getStatusBarHeight();
//        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true);
//        mImmersionBar.init();
        initProduct();
    }

    private void setListener() {

        appBar.addOnOffsetChangedListener(this);
        //评论的点击
        txtComment.setOnTouchListener((View v, MotionEvent ev) -> {
                    switch (ev.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            if (relativeTitle.getVisibility() == View.VISIBLE && !isScrolling && !txtComment.isSelected()) {
                                isCommentClick = true;
                                nestedScrollView.scrollTo(0, 0);
                                nestedScrollView.smoothScrollTo(0, 0);
//                                CoordinatorLayout.Behavior behavior =
//                                        ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).getBehavior();
//                                AppBarLayoutBehavior appBarLayoutBehavior = (AppBarLayoutBehavior) behavior;
//                                appBarLayoutBehavior.onInterceptTouchEvent(coordinator, appBar, ev);

                                setAppBarLayoutOffset(appBar, (int) -comments_height);
                                linearTherother.setVisibility(View.GONE);
                                selectTitle(txtComment);
                            }
                            break;
                    }
                    return false;
                }
        );


        //商品的点击
        txtProduct.setOnTouchListener((View v, MotionEvent ev) -> {
                    switch (ev.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            if (!txtProduct.isSelected()) {
                                isScrolling = true;
                                nestedScrollView.scrollTo(0, 0);
                                nestedScrollView.smoothScrollTo(0, 0);
//                                CoordinatorLayout.Behavior behavior =
//                                        ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).getBehavior();
//                                AppBarLayoutBehavior appBarLayoutBehavior = (AppBarLayoutBehavior) behavior;
//                                appBarLayoutBehavior.onInterceptTouchEvent(coordinator, appBar, ev);
                                appBar.setExpanded(true, true);
                                selectTitle(txtProduct);
                            }
                            break;
                    }
                    return false;
                }
        );

        //详情的点击
        txtDetail.setOnTouchListener((View v, MotionEvent ev) -> {
                    switch (ev.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            if (relativeTitle.getVisibility() == View.VISIBLE && !isScrolling && !txtDetail.isSelected()) {
                                nestedScrollView.scrollTo(0, 0);
                                nestedScrollView.smoothScrollTo(0, 0);
//                                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).getBehavior();
//                                AppBarLayoutBehavior appBarLayoutBehavior = (AppBarLayoutBehavior) behavior;
//                                appBarLayoutBehavior.onInterceptTouchEvent(coordinator, appBar, ev);
                                setAppBarLayoutOffset(appBar, -(int) (appBar.getTotalScrollRange() - getResources().getDimension(R.dimen.dp_50) - DensityUtils.getStatusBarHeight()));
                                selectTitle(txtDetail);
                            }
                            break;
                    }
                    return false;
                }
        );

        nestedScrollView.setFadingEdgeLength(0);
    }

    private void initProduct() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");

        CommentsAdapter commentsAdapter = new CommentsAdapter();
        commentsAdapter.setDataList(arrayList);
        recyclerViewComment.setAdapter(commentsAdapter);

        //这个方法是在获取商品详情接口后调用的。目的是填充数据，且测量评论区所占高度
        Observable.timer(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            measure(appBar.getTotalScrollRange());
        });
    }


    private void initFragment() {
        manager = getSupportFragmentManager();
        mTabs = new TextView[3];
        mTabs[0] = txtTopBase;
        mTabs[1] = txtTopExplain;
        mTabs[2] = txtTopFuwu;


        mTabs_second = new TextView[3];
        mTabs_second[0] = txtBottomBase;
        mTabs_second[1] = txtBottomExplain;
        mTabs_second[2] = txtBottomFuwu;

        fragment_one = new MyFragment();
        fragment_two = new ExplainFragment(1);
        fragment_three = new ExplainFragment(2);

        list_fragment.add(fragment_one);
        list_fragment.add(fragment_two);
        list_fragment.add(fragment_three);
        switchFragment(R.id.txt_top_base);
    }


    private void initArray() {
        textViews.add(txtProduct);
        textViews.add(txtDetail);
        textViews.add(txtComment);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (Math.abs(i) >= 10) {
            if (relativeTitle.getVisibility() == View.GONE) {
                relativeTitle.setVisibility(View.VISIBLE);
                txtStatus.setVisibility(View.VISIBLE);

                Animation animation = AnimationUtils.loadAnimation(MainScrollActivity.this, R.anim.alpha_detail_come);
                relativeTitle.setAnimation(animation);
                txtStatus.setAnimation(animation);
                animation.start();
            }
        } else {
            if (relativeTitle.getVisibility() == View.VISIBLE) {
                isScrolling = false;
                relativeTitle.setVisibility(View.GONE);
                txtStatus.setVisibility(View.INVISIBLE);
                Animation animation = AnimationUtils.loadAnimation(MainScrollActivity.this, R.anim.alpha_detail_go);
                relativeTitle.setAnimation(animation);
                txtStatus.setAnimation(animation);
                animation.start();
            }
        }


        if (comments_height != 0 && Math.abs(i) >= comments_height && Math.abs(i) < appBarLayout.getTotalScrollRange() - getResources().getDimension(R.dimen.dp_50) - DensityUtils.getStatusBarHeight()) {
            //选中评论
            if (!txtComment.isSelected()) {
                selectTitle(txtComment);
            }
            linearTherother.setVisibility(View.GONE);
            //选中
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange() - getResources().getDimension(R.dimen.dp_50) - DensityUtils.getStatusBarHeight()) {
            //选中详情
            if (!isCommentClick) {
                if (!txtDetail.isSelected()) {
                    selectTitle(txtDetail);
                }
                linearTherother.setVisibility(View.VISIBLE);
            }
            isCommentClick = false;


        } else {
            if (!txtProduct.isSelected()) {
                selectTitle(txtProduct);
            }
            linearTherother.setVisibility(View.GONE);
        }

    }


    public void measure(int total) {
        if (comments_height == 0) {
            comments_height = total - getResources().getDimension(R.dimen.dp_100) - DensityUtils.getStatusBarHeight() - recyclerViewComment.getHeight();
        }
    }


    public void switchFragment(int id) {
        FragmentTransaction ft = manager.beginTransaction();
        TextView relativeLayout = (TextView) findViewById(id);
        String tag = (String) relativeLayout.getTag();
        Fragment f = manager.findFragmentByTag(tag);
        if (f == null) {
            int num = Integer.parseInt(tag);
            ft.add(R.id.framLayout, list_fragment.get(num), tag);
        }

        for (int i = 0; i < list_fragment.size(); i++) {
            Fragment fragment = list_fragment.get(i);
            if (fragment.getTag() != null) {
                if (fragment.getTag().equals(tag)) {
                    ft.show(fragment);
                } else {
                    ft.hide(fragment);
                }
            }
        }
        ft.commitAllowingStateLoss();
        switch (id) {
            case R.id.txt_top_base://首页
                index = HOME_ONE;
                break;
            case R.id.txt_top_explain:
                index = HOME_TWO;
                break;
            case R.id.txt_top_fuwu:
                index = HOME_THREE;
                break;
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs_second[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        mTabs_second[index].setSelected(true);
        currentTabIndex = index;
    }


    public void selectTitle(TextView textView) {
        for (int i = 0; i < textViews.size(); i++) {
            if (textView == textViews.get(i)) {
                if (!textViews.get(i).isSelected()) {
                    textViews.get(i).setSelected(true);
                    textViews.get(i).setScaleX(1.3f);
                    textViews.get(i).setScaleY(1.3f);
                }
            } else {
                if (textViews.get(i).isSelected()) {
                    textViews.get(i).setSelected(false);
                    textViews.get(i).setScaleX(1.0f);
                    textViews.get(i).setScaleY(1.0f);
                }
            }

        }
    }


    /**
     * 设置appbar偏移量
     *
     * @param appBar
     * @param offset
     */
    public void setAppBarLayoutOffset(AppBarLayout appBar, int offset) {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {

            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();


            if (topAndBottomOffset != offset) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(appBarLayoutBehavior.getTopAndBottomOffset(), offset);
                valueAnimator.setDuration(500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int offetOther = (int) animation.getAnimatedValue();
                        appBarLayoutBehavior.setTopAndBottomOffset(offetOther);
                        if (relativeTitle.getVisibility() == View.GONE) {
                            relativeTitle.setVisibility(View.VISIBLE);
                            txtStatus.setVisibility(View.VISIBLE);

                            Animation animation_appBarScroll = AnimationUtils.loadAnimation(MainScrollActivity.this, R.anim.alpha_detail_come);
                            relativeTitle.setAnimation(animation_appBarScroll);
                            txtStatus.setAnimation(animation_appBarScroll);
                            animation_appBarScroll.start();
                        }


//                        if (Math.abs(offetOther) >= linearScroll_height - getResources().getDimension(R.dimen.dp_50) - DensityUtils.getStatusBarHeight()) {
//                            binding.linearTherother.setVisibility(View.VISIBLE);
//                        } else {
//                            binding.linearTherother.setVisibility(View.GONE);
//                        }


                    }
                });
                valueAnimator.start();
            }
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.txt_bottom_base:
            case R.id.txt_top_base:
                switchFragment(R.id.txt_top_base);
                break;


            case R.id.txt_bottom_explain:

            case R.id.txt_top_explain:
                switchFragment(R.id.txt_top_explain);
                break;


            case R.id.txt_bottom_fuwu:
            case R.id.txt_top_fuwu:
                switchFragment(R.id.txt_top_fuwu);
                break;
        }
    }
}
