package com.hydottech;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewDrugsController {
    @FXML
    private TableView<Drug> drugTable;
    @FXML
    private TableColumn<Drug, String> drugCodeCol;
    @FXML
    private TableColumn<Drug, String> nameCol;
    @FXML
    private TableColumn<Drug, String> descriptionCol;
    @FXML
    private TableColumn<Drug, Integer> stockCol;
    @FXML
    private TableColumn<Drug, Double> priceCol;

    @FXML
    public void initialize() {
        drugCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        drugTable.setItems(Database.getAllDrugs());
    }
}
