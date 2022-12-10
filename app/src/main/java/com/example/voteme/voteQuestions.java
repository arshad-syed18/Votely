package com.example.voteme;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class voteQuestions {

    String questionText;
    ArrayList<String> options;
    ArrayList<Integer> voteCounts;

    public voteQuestions() {
    }

    public voteQuestions(String questionText, ArrayList<String> options, ArrayList<Integer> voteCounts) {
        this.questionText = questionText;
        this.options = options;
        this.voteCounts = voteCounts;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<Integer> getVoteCounts() {
        return voteCounts;
    }

    public void setVoteCounts(ArrayList<Integer> voteCounts) {
        this.voteCounts = voteCounts;
    }
}
