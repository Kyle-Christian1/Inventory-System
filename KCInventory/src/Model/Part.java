package Model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




 public abstract class Part {
     
    
    private final IntegerProperty idSimple;
    private final StringProperty nameSimple;
    private final DoubleProperty priceSimple;
    private final IntegerProperty stockSimple;
//    private final IntegerProperty minSimple;
//    private final IntegerProperty maxSimple;
    private int min_;
    private int max_;
    

    public Part(){
        
       nameSimple = new SimpleStringProperty();
       stockSimple = new SimpleIntegerProperty();
       idSimple = new SimpleIntegerProperty();
       priceSimple = new SimpleDoubleProperty();
       
    }
    
    
    
    /*********SETTER FUNCTIONS*************/
    /**************************************/
    
    public void setId(int id) {
        this.idSimple.set(id);
    }

    public void setName(String name) {
        this.nameSimple.set(name);
    }

    public void setPrice(double price) {
        this.priceSimple.set(price);
    }

    public void setStock(int stock) {
        this.stockSimple.set(stock);
    }

    public void setMin(int min) {
        min_ = min;
    }

    public void setMax(int max) {
        max_ = max;
    }
    
    
    /************GETTER FUNCTIONS*************/
    /*****************************************/
    
    public IntegerProperty getIdProperty() {
        return idSimple;
    }

    public StringProperty getNameProperty() {
        return nameSimple;
    }

    public DoubleProperty getPriceProperty() {
        return priceSimple;
    }

    public IntegerProperty getStockProperty() {
        return stockSimple;
    }

    public int getId() {
        return this.idSimple.get();
    }

    public String getName() {
        return this.nameSimple.get();
    }

    public double getPrice() {
        return this.priceSimple.get();
    }

    public int getStock() {
        return this.stockSimple.get();
    }

    public int getMin() {
        return min_;
    }

    public int getMax() {
        return max_;
    }
    
    public StringProperty getNameSimple(){
        return nameSimple;
    }
    
    public DoubleProperty getPriceSimple(){
        return priceSimple;
    }
    
    public IntegerProperty getIdSimple(){
        return idSimple;
    }
    
    public IntegerProperty getStockSimple(){
        return stockSimple;
    }
}