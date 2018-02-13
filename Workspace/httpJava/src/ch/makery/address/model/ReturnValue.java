package ch.makery.address.model;

public class ReturnValue {
	private String minDate;
	private String maxDate;
	private double minRate;
	private double maxRate;
	
	public ReturnValue()
	{
		minDate = null;
		maxDate = null;
		minRate = 0;
		maxRate = 0;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public double getMinRate() {
		return minRate;
	}

	public void setMinRate(double minRate) {
		this.minRate = minRate;
	}

	public double getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(double maxRate) {
		this.maxRate = maxRate;
	}
	
}
