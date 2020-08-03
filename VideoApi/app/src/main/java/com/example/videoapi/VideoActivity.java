package com.example.videoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.view_video);

        Uri video_uri = Uri.parse(getIntent().getExtras().getString("video_uri"));
        videoView.setVideoURI(video_uri);
        videoView.start();
    }
}