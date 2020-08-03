package com.example.sqlitedatabaseexample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private EditText update_id, update_name, update_email;
    private Button bn_update;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        update_id = view.findViewById(R.id.update_id);
        update_name = view.findViewById(R.id.update_name);
        update_email = view.findViewById(R.id.update_email);
        bn_update = view.findViewById(R.id.bn_update_save);
        bn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });

        return view;
    }

    private void updateContact() {
        int id = Integer.parseInt(update_id.getText().toString());
        String name = update_name.getText().toString();
        String email = update_email.getText().toString();

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase db = contactDbHelper.getWritableDatabase();
        contactDbHelper.updateContact(id, name, email, db);
        contactDbHelper.close();

        update_id.setText("");
        update_name.setText("");
        update_email.setText("");

        Toast.makeText(getActivity(), "Contact updated successfully", Toast.LENGTH_SHORT).show();
    }

}
