package com.hao.androidrecord.activity.pinned2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.pinned2.adapter.RecyclerViewAdapter;
import com.hao.androidrecord.activity.pinned2.adapter.StockAdapter;
import com.hao.androidrecord.activity.pinned2.callback.OnItemClickListener;
import com.hao.androidrecord.activity.pinned2.entity.StickyHeadEntity;
import com.hao.androidrecord.activity.pinned2.entity.StockEntity;
import com.hao.androidrecord.activity.pinned2.lib.OnStickyChangeListener;
import com.hao.androidrecord.activity.pinned2.lib.StickyHeadContainer;
import com.hao.androidrecord.activity.pinned2.lib.StickyItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StockAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                initView();
            }

            @Override
            protected String doInBackground(Void... voids) {
                return MainPinned2Activity.getStrFromAssets(SecondActivity.this, "rasking.json");
            }

            @Override
            protected void onPostExecute(String result) {
                parseAndSetData(result);
            }

        }.execute();

    }

    private void initView() {

        final StickyHeadContainer container = (StickyHeadContainer) findViewById(R.id.shc);
        final TextView tvStockName = (TextView) container.findViewById(R.id.tv_stock_name);
        container.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                StockEntity.StockInfo item = mAdapter.getData().get(pos).getData();
                tvStockName.setText(item.stickyHeadName);
            }
        });
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "点击了粘性头部：" + tvStockName.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        final StickyItemDecoration stickyItemDecoration = new StickyItemDecoration(container, RecyclerViewAdapter.TYPE_SMALL_STICKY_HEAD_WITH_DATA);

        stickyItemDecoration.setOnStickyChangeListener(new OnStickyChangeListener() {
            @Override
            public void onScrollable(int offset) {
                container.scrollChild(offset);
                container.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInVisible() {
                container.reset();
                container.setVisibility(View.INVISIBLE);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(stickyItemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(stickyItemDecoration);
        mRecyclerView.addItemDecoration(new MainPinned2Activity.SpaceItemDecoration(mRecyclerView.getContext()));

        mAdapter = new StockAdapter(null);
        mAdapter.setItemClickListener(new OnItemClickListener<StockEntity.StockInfo>() {
            @Override
            public void onItemClick(View view, StockEntity.StockInfo data, int position) {
                Toast.makeText(SecondActivity.this, "点击了Item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseAndSetData(String result) {
        Gson gson = new Gson();

        final StockEntity stockEntity = gson.fromJson(result, StockEntity.class);

        List<StockEntity.StockInfo> data = new ArrayList<>();

        int count = 0;
        for (StockEntity.StockInfo info : stockEntity.increase_list) {
            if (count == 0) {
                info.stickyHeadName = "涨幅榜";
                info.setItemType(RecyclerViewAdapter.TYPE_SMALL_STICKY_HEAD_WITH_DATA);
            } else {
                info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            }
            data.add(info);
            count++;
        }

        count = 0;
        for (StockEntity.StockInfo info : stockEntity.down_list) {
            if (count == 0) {
                info.stickyHeadName = "跌幅榜";
                info.setItemType(RecyclerViewAdapter.TYPE_SMALL_STICKY_HEAD_WITH_DATA);
            } else {
                info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            }
            data.add(info);
            count++;
        }

        count = 0;
        for (StockEntity.StockInfo info : stockEntity.change_list) {
            if (count == 0) {
                info.stickyHeadName = "换手率";
                info.setItemType(RecyclerViewAdapter.TYPE_SMALL_STICKY_HEAD_WITH_DATA);
            } else {
                info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            }
            data.add(info);
            count++;
        }

        count = 0;
        for (StockEntity.StockInfo info : stockEntity.amplitude_list) {
            if (count == 0) {
                info.stickyHeadName = "振幅榜";
                info.setItemType(RecyclerViewAdapter.TYPE_SMALL_STICKY_HEAD_WITH_DATA);
            } else {
                info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            }
            data.add(info);
            count++;
        }

        List<StickyHeadEntity<StockEntity.StockInfo>> list = new ArrayList<>(data.size());
        for (StockEntity.StockInfo info : data) {
            list.add(new StickyHeadEntity<>(info, info.getItemType(), info.stickyHeadName));
        }

        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);

    }

}
