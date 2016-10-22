package com.example.w0143446.quizbuilder;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
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
    RadioGroup rgOptions;
    Button btnNext;

    BusinessEngine be = null;

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
        rgOptions = (RadioGroup) findViewById(R.id.rgOptions);
        btnNext = (Button) findViewById(R.id.btnNext);

        Intent myintent = getIntent();
        String name = myintent.getStringExtra("Name");
        HashMap<String, String> hmData = (HashMap<String, String>) myintent.getSerializableExtra("fileData"); //test
        be = BusinessEngine.getInstance(hmData);
        //create map object

        updateQuestion();

        rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                String checkedAnswer = checkedRadioButton.getText().toString();
                tvFeedback.setText(be.updateResult(checkedAnswer, getApplicationContext()));

                enableRadioButtons(false);
                showRadioButtons(true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                be.nextCard();
                updateQuestion();
                enableRadioButtons(true);
            }
        }); //rgOptions check listener end
    }// end onCreate

    private void enableRadioButtons(boolean enabled) {
        for(int i = 0; i < rgOptions.getChildCount(); i++){
            ((RadioButton)rgOptions.getChildAt(i)).setEnabled(enabled);
            if (!enabled) {
                ((RadioButton) rgOptions.getChildAt(i)).setChecked(false);
            }
        }
    }

    private void showRadioButtons(boolean show) {
        if (!show) { //hide
            rgOptions.setVisibility(View.GONE);
        }
        else { rgOptions.setVisibility(View.VISIBLE); }
    }
    public void updateQuestion(){
        /*
        Update the question/answer activity elements
        with the next value in the array
         */
        tvFeedback.setText("");
        tvQCount.setText(be.getCountText(getApplicationContext()));
        tvScore.setText(""+be.getScore()+" points");
        tvTerm.setText(""+be.getCard(be.card)[0]);
        //rdoOptionA.setText(be.getCard(be.card)[1]);

        ArrayList<String> fourAnswers = be.getFourAnswers();

        rdoOptionA.setText(fourAnswers.get(0));
        rdoOptionB.setText(fourAnswers.get(1));
        rdoOptionC.setText(fourAnswers.get(2));
        rdoOptionD.setText(fourAnswers.get(3));

    }

    public void getAnswer(String checkedAnswer) {
        /* Updates UI to respond to a selected
        RadioButton
         */
        if (checkedAnswer == be.getRightAnswer()) {
            tvFeedback.setText(R.string.txtFeedbackGood);
        }
        else {
            tvFeedback.setText(R.string.txtFeedbackBad);
        }
    }
}
