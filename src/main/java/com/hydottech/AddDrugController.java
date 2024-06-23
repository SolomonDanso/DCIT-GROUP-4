package com.hydottech;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class AddDrugController {
    @FXML
    private TextField drugCode;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField stock;
    @FXML
    private TextField price;

    @FXML
    public void handleAddDrug() {
        String code = drugCode.getText();
        String drugName = name.getText();
        String drugDescription = description.getText();
        int drugStock = Integer.parseInt(stock.getText());
        double drugPrice = Double.parseDouble(price.getText());

        Database.addDrug(new Drug(code, drugName, drugDescription, drugStock, drugPrice));

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Drug Added");
        alert.setHeaderText(null);
        alert.setContentText("Drug has been added successfully!");
        alert.showAndWait();

        // Clear the fields after adding the drug
        drugCode.clear();
        name.clear();
        description.clear();
        stock.clear();
        price.clear();
    }
}
