package com.example.firebaseregistrationexample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewPostFragment extends Fragment {

    private EditText title, content;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String user_email;
    private Button post;
    private ProgressBar progressBar;

    public NewPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);

        title = view.findViewById(R.id.new_title);
        content = view.findViewById(R.id.new_content);
        post = view.findViewById(R.id.post);
        progressBar = view.findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                user_email = fAuth.getCurrentUser().getEmail();
                Map<String, Object> post = new HashMap<>();
                post.put("user_email", user_email);
                post.put("post_title", title.getText().toString());
                post.put("post_content", content.getText().toString());
                fStore.collection("posts").document().set(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.INVISIBLE);
                        title.setText("");
                        content.setText("");
                        Log.d("post", "post created");
                        Toast.makeText(getContext(), "Post Created Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d("post", "post not created " + e.getMessage());
                        Toast.makeText(getContext(), "Error creating the post : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}