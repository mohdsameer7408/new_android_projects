package com.example.menuitemsatruntime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;
    private final int cast_item_id = 101;
    private boolean is_item_add = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bn_menu);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_share:
                showSnack("Share Selected...");
                return true;

            case R.id.action_settings:
                showSnack("Settings Selected...");
                return true;

            case R.id.action_search:
                showSnack("Search Selected...");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showSnack(String message) {
        Snackbar.make(findViewById(R.id.root_layout), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (is_item_add) {
            if (menu.findItem(cast_item_id) == null) {
                MenuItem castItem = menu.add(Menu.NONE, cast_item_id, 4, "Share");
                castItem.setIcon(R.drawable.ic_cast);
                castItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                castItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showSnack("Cast Selected...");
                        return true;
                    }
                });
            }
        }
        else {
            menu.removeItem(cast_item_id);
        }

        super.onPrepareOptionsMenu(menu);
        return true;
    }

    public void addMenuItem(View view) {
        if (! is_item_add) {
            is_item_add = true;
            button.setText("Remove Cast Option");
        }
        else {
            is_item_add = false;
            button.setText("Add Cast Option");
        }
        invalidateOptionsMenu();
    }
}