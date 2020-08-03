package com.example.firebaseregistrationexample;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {

    private TextView user_name, user_email, user_phone, verify_mail;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private FirebaseUser fUser;
    private StorageReference storageReference;
    private String user_id;
    private Button logout_btn, send_mail, reset_password, change_image;
    private ImageView profile_image;

    public interface OnDataSendListener {
        void onDataSend(String name, String email, String phone);
    }

    private OnDataSendListener onDataSendListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        user_name = view.findViewById(R.id.profile_name);
        user_email = view.findViewById(R.id.profile_email);
        user_phone = view.findViewById(R.id.profile_phone);
        verify_mail = view.findViewById(R.id.email_verification);

        logout_btn = view.findViewById(R.id.logout_btn);
        send_mail = view.findViewById(R.id.verify_mail);
        reset_password = view.findViewById(R.id.reset_password_local);

        profile_image = view.findViewById(R.id.profile_image);
        change_image = view.findViewById(R.id.change_image);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        user_id = fAuth.getCurrentUser().getUid();

        StorageReference profile_ref = storageReference.child("users/" + user_id + "/profile.jpg");
        profile_ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profile_image);
            }
        });

        final DocumentReference documentReference = fStore.collection("users").document(user_id);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                user_name.setText(documentSnapshot.getString("name"));
//                user_email.setText(documentSnapshot.getString("email"));
//                user_phone.setText(documentSnapshot.getString("phone"));
//            }
//        });

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    user_name.setText(documentSnapshot.getString("name"));
                    user_email.setText(documentSnapshot.getString("email"));
                    user_phone.setText(documentSnapshot.getString("phone"));
                }
            }
        });

        // mail verification
        fUser = fAuth.getCurrentUser();

        if (!fUser.isEmailVerified()) {
            verify_mail.setVisibility(View.VISIBLE);
            send_mail.setVisibility(View.VISIBLE);

            send_mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Verification Email has been sent to your mail id", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error sending mail : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText pass_reset = new EditText(v.getContext());
                AlertDialog.Builder password_reset_dialog = new AlertDialog.Builder(v.getContext());
                password_reset_dialog.setTitle("Reset Password");
                password_reset_dialog.setMessage("Enter your new password :");
                password_reset_dialog.setView(pass_reset);

                password_reset_dialog.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_pass = pass_reset.getText().toString().trim();
                        fUser.updatePassword(new_pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Error changing your password : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                password_reset_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                password_reset_dialog.create().show();
            }
        });

        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDataSendListener.onDataSend(user_name.getText().toString(), user_email.getText().toString(), user_phone.getText().toString());
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), SignIn.class));
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            onDataSendListener = (OnDataSendListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must override the interface methods...");
        }
    }
}