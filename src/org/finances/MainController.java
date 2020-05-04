/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.finances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private void handleButtonAction(ActionEvent event) {
          if(event.getSource() == btnFinances){
              System.out.println("Works!");
              
          }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
