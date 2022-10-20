package com.hao.androidrecord.custom.drapmenu;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.dropmenu.ConstellationAdapter;
import com.hao.androidrecord.custom.drapmenu.group.ColorDividerItemDecoration;
import com.hao.androidrecord.custom.drapmenu.group.RecyTest3Adapter;
import com.hao.androidrecord.custom.drapmenu.group.RecyTestAdapter;
import com.hao.androidrecord.custom.flow.FlowLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//https://github.com/dongjunkun/DropDownMenu
public class DropMenuActivity extends AppCompatActivity {

    DropDownMenu mDropDownMenu;
    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_menu);
        mDropDownMenu = findViewById(R.id.dropDownMenu);
        initView();

        recyclerView = findViewById(R.id.rv_test_dec);

       /* LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new ColorDividerItemDecoration());

        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<=20;i++){
            list.add(""+i);
        }
        recyclerView.setAdapter(new RecyTestAdapter(this,list));*/

        recyclerView.setLayoutManager(new FlowLayoutManager());
        ArrayList<TestData> list = new ArrayList<>();
        list.add(new TestData("连载",true));
        list.add(new TestData("连载中",false));
        list.add(new TestData("已完结",false));

        list.add(new TestData("价格",true));
        list.add(new TestData("免费",false));
        list.add(new TestData("会员",false));
        list.add(new TestData("付费",false));

        list.add(new TestData("更新",true));
        list.add(new TestData("3天内",false));
        list.add(new TestData("7天内",false));
        list.add(new TestData("15天内",false));
        list.add(new TestData("最近一个月",false));


        list.add(new TestData("字数",true));
        list.add(new TestData("20万字以内",false));
        list.add(new TestData("20～50万字",false));
        list.add(new TestData("50～100万字",false));
        list.add(new TestData("100～200万字",false));
        list.add(new TestData("200万字以上",false));

        recyclerView.setAdapter(new RecyTest3Adapter(this,list));

    }

    private void initView() {
         ArrayList<Integer> popPosition = new ArrayList();

        //init city menu
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);



        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation 自定义
        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = constellationView.findViewById(R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = constellationView.findViewById( R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popPosition.add(0);

        popupViews.add(ageView);
        popupViews.add(sexView);
        popPosition.add(1);
        popPosition.add(2);
//        popPosition.add(-1);
//        popPosition.add(-1);
        //自定义
        popupViews.add(constellationView);
        popPosition.add(3);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                if(position == 0){
                    mDropDownMenu.setNeedFocus(0,false);
                }else{
                    mDropDownMenu.setNeedFocus(0,true);
                }

                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        mDropDownMenu.setTabClickListener(new DropDownMenu.OnTabClickListener() {
            @Override
            public void onTabClick(ArrayList<String> list) {
                Log.e("======list",""+list);
            }
        });
        //init dropdownview
        mDropDownMenu.setMultipleChoice(true);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,popPosition);
    }

//    @Override
//    public void onBackPressed() {
//        //退出activity前关闭菜单
//        if (mDropDownMenu.isShowing()) {
//            mDropDownMenu.closeMenu();
//        } else {
//            super.onBackPressed();
//        }
//    }

    public class TestData{
        public String des;
        public boolean title;

        public TestData(String des, boolean title) {
            this.des = des;
            this.title = title;
        }
    }
}
