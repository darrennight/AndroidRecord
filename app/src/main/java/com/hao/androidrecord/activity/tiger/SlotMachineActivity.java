package com.hao.androidrecord.activity.tiger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.hao.androidrecord.R;
import com.hao.androidrecord.activity.tiger.view.SlotMachineView;

import static com.hao.androidrecord.activity.tiger.view.SlotMachineView.INDEX_COIN;


/**
 * Created by ethan on 2019/12/10
 * explain:
 * https://github.com/wuyajun7/SlotMachineView
 */
public class SlotMachineActivity extends Activity {

    private SlotMachineView slotMachineView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_slot_machine);

        slotMachineView = findViewById(R.id.sm_view);
        slotMachineView.setOnScrollListener(new SlotMachineView.OnScrollListener() {
            @Override
            public void onStop(int bingoIndex) {

            }
        });

        findViewById(R.id.btn_mix).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                slotMachineView.setBingoIndex(INDEX_COIN);
                slotMachineView.startSm();
            }
        });
    }
}
