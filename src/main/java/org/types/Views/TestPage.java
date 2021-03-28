package org.types.Views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.types.RandomWord;
import org.types.Singleton;

import java.util.ArrayList;

public class TestPage {

    public Button new_game;
    @FXML
    private TextFlow textFlow;

    @FXML
    private TextField text_input;

    private ArrayList<Label> labels = new ArrayList<>();

    private Label activeWord;
    private boolean word_changed = false;

    // set the timer;
    private Timeline timeline;

    @FXML
    void initialize(){
        timeline = new Timeline(Timeline.INDEFINITE,new KeyFrame(Duration.seconds(1),e->{
            Singleton s = Singleton.getInstance();
            s.player.setSec(s.player.getSec()+1);
            if(s.player.getSec() >=59){
                s.player.setMin(s.player.getMin()+1);
                s.player.setSec(0);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        text_input.setOnKeyPressed(e->{
            Singleton s = Singleton.getInstance();
            s.count_wpm();
            s.client.update_opponent(s.player.getName()+","+s.player.getProgress()+","+s.player.getNet_wpm());

            if(!s.typeStarted){
                timeline.play();
                s.typeStarted = true;
            }
            if(word_changed){
                text_input.setText(e.getCharacter().equals(" ") ? "" : e.getCharacter());
                word_changed = false;
            }
            char key = e.getCode().getChar().charAt(0);

            String userInput = text_input.getText();
            String activeWord = this.activeWord.getText();

            if(key == ' '){
                word_changed = true;
                if(userInput.equals(activeWord)){
                    this.activeWord.setStyle(
                            "-fx-background-color: white;\n"+
                                    "-fx-text-fill: green;"
                    );
                    s.player.setCorrect_words(s.player.getCorrect_words()+1);
                }else{
                    this.activeWord.setStyle(
                            "-fx-background-color: white;\n"+
                                    "-fx-text-fill: red;"
                    );
                    s.player.setWrong_words(s.player.getWrong_words()+1);
                }
                this.labels.remove(this.activeWord);
                if(labels.size() <= 0){
                    // it's finished.
                    timeline.stop();
                }else
                    this.activeWord = labels.get(0);
                text_input.setText("");
            }
            s.normalize(Math.abs(69-labels.size()),0,69);
            try {
                if (userInput.equals(activeWord.substring(0, userInput.length()))) {
                    this.activeWord.setStyle(
                            "-fx-background-color: rgb(80,80,80);\n" +
                                    "-fx-text-fill: black;"
                    );
                    s.player.setCorrect_keys(s.player.getCorrect_keys() + 1);
                } else {
                    this.activeWord.setStyle(
                            "-fx-background-color: rgb(80,80,80);\n" +
                                    "-fx-text-fill: red;"
                    );
                    s.player.setWrong_keys(s.player.getWrong_keys()+1);
                }
            } catch (Exception exception) {
                System.out.println("word out of range.");
            }
            if(key == ' ')
                text_input.setText("");
        });

        new_game.setText("Ready.");
        new_game.setOnAction(e->{
        });

        setup();
    }

    private void reset_player() {
        Singleton singleton = Singleton.getInstance();
        singleton.player.setProgress(0);
        singleton.player.setNet_wpm(0);
        singleton.player.setGross_wpm(0);
        singleton.player.setCorrect_words(0);
        singleton.player.setWrong_words(0);
        singleton.player.setCorrect_keys(0);
        singleton.player.setWrong_keys(0);
        singleton.player.setAccuracy(0);
        singleton.player.setMin(0);
        singleton.player.setSec(0);
    }

    public void setup(){
        RandomWord randomWord = new RandomWord();

        String[] words = Singleton.getInstance().client.getWords().split(",");

        int index = 0;
        for (String word : words) {
            Label label = new Label(word);
            label.setFont(Font.font("Arial",24));
            if(index == 0) activeWord = label;
            labels.add(label);
            textFlow.getChildren().addAll(label, new Label(" "));
            index++;
        }
    }
    public void updates(){

    }

}
