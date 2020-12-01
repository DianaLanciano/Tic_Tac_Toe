package com.example.tic_tac_toe;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private  ImageButton[] buttons = new ImageButton[9];
    private Button startGame;

    private int playerOneCount, playerTwoCount;
    private  int roundCount;
    boolean activePlayer;

    //p1=>0, p2=>1, empty=>2

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerOneScore =(TextView) findViewById(R.id.mainActivity_scorePlayer1);
        playerTwoScore =(TextView) findViewById(R.id.mainActivity_scorePlayer2);
        startGame = (Button) findViewById(R.id.mainActivity_btn_startGame);
        roundCount =0;

        for(int i=0;i<buttons.length;i++)
        {
            String buttonID = "mainActivity_btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
            buttons[i] = (ImageButton) findViewById(resID);
            buttons[i].setOnClickListener(this);
        }

        playerOneCount =0;
        playerTwoCount = 0;
        activePlayer = true;
    }

    @Override
    public void onClick(View v) {

       int pos = Integer.parseInt(v.getTag().toString());
       //cant put x/o in this position
       if(gameState[pos] != 2){
           return;
       }

        String btnID = v.getResources().getResourceEntryName(v.getId());
        int position = Integer.parseInt(btnID.substring(btnID.length()-1,btnID.length()));

        if(activePlayer){
            v.setBackgroundResource(R.drawable.x);
            gameState[pos] = 0;
            checkWinner(0);
        }else{
            v.setBackgroundResource(R.drawable.o);
            gameState[pos] = 1;
            checkWinner(1);
        }
        roundCount++;

        if(checkWinner(0)){
            updateScore();
            Toast.makeText(this,"Player one Won!",Toast.LENGTH_SHORT).show();
            playAgain();

        }
        else if(checkWinner(1)){
            updateScore();


            Toast.makeText(this,"Player two Won!",Toast.LENGTH_SHORT).show();
            playAgain();
        }
        else if(roundCount == 9){

            playAgain();
            Toast.makeText(this,"No Winner!",Toast.LENGTH_SHORT).show();

        }
        else{
            activePlayer = !activePlayer;
        }

    }


    public boolean checkWinner(int player)
    {
        boolean winnerRes = false;

        //rows
        if(gameState[0] == player && gameState[1] == player && gameState[2] == player)
        {
            winnerRes = true;
        }
        else if(gameState[3] == player && gameState[4] == player && gameState[5] == player)
        {
            winnerRes = true;
        }
        else if(gameState[6] == player && gameState[7] == player && gameState[8] == player)
        {
            winnerRes = true;
        }
        //col
        else if(gameState[0] == player && gameState[3] == player && gameState[6] == player)
        {
            winnerRes = true;
        }
        else if(gameState[1] == player && gameState[4] == player && gameState[7] == player)
        {
            winnerRes = true;
        }
        else if(gameState[2] == player && gameState[5] == player && gameState[8] == player)
        {
            winnerRes = true;
        }
        //cross
        else if(gameState[0] == player && gameState[4] == player && gameState[8] == player)
        {
            winnerRes = true;
        }
        else if(gameState[2] == player && gameState[4] == player && gameState[6] == player)
        {
            winnerRes = true;
        }
        return winnerRes;
    }

    public void updateScore() {
        playerOneScore.setText(Integer.toString(playerOneCount));
        playerTwoScore.setText(Integer.toString(playerOneCount));

    }

    public void playAgain(){
        roundCount =0;
        activePlayer = true;
        for(int i=0;i<buttons.length;i++)
        {
             gameState[i] = 2;
             buttons[i].setBackgroundResource(0);
        }


    }
}










