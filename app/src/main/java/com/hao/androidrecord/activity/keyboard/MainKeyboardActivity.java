package com.hao.androidrecord.activity.keyboard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.hao.androidrecord.R;

public class MainKeyboardActivity extends Activity {

	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_keyboard02);
		et = (EditText) findViewById(R.id.et);
		et.post(new Runnable() {

			@Override
			public void run() {
				new KeyboardPopup(MainKeyboardActivity.this, et);
			}
		});
	}

}
