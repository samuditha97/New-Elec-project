package com.example.klnresearch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import java.util.Map;

public class PreferrencesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity_Helper.initialize(this);
// Using this for compatibility with Android 2.2 devices
    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference pref = findPreference(s);
        if (pref instanceof ListPreference) {
            ListPreference listPref = (ListPreference) pref;
            pref.setSummary(listPref.getEntry());
            Activity_Helper.initialize(this);
        }

        if (pref instanceof EditTextPreference) {
            EditTextPreference editPref = (EditTextPreference) pref;
            pref.setSummary(editPref.getText());
        }
    }
    @Override
    protected void onPause() {

        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        Map<String, ?> keys = PreferenceManager.getDefaultSharedPreferences(this).getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {
// Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            Preference pref = findPreference(entry.getKey());
            if (pref != null) {
                pref.setSummary(entry.getValue().toString());
            }
        }

        super.onResume();
    }
}
