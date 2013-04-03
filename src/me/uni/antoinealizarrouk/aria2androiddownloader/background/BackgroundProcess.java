/**
 * 
 */
package me.uni.antoinealizarrouk.aria2androiddownloader.background;

import android.widget.Toast;
import me.uni.antoinealizarrouk.aria2androiddownloader.*;
/**
 * @author Antoine-Ali
 *
 */
public class BackgroundProcess extends Thread {
	
	public BackgroundProcess(MainActivity view)
	{
		super(new Aria2Runnable(view));
		this.start();
	}
}

class Aria2Runnable implements Runnable
{
	private MainActivity mainActivity;
	
	public Aria2Runnable(MainActivity view)
	{
		this.mainActivity = view;
	}
	
	@Override
	public void run() 
	{
		// TODO Contact aria2 and save the data received for later use
		final Aria2Runnable run = this; 
		run.mainActivity.runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while(run.mainActivity.work)
				{
					
					Toast toast = Toast.makeText(run.mainActivity.getApplicationContext(), "Resume", Toast.LENGTH_SHORT);
					toast.show();
					run.mainActivity.work = false;
				}
			}
		});
		// TODO Create a new expandable list and fill it with the data provided by aria2
	}
}