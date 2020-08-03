package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuccessActivity extends AppCompatActivity {

    SharedPreferenceConfig preferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
    }

    public void logoutUser(View view) {

        preferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}
