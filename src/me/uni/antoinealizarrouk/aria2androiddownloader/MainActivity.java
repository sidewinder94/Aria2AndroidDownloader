package me.uni.antoinealizarrouk.aria2androiddownloader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.Toast;
import me.uni.antoinealizarrouk.aria2androiddownloader.background.*;

public class MainActivity extends Activity {
	 private BackgroundProcess background;
	 public boolean work;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// TODO Import aria2 binairies into the project and launch it with the saved parameters
		// http://stackoverflow.com/questions/5583487/hosting-an-executable-within-android-application 
		// http://gimite.net/en/index.php?Run%20native%20executable%20in%20Android%20App
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		this.work = true;
		this.background = new BackgroundProcess(this);
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		
		Toast toast = Toast.makeText(getApplicationContext(), "Pause", Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.action_exit:
				finish();
				break;
			case R.id.action_settings:
				startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
				break;				
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// TODO shut down aria2
	}
	
}
