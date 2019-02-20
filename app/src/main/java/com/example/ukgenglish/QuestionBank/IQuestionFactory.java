package com.example.ukgenglish.QuestionBank;

public interface IQuestionFactory {
    IQptionsQuestion GetOptionsQuestionType();
    IFillBlankQuestion GetFillBlankQuestionType();

}
