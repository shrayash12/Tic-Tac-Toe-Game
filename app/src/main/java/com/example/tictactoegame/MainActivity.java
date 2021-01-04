package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore, playerTwoScore, playerStatus;

    private Button buttons[] = new Button[9];

    private Button resetButton;
    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    private boolean activePlayer;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2,2};
    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},//row
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},//column
            {0, 4, 8}, {2, 4, 6}//cross
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = findViewById(R.id.playerOneScore);
        playerTwoScore = findViewById(R.id.playerTwoScore);
        playerStatus = findViewById(R.id.playerStatus);

        resetButton = findViewById(R.id.btn_Reset_game);

        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn_" + i;
            int resourseID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resourseID);
            buttons[i].setOnClickListener(this);
        }
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        roundCount = 0;
        activePlayer = true;
    }

    @Override
    public void onClick(View v) {


      //  Log.i("id", "buttonClicked");

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId()); // btn_1
        int lenght  = buttonID.length();
        int gameStatePointer = Integer.parseInt(buttonID.substring(lenght - 1, lenght));

        if (activePlayer) {
            // x
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[gameStatePointer] = 0;
        } else {
            //0
            ((Button) v).setText("0");
            ((Button) v).setTextColor(Color.parseColor("#FFFFFF"));
             gameState[gameStatePointer] = 1;
        }

        roundCount++;


        if (checkWinner()) {
            if (activePlayer) {
                playerOneScoreCount++;
                updateScore();
                Toast.makeText(MainActivity.this, "First player won", Toast.LENGTH_SHORT).show();
                playAgain();
            } else {
                playerTwoScoreCount++;
                updateScore();
                Toast.makeText(MainActivity.this, "Second player won", Toast.LENGTH_SHORT).show();
                playAgain();
            }

        } else if (roundCount == 9) {
            playAgain();
            Toast.makeText(MainActivity.this, "No winner", Toast.LENGTH_SHORT).show();

        } else {
            activePlayer = !activePlayer;
        }


    }

    public boolean checkWinner() {
        boolean isWin = false;
        //gameState  = {1,1,1,2,2,2,2,2,2}
        for (int i = 0; i < winningPositions.length; i++) {
            int arr[] = winningPositions[i]; // {1,2,3 }

            if (gameState[arr[0]] == gameState[arr[1]]
                    && gameState[arr[1]] == gameState[arr[2]]
                    && gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2) {
                isWin = true;
            }

        }
        return isWin;
    }

    public void updateScore() {
        playerOneScore.setText("" + playerOneScoreCount);
        playerTwoScore.setText("" + playerTwoScoreCount);

    }

    public void playAgain() {
        activePlayer = true;
        roundCount = 0;

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            gameState[i] = 2;
        }

    }
}