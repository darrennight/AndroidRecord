package com.hao.androidrecord.table.table01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;

import java.util.ArrayList;
import java.util.List;

//https://www.jianshu.com/p/e4c08c52145e
//https://github.com/tuacy/LayoutManagerDemo
public class TableActivity extends AppCompatActivity {

	public static void startUp(Context context) {
		context.startActivity(new Intent(context, TableActivity.class));
	}

	public static final int COLUMN_COUNT = 10;

	private Context      mContext;
	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table01);
		mContext = this;
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mRecyclerView = findViewById(R.id.recycler_table);
		TableLayoutManager layoutManager = new TableLayoutManager.Build(mContext).setColumnCount(COLUMN_COUNT)
																				 .setFixColumnCount(1)
																				 .setFixHeader(true)
																				 .setHeadHeight(DensityUtils.dp2px(mContext, 32))
																				 .setRowHeight(DensityUtils.dp2px(mContext, 48))
																				 .setWidgetCount(3)
																				 .build();
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerView.addItemDecoration(new TableItemDecoration(mContext));
	}

	private void initEvent() {

	}

	private void initData() {
		TableAdapter adapter = new TableAdapter(mContext, obtainDataList());
		mRecyclerView.setAdapter(adapter);
		adapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position) {
				Log.d("tuacy", "position = " + position);
			}
		});
	}

	private List<String> obtainDataList() {
		List<String> dataList = new ArrayList<>();
		for (int column = 0; column < COLUMN_COUNT; column++) {
			if (column == 0) {
				dataList.add("身高/体重");
			} else {
				dataList.add(String.valueOf(152 + (column - 1) * 4) + "cm");
			}
		}
		for (int row = 0; row < 60; row++) {
			for (int column = 0; column < COLUMN_COUNT; column++) {
				if (column == 0) {
					dataList.add(String.valueOf(19 + row) + "岁");
				} else {
					dataList.add(String.valueOf(50 + row + (column - 1) * 2));
				}
			}
		}
		return dataList;
	}
}
