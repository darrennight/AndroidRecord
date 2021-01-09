package com.hao.androidrecord.activity.StickyHeaderViewPager;

import android.widget.ArrayAdapter;

import com.hao.androidrecord.custom.StickyHeaderViewPager.StickHeaderListFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewSimpleFragment extends StickHeaderListFragment {

    public static ListViewSimpleFragment newInstance() {
        ListViewSimpleFragment fragment = new ListViewSimpleFragment();
        return fragment;
    }

    public static ListViewSimpleFragment newInstance(String title) {
        ListViewSimpleFragment fragment = new ListViewSimpleFragment();
        fragment.setTitle(title);
        return fragment;
    }

    @Override
    public void bindData() {
        setAdapter();
    }

    public void setAdapter() {
        if (getActivity() == null) return;

        int size = 100;
        String[] stringArray = new String[size];
        for (int i = 0; i < size; ++i) {
            stringArray[i] = ""+i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stringArray);

        getScrollView().setAdapter(adapter);
    }
}