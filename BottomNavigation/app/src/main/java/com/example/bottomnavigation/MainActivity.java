package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment(), null).commit();

        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =

            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selected_fragment = null;

                    switch (item.getItemId()) {

                        case R.id.nav_home:
                            selected_fragment = new HomeFragment();
                            break;

                        case R.id.nav_search:
                            selected_fragment = new SearchFragment();
                            break;

                        case R.id.nav_dashboard:
                            selected_fragment = new DashboardFragment();
                            break;

                        default:
                            return false;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected_fragment, null).commit();
                    return true;

                }
            };

}