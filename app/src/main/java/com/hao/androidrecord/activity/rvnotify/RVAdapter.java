package com.hao.androidrecord.activity.rvnotify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hao.androidrecord.R;

import java.util.List;

/**
 * author: kun .
 * date:   On 2019/7/25
 */
public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private List<RVData> mDataList;
private Context mContext;

public RVAdapter(List<RVData> dataList, Context context) {
    mDataList = dataList;
    mContext = context;
}

@NonNull
@Override
public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view= LayoutInflater.from(mContext).inflate(R.layout.rv_item_layout,null);
    return new RVHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        //super.onBindViewHolder(holder, position, payloads);
        //bt_notify_5点击
        if (payloads.isEmpty()){
            onBindViewHolder(holder, position);
        }else{
            if(holder instanceof RVHolder){
                RVData data = (RVData) payloads.get(0);
                ((RVHolder) holder).mTvName.setText(data.getName());
                ((RVHolder) holder).mTvDes.setText(data.getDesc());
                Glide.with(mContext).load(data.getImage()).placeholder(R.mipmap.ic_launcher).into(((RVHolder) holder).mImageView);
            }
        }
    }

    @Override
public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
    Log.e("========holder",""+position);
    if(viewHolder instanceof RVHolder){
        final RVData data = mDataList.get(position);
        ((RVHolder) viewHolder).mTvName.setText(data.getName());
        ((RVHolder) viewHolder).mTvDes.setText(data.getDesc());
        Glide.with(mContext).load(data.getImage()).placeholder(R.mipmap.ic_launcher).into(((RVHolder) viewHolder).mImageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "name:"+data.getName()+" pos:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

@Override
public int getItemCount() {
    return mDataList.size();
}

class RVHolder extends RecyclerView.ViewHolder{
    ImageView mImageView;
    TextView mTvName,mTvDes;
    public RVHolder(@NonNull View itemView) {
        super(itemView);
        mImageView=itemView.findViewById(R.id.iv_image);
        mTvName=itemView.findViewById(R.id.tv_name);
        mTvDes=itemView.findViewById(R.id.tv_des);
    }
}
}