package com.example.tiktaktoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GameResultsDialog extends DialogFragment {

    private String my_title, description;

    public GameResultsDialog(String my_title, String description) {
        this.my_title = my_title;
        this.description = description;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(my_title);
        builder.setMessage(description);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity)getActivity()).game_reset();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (my_title.equals("Game Result")) {
                    ((MainActivity)getActivity()).finish();
                }
            }
        });

        return builder.create();
    }
}
