package com.example.dialogwithcheckbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CheckboxDialogFragment extends DialogFragment {

    private List<String> selected_items;

    public interface ToppingsSelectionListener {

        public void onToppingsSelected(List<String> toppings);

    }

    private ToppingsSelectionListener toppingsSelectionListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        selected_items = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick your toppings");
        builder.setMultiChoiceItems(R.array.toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                String[] items = getActivity().getResources().getStringArray(R.array.toppings);

                if (isChecked) {
                    selected_items.add(items[which]);
                }
                else if (selected_items.contains(items[which])) {
                    selected_items.remove(items[which]);
                }

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                toppingsSelectionListener.onToppingsSelected(selected_items);

                String final_selection = "";

                for (String item : selected_items) {
                    final_selection += "\n" + item;
                }

                Toast.makeText(getActivity(), "Selections : " + final_selection, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try {
            toppingsSelectionListener = (ToppingsSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " Must implement ToppingsSelectionListener methods...");
        }
    }
}
