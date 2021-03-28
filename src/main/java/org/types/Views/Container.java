package org.types.Views;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.types.Singleton;

public class Container {

    @FXML
    private VBox root;

    @FXML
    void initialize(){
        Singleton.getInstance().mainRoot = root;
    }

}
