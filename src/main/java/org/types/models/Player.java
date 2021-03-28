package org.types.models;

public class Player {

    private String name;
    private float progress;
    private int net_wpm;
    private double gross_wpm;
    private int correct_words;
    private int wrong_words;
    private int correct_keys;
    private int wrong_keys;
    private int accuracy;

    private int min;
    private int sec;

    public void Player(){
        progress = 0;
        net_wpm = 0;
        gross_wpm = 0;
        correct_words = 0;
        wrong_words = 0;
        correct_keys = 0;
        wrong_keys = 0;
        accuracy = 0;
        min = 0;
        sec = 0;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public int getCorrect_words() {
        return correct_words;
    }

    public void setCorrect_words(int correct_words) {
        this.correct_words = correct_words;
    }

    public int getWrong_words() {
        return wrong_words;
    }

    public void setWrong_words(int wrong_words) {
        this.wrong_words = wrong_words;
    }

    public int getCorrect_keys() {
        return correct_keys;
    }

    public void setCorrect_keys(int correct_keys) {
        this.correct_keys = correct_keys;
    }

    public int getWrong_keys() {
        return wrong_keys;
    }

    public void setWrong_keys(int wrong_keys) {
        this.wrong_keys = wrong_keys;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getNet_wpm() {
        return net_wpm;
    }

    public void setNet_wpm(int net_wpm) {
        this.net_wpm = net_wpm;
    }

    public double getGross_wpm() {
        return gross_wpm;
    }

    public void setGross_wpm(double gross_wpm) {
        this.gross_wpm = gross_wpm;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
