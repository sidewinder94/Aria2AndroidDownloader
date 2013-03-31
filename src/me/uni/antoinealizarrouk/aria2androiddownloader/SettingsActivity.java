/**
 * 
 */
package me.uni.antoinealizarrouk.aria2androiddownloader;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author Antoine-Ali
 * We are here using PreferenceActivity because we're targeting all devices from 2.2 (API8) to 4.2 (API17)
 * and we're only using simple parameters
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
