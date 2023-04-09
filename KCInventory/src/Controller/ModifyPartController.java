/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import inventoryfx.MainScreen;
import static inventoryfx.MainScreen.inv;
import java.net.URL;
import java.util.Optional;
import static java.util.Optional.of;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


public class ModifyPartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int index = MainScreenController.getModifyPartIndex();
    private boolean isOutsourced;   
    int id;
    InHouse newInHouse = new InHouse();
    Outsourced newOutsourced = new Outsourced();
    
    @FXML TextField modifyPartIdField;
    @FXML TextField modifyPartNameField;
    @FXML TextField modifyPartStockField;
    @FXML TextField modifyPartPriceField;
    @FXML TextField modifyPartMinField;
    @FXML TextField modifyPartMaxField;
    @FXML TextField dynamicField;
    @FXML Label modifyPartLabel; 
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Part part = inv.getAllParts().get(index);
        id = inv.getAllParts().get(index).getId();
        String getName = inv.getAllParts().get(index).getName();
        modifyPartIdField.setText(Integer.toString(id));
        modifyPartNameField.setText(getName);
        modifyPartStockField.setText(Integer.toString(inv.getAllParts().get(index).getStock()));
        modifyPartPriceField.setText(Double.toString(part.getPrice()));
        modifyPartMinField.setText(Integer.toString(part.getMin()));
        modifyPartMaxField.setText(Integer.toString(part.getMax()));
        
        //Create ToggleGroup
        
        ToggleGroup radioButtonGroup = new ToggleGroup();   
        RadioButton inHouse = inHouseRadio;
        RadioButton outsourced = outsourcedRadio;
        inHouse.setToggleGroup(radioButtonGroup);
        outsourced.setToggleGroup(radioButtonGroup);
       
        if(part instanceof Outsourced){
            System.out.println("stuck");
            outsourcedRadioPress();
            System.out.println("got through radio press funtion call");
            outsourced.setSelected(true);
            inHouse.setDisable(true);
            outsourced.setDisable(true);
            Outsourced parts = new Outsourced();
            parts =((Outsourced) MainScreen.inv.getAllParts().get(index));
            dynamicField.setText(parts.getCompanyName());
            System.out.println("WTF");
            
        }else {
            
                inHouseRadioPress();
                inHouse.setSelected(true);
                inHouse.setDisable(true);
                outsourced.setDisable(true);
                dynamicField.setText(Integer.toString(((InHouse) MainScreen.inv.getAllParts().get(index)).getMachineId()));
        }    
    }    
    
    @FXML RadioButton inHouseRadio = new RadioButton();
    @FXML RadioButton outsourcedRadio = new RadioButton();
    
    
    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane(); 
        Alert b = new Alert(Alert.AlertType.NONE); 
        b.setAlertType(Alert.AlertType.CONFIRMATION); 
        b.setContentText("Close ModifyPart window? "); 
          
        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } 
    }
    
    
    void inHouseRadioPress(){
        isOutsourced = false;
        modifyPartLabel.setText("Machine ID");
        dynamicField.setText(Integer.toString(((InHouse) MainScreen.inv.getAllParts().get(index)).getMachineId()));
    }
    
    void outsourcedRadioPress(){
        isOutsourced = true;
        modifyPartLabel.setText("Company Name");
        dynamicField.setText(((Outsourced) MainScreen.inv.getAllParts().get(index)).getCompanyName());
        
        
    }
    
   
    
    @FXML private void save(ActionEvent event){
    
        String idField = modifyPartIdField.getText();
        String nameField = modifyPartNameField.getText();
        String priceField = modifyPartPriceField.getText();
        String stockField = modifyPartStockField.getText();
        String minField = modifyPartMinField.getText();
        String maxField = modifyPartMaxField.getText();
        String switchField = dynamicField.getText();
        
        if (isOutsourced == false){
            
        InHouse newInHousePart = new InHouse();
        newInHousePart.setId(Integer.parseInt(idField));   
        newInHousePart.setPrice(Double.parseDouble(priceField));      
        newInHousePart.setStock(Integer.parseInt(stockField));           
        newInHousePart.setMin(Integer.parseInt(minField));       
        newInHousePart.setMax(Integer.parseInt(maxField));     
        newInHousePart.setName(nameField);
        newInHousePart.setMachineId(Integer.parseInt(switchField));
        inv.updatePart(index, newInHousePart);
        }
        
        if (isOutsourced == true){
            
        Outsourced newOutsourcedPart = new Outsourced();
        newOutsourcedPart.setId(Integer.parseInt(idField));   
        newOutsourcedPart.setPrice(Double.parseDouble(priceField));      
        newOutsourcedPart.setStock(Integer.parseInt(stockField));           
        newOutsourcedPart.setMin(Integer.parseInt(minField));       
        newOutsourcedPart.setMax(Integer.parseInt(maxField));     
        newOutsourcedPart.setName(nameField);
        newOutsourcedPart.setCompanyName(switchField);
        inv.updatePart(index, newOutsourcedPart);
        
        }
        
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        
    }
  
}
