package org.types.Views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.types.Singleton;
import org.types.models.Opponent;

public class PlayerProgress {

    public Label wpm;
    @FXML
    private Label player_name;

    @FXML
    private ProgressBar progress;

    @FXML
    void initialize(){

    }

    public void player_setup(){
        player_name.setText(Singleton.getInstance().player.getName());
        Singleton.getInstance().progressBar = progress;
        Singleton.getInstance().player_wpm = wpm;
    }

    public void opponent_setup(Opponent player){
        player_name.setText(player.getName());
        progress.setProgress(player.getProgress());
        wpm.setText(player.getWpm()+"");
    }



}