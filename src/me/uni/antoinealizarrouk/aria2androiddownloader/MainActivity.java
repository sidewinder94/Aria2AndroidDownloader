package me.uni.antoinealizarrouk.aria2androiddownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
		Context context = getApplicationContext();
		//Trying to open the version file describing the aria2 installed version
		try
		{
			String filePath = context.getFilesDir().getPath() + context.getPackageName();
			String fileName = "version.txt";
			File version = new File(filePath,fileName);
			if (version.exists())
			{
				//Reading the file content if existing
				FileInputStream fis = new FileInputStream(version);
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				fis = null;
				String versionString = new String(buffer);
				if (versionString != getString(R.string.aria2_version));
				{
					/*If the version contained in the file is not equal to the one in the app 
					 * copy the new version of aria2
					 */
					FileOutputStream fos = new FileOutputStream(version);
					fos.write(getString(R.string.aria2_version).getBytes());
					fos.close();
					copyAria(fileName, filePath);
				}
				
			}
			else
			{
				version.createNewFile();
				FileOutputStream fos = new FileOutputStream(version);
				fos.write(getString(R.string.aria2_version).getBytes());
				fos.close();
				copyAria(fileName, filePath);
			}
		}
		catch (IOException exception)
		{
			//TODO Handle Exception
		}
		//Aria2 is in the right folder ready to be executed
		
		// TODO Launch aria2 with the saved parameters
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
	
	private boolean copyAria(String fileName, String filePath)
	{
		try
		{
			Context context = getApplicationContext();
			InputStream ins = context.getResources().openRawResource(R.raw.aria2c);
			byte[] buffer = new byte[ins.available()];
			ins.read(buffer);
			ins.close();
			ins = null;
			FileOutputStream fos = context.openFileOutput(filePath + fileName, Context.MODE_PRIVATE);
			fos.write(buffer);
			fos.close();
			Process process = Runtime.getRuntime().exec("/system/bin/chmod 744 " + filePath + fileName);
			process.waitFor();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
}
