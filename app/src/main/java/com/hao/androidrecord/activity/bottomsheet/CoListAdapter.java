package com.hao.androidrecord.activity.bottomsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hao.androidrecord.R;

import java.util.List;

public class CoListAdapter extends RecyclerView.Adapter<CoListAdapter.ViewHolder>{
   private List<String> mOrderList;
   private Context mContext;

   //初始化list_item组件
   static class ViewHolder extends RecyclerView.ViewHolder {
      View fruitView;
      TextView chepaihao;
      public ViewHolder(View view) {
         super(view);
         fruitView = view;
         chepaihao = view.findViewById(R.id.chepaihao);//车牌号
         /*   ZhuangTai = view.findViewById(R.id.ZhuangTai);//车辆审核状态*/
      }
   }
   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.car_list_item, parent, false);
      final ViewHolder holder = new ViewHolder(view);
      return holder;
   }
   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      String cph = mOrderList.get(position);
      holder.chepaihao.setText(cph);
      /*holder.ZhuangTai.setText(cph.drivrName);*/


   }
   @Override
   public int getItemCount() {
      return mOrderList.size();
   }
   public CoListAdapter(List OrderReceivedList, Context context) {
      mOrderList=OrderReceivedList;
      this.mContext =context;
   }
   private String getStatus(int state){
      String stateStr = "";
      switch (state){
         case 1:
            stateStr = "待核查";
            break;
         case 2:
            stateStr = "核查通过";
            break;
         default:
            stateStr = "待核查";
            break;
      }
      return stateStr;
   }
   //点击回到多态实现
   OnItemClickListener mClickListener;
   public interface OnItemClickListener {
      void onItemClick(int postion);
   }
   public void setOnItemClickListener(OnItemClickListener listener) {
      this.mClickListener = listener;
   }
}