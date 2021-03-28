package org.types;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.types.Views.PlayerProgress;
import org.types.models.Opponent;
import org.types.models.Player;
import org.types.network.Client;

import java.io.IOException;
import java.util.ArrayList;

public class Singleton {

    public Stage stage;

    public Player player;

    public ProgressBar progressBar;
    public Label player_wpm;

    public VBox mainRoot;

    public Client client;

    public boolean typeStarted = false;

    private VBox players_box;

    public ArrayList<Opponent> opponents = new ArrayList<>();


    private static final Singleton singleton = new Singleton();

    // private constructor and setup.
    private Singleton(){
        player = new Player();
        client = new Client();
        players_box = new VBox();

        players_box.setAlignment(Pos.TOP_CENTER);
        players_box.setSpacing(20);
    }

    public static Singleton getInstance(){
        return singleton;
    }

    public FXMLLoader fxmlLoader(String _file){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(_file));
        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public void changeScene(String _scene){
        stage.setScene(new Scene(fxmlLoader(_scene).getRoot()));
        stage.show();
    }

    public void setHomePage(){

        changeScene("Views/Container.fxml");
        mainRoot.getChildren().add(players_box);

        FXMLLoader loader = fxmlLoader("Views/PlayerProgress.fxml");
        mainRoot.getChildren().add(loader.getRoot());
        PlayerProgress controller = loader.getController();
        controller.player_setup();

        new AnimationTimer(){

            @Override
            public void handle(long l) {
                players_box.getChildren().clear();
                for (Opponent opponent : opponents) {
                    FXMLLoader ld = fxmlLoader("Views/PlayerProgress.fxml");
                    players_box.getChildren().add(ld.getRoot());
                    PlayerProgress controller = ld.getController();
                    controller.opponent_setup(opponent);
                }
            }
        }.start();
        mainRoot.getChildren().add(fxmlLoader("Views/TestPage.fxml").getRoot());

        stage.setWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getBounds().getHeight());
    }

    public void count_wpm(){
        gross_wpm();
        net_wpm();
        accuracy();
    }

    private void net_wpm(){
        int x = (player.getWrong_keys()*5)/10;
        player.setNet_wpm((int) Math.abs(player.getGross_wpm()-x));
        player_wpm.setText(player.getNet_wpm()+"");
    }
    private void gross_wpm(){
        double keys = player.getCorrect_keys()+player.getWrong_keys();
        player.setGross_wpm(keys/5);
    }
    private void accuracy(){
        player.setAccuracy((int) Math.round(player.getNet_wpm()/player.getGross_wpm()*100));
    }

    public void normalize(int value, int min, int max){
        player.setProgress((float)(value - min) / (max - min));
        progressBar.setProgress(player.getProgress());
    }
}
