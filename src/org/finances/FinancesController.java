/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.finances;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author vasil
 */
public class FinancesController implements Initializable {

    @FXML
    private TextField tfAmount;
    @FXML
    private ChoiceBox<?> cbSource;
    @FXML
    private ChoiceBox<?> cbType;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextArea taNotes;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Finances> tvFinances;
    @FXML
    private TableColumn<Finances, Integer> colId;
    @FXML
    private TableColumn<Finances, Double> colAmount;
    @FXML
    private TableColumn<Finances, String> colSource;
    @FXML
    private TableColumn<Finances, String> colType;
    @FXML
    private TableColumn<Finances, Date> colDate;
    @FXML
    private TableColumn<Finances, String> colNotes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

 
    
}
