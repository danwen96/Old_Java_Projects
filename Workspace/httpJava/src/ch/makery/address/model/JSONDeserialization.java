package ch.makery.address.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Map;


public class JSONDeserialization //implements Serializable
{

	private String base;
	private Date date;
	private Map<String,Double> rates;
	
	
	JSONDeserialization()
	{}
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
}
