/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.finances;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void initialize(URL url, ResourceBundle rb) {    // When the App/Window start running it executes the following methods!
        showFinances();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");
            return conn;
        }catch(SQLException ex){
            System.out.println("ERROR getConnection(): " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Finances> getFinancesList(){
        ObservableList<Finances> financesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM myData.finances";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Finances finances;
            while(rs.next()){   // If these is sth. on the list then do:
                finances = new Finances(rs.getInt("id"), rs.getDouble("amount"), 
                        rs.getString("source"), rs.getString("type"), 
                        rs.getDate("date"), rs.getString("notes"));
                financesList.add(finances);
            }
            
            
        }catch(SQLException ex){
            System.out.println("ERROR getFinancesList(): " + ex.getMessage());

        }
        return financesList;
    }
    
    public void showFinances(){
        ObservableList<Finances> list = getFinancesList();
        colId.setCellValueFactory(new PropertyValueFactory<Finances, Integer>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<Finances, Double>("amount"));
        colSource.setCellValueFactory(new PropertyValueFactory<Finances, String>("source"));
        colType.setCellValueFactory(new PropertyValueFactory<Finances, String>("type"));
        colNotes.setCellValueFactory(new PropertyValueFactory<Finances, String>("notes"));
       
        tvFinances.setItems(list);
    }
    
}
