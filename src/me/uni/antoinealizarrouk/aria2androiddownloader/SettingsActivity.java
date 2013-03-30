/**
 * 
 */
package me.uni.antoinealizarrouk.aria2androiddownloader;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author Antoine-Ali
 *
 */
public class SettingsActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstaceState)
	{
		super.onCreate(savedInstaceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
