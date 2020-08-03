package com.example.materialforms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout email, password;
    private Button sign_in;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        sign_in = findViewById(R.id.sign_in);
        message = findViewById(R.id.display_data);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_email = email.getEditText().getText().toString();
                String user_pass = password.getEditText().getText().toString();

                if (TextUtils.isEmpty(user_email) && TextUtils.isEmpty(user_pass)) {
                    email.setError("Field can't be empty");
                    password.setError("Field can't be empty");
                    return;
                }
                else if (!TextUtils.isEmpty(user_email) && !TextUtils.isEmpty(user_pass)) {
                    email.setError(null);
                    email.setErrorEnabled(false);
                    password.setError(null);
                    password.setErrorEnabled(false);
                }
                else if (!TextUtils.isEmpty(user_email) || !TextUtils.isEmpty(user_pass)) {
                    if (!TextUtils.isEmpty(user_email)) {
                        email.setError(null);
                        email.setErrorEnabled(false);
                        if (TextUtils.isEmpty(user_pass)) {
                            password.setError("Field can't be empty");
                            return;
                        }

                    }
                    else {
                        password.setError(null);
                        password.setErrorEnabled(false);
                        if (TextUtils.isEmpty(user_email)) {
                            email.setError("Field can't be empty");
                        }
                    }
                    return;
                }
                else if (TextUtils.isEmpty(user_email)) {
                    email.setError("Field can't be empty");
                    return;
                }
                else if (TextUtils.isEmpty(user_pass)) {
                    password.setError("Field can't be empty");
                    return;
                }

                message.setText(user_email + "\n" + user_pass);

                startActivity(new Intent(v.getContext(), ProfileActivity.class));

            }
        });
    }
}