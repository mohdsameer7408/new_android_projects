package com.example.directreplynotification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        textView = findViewById(R.id.txt_msg);

        Bundle remoteReply = RemoteInput.getResultsFromIntent(getIntent());

        if (remoteReply != null) {
            String msg = remoteReply.getCharSequence(MainActivity.txt_reply).toString();
            textView.setText(msg);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.notification_id);
    }
}