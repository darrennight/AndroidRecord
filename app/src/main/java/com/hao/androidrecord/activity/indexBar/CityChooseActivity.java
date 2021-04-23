package com.hao.androidrecord.activity.indexBar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.hao.androidrecord.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: ProvinceChooseActivity 
 * @Description: 城市选择 
 * @author zenghao
 * @date 2015年4月2日 下午6:31:21 
 *
 */
public class CityChooseActivity extends AppCompatActivity {
	/**返回键*/
	private ImageView mBack = null;
	/**城市列表*/
	private PinnedListView mProvinceList = null;
	/***/
	private TextView mCover = null;
	/**侧边滑动块*/
	private TipScrollBar mTipScroolbar = null;
	/**无数据UI*/
	private LinearLayout mEmptyLayout = null;
	/**城市列表数据*/
	private List<CountryCode> mData = null;
	/**临时存储*/
	private List<CountryCode> mTemp = null;
	private CityChooseAdapter mAdapter = null;
	private SQLiteDatabase sql = null;
	
	public final String DB_CITY_PATH = PathUtility.getDBPath().getPath()+"/city";
    public final String DBCITYNAME = "country_province_city.db";
    public final String DB_CITY_FILE_PATH = DB_CITY_PATH+ File.separator+DBCITYNAME;
	
	private String mParentid = "";
	/**上一级*/
	private String mParentName = "";
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mData.clear();
			mData.addAll(mTemp);
			mAdapter.notifyDataSetChanged();
			
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_choose_activity);
		Intent intent = getIntent();
		mParentid = intent.getStringExtra("parentid");
		mParentName = intent.getStringExtra("name");
		mTemp = new ArrayList<CountryCode>();
		initView();
		initData();
		setListener();
	}
	/**
	 * 初始化相关UI
	 */
	private void initView(){
		mBack = (ImageView) this.findViewById(R.id.iv_city_choose_back);
		mProvinceList = (PinnedListView) this.findViewById(R.id.plv_city_choose);
		mCover = (TextView) this.findViewById(R.id.tv_city_cover);
		mTipScroolbar = (TipScrollBar) this.findViewById(R.id.tsb_city_scrollbar);
		mEmptyLayout = (LinearLayout) this.findViewById(R.id.ll_city_empty);
		
		mTipScroolbar.setVisibility(View.VISIBLE);
		mCover.setVisibility(View.VISIBLE);
		mProvinceList.setCoverTextView(mCover);
		mProvinceList.setTipScrollBar(mTipScroolbar);
		
	}
	/**
	 * 初始化相关数据
	 */
	private void initData(){
		mData = new ArrayList<CountryCode>();
		mAdapter = new CityChooseAdapter(this, mData);
		mProvinceList.setAdapter(mAdapter);
		new Thread(){
			@Override
			public void run() {
				queryCity();
				mHandler.sendEmptyMessage(1);
			};
		}.start();
		
	}
	/**
	 * 查询城市信息
	 */
	private void queryCity(){
		sql = SQLiteDatabase.openDatabase(DB_CITY_FILE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
		mTemp.clear();
		Cursor cursor = sql.query("city", new String[]{"name","codenum","firstletter"}, "parentid=?", new String[]{mParentid}, null, null, null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String codenum = cursor.getString(cursor.getColumnIndex("codenum"));
			String firstle = cursor.getString(cursor.getColumnIndex("firstletter"));
			
			CountryCode country = new CountryCode();
			country.setChName(name);
			country.setFirstLetter(firstle);
			country.setCountryCode(codenum);
			mTemp.add(country);
		}
		cursor.close();
	}
	/**
	 * 设置监听
	 */
	private void setListener(){
		mProvinceList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				String codeNum = mData.get(position).getCountryCode();
				Intent intent = new Intent();
				intent.putExtra("code", codeNum);
				intent.putExtra("name", mParentName+"_"+mData.get(position).getChName());
				setResult(RESULT_OK, intent);
				finish();
				
			}
		});
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
