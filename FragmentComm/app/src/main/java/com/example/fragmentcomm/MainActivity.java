package com.example.fragmentcomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MessageFragment.OnMessageReadListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MessageFragment messageFragment = new MessageFragment();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction().add(R.id.fragment_container, messageFragment, null);
            fragmentTransaction.commit();

        }

    }

    @Override
    public void onMessageRead(String message) {

        textView = findViewById(R.id.display_msg);
        textView.setText(message);

    }
}
