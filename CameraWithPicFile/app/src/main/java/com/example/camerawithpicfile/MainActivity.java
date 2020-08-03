package com.example.camerawithpicfile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String current_image_path = null;
    private static final int image_request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            File image_file = null;

            try {
                image_file = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (image_file != null) {
                Uri images_uri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", image_file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, images_uri);
                startActivityForResult(intent, image_request);
            }
        }
    }

    public void displayPicture(View view) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("image_path", current_image_path);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private File getImageFile() throws IOException {
        String time_stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String image_name = "jpg_" + time_stamp + "_";
        File storage_dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image_file = File.createTempFile(image_name, ".jpg", storage_dir);
        current_image_path = image_file.getAbsolutePath();
        return image_file;
    }

}