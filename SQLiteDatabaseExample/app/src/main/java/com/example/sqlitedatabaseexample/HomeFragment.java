package com.example.sqlitedatabaseexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button bn_save, bn_view, bn_update, bn_delete;

    OnDbOpListener dbOpListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    public interface OnDbOpListener {

        public void dbOpPerform (int method);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        bn_save = view.findViewById(R.id.bn_add_contact);
        bn_save.setOnClickListener(this);

        bn_view = view.findViewById(R.id.bn_view_contact);
        bn_view.setOnClickListener(this);

        bn_update = view.findViewById(R.id.bn_update_contact);
        bn_update.setOnClickListener(this);

        bn_delete = view.findViewById(R.id.bn_delete_contact);
        bn_delete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bn_add_contact:
                dbOpListener.dbOpPerform(0);
                break;

            case R.id.bn_view_contact:
                dbOpListener.dbOpPerform(1);
                break;

            case R.id.bn_update_contact:
                dbOpListener.dbOpPerform(2);
                break;

            case R.id.bn_delete_contact:
                dbOpListener.dbOpPerform(3);
                break;

        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try {
            dbOpListener = (OnDbOpListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement dbOpPerform method");
        }

    }
}
