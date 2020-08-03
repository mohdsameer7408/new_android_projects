package com.example.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayPopUp(View view) {

        Snackbar snackbar = Snackbar.make(findViewById(R.id.root_layout), R.string.offline_msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.undo_string, this);
        snackbar.show();

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Undo Operation Selected...", Toast.LENGTH_SHORT).show();
    }
}
