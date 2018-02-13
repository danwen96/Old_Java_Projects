package ch.makery.address.model;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    public enum ProductType{
        VEGETABLES("Vegetables"),FRUITS("Fruits"),BAKEDGOODS("Bakedgoods");
    	private String description;
    	private ProductType(String description)
    	{
    		this.description = description;
    	}
    	@Override
    	public String toString()
    	{
    		return description;
    	}
    	
    }

    private final StringProperty name;
    private final IntegerProperty quanity;
    private final ObjectProperty<ProductType> type;
    private final BooleanProperty supplied;
    
    public Product() {
        this(null, 0,Product.ProductType.VEGETABLES);
    }
    
    public Product(String firstName, int quanity,ProductType type)
    {
    	this.name = new SimpleStringProperty(firstName);
    	this.quanity = new SimpleIntegerProperty(quanity);
    	this.type = new SimpleObjectProperty<ProductType> (type);
    	this.supplied = new SimpleBooleanProperty(true);
    }
    
    
    public StringProperty nameProperty()
    {
    	return name;
    }
    
    public IntegerProperty quanityProperty()
    {
    	return quanity;
    }
    
    public ObjectProperty<ProductType> typeProperty()
    {
    	return type;
    }
    
    public BooleanProperty suppliedProperty()
    {
    	return supplied;
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String firstName) {
        this.name.set(firstName);
    }

    public int getQuanity() {
    	
        return quanity.get();
    }

    public void setQuanity(int quanity) {
        this.quanity.set(quanity);
    }

   /* public boolean isSupplied() {
        return supplied;
    }*/

    public void setSupplied(boolean supplied) {
        this.supplied.set(supplied);
    }
    
    public Product.ProductType getType()
    {
    	return type.get();
    }
    
    public void setType(Product.ProductType type)
    {
    	this.type.set(type);
    }
    
    public String getSuppliedString()
    {
    	if(supplied.get())
    	{
    		return "Yes";
    	}
    	else
    	{
    		return "No";
    	}
    }
    
    public boolean getSupplied()
    {
    	return supplied.get();
    }
    
}