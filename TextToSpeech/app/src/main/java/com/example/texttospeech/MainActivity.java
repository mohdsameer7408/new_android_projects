package com.example.texttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final int tts_engine_request = 101;
    private TextToSpeech textToSpeech;
    private EditText text_for_speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_for_speech = findViewById(R.id.speech_text);
    }

    public void performSpeech(View view) {

        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, tts_engine_request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == tts_engine_request && resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            textToSpeech = new TextToSpeech(this, this);
        }
        else {
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int language_status = textToSpeech.setLanguage(Locale.US);

            if (language_status == TextToSpeech.LANG_MISSING_DATA || language_status == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language isn't supported...", Toast.LENGTH_SHORT).show();
            }
            else {
                String data = text_for_speech.getText().toString();
                int speech_status = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                if (speech_status == TextToSpeech.ERROR) {
                    Toast.makeText(this, "Error while speech...", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(this, "Text to speech engine failed...", Toast.LENGTH_SHORT).show();
        }
    }
}