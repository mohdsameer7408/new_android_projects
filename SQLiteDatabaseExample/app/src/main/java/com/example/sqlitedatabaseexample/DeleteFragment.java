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
public class DeleteFragment extends Fragment {

    private EditText delete_id;
    private Button button;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        delete_id = view.findViewById(R.id.delete_id);
        button = view.findViewById(R.id.bn_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });


        return view;
    }

    private void deleteContact() {
        int id = Integer.parseInt(delete_id.getText().toString());

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase db = contactDbHelper.getWritableDatabase();
        contactDbHelper.deleteContact(id, db);
        contactDbHelper.close();

        delete_id.setText("");
        Toast.makeText(getActivity(), "Contact Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

}
