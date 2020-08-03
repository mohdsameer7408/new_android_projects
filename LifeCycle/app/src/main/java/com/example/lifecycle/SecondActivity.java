package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.txt_msg);
    }

    public void sendMessage(View view) {

        String msg = editText.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("message", msg);
        setResult(RESULT_OK, returnIntent);
        finish();

    }
}
