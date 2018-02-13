package ch.makery.address.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardRepresantation {
    private ImageView imageview;
    private Image image;
    private Label attackLabel;
    private Label retalationLabel;
    private Label hpLabel;
    private Card card;
    //private boolean ifMoved = false;

    public CardRepresantation()
    {
        this(null,null,null,null,null,null);
    }

    public CardRepresantation(ImageView imageview,Label attackLabel,Label retalationLabel,Label hpLabel,Image image,Card card)
    {
        this.imageview = imageview;
        this.attackLabel = attackLabel;
        this.retalationLabel = retalationLabel;
        this.hpLabel = hpLabel;
        this.image = image;
        this.card = new Card();
    }

    //Getters and setters
	/*public boolean isIfMoved() {
		return ifMoved;
	}
	public void setIfMoved(boolean ifMoved) {
		this.ifMoved = ifMoved;
	}*/
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public ImageView getImageview() {
        return imageview;
    }
    public void setImageview(ImageView imageview) {
        this.imageview = imageview;
    }
    public Label getAttackLabel() {
        return attackLabel;
    }
    public void setAttackLabel(Label attackLabel) {
        this.attackLabel = attackLabel;
    }
    public Label getRetalationLabel() {
        return retalationLabel;
    }
    public void setRetalationLabel(Label retalationLabel) {
        this.retalationLabel = retalationLabel;
    }
    public Label getHpLabel() {
        return hpLabel;
    }
    public void setHpLabel(Label hpLabel) {
        this.hpLabel = hpLabel;
    }
    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
}
