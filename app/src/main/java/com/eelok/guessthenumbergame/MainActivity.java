package com.eelok.guessthenumbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView secretNumber;
    private EditText guessedNumber;
    private TextView promptText;
    private Button startBtn;
    private Button guessBtn;
    Random random = new Random();
    private int secretNumValue;
    private int attemptNumber;

    public int getSecretNumValue() {
        return this.secretNumValue;
    }

    public void setSecretNumValue() {
        this.secretNumValue = random.nextInt(20) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.startBtn = findViewById(R.id.buttonStart);
        this.guessBtn = findViewById(R.id.guessBtn);
        this.secretNumber = findViewById(R.id.secretNumText);
        this.guessedNumber = findViewById(R.id.guessedNumber);
        this.promptText = findViewById(R.id.promptText);
        this.guessBtn.setEnabled(false);
    }


    public void handleStartBtn(View view) {
        this.attemptNumber = 5;
        Log.d("text", guessedNumber.getText().toString());
        Log.d("START BTN", "Was clicked");
        this.startBtn.setBackgroundColor(Color.BLUE);
        this.guessBtn.setEnabled(true);
        this.guessBtn.setBackgroundColor(Color.BLUE);
        this.guessBtn.setTextColor(Color.WHITE);
        setSecretNumValue();
        this.secretNumber.setText("???");
        showPromptMessage("Guess a number between 1 a and 20.\n", String.valueOf(this.attemptNumber));
    }

    public void handleGuessBtn(View view) {
        Log.d("SECRET NUM", String.valueOf(this.secretNumValue));
        String userGuess = this.guessedNumber.getText().toString();
        this.guessedNumber.getText().clear();
        if (userGuess.trim().length() == 0) {
            this.promptText.setText("Enter your number");
            return;
        }
        this.attemptNumber--;
        int userGuessNum = Integer.parseInt(userGuess);
        if (this.attemptNumber > 0) {
            if (userGuessNum < getSecretNumValue()) {
                showPromptMessage("Too small ", String.valueOf(this.attemptNumber));
            } else if (userGuessNum > getSecretNumValue()) {
                showPromptMessage("Too big ", String.valueOf(this.attemptNumber));
            } else {
                won();
            }
        } else {
            if (userGuessNum != getSecretNumValue()) {
                lost();
            } else {
                won();
            }
        }
    }

    private void showPromptMessage(String message, String numOfAttempt) {
        this.promptText.setText(message + " You have " + numOfAttempt + " attempt");
    }

    private void lost() {
        this.promptText.setText("YOU LOST! :(");
        this.guessBtn.setBackgroundColor(Color.GRAY);
        this.guessBtn.setEnabled(false);
    }

    private void won() {
        this.promptText.setText("YOU WON! :)");
        this.guessBtn.setEnabled(false);
        this.guessBtn.setBackgroundColor(Color.GRAY);
        this.secretNumber.setText(String.valueOf(getSecretNumValue()));
        this.secretNumber.setBackgroundColor(Color.RED);
    }
}