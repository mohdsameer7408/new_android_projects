package com.example.listviewwithcontextualaction;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<String> {

    private List<String> fruits = new ArrayList<>();
    private Context context;

    public ListViewAdapter(List<String> fruits, Context context) {
        super(context, R.layout.item_layout, fruits);
        this.fruits = fruits;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View row = layoutInflater.inflate(R.layout.item_layout, parent, false);
        final TextView fruit_view = row.findViewById(R.id.fruits);
        fruit_view.setText(fruits.get(position));

        CheckBox checkBox = row.findViewById(R.id.check_box);
        checkBox.setTag(position);

        if (MainActivity.is_action_mode) {
            checkBox.setVisibility(View.VISIBLE);
        }
        else {
            checkBox.setVisibility(View.GONE);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = (int) buttonView.getTag();

                if (MainActivity.user_selection.contains(fruits.get(position))) {
                    MainActivity.user_selection.remove(fruits.get(position));
                }
                else {
                    MainActivity.user_selection.add(fruits.get(position));
                }
                MainActivity.actionMode.setTitle(MainActivity.user_selection.size() + " items selected...");
            }
        });

        return row;

    }

    public void removeItems(List<String> items) {
        for (String item : items) {
            fruits.remove(item);
        }
        notifyDataSetChanged();
    }

}
