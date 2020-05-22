package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int winner=1;
    boolean gameIsActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        TextView winnerTv = (TextView) findViewById(R.id.winnerTv);
        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
        String tag = counter.getTag().toString();
        int position = Integer.parseInt(tag);
        if(gameState[position]==2 && gameIsActive){
            if(activePlayer==0 ){
                counter.setImageResource(R.drawable.close);
                gameState[position]=activePlayer;
                activePlayer=1;
            }
            else{
                counter.setImageResource(R.drawable.circle);
                gameState[position]=activePlayer;
                activePlayer=0;
            }
        }

        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    && gameState[winningPosition[0]]!=2){
//                System.out.println("Winner: "+gameState[winningPosition[0]]);
                gameIsActive=false;
                winner = gameState[winningPosition[0]]+1;
                winnerTv.setText("Player "+winner+" Won! ");
                playAgain.setVisibility(View.VISIBLE);

            }
            else{
                boolean gameIsOver=true;
                for(int i:gameState){
                    if(i==2){
                        gameIsOver=false;
                    }
                }
                if(gameIsOver){
                    winnerTv.setText("Match has tied!");
                    playAgain.setVisibility(View.VISIBLE);
                }
            }

        }
        counter.animate().alpha(1f).setDuration(1000);

    }

    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.VISIBLE);
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout = findViewById(R.id.boardLayout);
        for(int i =0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        playAgainLayout.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
