package ch.makery.address.model;

import java.awt.image.BufferedImage;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {
	private BufferedImage part;
	private int num;


	public Tile(double width,double height, BufferedImage part,int num )
	{
		super(width,height);
		this.part = part;
		this.num = num;
	}
	
	public Tile(BufferedImage part,int num)
	{
		this.part = part;
		this.num = num;
	}
	
	
	//getters and setters
	public BufferedImage getPart() {
		return part;
	}

	public void setPart(BufferedImage part) {
		this.part = part;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
}
