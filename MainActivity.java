package com.example.kon_boot.reflex;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnstart;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button playagain;
    TextView txtrandom;
    TextView timer;
    TextView result;
    TextView pointstxt;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctanswerposition;
    int numofques=0;
    int incorrectanswer;
    int score=0;
    RelativeLayout playmain;
    public void playagain(View view)
    {
        score =0;
        numofques =0;
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);

        timer.setText("30s");
        pointstxt.setText("0/0");
        result.setText("");
        playagain.setVisibility(View.INVISIBLE);
        generate();
        new CountDownTimer(30100,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf(millisUntilFinished/1000)+ "s");

            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Well Done!!Your Score: " + Integer.toString(score)+ "/"+Integer.toString(numofques));
                playagain.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.INVISIBLE);
            }
        }.start();

    }
    public void generate()
    {
        Random random =new Random();
        int a = random.nextInt(50);
        int b= random.nextInt(50);
        txtrandom.setText(Integer.toString(a)+ " + "+Integer.toString(b));
        correctanswerposition =random.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==correctanswerposition)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectanswer = random.nextInt(101);
                while(incorrectanswer==a+b)
                {
                    incorrectanswer = random.nextInt(101);
                }
                answers.add(incorrectanswer);
            }
        }
        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart = findViewById(R.id.btnGo);
        txtrandom =findViewById(R.id.TxtNum);
        timer=findViewById(R.id.timer);
        pointstxt = findViewById(R.id.points);
        result =findViewById(R.id.ResultText);
         btn1 =findViewById(R.id.button0);
         btn2 = findViewById(R.id.button1);
         btn3 =findViewById(R.id.button2);
         btn4= findViewById(R.id.button3);
         playagain=findViewById(R.id.playagain);
         playmain =findViewById(R.id.Main);





        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstart.setVisibility(View.INVISIBLE);
                playmain.setVisibility(RelativeLayout.VISIBLE);
                playagain(findViewById(R.id.playagain));
            }
        });
    }

    public void chooseanswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(correctanswerposition)))
        {
          score++;
          result.setText("Correct");
        }
        else
        {
            result.setText("Incorrect");
        }
        numofques++;
        pointstxt.setText(Integer.toString(score)+ "/"+Integer.toString(numofques));
        generate();
    }
}
