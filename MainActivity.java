package com.example.w0143446.quizbuilder;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;


import java.io.*;
import java.util.*;


public class MainActivity extends AppCompatActivity {
    TextView tvQCount;
    TextView tvScore;
    TextView tvTerm;
    TextView tvFeedback;
    RadioButton rdoOptionA;
    RadioButton rdoOptionB;
    RadioButton rdoOptionC;
    RadioButton rdoOptionD;

    BusinessEngine be = new BusinessEngine();
    Integer questionNo = 1; //current question number
    ArrayList<String> alQuestions; //array of questions
    //https://github.com/ryansutc/APPD2000_Assign2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQCount = (TextView) findViewById(R.id.tvQCount);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvTerm = (TextView) findViewById(R.id.tvTerm);
        tvFeedback = (TextView) findViewById(R.id.tvFeedback);

        rdoOptionA = (RadioButton) findViewById(R.id.rdoOptionA);
        rdoOptionB = (RadioButton) findViewById(R.id.rdoOptionB);
        rdoOptionC = (RadioButton) findViewById(R.id.rdoOptionC);
        rdoOptionD = (RadioButton) findViewById(R.id.rdoOptionD);

        Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        String name = extras.getString("Name");
        tvFeedback.setText("");
        //create map object

        Toast myToast = Toast.makeText(getApplicationContext(),"what!", Toast.LENGTH_LONG);
        myToast.show();

     }

    public void updateQuestion(Integer questionNo){
        /*
        Update the question/answer activity elements
        with the next value in the array
         */
        tvQCount.setText(questionNo + " out of " + be.count);
        tvScore.setText(be.getScore() + "%");
        tvTerm.setText(be.getCard(be.card)[0]);
        rdoOptionA.setText(be.getCard(be.card)[1]);

    }


}
