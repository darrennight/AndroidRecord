package com.hao.androidrecord.activity.indexbar2;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.indexbar2.adapter.MeituanAdapter;
import com.hao.androidrecord.activity.indexbar2.decoration.DividerItemDecoration;
import com.hao.androidrecord.activity.indexbar2.model.MeiTuanBean;
import com.hao.androidrecord.activity.indexbar2.model.MeituanHeaderBean;
import com.hao.androidrecord.activity.indexbar2.model.MeituanTopHeaderBean;
import com.hao.androidrecord.activity.indexbar2.utils.CommonAdapter;
import com.hao.androidrecord.activity.indexbar2.utils.HeaderRecyclerAndFooterWrapperAdapter;
import com.hao.androidrecord.activity.indexbar2.utils.ViewHolder;
import com.hao.androidrecord.custom.suspensionindexbar.SuspensionDecoration;
import com.hao.androidrecord.custom.suspensionindexbar.indexbar.bean.BaseIndexPinyinBean;
import com.hao.androidrecord.custom.suspensionindexbar.indexbar.widget.IndexBar;

import java.util.ArrayList;
import java.util.List;



/**
 * 介绍： 高仿美团选择城市页面
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 2016/11/7.
 */
public class MeituanSelectCityActivity extends AppCompatActivity {
    private static final String TAG = "zxt";
    private Context mContext;
    private RecyclerView mRv;
    private MeituanAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<MeituanHeaderBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<MeiTuanBean> mBodyDatas;

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meituan);
        mContext = this;

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        mHeaderDatas.add(new MeituanHeaderBean(locationCity, "定位城市", "定"));
        List<String> recentCitys = new ArrayList<>();
        mHeaderDatas.add(new MeituanHeaderBean(recentCitys, "最近访问城市", "近"));
        List<String> hotCitys = new ArrayList<>();
        mHeaderDatas.add(new MeituanHeaderBean(hotCitys, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);

        mAdapter = new MeituanAdapter(this, R.layout.meituan_item_select_city, mBodyDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.meituan_item_header:
                        final MeituanHeaderBean meituanHeaderBean = (MeituanHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(mContext, R.layout.meituan_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mContext, "cityName:" + cityName, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                        break;
                    case R.layout.meituan_item_header_top:
                        MeituanTopHeaderBean meituanTopHeaderBean = (MeituanTopHeaderBean) o;
                        holder.setText(R.id.tvCurrent, meituanTopHeaderBean.getTxt());
                        break;
                    default:
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0, R.layout.meituan_item_header_top, new MeituanTopHeaderBean("当前：上海徐汇"));
        mHeaderAdapter.setHeaderView(1, R.layout.meituan_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.meituan_item_header, mHeaderDatas.get(1));
        mHeaderAdapter.setHeaderView(3, R.layout.meituan_item_header, mHeaderDatas.get(2));


        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(mContext.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        mRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());

        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBodyDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    MeiTuanBean cityBean = new MeiTuanBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mBodyDatas.add(cityBean);
                }
                //先排序
                mIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);

                mAdapter.setDatas(mBodyDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mSourceDatas);
            }
        }, 1000);

        //延迟两秒加载头部
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                MeituanHeaderBean header1 = mHeaderDatas.get(0);
                header1.getCityList().clear();
                header1.getCityList().add("上海");

                MeituanHeaderBean header2 = mHeaderDatas.get(1);
                List<String> recentCitys = new ArrayList<>();
                recentCitys.add("日本");
                recentCitys.add("北京");
                header2.setCityList(recentCitys);

                MeituanHeaderBean header3 = mHeaderDatas.get(2);
                List<String> hotCitys = new ArrayList<>();
                hotCitys.add("上海");
                hotCitys.add("北京");
                hotCitys.add("杭州");
                hotCitys.add("广州");
                header3.setCityList(hotCitys);

                mHeaderAdapter.notifyItemRangeChanged(1, 3);

            }
        }, 2000);

    }

    /**
     * 更新数据源
     *
     * @param view
     */
    public void updateDatas(View view) {
        for (int i = 0; i < 5; i++) {
            mBodyDatas.add(new MeiTuanBean("东京"));
            mBodyDatas.add(new MeiTuanBean("大阪"));
        }
        //先排序
        mIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);
        mSourceDatas.clear();
        mSourceDatas.addAll(mHeaderDatas);
        mSourceDatas.addAll(mBodyDatas);

        mHeaderAdapter.notifyDataSetChanged();
        mIndexBar.invalidate();
    }
}
