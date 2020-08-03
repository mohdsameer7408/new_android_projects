package com.example.roomdatabase;

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
public class UpdateUserFragment extends Fragment {

    private EditText id, name, email;
    private Button bn_update;

    public UpdateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_user, container, false);

        id = view.findViewById(R.id.update_id);
        name = view.findViewById(R.id.update_name);
        email = view.findViewById(R.id.update_email);
        bn_update = view.findViewById(R.id.bn_update_save);

        bn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Id = Integer.parseInt(id.getText().toString());
                String Name = name.getText().toString();
                String Email = email.getText().toString();

                User user = new User();
                user.setId(Id);
                user.setName(Name);
                user.setEmail(Email);

                MainActivity.myAppDatabase.myDao().updateUser(user);

                id.setText("");
                name.setText("");
                email.setText("");

                Toast.makeText(getActivity(), "User updated successfully", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
