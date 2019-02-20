package com.example.ukgenglish.QuestionBank;

public class OptionsQuestion implements IQptionsQuestion {

    Question question;
    @Override
    public Question GetQuestion() {
        return null;
    }
    public Question getQuestion(String qsn,String ans){
        question = new Question();
        question.setQuestion(qsn);
        question.setAnswer(ans);
        return question;
    }
}
