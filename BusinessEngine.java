package com.example.w0143446.quizbuilder;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by w0143446 on 10/19/2016.
 */
public class BusinessEngine {
    private int score = 0; //your current cumulative score(0-100%)
    protected int card = 1; //the current question/answer from list.
    private HashMap<String, String> hmData; //the question/answer data from hashmap
    private ArrayList<String> alQuestions; //array of questions
    private ArrayList<String> alAnswers; //array of answers
    protected int count; //get count of records

    public void loadCSVDataToHash(String filename, Context appContext) {
        AssetManager am = appContext.getAssets(); //now aware of your project folder config and gets assets within

        //create an input stream reader
        InputStream is = null;
        try {
            is = am.open(filename);
            System.out.println("file in assets is open");

        } catch(IOException e) {
            System.out.println("error opening file");
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        String mykey = new String();
        String myval = new String();
        HashMap<String, String> hmData = new HashMap<String, String>();

        try  {
            while ((s = br.readLine()) != null) {
                mykey = s.substring(0,s.indexOf(','));
                myval = s.substring(s.indexOf(',') + 1, s.length());
                hmData.put(mykey,myval); //populate hashMap
            }
        }
        catch(IOException e) {
            System.out.println("error reading file with buffered reader");
            return;
        }

        this.hmData = hmData;
    }

    public void getQuestionArray(HashMap<String, String> hmData) {
        /*
        Get an array list of questions, terms based on the hashmap,
        also allows you to provide the definition you DONT want.
         */

        ArrayList alData = new ArrayList();
        Iterator it = hmData.entrySet().iterator();

        for (Map.Entry<String, String> entry : hmData.entrySet())
        {
            //System.out.println(entry.getKey());
            alData.add(entry.getKey()); //definitions are key
        }

        this.alQuestions = alData;
    }
    public void getAnswerArray(HashMap<String, String> hmData) {
        /*
        Get an array list of answers, definitions based on the hashmap,
        also allows you to provide the definition you DONT want.
         */

        ArrayList alData = new ArrayList();
        Iterator it = hmData.entrySet().iterator();

        for (Map.Entry<String, String> entry : hmData.entrySet())
        {
            //System.out.println(entry.getKey());
            alData.add(entry.getKey()); //definitions are key
        }

        this.alAnswers = alData;
    }

    private int getQuestionCount(ArrayList<String> questionsArray) {
        /*
        Get count of questions in deck
         */
        System.out.println("the length of the array is " + questionsArray.size());

        this.count = questionsArray.size();
        return questionsArray.size();
    }

    //Getters, setters
    public int getScore() {
        return this.score;
    }
    public void setScore(int newScore) {
        this.score = newScore;
    }
    public String[] getCard(int mynum) {
        /*
        function that returns a question/answer pair.
        the question is taken from the next index of the
        array, while the answer is taken from the hashmap.
         */

        String[] card = {this.alQuestions.get(mynum), this.hmData.get(this.alQuestions.get(mynum))};
        return card;
    }
    public void nextCard() {
        this.card += 1;
        if (this.card > this.count) {
            this.card = 1; //[todo: make this trigger a done screen]
        }
    }
}
