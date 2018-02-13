package ch.makery.address.model;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Card implements Serializable{
    //private boolean handCard ;
    private int hp;
    private int attack;
    private int retaliation;
    private String picName;
    //private ImageView imageview;
    //private Image pic;

    public Card()
    {
        this(0,0,0,"transparent.png");
    }

    public Card(int attack,int retaliation,int hp,String picName)
    {
        this.hp = hp ;
        this.attack = attack ;
        this.retaliation = retaliation ;
        //this.handCard = true;
        //this.imageview = imageview;
        this.picName = picName;
    }




	/*public void setPicture(ImageView picture) {
		this.imageview = picture;
	}*/

	/*public ImageView getImageview() {
		return imageview;
	}

	public void setImageview(ImageView imageview) {
		this.imageview = imageview;
	}*/

	/*public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}*/

	/*public ImageView getPicture() {
		return imageview;
	}*/

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getRetaliation() {
        return retaliation;
    }

    public void setRetaliation(int retaliation) {
        this.retaliation = retaliation;
    }




	/*public IntegerProperty hpProperty() {
		return hp;
	}
	public IntegerProperty attackProperty() {
		return attack;
	}
	public IntegerProperty retaliationProperty() {
		return retaliation;
	}

	public void setHp (int hp)
	{
		this.hp.set(hp);
	}

	public void setAttack (int attack)
	{
		this.attack.set(attack);
	}

	public void setRetaliation (int retaliation)
	{
		this.retaliation.set(retaliation);
	}

	public int getHp()
	{
		return hp.get();
	}

	public int getAttack()
	{
		return attack.get();
	}

	public int getRetaliation()
	{
		return retaliation.get();
	}
*/
}
