package com.example.voteme;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class voteQuestions {

    String questionText;
    ArrayList<String> options;
    ArrayList<Integer> voteCounts;
    String voteQuesstionId;



    public voteQuestions() {
    }

    public voteQuestions(String questionText, ArrayList<String> options, ArrayList<Integer> voteCounts, String voteQuesstionId) {
        this.questionText = questionText;
        this.options = options;
        this.voteCounts = voteCounts;
        this.voteQuesstionId = voteQuesstionId;
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

    public String getVoteQuesstionId() {
        return voteQuesstionId;
    }

    public void setVoteQuesstionId(String voteQuesstionId) {
        this.voteQuesstionId = voteQuesstionId;
    }
}
