package me.uni.antoinealizarrouk.aria2androiddownloader;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import me.uni.antoinealizarrouk.aria2androiddownloader.menus.Main;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		return (Main.applyMenuChoice(item) || 
				super.onOptionsItemSelected(item));
	}
	
}
