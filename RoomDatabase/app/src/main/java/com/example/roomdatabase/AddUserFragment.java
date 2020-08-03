package com.example.roomdatabase;

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
public class AddUserFragment extends Fragment {

    private EditText id, name, email;
    private Button bn_save;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        id = view.findViewById(R.id.contact_id);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        bn_save = view.findViewById(R.id.bn_save);

        bn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Id = Integer.parseInt(id.getText().toString());
                String Name = name.getText().toString();
                String Email = email.getText().toString();

                User user = new User();
                user.setId(Id);
                user.setName(Name);
                user.setEmail(Email);

                MainActivity.myAppDatabase.myDao().addUser(user);

                id.setText("");
                name.setText("");
                email.setText("");

                Toast.makeText(getActivity(), "Contact saved successfully", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
