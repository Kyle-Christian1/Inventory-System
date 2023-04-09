/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Part;
import Model.Product;
import inventoryfx.MainScreen;
import static inventoryfx.MainScreen.inv;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hello
 */


public class ModifyProductController implements Initializable {
    
    int index = MainScreenController.getModifyProductIndex();
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    private int id;

    /* Initializes the controller class. */
    
    @FXML TextField modifyProductIdField = new TextField();
    @FXML TextField modifyProductNameField = new TextField();
    @FXML TextField modifyProductPriceField = new TextField();
    @FXML TextField modifyProductStockField = new TextField();
    @FXML TextField modifyProductMinField = new TextField();
    @FXML TextField modifyProductMaxField = new TextField();
    
    @FXML TextField modifyProductSearchField = new TextField();
    
    
     /* Creation of the allParts TableView's columns  */
    @FXML TableView<Part> tvParts;
    @FXML TableColumn<Part, Integer> partId = new TableColumn<>("Part ID");
    @FXML TableColumn<Part, String> partName = new TableColumn<>("Part Name");   
    @FXML TableColumn<Part, Double>partPrice = new TableColumn<>("Price/Cost");    
    @FXML TableColumn<Part, Integer> partStock = new TableColumn<>("Stock");
    
    /* Creationg of the allProducts TableView's columns  */
    @FXML TableView<Part> tvProductParts;
    @FXML TableColumn<Part, Integer> productId = new TableColumn<>("Part ID");
    @FXML TableColumn<Part, String> productName = new TableColumn<>("Part Name");   
    @FXML TableColumn<Part, Double>productPrice;    
    @FXML TableColumn<Part, Integer> productStock = new TableColumn<>("Stock"); 
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        partId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        partPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        partStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        
        
        tvParts.setItems(MainScreen.inv.getAllParts());
        
        productId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        productStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        
        tvProductParts.setItems(MainScreen.inv.getAllProducts().get(index).getAllAssociatedParts());
//        Product selProduct = tvSelProductParts.getSelectionModel().getSelectedItem();
//        int selProductIndex = MainScreen.inv.getAllProducts().indexOf(selProduct);
//        TableView.TableViewSelectionModel<Part> tvSelProductParts = tvProductParts.getSelectionModel().
//        ObservableList<Part> tempList = FXCollections.observableArrayList();
//        tempList = MainScreen.inv.getAllProducts().get(selProductIndex).getAllAssociatedParts();
//        tvProductParts.setItems(MainScreen.inv.getAllProducts().get(selProductIndex).getAllAssociatedParts());
//        
        TableView.TableViewSelectionModel<Part> tvSelPart = tvParts.getSelectionModel();
        TableView.TableViewSelectionModel<Part> tvSelProductParts = tvProductParts.getSelectionModel();
        
        Product product = inv.getAllProducts().get(index);
        id = inv.getAllProducts().get(index).getId();
        String getName = inv.getAllProducts().get(index).getName();
        modifyProductIdField.setText(Integer.toString(id));
        modifyProductNameField.setText(getName);
        modifyProductStockField.setText(Integer.toString(inv.getAllProducts().get(index).getStock()));
        modifyProductPriceField.setText(Double.toString(product.getPrice()));
        modifyProductMinField.setText(Integer.toString(product.getMin()));
        modifyProductMaxField.setText(Integer.toString(product.getMax()));
    }    
    
    @FXML private void save(ActionEvent event){
        
        String idField = modifyProductIdField.getText();
        String priceField = modifyProductPriceField.getText();
        String stockField = modifyProductStockField.getText();
        String nameField = modifyProductNameField.getText();
        String minField = modifyProductMinField.getText();
        String maxField = modifyProductMaxField.getText();
        
        Product product = new Product();  
        
        product.setId(id);   
        product.setPrice(Double.parseDouble(priceField));      
        product.setStock(Integer.parseInt(stockField));           
        product.setMin(Integer.parseInt(minField));       
        product.setMax(Integer.parseInt(maxField));     
        product.setName(nameField);
     
        
        inv.updateProduct(index, product);
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML void searchProduct(ActionEvent event) throws IOException {
        
        
        int index = -1;
        String partField = modifyProductSearchField.getText();
        
        
        if(MainScreen.inv.lookupPart(partField) != -1)
        {
            index = MainScreen.inv.lookupPart(partField);
            Part temp = MainScreen.inv.getAllParts().get(index);
            ObservableList<Part> tempList = FXCollections.observableArrayList();
            tempList.add(temp);
            tvParts.setItems(tempList);
            
        }

    } 
    
    ObservableList<Part> parts = FXCollections.observableArrayList();
    
    @FXML private void add(ActionEvent event){
       
        Part part = tvParts.getSelectionModel().getSelectedItem();
        MainScreen.inv.getAllProducts().get(index).getAllAssociatedParts().add(part);
        tvProductParts.setItems(MainScreen.inv.getAllProducts().get(index).getAllAssociatedParts());
        
        
                     
    }
    
    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane(); 
  
        // create an alert 
        Alert b = new Alert(Alert.AlertType.NONE); 
  
        
        // set an alert type 
        b.setAlertType(Alert.AlertType.CONFIRMATION); 
        b.setContentText("Close ModifyProduct window? "); 
                
                
        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } 
    }
    
    @FXML
    private void deletePart(ActionEvent event) throws IOException{
        
        if (MainScreen.inv.getAllProducts().get(index).getAllAssociatedParts().size() != 1){
            Part part = tvProductParts.getSelectionModel().getSelectedItem();
            MainScreen.inv.getAllProducts().get(index).deleteAssociatedPart(part);
        }else{
            TilePane r = new TilePane();
            Alert b = new Alert(Alert.AlertType.NONE); 
            b.setAlertType(Alert.AlertType.CONFIRMATION); 
            b.setContentText("Products cannot have less than 1 part");                 
            Optional<ButtonType> result = b.showAndWait();
            if (result.get() == ButtonType.OK) {
                
            } 
        
        }     
    }
    
}
