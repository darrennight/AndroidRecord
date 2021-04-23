package com.hao.androidrecord.activity.indexBar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.hao.androidrecord.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: CountryChooseActivity
 * @Description:国家选择界面
 * @author zenghao
 * @date 2015年4月2日 上午10:41:52
 *
 */
public class CountryChooseActivity extends AppCompatActivity {
	/** 国家列表 */
	private ListView lv_country = null;
	/** 国家数据列表集合 */
	private List<CountryCode> mData = null;
	/** 国家适配器 */
	private CountryChooseAdapter mAdapter = null;
	private SQLiteDatabase sql = null;
	/** 临时存储国家数据 */
	private List<CountryCode> mTemp = null;

	/** 返回键 */
	private ImageView mBack = null;
	/** 当前定位位置 */
	private TextView mCurrentPosition = null;
	private ImageView ivGetLocation;
	public final String DB_CITY_PATH = PathUtility.getDBPath().getPath() + "/city";
	public final String DBCITYNAME = "country_province_city.db";
	public final String DB_CITY_FILE_PATH = DB_CITY_PATH + File.separator + DBCITYNAME;
	private String parentid;
	/** 跳转到省份请求码 */
	private final int TOPROVINCEREQUESTCODE = 101;

	/** 当前定位到的地区ID */
	private String mRegionID = "";
	/** 当前定位到的地区NAME */
	private String mRegionName = "";
	/** 自是否注册了定位广播标志 防止其他地方发送定位广播影响 */
	private boolean isRegist = false;
	/** 定位请求code */
	private final int mRequestCode = 11;
	/**默认已选择的国家*/
	private String mDefaultSelected = "中国";
	private String mSelected = "";
	private Handler mHandler = new Handler() {

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
		setContentView(R.layout.country_choose_activity);
		mTemp = new ArrayList<CountryCode>();

		Intent intnet = getIntent();
		mSelected = intnet.getStringExtra("name");
		initView();
		initData();
		setListener();

	}

	/**
	 * 初始化UI
	 */
	private void initView() {
		mCurrentPosition = (TextView) this.findViewById(R.id.tv_country_current_positon);
		ivGetLocation = (ImageView) this.findViewById(R.id.ivGetLocation);
		lv_country = (ListView) this.findViewById(R.id.lv_country_shoose);
		mBack = (ImageView) this.findViewById(R.id.iv_country_choose_back);
	}

	/**
	 * 初始化相关数据
	 */
	private void initData() {

		mData = new ArrayList<CountryCode>();
		mAdapter = new CountryChooseAdapter(this, mData);
		lv_country.setAdapter(mAdapter);

		final File file = new File(PathUtility.DB_CITY_FILE_PATH);
		new Thread() {
			@Override
			public void run() {
				if (!file.exists()) {
					PathUtility.copyCityDataToSdcard(CountryChooseActivity.this);
				}
				sql = SQLiteDatabase.openDatabase(DB_CITY_FILE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
				queryCountry();
				mHandler.sendEmptyMessage(1);
			};
		}.start();

	}



	/**
	 * 查询country数据
	 */
	private void queryCountry() {
		mSelected = mDefaultSelected;
		if(!mDefaultSelected.equalsIgnoreCase(mSelected)){
			getSelectedCountry(mSelected);
		}
		
		// 国家的parentID默认为1
		Cursor cursor = sql.query("city", new String[] { "name", "codenum", "parentid", "firstletter" }, "parentid=?",
				new String[] { "1" }, null, null, null);
		mTemp.clear();
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String codenum = cursor.getString(cursor.getColumnIndex("codenum"));

			CountryCode country = new CountryCode();
			country.setChName(name);
			country.setCountryCode(codenum);
			if(name.equalsIgnoreCase(mSelected)){
				mTemp.add(0,country);
			}else{
				mTemp.add(country);
			}
			
		}
		cursor.close();

	}
	
	/**
	 * 获取上次选择的城市
	 * 国外港澳台直接根据一级名
	 * 国内根据省份获取国家
	 */
	private void getSelectedCountry(String selected){
		Cursor cursor = sql.query("city", new String[]{"name","parentid"}, "name=?", new String[]{selected}, null, null, null);
		if(cursor.moveToNext()){
			String pID = cursor.getString(cursor.getColumnIndex("parentid"));
			//国家默认parentid＝＝1
			if(pID.equalsIgnoreCase("1")){
				//国外港澳台
			}else{
				//国内
				mSelected = mDefaultSelected;
			}
		
		}
		cursor.close();
	}

	/**
	 * 设置监听
	 */
	private void setListener() {
		lv_country.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				parentid = mData.get(position).getCountryCode();
				Cursor cursor = sql.query("city", new String[] { "name" }, "parentid=?", new String[] { parentid }, null, null, null);
				if (cursor.getCount() > 0) {
					// 还有下一级城市
					Intent intent = new Intent(CountryChooseActivity.this, ProvinceChooseActivity.class);
					intent.putExtra("parentid", parentid);
					intent.putExtra("name", mData.get(position).getChName());
					startActivityForResult(intent, TOPROVINCEREQUESTCODE);
				} else {
					// 没有下一级城市
					Intent intent = new Intent();
					intent.putExtra("code", parentid);
					intent.putExtra("name", mData.get(position).getChName());
					setResult(RESULT_OK, intent);
					finish();
				}

			}
		});
		mCurrentPosition.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!TextUtils.isEmpty(mRegionName)){
					mRegionName = mRegionName.replace("_", " ");
					Intent intent = new Intent();
					intent.putExtra("code", mRegionID);
					intent.putExtra("name", mRegionName);
					setResult(RESULT_OK, intent);
					finish();

				}
				
			}
		});
		mBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Intent intent = new Intent();
			intent.putExtra("code", data.getStringExtra("code"));
			intent.putExtra("name", data.getStringExtra("name"));
			setResult(RESULT_OK, intent);
			finish();
		}
	}



}
