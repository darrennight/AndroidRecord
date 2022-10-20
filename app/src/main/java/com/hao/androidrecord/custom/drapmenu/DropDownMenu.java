package com.hao.androidrecord.custom.drapmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hao.androidrecord.R;
import com.hao.androidrecord.util.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by dongjunkun on 2015/6/17.
 */
public class DropDownMenu extends LinearLayout {

    //顶部菜单布局
    private LinearLayout tabMenuView;
    //底部容器，包含popupMenuViews，maskView
    private FrameLayout containerView;
    //弹出菜单父布局
    private FrameLayout popupMenuViews;
    //遮罩半透明View，点击可关闭DropDownMenu
    private View maskView;
    //tabMenuView里面选中的tab位置，-1表示未选中
    private int current_tab_position = -1;

    //分割线颜色
    private int dividerColor = 0xffcccccc;
    //tab选中颜色
    private int textSelectedColor = 0xff890c85;
    //tab未选中颜色
    private int textUnselectedColor = 0xff111111;
    //遮罩颜色
    private int maskColor = 0x88888888;
    //tab字体大小
    private int menuTextSize = 14;

    //tab选中图标
    private int menuSelectedIcon;
    //tab未选中图标
    private int menuUnselectedIcon;

    private float menuHeighPercent = 0.5f;
    private int menuBackgroundColor = 0xffffffff;
    //tabcontain是否有drop
    private ArrayList<Integer> popPosition = new ArrayList();
    //没有drop选择的位置记录
    private ArrayList<String> checkSingle = new ArrayList();
    //有drop的选择的位置记录
    private ArrayList<String> needFocus = new ArrayList();

    private OnTabClickListener tabClickListener;
    private boolean multipleChoice = true;

    public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    public void setTabClickListener(OnTabClickListener tabClickListener) {
        this.tabClickListener = tabClickListener;
    }

    public void setNeedFocus(int position,boolean isNeed){
        if (isNeed){
            needFocus.add(String.valueOf(position));
            TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(position)).getChildAt(0);
            tabTextViewCheck.setTextColor(textSelectedColor);

        }else{
            needFocus.remove(String.valueOf(position));
            TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(position)).getChildAt(0);
            tabTextViewCheck.setTextColor(textUnselectedColor);
        }
    }

    public DropDownMenu(Context context) {
        this(context, null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);

        //为DropDownMenu添加自定义属性

        int underlineColor = 0xffcccccc;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        underlineColor = a.getColor(R.styleable.DropDownMenu_ddunderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.DropDownMenu_dddividerColor, dividerColor);
        textSelectedColor = a.getColor(R.styleable.DropDownMenu_ddtextSelectedColor, textSelectedColor);
        textUnselectedColor = a.getColor(R.styleable.DropDownMenu_ddtextUnselectedColor, textUnselectedColor);
        menuBackgroundColor = a.getColor(R.styleable.DropDownMenu_ddmenuBackgroundColor, menuBackgroundColor);
        maskColor = a.getColor(R.styleable.DropDownMenu_ddmaskColor, maskColor);
        menuTextSize = a.getDimensionPixelSize(R.styleable.DropDownMenu_ddmenuTextSize, menuTextSize);
        menuSelectedIcon = a.getResourceId(R.styleable.DropDownMenu_ddmenuSelectedIcon, menuSelectedIcon);
        menuUnselectedIcon = a.getResourceId(R.styleable.DropDownMenu_ddmenuUnselectedIcon, menuUnselectedIcon);
        menuHeighPercent = a.getFloat(R.styleable.DropDownMenu_ddmenuMenuHeightPercent,menuHeighPercent);
        a.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tabMenuView = (LinearLayout) getChildAt(0);
        tabMenuView.setBackgroundColor(menuBackgroundColor);

        containerView = (FrameLayout) getChildAt(1);
    }

    /**
     * 初始化DropDownMenu
     *
     * @param tabTexts
     * @param popupViews
     */
    public void setDropDownMenu(@NonNull List<String> tabTexts, @NonNull List<View> popupViews,ArrayList<Integer> popPosition) {

        this.popPosition.clear();
        this.popPosition.addAll(popPosition);

        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }

        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        containerView.addView(maskView, 1);
        maskView.setVisibility(GONE);
        if (containerView.getChildAt(2) != null){
            containerView.removeViewAt(2);
        }

        popupMenuViews = new FrameLayout(getContext());
        popupMenuViews.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (ViewUtils.INSTANCE.getScreenHeight(getContext())*menuHeighPercent)));
        popupMenuViews.setVisibility(GONE);
        containerView.addView(popupMenuViews, 2);

        for (int i = 0; i < popupViews.size(); i++) {
            popupViews.get(i).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            View popChild = popupViews.get(i);
            popChild.setVisibility(GONE);
            popupMenuViews.addView(popChild, i);
        }

    }

    /**
     * 获取tabView中id为tv_tab的textView
     *
     * @param tabView
     * @return
     */
    private TextView getTabTextView(View tabView) {
        TextView tabtext = (TextView) tabView.findViewById(R.id.tv_tab);
        return tabtext;
    }

    private void addTab(@NonNull List<String> tabTexts, int i) {

        final View tab = inflate(getContext(), R.layout.tab_item, null);
        tab.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        final TextView textView = getTabTextView(tab);
        textView.setText(tabTexts.get(i));
        textView.setTextColor(textUnselectedColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);

        int popv = popPosition.get(i);
        if(popv != -1 ){
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(menuUnselectedIcon), null);
        }



        //添加点击事件
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int popv = popPosition.get(i);
                if(popv >= 0){
                    if(checkSingle.contains(String.valueOf(current_tab_position))){
                        current_tab_position = -1;
                    }
                    switchMenu(i);
                }else{
                    switchNoDropMenu(i);
                }

            }
        });
        tabMenuView.addView(tab);


    }

    /**
     * 改变tab文字
     *
     * @param text
     */
    public void setTabText(String text) {
        if (current_tab_position != -1) {
            TextView tabTextView = (TextView)((ViewGroup)tabMenuView.getChildAt(current_tab_position)).getChildAt(0);
            tabTextView.setText(text);
        }
    }

    public void setTabClickable(boolean clickable) {
        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
            tabMenuView.getChildAt(i).setClickable(clickable);
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (current_tab_position != -1) {
            TextView tabTextView = (TextView)((ViewGroup)tabMenuView.getChildAt(current_tab_position)).getChildAt(0);
            if(needFocus.contains(String.valueOf(current_tab_position))){
                tabTextView.setTextColor(textSelectedColor);
            }else{
                tabTextView.setTextColor(textUnselectedColor);
            }

            tabTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(menuUnselectedIcon), null);

            popupMenuViews.setVisibility(View.GONE);
            popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
            int popvPre = popPosition.get(current_tab_position);
            if(popvPre != -1){
                popupMenuViews.getChildAt(popvPre).setVisibility(View.GONE);
            }
            maskView.setVisibility(GONE);
            maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
            current_tab_position = -1;
        }

    }

    /**
     * DropDownMenu是否处于可见状态
     *
     * @return
     */
    public boolean isShowing() {
        return current_tab_position != -1;
    }

    /**
     * 切换菜单
     *
     * @param
     */
    private void switchMenu(int position){

        if(current_tab_position == position){
            //点击同一个tab
            if(isShowing()){
                closeMenu();
            }

        }else{
            if(isShowing()){

                TextView tabTextView = (TextView)((ViewGroup)tabMenuView.getChildAt(current_tab_position)).getChildAt(0);
                if (!needFocus.contains(String.valueOf(current_tab_position))){
                    tabTextView.setTextColor(textUnselectedColor);
                }

                tabTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        getResources().getDrawable(menuUnselectedIcon), null);

                TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(position)).getChildAt(0);
                tabTextViewCheck.setTextColor(textSelectedColor);
                tabTextViewCheck.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        getResources().getDrawable(menuSelectedIcon), null);


                int popvPre = popPosition.get(current_tab_position);
                if(popvPre != -1){
                    popupMenuViews.getChildAt(popvPre).setVisibility(View.GONE);
                }

                int popv = popPosition.get(position);
                if(popv != -1){
                    popupMenuViews.getChildAt(popv).setVisibility(View.VISIBLE);
                }

                current_tab_position = position;

            }else{

                current_tab_position = position;
                TextView tabTextView = (TextView)((ViewGroup)tabMenuView.getChildAt(position)).getChildAt(0);
                tabTextView.setTextColor(textSelectedColor);
                tabTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        getResources().getDrawable(menuSelectedIcon), null);

                popupMenuViews.setVisibility(View.VISIBLE);
                popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));

                maskView.setVisibility(VISIBLE);
                maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));

                int popv = popPosition.get(position);
                if(popv != -1){
                    popupMenuViews.getChildAt(popv).setVisibility(View.VISIBLE);
                }


            }
        }
    }


    private void switchNoDropMenu(int i){
        if(current_tab_position == i){
            //取消
            TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(i)).getChildAt(0);
            tabTextViewCheck.setTextColor(textUnselectedColor);

            current_tab_position = -1;
            checkSingle.remove(String.valueOf(i));
            tabClickListener.onTabClick(checkSingle);
        }else{
            //选择

            if(multipleChoice){
                //多选
                if(checkSingle.contains(String.valueOf(i))){
                    //取消
                    checkSingle.remove(String.valueOf(i));
                    TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(i)).getChildAt(0);
                    tabTextViewCheck.setTextColor(textUnselectedColor);



                    if(isShowing() && popPosition.get(current_tab_position) != -1){
                        closeMenu();
                    }
                    if(checkSingle.isEmpty()){
                        current_tab_position = -1;
                    }
                    tabClickListener.onTabClick(checkSingle);
                }else{
                    //选择
                    checkSingle.add(String.valueOf(i));
                    TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(i)).getChildAt(0);
                    tabTextViewCheck.setTextColor(textSelectedColor);

                    if(isShowing() && popPosition.get(current_tab_position) != -1){
                        closeMenu();
                    }
                    current_tab_position = i;
                    tabClickListener.onTabClick(checkSingle);
                }


            }else{
                //单选

                if(checkSingle.contains(String.valueOf(i))){
                    checkSingle.clear();
                    TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(i)).getChildAt(0);
                    tabTextViewCheck.setTextColor(textUnselectedColor);

                    closeMenu();
                    current_tab_position = -1;
                    tabClickListener.onTabClick(checkSingle);
                }else{
                    if(!checkSingle.isEmpty()){

                        if (current_tab_position!=-1){
                            TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(current_tab_position)).getChildAt(0);
                            tabTextViewCheck.setTextColor(textUnselectedColor);
                            int dropPos = popPosition.get(current_tab_position);
                            if(dropPos != -1){
                                tabTextViewCheck.setCompoundDrawablesWithIntrinsicBounds(null, null,
                                        getResources().getDrawable(menuUnselectedIcon), null);
                            }

                        }


                        int checkPos = Integer.parseInt(checkSingle.get(0));
                        if(checkPos != current_tab_position){
                            TextView tabTextViewCheck1 = (TextView)((ViewGroup)tabMenuView.getChildAt(checkPos)).getChildAt(0);
                            tabTextViewCheck1.setTextColor(textUnselectedColor);
                        }


                        checkSingle.clear();
                    }
                    checkSingle.add(String.valueOf(i));
                    TextView tabTextViewCheck = (TextView)((ViewGroup)tabMenuView.getChildAt(i)).getChildAt(0);
                    tabTextViewCheck.setTextColor(textSelectedColor);

                    if(isShowing() && popPosition.get(current_tab_position) != -1){
                        closeMenu();
                    }
                    current_tab_position = i;
                    tabClickListener.onTabClick(checkSingle);
                }




            }


        }
    }



    public interface OnTabClickListener{
         void onTabClick(ArrayList<String> list);
    }
}
