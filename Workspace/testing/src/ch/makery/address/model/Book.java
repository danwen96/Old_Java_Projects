package ch.makery.address.model;

import javafx.beans.property.*;

public class Book
{
	 	private final StringProperty name;
	 	private final StringProperty author;
	    private final IntegerProperty amount;
	    private final ObjectProperty<ProductType> type;
	    private final BooleanProperty available;


	    
	    public Book()
	    {
	        this.name = new SimpleStringProperty("New");
	        this.author = new SimpleStringProperty("New");
	        this.amount = new SimpleIntegerProperty(0);
	        this.type = new SimpleObjectProperty<ProductType>(ProductType.TEXTBOOK);
	        this.available = new SimpleBooleanProperty(false);
	    }

	    public Book(String name, String author, int amount, ProductType type, boolean availalbe)
	    {
	        this.name = new SimpleStringProperty(name);
	        this.author = new SimpleStringProperty(author);
	        this.amount = new SimpleIntegerProperty(amount);
	        this.type = new SimpleObjectProperty<ProductType>(type);
	        this.available = new SimpleBooleanProperty(availalbe);
	    
	    }

	
	    public String getStringAuthor()
	    {
	    	return author.get();
	    }
	    
		public StringProperty getAuthor()
		{
			return author;
		}

		public void setAuthor(String author)
		{
			this.author.set(author);
		}
		
		public String getTitle()
		{
			return name.get();
		}
		
		public StringProperty getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name.set(name);;
		}

		public int getIntAmount()
		{
			return amount.get();
		}
		
		public IntegerProperty getAmount()
		{
			return amount;
		}

		public void setAmount(int amount)
		{
			this.amount.set(amount);;
		}
		
		public ProductType getProductType()
		{
			return type.get();
		}
		
		public ObjectProperty<ProductType> getType()
		{
			return type;
		}
		
		public void setType(ProductType type)
		{
			this.type.set(type);;
		}
		
	    public boolean getavailable()
	    {
	        return available.get();
	    }

		public BooleanProperty getAvailable()
		{
			return available;
		}
		
		public void setAvailable(boolean available)
		{
			this.available.set(available);;
		}
}
