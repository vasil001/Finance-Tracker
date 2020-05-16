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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author vasil
 */
public class FinancesController implements Initializable {
    
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfAmount;
    @FXML
    private DatePicker datepicker;
    @FXML
    private TextArea taNotes;
    @FXML
    private TextField tfSource;
    @FXML
    private TextField tfType;
    
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
    private TableColumn<Finances, Integer> colAmount;
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
        if(event.getSource() == btnInsert) {
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if (event.getSource() == btnDelete) {
            deleteRecord();
        }
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
                finances = new Finances(rs.getInt("id"), rs.getInt("amount"), 
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
        colAmount.setCellValueFactory(new PropertyValueFactory<Finances, Integer>("amount"));
        colSource.setCellValueFactory(new PropertyValueFactory<Finances, String>("source"));
        colType.setCellValueFactory(new PropertyValueFactory<Finances, String>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<Finances, Date>("date"));
        colNotes.setCellValueFactory(new PropertyValueFactory<Finances, String>("notes"));
       
        tvFinances.setItems(list);

    }
    
    private void insertRecord(){
        System.out.println("ID: " + tfID.getText());
        System.out.println("Amount: " + tfAmount.getText());
        System.out.println("Source: " + tfSource.getText());
        System.out.println("Type: " + tfType.getText());
        System.out.println("Date: " + datepicker.getValue());
        System.out.println("Notes: " + taNotes.getText());

        String query = "INSERT INTO myData.finances (id, amount, source, type, date, notes) "
                              + "VALUES ("+ tfID.getText()+ "," + tfAmount.getText()+ ",'" + tfSource.getText() + "','" + 
                                  tfType.getText()  + "','" + datepicker.getValue() + "','" + taNotes.getText() + "')";
        executeQuery(query);
        showFinances();
      }
    
    public void updateRecord(){
        String query = "UPDATE myData.finances SET amount = " + tfAmount.getText() + ", source = '" + tfSource.getText() +
                "', type = '" + tfType.getText() + "', date = '" + datepicker.getValue() + "', notes = '" + taNotes.getText() + 
                "' WHERE id = " + tfID.getText() + "";
        executeQuery(query);
        showFinances();
    }
    
    public void deleteRecord(){
        String query = "DELETE FROM myData.finances WHERE id=" + tfID.getText();
        executeQuery(query);
        showFinances();
    }

    public void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;
        
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("Query executed!");
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("ERROR executeQuery..." + ex.getMessage());

        }
    }
    
    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Finances finances = tvFinances.getSelectionModel().getSelectedItem();
        tfID.setText("" + finances.getId());
        tfAmount.setText("" + finances.getAmount());
        datepicker.setValue(convertToLocalDateViaSqlDate(finances.getDate()));
        taNotes.setText(finances.getNotes());
        tfSource.setText(finances.getSource());
        tfType.setText(finances.getType());
    }   
}
