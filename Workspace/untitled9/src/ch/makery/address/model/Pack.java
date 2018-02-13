package ch.makery.address.model;

import java.io.Serializable;

public class Pack implements Serializable{
    private int card1;
    private int card2;
    private Card cardPlayed;
    private boolean isEndTurnClicked;

    public Pack()
    {
        card1 = -1;
        card2 = -1;
        cardPlayed = null;
        isEndTurnClicked = false;
    }

    public Pack(int card1,int card2,Card cardPlayed,boolean isEndTurnClicked)
    {
        this.card1 = card1;
        this.card2 = card2;
        this.cardPlayed = cardPlayed;
        this.isEndTurnClicked = isEndTurnClicked;
    }

    public int getCard1() {
        return card1;
    }

    public void setCard1(int card1) {
        this.card1 = card1;
    }

    public int getCard2() {
        return card2;
    }

    public void setCard2(int card2) {
        this.card2 = card2;
    }

    public Card getCardPlayed() {
        return cardPlayed;
    }

    public void setCardPlayed(Card cardPlayed) {
        this.cardPlayed = cardPlayed;
    }

    public boolean isEndTurnClicked() {
        return isEndTurnClicked;
    }

    public void setEndTurnClicked(boolean isEndTurnClicked) {
        this.isEndTurnClicked = isEndTurnClicked;
    }

}
