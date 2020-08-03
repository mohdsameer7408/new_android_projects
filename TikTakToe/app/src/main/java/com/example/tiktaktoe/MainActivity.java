package com.example.tiktaktoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView status;
    private boolean game_activity = true;
    private int active_player = 0;
    private int tap_count = 0;
    private String my_dialog_title, dialog_description;
    private int[] game_progress = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] game_rules = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.status);
    }

    public void player_tap(View view) {

        ImageView img = (ImageView) view;
        int tapped_position = Integer.parseInt(img.getTag().toString());

        if (game_progress[tapped_position] == 2) {
            tap_count++;
            game_progress[tapped_position] = active_player;

            img.setTranslationY(-1000f);

            if (active_player == 0) {
                img.setImageResource(R.drawable.x);
                active_player = 1;
                status.setText(getResources().getString(R.string.o_turn));
            } else {
                img.setImageResource(R.drawable.o);
                active_player = 0;
                status.setText(getResources().getString(R.string.x_turn));
            }

            img.animate().translationYBy(1000f).setDuration(300);

            if (!check_for_winner() && tap_count == 9) {
                game_activity = false;
                my_dialog_title = "Game Result";
                dialog_description = "It's a tie!!!\nDo you want to continue?";
                status.setText(getResources().getString(R.string.tie));
            }

        }

        if (!game_activity) {
            new GameResultsDialog(my_dialog_title, dialog_description).show(getSupportFragmentManager(), "game_result");
        }

    }

    public boolean check_for_winner() {
        for (int[] game_rule : game_rules) {
            if (game_progress[game_rule[0]] == game_progress[game_rule[1]] &&
                    game_progress[game_rule[1]] == game_progress[game_rule[2]] &&
                    game_progress[game_rule[0]] != 2){
                String winner;
                if (game_progress[game_rule[0]] == 0) {
                    winner = "X has won!!!";
                }
                else {
                    winner = "O has won!!!";
                }
                my_dialog_title = "Game Result";
                dialog_description = winner + "\nDo you want to continue?";
                status.setText(winner);
                game_activity = false;
            }
        }
        return false;
    }

    public void game_reset() {
        game_activity = true;
        active_player = 0;
        Arrays.fill(game_progress, 2);
        tap_count = 0;

        ((ImageView)findViewById(R.id.image0)).setImageResource(0);
        ((ImageView)findViewById(R.id.image1)).setImageResource(0);
        ((ImageView)findViewById(R.id.image2)).setImageResource(0);
        ((ImageView)findViewById(R.id.image3)).setImageResource(0);
        ((ImageView)findViewById(R.id.image4)).setImageResource(0);
        ((ImageView)findViewById(R.id.image5)).setImageResource(0);
        ((ImageView)findViewById(R.id.image6)).setImageResource(0);
        ((ImageView)findViewById(R.id.image7)).setImageResource(0);
        ((ImageView)findViewById(R.id.image8)).setImageResource(0);

        status.setText(getResources().getString(R.string.x_turn));
    }

    public void reset_game(View view) {
        if (tap_count > 0) {
            new GameResultsDialog("", "Are you sure you wish to reset the game")
                    .show(getSupportFragmentManager(), "reset_dialog");
        }
        else {
            Toast.makeText(this, "There's nothing to reset...", Toast.LENGTH_SHORT).show();
        }
    }
}