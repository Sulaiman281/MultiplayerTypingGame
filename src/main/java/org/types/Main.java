package org.types;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Singleton singleton = Singleton.getInstance();
        singleton.stage = stage;
        singleton.changeScene("Views/LoginPage.fxml");
    }

    public static void main(String[] args){
        launch(args);
    }
}
