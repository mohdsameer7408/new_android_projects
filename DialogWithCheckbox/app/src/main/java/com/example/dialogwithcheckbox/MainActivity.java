package com.example.dialogwithcheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CheckboxDialogFragment.ToppingsSelectionListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_display);
    }

    public void displayPopUp(View view) {
        new CheckboxDialogFragment().show(getSupportFragmentManager(), "check the boxes");
    }

    @Override
    public void onToppingsSelected(List<String> toppings) {

        String final_selection = "";

        for (String item : toppings) {
            final_selection += "\n" + item;
        }

        textView.setText(final_selection);

    }
}
