package com.example.ukgenglish.QuestionBank;

public class FillBlankQuestion implements IFillBlankQuestion {
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
