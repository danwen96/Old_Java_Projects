package ch.makery.address.model;

import java.io.Serializable;

public class Line implements Serializable{
	private String nazwa;
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private boolean theEnd;
	
	

	public Line()
	{
		nazwa = "probna";
		x1 = -1;
		y1 = -1;
		x2 = -1;
		y2 = -1;
		theEnd = false;
	}
	
	public boolean isTheEnd() {
		return theEnd;
	}

	public void setTheEnd(boolean theEnd) {
		this.theEnd = theEnd;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x) {
		this.x1 = x;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y) {
		this.y1 = y;
	}
	
	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
}
