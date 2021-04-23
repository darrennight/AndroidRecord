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
 * @Description: 省份选择 
 * @author zenghao
 * @date 2015年4月2日 下午6:31:21 
 *
 */
public class ProvinceChooseActivity extends AppCompatActivity {
	/**返回键*/
	private ImageView mBack = null;
	/**省份列表*/
	private PinnedListView mProvinceList = null;
	/**首字母显示*/
	private TextView mCover = null;
	/**侧边滑动块*/
	private TipScrollBar mTipScroolbar = null;
	/**无数据UI*/
	private LinearLayout mEmptyLayout = null;
	/**列表数据*/
	private List<CountryCode> mData = null;
	/**临时集合*/
	private List<CountryCode> mTemp = null;
	private ProvinceChooseAdapter mAdapter = null;
	private SQLiteDatabase sql = null;

	/**上级传递*/
	private String parentid  = "";
	/**上级name*/
	private String mParentName = "";
	/**选择的*/
	private String chosenParentid;
	private final String DB_CITY_PATH = PathUtility.getDBPath().getPath()+"/city";
    private final String DBCITYNAME = "country_province_city.db";
    private final String DB_CITY_FILE_PATH = DB_CITY_PATH+ File.separator+DBCITYNAME;
    /**结果码*/
    private final int GOBACKRESULTCODE = 201;
    /**请求码*/
    private final int GOCITYREQUESTCODE = 202;
    /**是否是中国*/
    private final String mChina = "中国";
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
		setContentView(R.layout.province_choose_activity);
		Intent intent = getIntent();
		parentid = intent.getStringExtra("parentid");
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
		mBack = (ImageView) this.findViewById(R.id.iv_province_choose_back);
		mProvinceList = (PinnedListView) this.findViewById(R.id.plv_provinec_choose);
		mCover = (TextView) this.findViewById(R.id.tv_provinec_cover);
		mTipScroolbar = (TipScrollBar) this.findViewById(R.id.tsb_province_scrollbar);
		mEmptyLayout = (LinearLayout) this.findViewById(R.id.ll_provinec_empty);
		
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
		mAdapter = new ProvinceChooseAdapter(this, mData);
		mProvinceList.setAdapter(mAdapter);
		new Thread(){
			@Override
			public void run() {
				queryProvivce();
				mHandler.sendEmptyMessage(1);
			};
		}.start();
	}
	/**
	 * 查询省份数据
	 */
	private void queryProvivce(){
		sql = SQLiteDatabase.openDatabase(DB_CITY_FILE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
		mTemp.clear();
		Cursor cursor = sql.query("city", new String[]{"name","codenum","parentid","firstletter"}, "parentid=?",new String[]{parentid}, null, null, null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String codenum = cursor.getString(cursor.getColumnIndex("codenum"));
			String parentid = cursor.getString(cursor.getColumnIndex("parentid"));
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
				 chosenParentid = mData.get(position).getCountryCode();
				//此情况由于数据库设计parentid和东京的ID相等需要特别处理
				 if("东京".equalsIgnoreCase(mData.get(position).getChName())){
					//没有下一级城市
						Intent intent = new Intent();
						intent.putExtra("code", chosenParentid);
						intent.putExtra("name", mParentName+"_"+mData.get(position).getChName());
						setResult(RESULT_OK, intent);
						finish();
					 return ;
				 }
				Cursor cursor = sql.query("city", new String[]{"name"}, "parentid=?", new String[]{chosenParentid}, null, null, null);
				if(cursor.getCount()>0){
					//还有下一级城市
					Intent intent = new Intent(ProvinceChooseActivity.this,CityChooseActivity.class);
					intent.putExtra("parentid", chosenParentid);
					//中国和其他国家显示格式不一样
					if(mParentName.contains(mChina)){
						intent.putExtra("name", mData.get(position).getChName());
					}else{
						intent.putExtra("name", mParentName);
					}
					
					startActivityForResult(intent, GOCITYREQUESTCODE);

				}else{
					//没有下一级城市
					Intent intent = new Intent();
					intent.putExtra("code", chosenParentid);
					intent.putExtra("name", mParentName+"_"+mData.get(position).getChName());
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
		if(resultCode == RESULT_OK){
			Intent intent = new Intent();
			intent.putExtra("code", data.getStringExtra("code"));
			intent.putExtra("name", data.getStringExtra("name"));
			setResult(RESULT_OK, intent);
			finish();
		}
	}
	
}
