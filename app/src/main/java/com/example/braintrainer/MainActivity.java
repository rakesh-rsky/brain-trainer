package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView result;
    TextView points;
    TextView timer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    ConstraintLayout gameLayout;
    TextView sum;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;

        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        //playAgain.setVisibility(View.INVISIBLE);
        playAgain.animate().translationYBy(1000f).setDuration(1000);
        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
                playAgain.animate().translationYBy(-1000f).setDuration(1000);
                timer.setText("0s");
                result.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sum.setText(Integer.toString(a) + " + "+ Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer== a + b ){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    public void chooseAnswer(View view){
        //Log.i("tag",(String) view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            result.setText("Correct!");
        }else{
            result.setText("Wrong!");
        }
        numberOfQuestions++;
        points.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view){

        startButton.animate().translationYBy(3000f).setDuration(1000);
        //startButton.animate().scaleXBy(0f).scaleYBy(0f);
        //startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sum = (TextView) findViewById(R.id.sum);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        result = (TextView)findViewById(R.id.result);
        points = (TextView)findViewById(R.id.points);
        timer = (TextView)findViewById(R.id.timer);
        playAgain = (Button)findViewById(R.id.playAgain);
        gameLayout = (ConstraintLayout)findViewById(R.id.gameLayout);


    }
}