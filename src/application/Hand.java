package application;
import java.util.*;

public class Hand
{
Card card1;
Card card2;
Card card3;
Card card4;
Card card5;
int handValue;
private boolean pairFound;
private boolean twoPair;
private boolean threeOfKind;
private boolean fourOfKind;
private boolean Straight;
private boolean Flush;
private boolean fullHouse;
private boolean royal;


	public Hand(Card card1, Card card2, Card card3,Card card4, Card card5)
	{
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
		this.card5 = card5;
	}
	
	public String handDetermine()
	{
		ArrayList<Card> sortedHand = new ArrayList<Card>();
		sortedHand.add(card1);
		sortedHand.add(card2);
		sortedHand.add(card3);
		sortedHand.add(card4);
		sortedHand.add(card5);
		Collections.sort(sortedHand);
		System.out.println(sortedHand.get(0).getValue());
		System.out.println(sortedHand.get(1).getValue());
		System.out.println(sortedHand.get(2).getValue());
		System.out.println(sortedHand.get(3).getValue());
		System.out.println(sortedHand.get(4).getValue());
		
		return "";
	}
	private boolean isPair()
	{
		return pairFound;
	}
	private boolean isTwoPair()
	{
		return twoPair;
	}
	private boolean isThreeOfKind()
	{
		return threeOfKind;
	}
	private boolean isFourOfKind()
	{
		return fourOfKind;
	}
	private boolean isStraight()
	{
		return Straight;
	}
	private boolean isFlush()
	{
		return Flush;
	}
	private boolean isFullHouse()
	{
		return true;
	}
	private boolean isStraightFlush()
	{
		return true;
	}
	private boolean isRoyalFlush()
	{
		return true;
	}
}

