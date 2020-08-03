package com.example.appsettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {

    public static final String pref_sms_limit = "sms_delete_limit";
    public static final String pref_mms_limit = "mms_delete_limit";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(pref_sms_limit)) {
                    Preference sms_pref = findPreference(key);
                    sms_pref.setSummary(sharedPreferences.getString(key, "") + " messages per conversation");
                }
                if (key.equals(pref_mms_limit)) {
                    Preference mms_pref = findPreference(key);
                    mms_pref.setSummary(sharedPreferences.getString(key, "") + " messages per conversation");
                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference sms_pref = findPreference(pref_sms_limit);
        sms_pref.setSummary(getPreferenceScreen().getSharedPreferences().getString(pref_sms_limit, "") + " messages per conversation");

        Preference mms_pref = findPreference(pref_mms_limit);
        mms_pref.setSummary(getPreferenceScreen().getSharedPreferences().getString(pref_mms_limit, "") + " messages per conversation");
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }
}
