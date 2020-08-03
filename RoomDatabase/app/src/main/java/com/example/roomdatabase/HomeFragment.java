package com.example.roomdatabase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button bn_add, bn_view, bn_update, bn_delete;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bn_add = view.findViewById(R.id.bn_add_contact);
        bn_add.setOnClickListener(this);

        bn_view = view.findViewById(R.id.bn_view_contact);
        bn_view.setOnClickListener(this);

        bn_delete = view.findViewById(R.id.bn_delete_contact);
        bn_delete.setOnClickListener(this);

        bn_update = view.findViewById(R.id.bn_update_contact);
        bn_update.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bn_add_contact:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new AddUserFragment(), null).addToBackStack(null).commit();
                break;

            case R.id.bn_view_contact:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new ReadUserFragment(), null).addToBackStack(null).commit();
                break;

            case R.id.bn_delete_contact:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new DeleteUserFragment(), null).addToBackStack(null).commit();
                break;

            case R.id.bn_update_contact:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new UpdateUserFragment(), null).addToBackStack(null).commit();
                break;

        }

    }
}
