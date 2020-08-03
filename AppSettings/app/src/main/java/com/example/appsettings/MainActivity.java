package com.example.appsettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public static final String pref_delete_old_message = "pref_delete_old_message";
    public static final String sms_delete_limit = "sms_delete_limit";
    public static final String mms_delete_limit = "mms_delete_limit";
    public static final String pref_delivery_reports_sms = "pref_delivery_reports_sms";
    public static final String pref_delivery_reports_mms = "pref_delivery_reports_mms";

    private TextView txt_delete_old_messages, txt_sms_delete, txt_mms_delete, txt_sms_delivery, txt_mms_delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        txt_delete_old_messages = findViewById(R.id.message_delete_status);
        txt_sms_delete = findViewById(R.id.sms_delete_limit);
        txt_mms_delete = findViewById(R.id.mms_delete_limit);
        txt_sms_delivery = findViewById(R.id.sms_delivery_report);
        txt_mms_delivery = findViewById(R.id.mms_delivery_report);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void readSettings(View view) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        txt_delete_old_messages.setText("Message delete status : " + String.valueOf(sharedPreferences.getBoolean(pref_delete_old_message, false)));
        txt_sms_delete.setText("SMS delete limit : " + sharedPreferences.getString(sms_delete_limit, "0"));
        txt_mms_delete.setText("MMS delete limit : " + sharedPreferences.getString(mms_delete_limit, "0"));
        txt_sms_delivery.setText("SMS delivery report : " + String.valueOf(sharedPreferences.getBoolean(pref_delivery_reports_sms, false)));
        txt_mms_delivery.setText("MMS delivery report : " + String.valueOf(sharedPreferences.getBoolean(pref_delivery_reports_mms, false)));

    }
}