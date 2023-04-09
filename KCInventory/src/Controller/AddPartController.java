/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.Part;
import Model.InHouse;
import Model.Outsourced;
import inventoryfx.MainScreen;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hello
 */
public class AddPartController implements Initializable {
    
    int id;
    
    @FXML TextField idField = new TextField();
    @FXML TextField nameField = new TextField();
    @FXML TextField priceField = new TextField();
    @FXML TextField stockField = new TextField();
    @FXML TextField minField = new TextField();
    @FXML TextField maxField = new TextField();
    @FXML TextField machineIdField = new TextField();
    @FXML TextField companyNameField = new TextField();
    
    @FXML Label addPartLabel = new Label();
    
    
    
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    // TODO
        ToggleGroup radioButtonGroup = new ToggleGroup();   
        RadioButton inHouse = addPartInHouseRadio;
        RadioButton outsourced = addPartOutsourcedRadio;
        inHouse.setToggleGroup(radioButtonGroup);
        outsourced.setToggleGroup(radioButtonGroup);
        
        id = MainScreen.inv.getPartIdCount();
        idField.setText("Disabled/Auto-Generate: " + id);
               
    }    
    
    
    
    @FXML RadioButton addPartInHouseRadio = new RadioButton();
    @FXML RadioButton addPartOutsourcedRadio = new RadioButton();
    
    @FXML void inHouseRadioPress(){
        
        addPartLabel.setText("Machine ID");
        machineIdField.setPromptText("mach ID");
    }
    
    @FXML void outsourcedRadioPress(){
        
        addPartLabel.setText("Company Name");
        machineIdField.setPromptText("Company A");
        
    }
    
    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane(); 
  
        // create an alert 
        Alert b = new Alert(Alert.AlertType.NONE); 
  
        
        // set an alert type 
        b.setAlertType(Alert.AlertType.CONFIRMATION); 
        b.setContentText("Close AddPart window? "); 
                
                
        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } 
        
    }
   
    
    @FXML private void save(ActionEvent event){
        
      
        String idText = idField.getText();
        String priceText = priceField.getText();
        String stockText = stockField.getText();
        String nameText = nameField.getText();
        String minText = minField.getText();
        String maxText = maxField.getText();
        String machineIdText = machineIdField.getText();
        String companyNameText = machineIdField.getText();
        
        if (addPartInHouseRadio.isSelected()){
            
            InHouse newInHouse = new InHouse();  
            newInHouse.setId(id);   
            newInHouse.setPrice(Double.parseDouble(priceText));      
            newInHouse.setStock(Integer.parseInt(stockText));           
            newInHouse.setMin(Integer.parseInt(minText));       
            newInHouse.setMax(Integer.parseInt(maxText));     
            newInHouse.setName(nameText);
            newInHouse.setMachineId(Integer.parseInt(machineIdText));
            MainScreen.inv.addPart(newInHouse);
        
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                        
        } else if (addPartOutsourcedRadio.isSelected()){
            
            Outsourced newOutsourced = new Outsourced();  
            newOutsourced.setId(id);   
            newOutsourced.setPrice(Double.parseDouble(priceText));      
            newOutsourced.setStock(Integer.parseInt(stockText));           
            newOutsourced.setMin(Integer.parseInt(minText));       
            newOutsourced.setMax(Integer.parseInt(maxText));     
            newOutsourced.setName(nameText);
            newOutsourced.setCompanyName(companyNameText);
            
            MainScreen.inv.addPart(newOutsourced);
        
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            
            
        } else {
            TilePane r = new TilePane(); 
  
            // create an alert 
            Alert b = new Alert(Alert.AlertType.NONE); 
  
        
            // set an alert type 
             b.setAlertType(Alert.AlertType.CONFIRMATION); 
             b.setContentText("Select outsourced or In-House ~"); 
                
                
            Optional<ButtonType> result = b.showAndWait();
            if (result.get() == ButtonType.OK) {
            
            } 
            System.out.println("Failed to select outsourced or In-House.");
        }
        
        
//        part.setId(id);   
//        part.setPrice(Double.parseDouble(priceText));      
//        part.setStock(Integer.parseInt(stockText));           
//        part.setMin(Integer.parseInt(minText));       
//        part.setMax(Integer.parseInt(maxText));     
//        part.setName(nameText);
//        }
//        
//        Part part = new Part();  
        
        
//        part.setId(id);   
//        part.setPrice(Double.parseDouble(priceText));      
//        part.setStock(Integer.parseInt(stockText));           
//        part.setMin(Integer.parseInt(minText));       
//        part.setMax(Integer.parseInt(maxText));     
//        part.setName(nameText);
//             
//        MainScreen.inv.addPart(part);
//        
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        
    }

    
    

    
}
