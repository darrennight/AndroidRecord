package com.hao.androidrecord.activity.DragPanelLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hao.androidrecord.R;


public class DragMainActivity extends AppCompatActivity {

	private DragPanelLayout mDragPanelLayout;
//	private Toolbar mToolbar;

	@SuppressWarnings("ConstantConditions")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_drag);

//		mToolbar = (Toolbar) findViewById(R.id.toolbar);
//		setSupportActionBar(mToolbar);
//		getSupportActionBar().setHomeButtonEnabled(true);
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);

		mDragPanelLayout = (DragPanelLayout) findViewById(R.id.dpl);

//		mToolbar.getBackground().setAlpha(0);

		findViewById(R.id.kdpl_content).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toast("click content");
			}
		});
		mDragPanelLayout.setOnDragListener(new DragPanelLayout.OnDragListener() {
			@Override
			public void onDragStateChanged(DragPanelLayout.DragState oldState, DragPanelLayout.DragState newState) {

			}

			@Override
			public void onDragging(float progress) {
//				mToolbar.getBackground().setAlpha((int) (progress * 255));
			}
		});
	}

	private void toast(CharSequence message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
