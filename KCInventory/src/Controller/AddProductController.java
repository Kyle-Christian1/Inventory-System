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


public class AddProductController implements Initializable {

    private int id;
    private ObservableList<Part> productParts = FXCollections.observableArrayList();
    
    int index = MainScreenController.getModifyProductIndex();

    /* Initializes the controller class. */
    
    @FXML TextField addProductIdField = new TextField();
    @FXML TextField addProductNameField = new TextField();
    @FXML TextField addProductPriceField = new TextField();
    @FXML TextField addProductStockField = new TextField();
    @FXML TextField addProductMinField = new TextField();
    @FXML TextField addProductMaxField = new TextField();
    
    @FXML TextField addProductSearchField = new TextField();
    
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
    @FXML TableColumn<Part, Double>productPrice = new TableColumn<>("Price/Cost");    
    @FXML TableColumn<Part, Integer> productStock = new TableColumn<>("Stock"); 
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id = MainScreen.inv.getProductIdCount();
        addProductIdField.setText("Auto Generate: " + id);
        Part part = tvParts.getSelectionModel().getSelectedItem();
       
        
        partId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        partPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        partStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        
        
        tvParts.setItems(MainScreen.inv.getAllParts());
        
        productId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        productStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        
        
                
        TableView.TableViewSelectionModel<Part> tvSelPart = tvParts.getSelectionModel();
        TableView.TableViewSelectionModel<Part> tvProductPart = tvProductParts.getSelectionModel();
        
        
    }    
    
    public ObservableList<Part> getProductParts(){
        return this.productParts;
    }
    
    @FXML private void save(ActionEvent event){
        
        String idField = addProductIdField.getText();
        String priceField = addProductPriceField.getText();
        String stockField = addProductStockField.getText();
        String nameField = addProductNameField.getText();
        String minField = addProductMinField.getText();
        String maxField = addProductMaxField.getText();
        
        Product product = new Product();  
        
        product.setId(id);   
        product.setPrice(Double.parseDouble(priceField));      
        product.setStock(Integer.parseInt(stockField));           
        product.setMin(Integer.parseInt(minField));       
        product.setMax(Integer.parseInt(maxField));     
        product.setName(nameField);
        
        for (int i = 0; i < productParts.size(); i++)
        {
            product.addAssociatedPart(productParts.get(i));
        }
        
       
        
        
        
        if (productParts.isEmpty()){
            TilePane r = new TilePane(); 
            Alert b = new Alert(Alert.AlertType.NONE); 
            b.setAlertType(Alert.AlertType.CONFIRMATION); 
            b.setContentText("Products must have at least 1 part."); 
                                
            Optional<ButtonType> result = b.showAndWait();
            if (result.get() == ButtonType.OK) {
                
            } 
            }else {
                MainScreen.inv.addProduct(product);
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }
        }
    
    ObservableList<Part> parts = FXCollections.observableArrayList();
    
    @FXML private void add(ActionEvent event){
       
        Part part = tvParts.getSelectionModel().getSelectedItem();
        productParts.add(part);
        tvProductParts.setItems(productParts);
                     
    }
    
    @FXML void searchProduct(ActionEvent event) throws IOException {
        
        
        int index = -1;
        String partField = addProductSearchField.getText();
                
        if(MainScreen.inv.lookupPart(partField) != -1)
        {
            index = MainScreen.inv.lookupPart(partField);
            Part temp = MainScreen.inv.getAllParts().get(index);
            ObservableList<Part> tempList = FXCollections.observableArrayList();
            tempList.add(temp);
            tvParts.setItems(tempList);
            
        }

    } 
    
    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane(); 
        Alert b = new Alert(Alert.AlertType.NONE); 
        b.setAlertType(Alert.AlertType.CONFIRMATION); 
        b.setContentText("Close AddProduct window? "); 
                                
        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } 
    }
        
    @FXML
    private void deletePart(ActionEvent event) throws IOException{
      
        Part part = tvProductParts.getSelectionModel().getSelectedItem();
        productParts.remove(part);
        tvProductParts.setItems(productParts);
        
        
    }
    
}
