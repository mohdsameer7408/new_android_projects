package com.example.sqlitedatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnDbOpListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new HomeFragment(), null).commit();

        }
    }

    @Override
    public void dbOpPerform(int method) {
        switch (method) {

            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddContactFragment(), null).addToBackStack(null).commit();
                break;

            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReadContactsFragment(), null).addToBackStack(null).commit();
                break;

            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateFragment(), null).addToBackStack(null).commit();
                break;

            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteFragment(), null).addToBackStack(null).commit();
                break;

        }
    }
}
