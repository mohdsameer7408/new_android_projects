package com.example.firebaseregistrationexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements ProfileFragment.OnDataSendListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new PostFragment(), null).commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected_fragment = null;

                switch (item.getItemId()) {

                    case R.id.posts:
                        selected_fragment = new PostFragment();
                        break;

                    case R.id.new_post:
                        selected_fragment = new NewPostFragment();
                        break;

                    case R.id.profile:
                        selected_fragment = new ProfileFragment();
                        break;

                    default:
                        return false;

                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selected_fragment, null).addToBackStack(null).commit();
                return true;
            }
        });

    }

    @Override
    public void onDataSend(String name, String email, String phone) {
        Intent edit_profile = new Intent(this, EditProfile.class);
        edit_profile.putExtra("name", name);
        edit_profile.putExtra("email", email);
        edit_profile.putExtra("phone", phone);
        startActivity(edit_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SignIn.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}