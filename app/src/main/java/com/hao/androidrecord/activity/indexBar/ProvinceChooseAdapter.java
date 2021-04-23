package com.hao.androidrecord.activity.indexBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.hao.androidrecord.R;

import java.util.List;

/**
 * 省份选择适配器
 * @ClassName: ProvinceChooseAdapter 
 *  @Description:
 * @author zenghao
 * @date 2015年4月3日 下午3:40:08 
 *
 */
public class ProvinceChooseAdapter extends BaseAdapter implements SectionIndexer {

	private Context mContext = null;
	private List<CountryCode> mList = null;
	public ProvinceChooseAdapter(Context context, List<CountryCode> list) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.province_choose_item, parent, false);
			holder.mFirstLetter = (TextView) convertView.findViewById(R.id.tv_province_first_letter);
			holder.mProvinceName = (TextView) convertView.findViewById(R.id.tv_province_name);
			holder.splitLine = convertView.findViewById(R.id.view_split_line);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		CountryCode item = (CountryCode) getItem(position);
		holder.mFirstLetter.setText(item.getFirstLetter());
		holder.mProvinceName.setText(item.getChName());


		int section = getSectionForPosition(position);
		if(position == getPositionForSection(section)){
			item.setFirst(true);
			holder.mFirstLetter.setVisibility(View.VISIBLE);
			if (position > 0){
				holder.splitLine.setVisibility(View.VISIBLE);
			}else {
				holder.splitLine.setVisibility(View.GONE);
			}
		}else{
			holder.mFirstLetter.setVisibility(View.INVISIBLE);
			holder.splitLine.setVisibility(View.GONE);
		}

		
		return convertView;
	}
	
	class ViewHolder{
		TextView mFirstLetter;
		TextView mProvinceName;
		View splitLine;
	}

	@Override
	public Object[] getSections() {
		return null;
	}
	@Override
	public int getPositionForSection(int section) {
		for(int i=0; i<getCount();i++){
			String fletter = mList.get(i).getFirstLetter();
			char fl = fletter.charAt(0);
			if(fl == section){
				return i;
			}
		}
		return -1;
	}
	@Override
	public int getSectionForPosition(int position) {
		return mList.get(position).getFirstLetter().charAt(0);
	}

}
