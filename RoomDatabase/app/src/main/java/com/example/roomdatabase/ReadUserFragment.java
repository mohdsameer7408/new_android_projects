package com.example.roomdatabase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    private TextView textView;

    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);

        textView = view.findViewById(R.id.txt_display);

        List<User> users = MainActivity.myAppDatabase.myDao().getUsers();

        String info = "";

        for (User user : users) {

            int id = user.getId();
            String name = user.getName();
            String email = user.getEmail();

            info += "\n\nId : " + id + "\nName : " + name + "\nEmail : " + email;

        }

        info += "\n\n";
        textView.setText(info);

        return view;
    }
}
