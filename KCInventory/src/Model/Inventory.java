package Model;


import inventoryfx.MainScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Inventory {
    
    private static int partIdCount = 0;
    private static int productIdCount = 0;

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
            
    public Inventory(){
        System.out.println("Inventory object created.");
    }

    public Inventory(ObservableList<Part> allParts_) {
        this.allParts = allParts_;
    }

    public void addPart(Part newPart){
       this.allParts.add(newPart);
        System.out.println("Part was added to all parts list");
     
    }

    public void  addProduct(Product newProduct){
        this.allProducts.add(newProduct);
        System.out.println("Product was added to all products list");
    }

//    Part lookupPart(int partId){
//       for (int i = 0; i < allParts.size(); i++){
//            boolean found = false;
//            if (allParts.get(i).getId() == partId){
//                found = true;
//                return allParts.get(i);
//            }
//            
//        }
//    }

    public Product lookupProduct(int productId){
        boolean isFound = false;
        Product product = new Product();
        int size = MainScreen.inv.allProducts.size();
        
            for (int i = 0; i < size; i++){
                if (allProducts.get(i).getId() == productId){
                    isFound = true;
                    product = allProducts.get(i);
                    
                }else{
                  
                    product = new Product();
                    
                }
                
           
            }
            
            return product;
    }

    public int lookupPart(String partName){
        boolean isFound = false;
        int index = -1;
        int size = MainScreen.inv.allParts.size();
        
            for (int i = 0; i < size; i++){
                if (allParts.get(i).getName().equals(partName)){
                    isFound = true;
                    index = i;
                    
                }else{
                  
                    index = -1;
                    
                }
                
           
            }
            
            if (isFound == true){
                return index;
            }else{
                return -1;
            }
    }
    
    public ObservableList<Product> lookupProduct(String productName){
        
        Product product = new Product();
        boolean isFound = false;
        int index = -1;
        int size = MainScreen.inv.allProducts.size();
        
            for (int i = 0; i < size; i++){
                if (allProducts.get(i).getName().equals(productName)){
                    isFound = true;
                    product = allProducts.get(i);
                    
                }else{
                  
                    product = new Product();
                    
                }
                
           
            }
            
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        tempList.add(product);
        
            
        return tempList;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        
        }
    }

    public void updatePart(int index, Part selectedPart){
        this.allParts.set(index, selectedPart);
    }
    
    public void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    public static int getPartIdCount() {
        partIdCount++;
        return partIdCount;
    }
    
    public static int getProductIdCount(){
        productIdCount++;
        return productIdCount;
    }
    
    public ObservableList<Part> getAllParts() {
               
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public boolean deleteProduct(Product selectedProduct){
       for (int i = 0; i < allProducts.size(); i++){
          if (this.allProducts.get(i).getId()  == (selectedProduct.getId())){
              this.allProducts.remove(i);
               System.out.println(selectedProduct.getName() + " was deleted");
               return true;
          }else{
              return false;
          }
       }return false;
      
    }
    
    public boolean deletePart(Part selectedPart){
        for (int i = 0; i < allParts.size(); i++){
          if (this.allParts.get(i).getId()  == (selectedPart.getId())){
              this.allParts.remove(i);
               System.out.println(selectedPart.getName() + " was deleted");
               return true;
          }else{
              return false;
          }
       }return false;    }
  
}
