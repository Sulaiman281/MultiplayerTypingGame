package org.types.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.types.Singleton;

public class LoginPage {

    @FXML
    private TextField input_field;

    @FXML
    void enter_game(ActionEvent event) {
        if(input_field.getText().isEmpty()) return;
        Singleton singleton = Singleton.getInstance();
        singleton.player.setName(input_field.getText());
        singleton.client.connect(singleton.player.getName());
        singleton.client.update_opponent(singleton.player.getName());
        singleton.stage.setTitle(singleton.player.getName());
        singleton.setHomePage();
    }

}
