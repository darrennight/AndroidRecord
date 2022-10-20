package com.hao.androidrecord.activity.viewcacheext;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;

//DemoRvActivity.java:

/**
 * 总结一下上述流程：通过mAttachedScrap、mCachedViews及mViewCacheExtension获取的ViewHolder不需要重新创建布局及绑定数据；
 * 通过缓存池mRecyclerPool获取的ViewHolder不需要重新创建布局，但是需要重新绑定数据；如果上述缓存中都没有获取到目标ViewHolder，
 * 那么就会回调Adapter#onCreateViewHolder创建布局，以及回调Adapter#onBindViewHolder来绑定数据。
 *ViewCacheExtension适用场景：ViewHolder位置固定、内容固定、数量有限时使用
 * ViewCacheExtension使用举例：
 * 比如在position = 0时展示的是一个广告，位置不变，内容不变，来看看如何实现：
 * 位置固定
 *
 * 内容不变
 *
 * 数量有限
 *
 * 三级缓存：返回View
 *
 * 按照position和type进行匹配
 * 直接返回View
 * 需要自己继承ViewCacheExtension实现
 * 位置固定，内容不发生改变的情况，比如说Header如果内容固定，就可以使用
 *
 * 作者：凯玲之恋
 * 链接：https://www.jianshu.com/p/0a928ae583fe
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * RecycledViewPool
 * * 按照Type来查找ViewHolder
 * * 每个Type默认最多缓存5个
 */
  public class DemoRvActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_rv);
        recyclerView = findViewById(R.id.rv_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new DemoAdapter();
        recyclerView.setAdapter(adapter);

        //viewType类型为TYPE_SPECIAL时，设置四级缓存池RecyclerPool不存储对应类型的数据 因为需要开发者自行缓存
        recyclerView.getRecycledViewPool().setMaxRecycledViews(DemoAdapter.TYPE_SPECIAL, 0);
        //设置ViewCacheExtension缓存
        recyclerView.setViewCacheExtension(new MyViewCacheExtension());
    }

    //实现自定义缓存ViewCacheExtension
    class MyViewCacheExtension extends RecyclerView.ViewCacheExtension {
        @Nullable
        @Override
        public View getViewForPositionAndType(@NonNull RecyclerView.Recycler recycler, int position, int viewType) {
            //如果viewType为TYPE_SPECIAL,使用自己缓存的View去构建ViewHolder
            // 否则返回null，会使用系统RecyclerPool缓存或者从新通过onCreateViewHolder构建View及ViewHolder
            return viewType == DemoAdapter.TYPE_SPECIAL ? adapter.caches.get(position) : null;
        }
    }
 }