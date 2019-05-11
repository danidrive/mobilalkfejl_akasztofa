package com.danidrive.mobilalkalmazasfejlesztes.akasztofa.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.danidrive.mobilalkalmazasfejlesztes.akasztofa.R;
import com.danidrive.mobilalkalmazasfejlesztes.akasztofa.exceptions.AlreadyGuessedException;
import com.danidrive.mobilalkalmazasfejlesztes.akasztofa.model.Game;

public class GameActivity extends AppCompatActivity {

    private Game game;
    private EditText input;
    private TextView guess;
    private TextView guessedChars;
    private View gameView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.game = new Game(getResources().getStringArray(R.array.words));

        guess = findViewById(R.id.guess);
        guessedChars = findViewById(R.id.guessed_char);
        input = findViewById(R.id.editText);
        gameView = findViewById(R.id.gameView);
        imageView = findViewById(R.id.imageView);

        guess.setText(game.getGuess());
    }

    private void startNewGame(){
        game.startNewGame();
        input.setText("");
        guessedChars.setText("");
        guess.setText(game.getGuess());
    }

    public void guessButtonClicked(View view){
        if (input.getText().toString().length() != 1)
            return;

        char c = input.getText().charAt(0);
        try {
            if(game.guessCharacter(c)){
                guessedChars.setText(game.getGuessedChars());
                guess.setText(game.getGuess());

                if (game.isPlayerWin()){
                    String message = getString(R.string.solution) + " " + game.getSolution() + ". " + getString(R.string.want_a_new_game);
                    makeAlert(getString(R.string.win), message);
                }
                else{
                    Snackbar.make(gameView, R.string.success, Snackbar.LENGTH_SHORT).show();
                }
            }
            else {
                guessedChars.setText(game.getGuessedChars());

                if (game.isPlayerLose()){
                    String message = getString(R.string.solution)+ " " + game.getSolution() + ". " + getString(R.string.want_a_new_game);
                    makeAlert(getString(R.string.lose), message);
                }
                else{
                    changeImage(game.getWrongGuesses());
                    Snackbar.make(gameView, R.string.fail, Snackbar.LENGTH_SHORT).show();
                }
            }
            input.setText("");

        } catch (AlreadyGuessedException e) {
            Snackbar.make(gameView, R.string.already_guessed, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void makeAlert(String title, String message){

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.new_game, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startNewGame();
                    }
                })
                .setNegativeButton(R.string.no_thanks, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void changeImage(int wrongGuesses){
        Drawable d;
        switch (wrongGuesses){
            case 0:
                d = getDrawable(R.drawable.a0);
                imageView.setImageDrawable(d);
                break;
            case 1:
                d = getDrawable(R.drawable.a1);
                imageView.setImageDrawable(d);
                break;
            case 2:
                d = getDrawable(R.drawable.a2);
                imageView.setImageDrawable(d);
                break;
            case 3:
                d = getDrawable(R.drawable.a3);
                imageView.setImageDrawable(d);
                break;
            case 4:
                d = getDrawable(R.drawable.a4);
                imageView.setImageDrawable(d);
                break;
            case 5:
                d = getDrawable(R.drawable.a5);
                imageView.setImageDrawable(d);
                break;
            case 6:
                d = getDrawable(R.drawable.a6);
                imageView.setImageDrawable(d);
                break;
            case 7:
                d = getDrawable(R.drawable.a7);
                imageView.setImageDrawable(d);
                break;
            case 8:
                d = getDrawable(R.drawable.a8);
                imageView.setImageDrawable(d);
                break;
            case 9:
                d = getDrawable(R.drawable.a9);
                imageView.setImageDrawable(d);
                break;
            case 10:
                d = getDrawable(R.drawable.a10);
                imageView.setImageDrawable(d);
                break;
        }
    }
}
