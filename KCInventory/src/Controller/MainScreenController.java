/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Part;
import Model.Product;
import inventoryfx.MainScreen;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class MainScreenController implements Initializable {
    
    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;
    
    
    void display(ActionEvent event){
        
    }
    
    
    /* Creation of the allParts TableView's columns  */
    @FXML TableView<Part> tvParts;
    @FXML TableColumn<Part, Integer> partId = new TableColumn<>("Part ID");
    @FXML TableColumn<Part, String> partName = new TableColumn<>("Part Name");   
    @FXML TableColumn<Part, Double>partPrice = new TableColumn<>("Price/Cost");    
    @FXML TableColumn<Part, Integer> partStock = new TableColumn<>("Stock");
    
    /* Creationg of the allProducts TableView's columns  */
    @FXML TableView<Product> tvProducts;
    @FXML TableColumn<Product, Integer> productId = new TableColumn<>("Part ID");
    @FXML TableColumn<Product, String> productName = new TableColumn<>("Part Name");   
    @FXML TableColumn<Product, Double>productPrice;    
    @FXML TableColumn<Product, Integer> productStock = new TableColumn<>("Stock"); 
       
    @FXML TextField searchPartField = new TextField();
    @FXML TextField searchProductField = new TextField();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        // Populate the cells    
        partId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        partPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        partStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        
        
        tvParts.setItems(MainScreen.inv.getAllParts());
        
        productId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        productStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());
        
        tvProducts.setItems(MainScreen.inv.getAllProducts());
        
        TableView.TableViewSelectionModel<Part> tvSelPart = tvParts.getSelectionModel();
        TableView.TableViewSelectionModel<Product> tvSelProduct = tvProducts.getSelectionModel();
       
      
    } 

    
    public static int getModifyPartIndex(){
        return modifyPartIndex;
    }
    
    public static int getModifyProductIndex(){
        return modifyProductIndex;
    }
    
    /* Searches a list of parts for a specific part based on name */
    
    @FXML void searchPart(ActionEvent event) throws IOException {
        int index = -1;
        String partField = searchPartField.getText();
        if(MainScreen.inv.lookupPart(partField) != -1)
        {
            index = MainScreen.inv.lookupPart(partField);
            Part temp = MainScreen.inv.getAllParts().get(index);
            ObservableList<Part> tempList = FXCollections.observableArrayList();
            tempList.add(temp);
            tvParts.setItems(tempList);
            
        }
    } 
    
    @FXML void searchProduct(ActionEvent event) throws IOException {
        
        String productField = searchProductField.getText();
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        tempList = MainScreen.inv.lookupProduct(productField);
        tvProducts.setItems(tempList);

    } 
    
        
    @FXML
    private void openAddPart(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void openModifyPart(ActionEvent event) throws IOException{
        modifyPart = tvParts.getSelectionModel().getSelectedItem();
        modifyPartIndex = MainScreen.inv.getAllParts().indexOf(modifyPart);
        Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyPart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void openModifyProduct(ActionEvent event) throws IOException{
        modifyProduct = tvProducts.getSelectionModel().getSelectedItem();
        modifyProductIndex = MainScreen.inv.getAllProducts().indexOf(modifyProduct);
        Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void openAddProduct(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void deletePart(ActionEvent event) throws IOException{
           
        TilePane r = new TilePane();
        Alert a = new Alert(AlertType.NONE); 
        a.setAlertType(AlertType.CONFIRMATION); 
        a.setContentText("Delete " + tvParts.getSelectionModel().getSelectedItem().getName() + "?"); 
               
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            tvParts.getItems().removeAll(tvParts.getSelectionModel().getSelectedItem());
            tvParts.setItems(MainScreen.inv.getAllParts());
            System.out.println("Product " + tvParts.getSelectionModel().getSelectedItem().getName() + " was removed.");
        } else {
            System.out.println("Product " + tvParts.getSelectionModel().getSelectedItem().getName() + " was not removed.");
        }
               
    }
    
    @FXML
    private void deleteProduct(ActionEvent event) throws IOException{
        TilePane r = new TilePane(); 
        Alert b = new Alert(AlertType.NONE); 
        b.setAlertType(AlertType.CONFIRMATION); 
        b.setContentText("Delete " + tvProducts.getSelectionModel().getSelectedItem().getName() + "?"); 

        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            tvProducts.getItems().removeAll(tvProducts.getSelectionModel().getSelectedItem());
            tvProducts.setItems(MainScreen.inv.getAllProducts());
            System.out.println("Product " + tvProducts.getSelectionModel().getSelectedItem().getName() + " was removed.");
        } else {
            System.out.println("Product " + tvProducts.getSelectionModel().getSelectedItem().getName() + " was not removed.");
          }

        
    }
 
    
    @FXML
    private void exit(ActionEvent event) throws IOException{
        System.exit(0);
    }

    
    
    
}
