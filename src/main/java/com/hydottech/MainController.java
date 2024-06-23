package com.hydottech;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.application.Platform;

public class MainController {
    @FXML
    private StackPane contentPane;

    public void showAddDrug() {
        loadUI("AddDrug");
    }

    public void showViewDrugs() {
        loadUI("ViewDrugs");
    }

    private void loadUI(String ui) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/" + ui + ".fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }
}
