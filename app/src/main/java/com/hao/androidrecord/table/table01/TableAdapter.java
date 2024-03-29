package com.hao.androidrecord.table.table01;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ItemViewHolder> {

	public interface OnItemClickListener {

		void onItemClick(int position);
	}

	private Context             mContext;
	private List<String>        mDataList;
	private OnItemClickListener mListener;

	public TableAdapter(Context context, List<String> dataList) {
		mContext = context;
		mDataList = dataList;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mListener = listener;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_table_content, parent, false));
	}

	@Override
	public void onBindViewHolder(final ItemViewHolder holder, int position) {
		holder.mTextItem.setText(mDataList.get(position));
		if (position / TableActivity.COLUMN_COUNT % 2 == 0) {
			holder.mTextItem.setBackgroundColor(mContext.getResources().getColor(R.color.colorEven));
		} else {
			holder.mTextItem.setBackgroundColor(mContext.getResources().getColor(R.color.colorOdd));
		}
		holder.mTextItem.setTag(position);
		holder.mTextItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onItemClick((Integer) holder.mTextItem.getTag());
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return mDataList == null ? 0 : mDataList.size();
	}

	static class ItemViewHolder extends RecyclerView.ViewHolder {

		TextView mTextItem;

		ItemViewHolder(View itemView) {
			super(itemView);
			mTextItem = itemView.findViewById(R.id.text_content_item);
		}
	}

}
