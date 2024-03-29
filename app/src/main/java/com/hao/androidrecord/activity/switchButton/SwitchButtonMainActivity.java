package com.hao.androidrecord.activity.switchButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hao.androidrecord.R;
import com.hao.androidrecord.custom.switchButton.SwitchMultiButton;

//https://github.com/kyleduo/SwitchButton
public class SwitchButtonMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_switch_button);
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		int id = item.getItemId();
		switch (id) {
			case R.id.action_github:
				intent.setData(Uri.parse("https://github.com/kyleduo/SwitchButton"));
				startActivity(intent);
				return true;
			case R.id.action_blog:
				intent.setData(Uri.parse("https://kyleduo.com"));
				startActivity(intent);
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void jumpToStyle() {
		startActivity(new Intent(this, StyleActivity.class));
	}

	private void jumpToStyleInCode() {
		startActivity(new Intent(this, StyleInCodeActivity.class));
	}

	private void jumpToUse() {
		startActivity(new Intent(this, UseActivity.class));
	}

	private void jumpToRecycler() {
		startActivity(new Intent(this, RecyclerActivity.class));
	}

	private void gotoBlog() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://kyleduo.com"));
		startActivity(intent);
	}

	private void gotoLicense() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://www.apache.org/licenses/LICENSE-2.0"));
		startActivity(intent);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case 0:
				jumpToStyle();
				break;
			case 1:
				jumpToStyleInCode();
				break;
			case 2:
				jumpToUse();
				break;
			case 3:
				jumpToRecycler();
				break;
			case 4:
				gotoBlog();
				break;
			case 5:
				gotoLicense();
				break;
			case 6:
				//https://github.com/KingJA/SwitchButton
				startActivity(new Intent(SwitchButtonMainActivity.this, MutilSwtichActivity.class));
				break;

			default:
				break;
		}
	}

}
