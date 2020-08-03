package com.example.messagebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();

        String msg = intent.getStringExtra("message");
        TextView textView = findViewById(R.id.display_msg);
        textView.setText(msg);

    }

    public void openFinalActivity(View view) {

        startActivity(new Intent(this, TestActivity.class));

    }
}
