package com.hao.androidrecord.activity.bottomsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hao.androidrecord.R;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity {

   //底部抽屉中的自定义布局
   private RecyclerView recycler_view;
   //抽屉的主view
   private View bottomSheet;
   //抽屉view的id
   private LinearLayout chou_ti_id;

   private BottomSheetBehavior<View> behavior;
   private List<String> carList = new ArrayList<>();



   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_coordinator);
      //设置抽屉最大高度
      chou_ti_id = findViewById(R.id.chou_ti_id);
      ViewGroup.LayoutParams para;
      para = chou_ti_id.getLayoutParams();
      int height1 = (int) (getResources().getDisplayMetrics().heightPixels * 0.7);
      para.height=height1;
      chou_ti_id.setLayoutParams(para);
      //底部抽屉栏展示地址
      bottomSheet = findViewById(R.id.bottom_sheet);
      behavior = BottomSheetBehavior.from(bottomSheet);
      int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.2);
      behavior.setPeekHeight(height);
      // bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); 设置展开折叠的默认状态
      behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
         @Override
         public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
            String state = "null";
            switch (newState) {
               case 1:
                  state = "STATE_DRAGGING";//过渡状态此时用户正在向上或者向下拖动bottom sheet

                  break;
               case 2:
                  state = "STATE_SETTLING"; // 视图从脱离手指自由滑动到最终停下的这一小段时间
                  break;
               case 3:
                  state = "STATE_EXPANDED"; //处于完全展开的状态

                  break;
               case 4:
                  state = "STATE_COLLAPSED"; //默认的折叠状态
                  break;
               case 5:
                  state = "STATE_HIDDEN"; //下滑动完全隐藏 bottom sheet
                  break;
            }

         }

         //实时返回滚动数据
         @Override
         public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Log.d("BottomSheetDemo", "slideOffset:" + slideOffset);
         }
      });

      //用于测试的列表数据
      recycler_view = findViewById(R.id.recycler_view);
      List<String> strList = new ArrayList<>();
      for(int i = 0;i<100;i++){
         strList.add("fffff"+i);
      }

      LinearLayoutManager layoutManager = new LinearLayoutManager(this);
      recycler_view.setLayoutManager(layoutManager);
      CoListAdapter adapter = new CoListAdapter(strList, this);
      recycler_view.setAdapter(adapter);
      adapter.setOnItemClickListener(postion -> {
         String chepaihao = carList.get(postion)+"";

         Log.e("点击", "onItemLongClick: ");

      });
   }
}