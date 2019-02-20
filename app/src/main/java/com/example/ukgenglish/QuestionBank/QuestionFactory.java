package com.example.ukgenglish.QuestionBank;

public class QuestionFactory implements IQuestionFactory {
    @Override
    public OptionsQuestion GetOptionsQuestionType() {
        return new OptionsQuestion();
    }

    @Override
    public FillBlankQuestion GetFillBlankQuestionType() {
        return new FillBlankQuestion();
    }
}
