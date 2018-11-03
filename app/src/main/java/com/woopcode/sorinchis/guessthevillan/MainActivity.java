package com.woopcode.sorinchis.guessthevillan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private ImageView imageCard1, imageCard2, imageCard3;
    private Button buttonShuffle;
    private Button videoButton;
    private TextView mewText;

    private Random random;

    private int correctGuess;

    private MediaPlayer winSound, failSound, shuffleSound;

    private ArrayList<ImageView> mCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoButton=findViewById(R.id.youtube_button);

        winSound=MediaPlayer.create(this,R.raw.win);
        failSound=MediaPlayer.create(this,R.raw.fail);
        shuffleSound=MediaPlayer.create(this,R.raw.shuffle);

        youtubeButtonConfig();

        random=new Random();

        buttonShuffle=findViewById(R.id.shuffle_button);
        //mewText=findViewById(R.id.mew_text);

        mCards=new ArrayList<>();

        imageCard1=findViewById(R.id.card1);
        mCards.add(imageCard1);

        imageCard2=findViewById(R.id.card2);
        mCards.add(imageCard2);

        imageCard3=findViewById(R.id.card3);
        mCards.add(imageCard3);

        buttonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shuffleSound.start();
                videoButton.setVisibility(View.INVISIBLE);

                for(int i=0;i<mCards.size();i++){
                    ImageView card=mCards.get(i);
                    card.setImageResource(R.drawable.cardback);
                }

                correctGuess=random.nextInt(3);
                mewText.setText("Hello "+correctGuess);
            }
        });

        ImageView card;

        for(int i=0;i<mCards.size();i++){
            card=mCards.get(i);
            card.setTag(i);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int choosenCard=(int)v.getTag();
                    selectedCard(choosenCard);
                }
            });
        }

    }

    private void youtubeButtonConfig() {

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,VideoImplActivity.class));
            }
        });
    }

    private void selectedCard(int choosenCard) {

        if(correctGuess !=99 ) {
            ImageView card = mCards.get(choosenCard);

            if (choosenCard == correctGuess) {
                winSound.start();
                card.setImageResource(R.drawable.magneto);
                videoButton.setVisibility(View.VISIBLE);
            } else {
                failSound.start();
                card.setImageResource(R.drawable.xavier);
            }

        }
        correctGuess=99;

    }
}
