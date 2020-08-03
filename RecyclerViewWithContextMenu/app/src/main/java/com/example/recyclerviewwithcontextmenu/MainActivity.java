package com.example.recyclerviewwithcontextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] images = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4,
            R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
            R.drawable.pic10, R.drawable.pic11, R.drawable.pic12, R.drawable.pic13};
    private List<Item> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getItems();
        adapter = new RecyclerAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    private void getItems() {
        int count = 0;
        for (int image : images) {
            Item item = new Item(image, "Image : " + count);
            items.add(item);
            count ++;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case 121:
                adapter.removeItem(item.getGroupId());
                showSnack("Item deleted");
                return true;

            case 122:
                showSnack("Added to wish list");
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    public void showSnack(String message) {
        Snackbar.make(findViewById(R.id.root_layout), message, Snackbar.LENGTH_SHORT).show();
    }

}
