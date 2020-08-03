package com.example.floatingcontextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bn_context);

        registerForContextMenu(button);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_playlist:
                showSnack("Added to playlist");
                return true;

            case R.id.action_delete:
                showSnack("Deleted");
                return true;

            case R.id.action_info:
                showSnack("Here's the info");
                return true;

            case R.id.action_report:
                showSnack("Here's the report");
                return true;

            case R.id.action_share:
                showSnack("Shared");
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    public void showSnack(String message) {
        Snackbar.make(findViewById(R.id.root_layout), message, Snackbar.LENGTH_SHORT).show();
    }

}