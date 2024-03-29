package com.hao.androidrecord.leonids;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.hao.androidrecord.R;
import com.plattysoft.leonids.ParticleSystem;

public class OneShotSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		new ParticleSystem(this, 100, R.drawable.star_pink, 800)		
		.setSpeedRange(0.1f, 0.25f)
		.oneShot(arg0, 100);
	}

}
