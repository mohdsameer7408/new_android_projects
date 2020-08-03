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
public class DeleteUserFragment extends Fragment {

    private EditText delete_id;
    private Button bn_delete;

    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);

        delete_id = view.findViewById(R.id.delete_id);
        bn_delete = view.findViewById(R.id.bn_delete);
        bn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(delete_id.getText().toString());
                User user = new User();
                user.setId(id);

                MainActivity.myAppDatabase.myDao().deleteUser(user);

                Toast.makeText(getActivity(), "User deleted successfully", Toast.LENGTH_SHORT).show();

                delete_id.setText("");
            }
        });

        return view;
    }
}
