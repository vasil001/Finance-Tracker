/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.finances;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author vasil
 */
public class MainController implements Initializable {
    

    @FXML
    private Button btnFinances;
    @FXML
    private Label label;
    @FXML
    private Button btnShowGraphs;
    @FXML
    private LineChart<String, Integer> linechart;
    @FXML
    private Button btnFilter;
    
 
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
          if(event.getSource() == btnFinances){
            Parent part = FXMLLoader.load(getClass().getResource("Finances.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();     
          }else if(event.getSource() == btnShowGraphs){
              showGraphs();
          }else if(event.getSource() == btnFilter){
            Parent part = FXMLLoader.load(getClass().getResource("Filter.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();     
            filterGraphs();
          }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
        
    public void showGraphs(){
        linechart.getData().clear();
        String query = "SELECT amount, date FROM myData.finances ORDER BY date";
        FinancesController f = new FinancesController();
        Connection c = f.getConnection(); 

         try{
            ResultSet rs = c.createStatement().executeQuery(query);
            XYChart.Series<String, Integer>  series = new XYChart.Series<>();
            
            while(rs.next()){   // If these is sth. on the list then do:
                series.getData().add(new XYChart.Data<>(rs.getString(2),rs.getInt(1)));
            }
            
            linechart.getData().add(series);
            
        }catch(SQLException ex){
            System.out.println("ERROR getFinancesList(): " + ex.getMessage());

        }
    }
    
    public void filterGraphs(){
        
    }
}
