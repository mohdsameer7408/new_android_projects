package com.example.lifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int msg_request = 01;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLog("onCreate finish...");
        textView = findViewById(R.id.txt_display);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("onStart finish...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("onResume finish...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog("onPause finish...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("onStop finish...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLog("onRestart finish...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("onDestroy finish...");
    }

    public void startSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, msg_request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (msg_request == requestCode) {

            if (resultCode == RESULT_OK) {

                String msg = data.getStringExtra("message");
                textView.setText(msg);

            }

        }
    }

    public void finishActivity(View view) {
        finish();
    }

    private void showLog(String message) {
        Log.d("Lifecycle Test ", message);
    }

}
