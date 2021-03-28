package org.types.models;

public class Opponent {

    private String name;
    private float progress;
    private int wpm;
    private boolean ready;

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

    public String toString(){
        return getName()+","+getProgress()+","+getWpm();
    }

    public int getWpm() {
        return wpm;
    }

    public void setWpm(int wpm) {
        this.wpm = wpm;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
