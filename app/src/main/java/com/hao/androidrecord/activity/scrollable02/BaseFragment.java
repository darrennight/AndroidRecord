package com.hao.androidrecord.activity.scrollable02;


import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements ScrollableHelper.ScrollableContainer{

    public abstract void pullToRefresh();
    public abstract void refreshComplete();
}
