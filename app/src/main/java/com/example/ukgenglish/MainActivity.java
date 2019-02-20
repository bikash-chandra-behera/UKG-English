package com.example.ukgenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ukgenglish.QuestionBank.OptionsQuestion;
import com.example.ukgenglish.QuestionBank.Question;
import com.example.ukgenglish.QuestionBank.QuestionFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView mQuestinTextView;
    TextView mSubQuestinTextView1;
    TextView mSubQuestinTextView2;
    TextView mSubQuestinTextView3;
    TextView mSubQuestinTextView4;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    //int[] randList ;
    ArrayList<Integer> randList;
    ArrayList<Question> questions;
    QuestionFactory questionFactory;
    ArrayList<Question> questionsList;
    int chooseTag=0;
    int selectAnswer =0;
   private static final String cnxName = "com.example.ukgenglish";

    private static final String KYE = "QUESTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestinTextView = findViewById(R.id.mainQuestionLebel);
        mSubQuestinTextView1 = findViewById(R.id.questionLebel1);
        mSubQuestinTextView2 = findViewById(R.id.questionLebel2);
        mSubQuestinTextView3 = findViewById(R.id.questionLebel3);
        mSubQuestinTextView4 = findViewById(R.id.questionLebel4);
        mButton1 = findViewById(R.id.optionButon1);
        mButton2 = findViewById(R.id.optionButon2);
        mButton3 = findViewById(R.id.optionButon3);
        mButton4 = findViewById(R.id.optionButon4);
        questionsList = new ArrayList<Question>();
        questions = new ArrayList<>();
        questionFactory = new QuestionFactory();


       //start();

        loadData();

        makeRandomSet();
    }

    private void makeRandomSet() {
        randList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0;i<=3;i++){
            randList.add(i);
        }
        Collections.shuffle(randList);
        for(int i = 0;i<=3;i++){
            Log.i("arraylist",Integer.toString(randList.get(i)));
        }
    }

    private void setList() {
        makeRandomSet();
        mSubQuestinTextView1.setText("Q a." + questionsList.get(randList.get(0)).getQuestion());
        mSubQuestinTextView1.setTag(randList.get(0));
        mSubQuestinTextView2.setText("Q b." + questionsList.get(randList.get(1)).getQuestion());
        mSubQuestinTextView2.setTag(randList.get(1));
        mSubQuestinTextView3.setText("Q c." + questionsList.get(randList.get(2)).getQuestion());
        mSubQuestinTextView3.setTag(randList.get(2));
        mSubQuestinTextView4.setText("Q d." + questionsList.get(randList.get(3)).getQuestion());
        mSubQuestinTextView4.setTag(randList.get(3));


        randList = null;
      makeRandomSet();
        mButton1.setText(questionsList.get(randList.get(0)).getAnswer());
        mButton1.setTag(randList.get(0));
        mButton2.setText(questionsList.get(randList.get(1)).getAnswer());
        mButton2.setTag(randList.get(1));
        mButton3.setText(questionsList.get(randList.get(2)).getAnswer());
        mButton3.setTag(randList.get(2));
        mButton4.setText(questionsList.get(randList.get(3)).getAnswer());
        mButton4.setTag(randList.get(3));





    }


    public void  start(){
        OptionsQuestion optionsQuestion = questionFactory.GetOptionsQuestionType();
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.ukgenglish", Context.MODE_PRIVATE);
        Question question = optionsQuestion.getQuestion("------- is a Boy.","He");
        Question question1 = optionsQuestion.getQuestion("------- is a Girl.","She");
        Question question2 = optionsQuestion.getQuestion("------- are  Boys.","They");
        Question question3 = optionsQuestion.getQuestion("------- name is Raddhika.","Her");
        questionsList.add(question);
        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        saveData();

    }
    public void saveData(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(cnxName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(questionsList);
        editor.putString(KYE,json);
        editor.apply();
        Log.i("QA","data saved");

    }
    private void loadData(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(cnxName, Context.MODE_PRIVATE);
        //sharedPreferences.edit().remove(KYE).commit();
        if(sharedPreferences.getString(KYE,null)!= null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(KYE, null);
            Type type = new TypeToken<ArrayList<Question>>() {
            }.getType();
            questionsList = gson.fromJson(json, type);
            if (questionsList == null) {
                questionsList = new ArrayList<>();
            }
            /*Log.i("QA", "data loaded");
            if (questionsList.size() < 1) {
                Log.i("QA", "no data avelable");
            } else {
                int size = questionsList.size();
                Log.i("QA", Integer.toString(questionsList.size()));
                for (int i = 0; i < size; i++) {
                    Log.i("QA", questionsList.get(i).getQuestion());
                }

            }*/
            setList();
        }else{
            start();
            loadData();
            Log.i("QA", "File is not saved");
        }

    }

    public void chooseQuestion(View view) {
        if(view.getTag() != null) {
            chooseTag =(Integer) view.getTag();
            Log.i("select",     Integer.toString(chooseTag));

        }else{
            Log.i("select", "Tag is empty");
        }

    }

    public void selectAnswer(View view) {
        if(view.getTag() != null) {
            selectAnswer = (Integer) view.getTag();
       if(questionsList.get(randList.get(chooseTag)).equals(questionsList.get(randList.get(selectAnswer )))){
            Toast.makeText(this,"You are Right",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"You are WRONG",Toast.LENGTH_LONG).show();
        }
            Log.i("select", Integer.toString(selectAnswer));
        }else{
            Log.i("select", "Tag is empty");
        }
    }
}
