package ch.makery.address.model;

public enum ProductType
{
	TEXTBOOK
	{
		@Override public String toString()
		{
			return "Podrecznik"; 
		}
	},

	READING
	{
		@Override public String toString()
		{
			return "Lektura"; 
		}
	},
	MANUAL
	{
		@Override public String toString()
		{
			return "Poradnik"; 
		}
	}
}
