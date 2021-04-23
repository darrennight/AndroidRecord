package com.hao.androidrecord.activity.indexBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hao.androidrecord.R;

import java.util.List;

/**
 * @ClassName: CountryChooseAdapter 
 * @Description: 国家选择适配器
 * @author zenghao
 * @date 2015年4月2日 下午4:55:12 
 *
 */
public class CountryChooseAdapter extends BaseAdapter {
	
	private Context mContext = null;
	private List<CountryCode> mList = null;
	
	public CountryChooseAdapter(Context context, List<CountryCode> list){
			this.mContext = context;
			this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.country_choose_item, parent, false);
			holder.mCountry = (TextView) convertView.findViewById(R.id.tv_country_name);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		if(position == 0){
			holder.mCountry.setTextColor(mContext.getResources().getColor(R.color.color_4abdcc));
		}else{
			holder.mCountry.setTextColor(mContext.getResources().getColor(R.color.tracksNotes));
		}
		holder.mCountry.setText(mList.get(position).getChName());
		return convertView;
	}
	
	class ViewHolder{
		TextView mCountry;
	}

}
