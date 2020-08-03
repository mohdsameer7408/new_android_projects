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
public class AddContactFragment extends Fragment {

    private Button button;
    private EditText id, name, email;

    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        id = view.findViewById(R.id.contact_id);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        button = view.findViewById(R.id.bn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Id = id.getText().toString();
                String Name = name.getText().toString();
                String Email = email.getText().toString();

                ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
                SQLiteDatabase db = contactDbHelper.getWritableDatabase();
                contactDbHelper.addContact(Integer.parseInt(Id), Name, Email, db);
                contactDbHelper.close();

                id.setText("");
                name.setText("");
                email.setText("");

                Toast.makeText(getActivity(), "Contact saved successfully", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
