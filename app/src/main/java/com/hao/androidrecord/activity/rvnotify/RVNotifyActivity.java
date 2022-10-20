package com.hao.androidrecord.activity.rvnotify;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hao.androidrecord.R;

import java.util.ArrayList;
import java.util.List;

public class RVNotifyActivity extends AppCompatActivity implements View.OnClickListener {

private RecyclerView mRvList;
private List<RVData> mDataList=new ArrayList<>();
private String image="http://ww3.sinaimg.cn/large/610dc034gw1f9shm1cajkj20u00jy408.jpg";
private String refreshImage="http://ww3.sinaimg.cn/large/610dc034jw1fa2vh33em9j20u00zmabz.jpg";
private String notifyImage="http://ww2.sinaimg.cn/large/610dc034jw1f9vyl2fqi0j20u011habc.jpg";
private Button mBtNotify_1;
private Button mBtNotify_2;
private Button mBtNotify_3;
private Button mBtNotify_4;
private Button mBtNotify_5;
private Button mBtNotify_6;
private Button mBtNotify_7;
private RVAdapter mRvAdapter;
private GridLayoutManager mGridLayoutManager;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rv_notify);
    initView();
    initListener();
}

private void initView() {
    mRvList = findViewById(R.id.recyclerview);
    mBtNotify_1 = findViewById(R.id.bt_notify_1);
    mBtNotify_2 = findViewById(R.id.bt_notify_2);
    mBtNotify_3 = findViewById(R.id.bt_notify_3);
    mBtNotify_4 = findViewById(R.id.bt_notify_4);
    mBtNotify_5 = findViewById(R.id.bt_notify_5);
    mBtNotify_6 = findViewById(R.id.bt_notify_6);
    mBtNotify_7 = findViewById(R.id.bt_notify_7);
    mGridLayoutManager = new GridLayoutManager(this,2);
    mRvList.setLayoutManager(mGridLayoutManager);
    mRvAdapter = new RVAdapter(mDataList,this);
    mRvList.setAdapter(mRvAdapter);
    initData();
    mRvAdapter.notifyDataSetChanged();
}

private void initData() {
    for (int i = 0; i < 10; i++) {
        RVData data=new RVData();
        data.setName("name:"+i);
        data.setDesc("desc:"+i);
        data.setImage(image);
        mDataList.add(data);
    }
}

private void initListener() {
    mBtNotify_1.setOnClickListener(this);
    mBtNotify_2.setOnClickListener(this);
    mBtNotify_3.setOnClickListener(this);
    mBtNotify_4.setOnClickListener(this);
    mBtNotify_5.setOnClickListener(this);
    mBtNotify_6.setOnClickListener(this);
    mBtNotify_7.setOnClickListener(this);
    mRvList.setOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int firstVisibleItemPosition = mGridLayoutManager.findFirstCompletelyVisibleItemPosition();
            int lastVisibleItemPosition = mGridLayoutManager.findLastCompletelyVisibleItemPosition();
            for (int i = firstVisibleItemPosition; i <lastVisibleItemPosition; i++) {
                View view = mGridLayoutManager.findViewByPosition(i);
                ImageView imageView=view.findViewById(R.id.iv_image);
                //可实现可见item的动画，而不是绘制item的动画，滑动就会触发动画
//              setAnimation(imageView);
            }
        }
    });
}

@Override
public void onClick(View v) {
    switch (v.getId()){
        case R.id.bt_notify_1:
            //全部刷新
            mDataList.addAll(addData());
            mRvAdapter.notifyDataSetChanged();
            break;
        case R.id.bt_notify_2:
            //移除刷新
            mDataList.remove(1);
            mRvAdapter.notifyItemRemoved(1);
            //受影响的item都刷新position
            mRvAdapter.notifyItemRangeChanged(1, mDataList.size() - 1);
            //批量删除
            //mRvAdapter.notifyItemRangeRemoved(int positionStart, int itemCount)
            break;
        case R.id.bt_notify_3:
            //插入刷新
            RVData data=new RVData();
            data.setName("name:"+"插入");
            data.setDesc("des:"+"插入");
            data.setImage(refreshImage);
            mDataList.add(1,data);
            mRvAdapter.notifyItemInserted(1);
            mRvAdapter.notifyItemRangeChanged(1, mDataList.size() + 1);
            break;
        case R.id.bt_notify_4:
            //移动刷新
            //注意位置的变换 1 和 4 交换
            RVData remove4 = mDataList.remove(4);
            RVData remove1 = mDataList.remove(1);
            mDataList.add(1,remove4);
            mDataList.add(4,remove1);
            mRvAdapter.notifyItemMoved(4,1);
            //受影响的item都刷新position
            mRvAdapter.notifyItemRangeChanged(Math.min(4, 1), Math.abs(4 - 1) +1);//受影响的item都刷新position
            break;
        case R.id.bt_notify_5:
            //局部刷新
//            RVData data1=new RVData();
//            data1.setImage(refreshImage);
            RVData data1 = mDataList.get(1);
            data1.setName("name局部payload");
            data1.setDesc("des局部payload");
            data1.setImage(refreshImage);
            mRvAdapter.notifyItemChanged(1,data1);
            break;
        case R.id.bt_notify_6:
            //局部刷新2
            mDataList.get(1).setImage(refreshImage);
            mRvAdapter.notifyItemChanged(1);
            break;
        case R.id.bt_notify_7:
            //自己实现局部
            //获取到viewholder的view
            View viewHolder = mGridLayoutManager.findViewByPosition(3);
            mDataList.get(3).setImage(notifyImage);
            ImageView imageView=viewHolder.findViewById(R.id.iv_image);
            Glide.with(this).load(notifyImage).placeholder(R.mipmap.ic_launcher).into(imageView);
            break;
    }
}

private List<RVData> addData(){
    List<RVData> datalist=new ArrayList<>();
    for (int i = 0; i < 6; i++) {
        RVData data=new RVData();
        data.setName("name:"+"refresh-"+i);
        data.setDesc("des:"+"refresh-"+i);
        data.setImage(notifyImage);
        datalist.add(data);
    }
    return datalist;
}

public void setAnimation(View view) {
    ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(view, "rotation", -6, 6,6,-6,0);
    mObjectAnimator.setDuration(500);
    mObjectAnimator.setRepeatCount(1);
    mObjectAnimator.setInterpolator(new AccelerateInterpolator());
    mObjectAnimator.start();
}
}