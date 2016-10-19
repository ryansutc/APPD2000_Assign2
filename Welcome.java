package com.example.w0143446.quizbuilder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by w0143446 on 10/11/2016.
 */
public class Welcome extends AppCompatActivity implements View.OnClickListener{
    Button btnStart;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(v.getContext(), MainActivity.class);
        Bundle extras = new Bundle();//create bundle
        String myfile = new String();
        myfile = "udwords.txt";

        extras.putString("Name",etName.getText().toString());//fill bundle
        extras.putString("File", myfile);
        myIntent.putExtras(extras);//put bundle in intent
        startActivityForResult(myIntent, 0);
    }
}
