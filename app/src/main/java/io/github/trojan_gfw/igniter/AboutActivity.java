package io.github.trojan_gfw.igniter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference;

public class AboutActivity extends AppCompatActivity {

    public static Intent create(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
	    getPreferenceScreen().setOnPreferenceClickListener(new PreferenceScreen.OnPreferenceClickListener() {
		    public boolean onPreferenceClick(Preference preference) {
			boolean isHandled = true;
			String preference_summary = preference.getSummary().toString();
			if(preference_summary.length() > 4){
			    switch(preference_summary.toString().substring(0, 3)) {
				case "http":
				    Uri uri = Uri.parse(preference_summary);
				    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				    startActivity(intent);
				    break;
			    }
			    isHandled = true;
			} else {
			    isHandled = false;
			}
			return isHandled;
		    }
		});
        }
    }
}
