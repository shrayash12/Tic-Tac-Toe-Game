package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button buttons[] = new Button[9];
    private Button resetButton;
    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    private boolean activePlayer;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},//row
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},//column
            {0, 4, 8}, {2, 4, 6}
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
        Log.i("id","buttonClicked");

        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
    }
}