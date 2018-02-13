package ch.makery.address.model;

import java.io.Serializable;

import ch.makery.address.model.Card;

public class Move implements Serializable{
	//private Card handCard;
	private CardRepresantation card1;
	private CardRepresantation card2;
	private boolean AttackHero = false;
	
	
	public Move()
	{
		this.card1 = null;
		this.card2 = null;
		this.AttackHero = false;
	}
	
	public void setCard1(CardRepresantation card1) {
		this.card1 = card1;
	}

	public void setCard2(CardRepresantation card2) {
		this.card2 = card2;
	}
	
	public CardRepresantation getCard1() {
		return card1;
	}

	public CardRepresantation getCard2() {
		return card2;
	}
	
	public boolean isAttackHero() {
		return AttackHero;
	}
	public void setAttackHero(boolean attackHero) {
		AttackHero = attackHero;
	}
	
}
