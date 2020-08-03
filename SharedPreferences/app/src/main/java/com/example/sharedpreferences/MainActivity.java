package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;
    private EditText user_name, user_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        user_name = findViewById(R.id.user_name);
        user_pass = findViewById(R.id.user_pass);

        if (preferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, SuccessActivity.class));
            finish();
        }

    }

    public void loginUser(View view) {

        String username = user_name.getText().toString();
        String userpass = user_pass.getText().toString();

        if (username.equals(getResources().getString(R.string.user_name))
                && userpass.equals(getResources().getString(R.string.user_password))) {
            startActivity(new Intent(this, SuccessActivity.class));
            preferenceConfig.writeLoginStatus(true);
            finish();
        }
        else {
            Toast.makeText(this, "Login Failed due to invalid credentials", Toast.LENGTH_SHORT).show();
            user_name.setText("");
            user_pass.setText("");
        }

    }
}
