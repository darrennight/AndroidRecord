package com.hao.androidrecord.custom.picker.adapters;

import android.content.Context;

import com.hao.androidrecord.custom.picker.view.WheelAdapter;


/**
 * Adapter class for old wheel2 adapter (deprecated WheelAdapter class).
 * 
 * @deprecated Will be removed soon
 */
public class AdapterWheel extends AbstractWheelTextAdapter {

    // Source adapter
    private WheelAdapter adapter;
    
    /**
     * Constructor
     * @param context the current context
     * @param adapter the source adapter
     */
    public AdapterWheel(Context context, WheelAdapter adapter) {
        super(context);
        
        this.adapter = adapter;
    }

    /**
     * Gets original adapter
     * @return the original adapter
     */
    public WheelAdapter getAdapter() {
        return adapter;
    }
    
    @Override
    public int getItemsCount() {
        return adapter.getItemsCount();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return adapter.getItem(index);
    }

}