package application;
import java.util.*;

public class Hand
{
Card card1;
Card card2;
Card card3;
Card card4;
Card card5;
int handValue = 0;
boolean handDetermined = false;
private boolean pairFound= false;
private boolean twoPair= false;
private boolean threeOfKind= false;
private boolean fourOfKind= false;
private boolean straight= false;
private boolean flush= false;
private boolean fullHouse= false;
private boolean royal= false;
private int high = 0;



	public Hand(Card card1, Card card2, Card card3,Card card4, Card card5)
	{
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
		this.card5 = card5;
	}
	public void sortHand()
	{
		ArrayList<Card> sortedHand = new ArrayList<Card>();
		sortedHand.add(card1);
		sortedHand.add(card2);
		sortedHand.add(card3);
		sortedHand.add(card4);
		sortedHand.add(card5);
		Collections.sort(sortedHand);
		card1 = sortedHand.get(0);
		card2 = sortedHand.get(1);
		card3 = sortedHand.get(2);
		card4 = sortedHand.get(3);
		card5 = sortedHand.get(4);
	}
	public boolean isValid()
	{
		boolean check = true;
		if(card1.getValue() == card2.getValue() && card1.getSuit().equals(card2.getSuit()))
			check = false;
		if(card1.getValue() == card3.getValue() && card1.getSuit().equals(card3.getSuit()))
			check = false;
		if(card1.getValue() == card4.getValue() && card1.getSuit().equals(card4.getSuit()))
			check = false;
		if(card1.getValue() == card5.getValue() && card1.getSuit().equals(card5.getSuit()))
			check = false;
		if(card2.getValue() == card3.getValue() && card2.getSuit().equals(card3.getSuit()))
			check = false;
		if(card2.getValue() == card4.getValue() && card2.getSuit().equals(card4.getSuit()))
			check = false;
		if(card2.getValue() == card5.getValue() && card2.getSuit().equals(card5.getSuit()))
			check = false;
		if(card3.getValue() == card4.getValue() && card3.getSuit().equals(card4.getSuit()))
			check = false;
		if(card3.getValue() == card5.getValue() && card3.getSuit().equals(card5.getSuit()))
			check = false;
		if(card4.getValue() == card5.getValue() && card4.getSuit().equals(card5.getSuit()))
			check = false;
		return check;
	}
	public void setHand(Card card1, Card card2, Card card3,Card card4, Card card5)
	{
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
		this.card5 = card5;
		handDetermined = false;
	}
	public String getHand()
	{
		sortHand();
		return card1.getSuit() + card1.getValue() + ","+card2.getSuit() + card2.getValue() + ","+card3.getSuit() + card3.getValue() + ","+card4.getSuit() + card4.getValue() + ","+card5.getSuit() + card5.getValue();
	}
	public void handDetermine()
	{
		ArrayList<Card> sortedHand = new ArrayList<Card>();
		sortedHand.add(card1);
		sortedHand.add(card2);
		sortedHand.add(card3);
		sortedHand.add(card4);
		sortedHand.add(card5);
		Collections.sort(sortedHand);
		int[] values = new int[] {sortedHand.get(0).getValue(), sortedHand.get(1).getValue(), sortedHand.get(2).getValue(), sortedHand.get(3).getValue(),sortedHand.get(4).getValue()};
		high = values[4];
		fullHouse = isFullHouse(values);
		flush = isFlush();
		straight = isStraight(values);
		fourOfKind = isFourOfKind(values);
		threeOfKind = isThreeOfKind(values);
		twoPair = isTwoPair(values);
		pairFound = isPair(values);
		if(royal == true)
			handValue = 900 + high;
		if(straight == true && flush==true && handValue == 0)
			handValue = 800 + high;
		if(fourOfKind == true && handValue == 0)
			handValue = 700 + high;
		if(fullHouse == true && handValue == 0)
			handValue = 600 + high;
		if(flush == true && handValue == 0)
			handValue = 500 + high;
		if(straight == true && handValue == 0)
			handValue = 400 + high;
		if(threeOfKind == true && handValue == 0)
			handValue = 300 + high;
		if(twoPair == true && handValue == 0)
			handValue = 200 + high;
		if(pairFound == true && handValue == 0)
			handValue = 100 + high;
		if(handValue == 0)
			handValue = values[4];
		handDetermined = true;
	}
	private boolean isPair(int[] values)
	{
		boolean check = false;
		if(values[0] == values[1])
			check = true;
		if(values[1] == values[2])
			check = true;
		if(values[2] == values[3])
			check = true;
		if(values[3] == values[4])
			check = true;
		return check;
	}
	private boolean isTwoPair(int[] values)
	{
		boolean check = false;
		if(values[0] == values[1] && values[2] == values[3])
			check = true;
		if(values[1] == values[2] && values[3] == values[4])
			check = true;
		if(values[0] == values[1] && values[3] == values[4])
			check = true;
		return check;
	}
	private boolean isThreeOfKind(int[] values)
	{
		boolean check = false;
		if(values[0] == values[1] && values[0] == values[2])
			check = true;
		if(values[1] == values[2] && values[1] == values[3])
			check = true;
		if(values[2] == values[3] && values[2] == values[4])
			check = true;
		return check;
	}
	private boolean isFourOfKind(int[] values)
	{
		boolean check = false;
		if(values[0] == values[1] && values[0] == values[2] && values[0] == values[3])
		{
			check = true;
			high = values[0];
		}
		if(values[1] == values[2] && values[1] == values[3] && values[1] == values[4])
		{
			check = true;
			high = values[1];
		}
		return check;
	}
	private boolean isStraight(int[] values)
	{
		boolean check = false;
		if(values[0] == values[1]-1
				&& values[1] == values[2]-1
				&& values[2] == values[3]-1
				&& values[3] == values[4]-1)
			check = true;
		return check;
	}
	public boolean isFlush()
	{
		boolean check = true;
		String control = card1.getSuit();
		if(!card2.getSuit().equals(control))
			check = false;
		if(!card3.getSuit().equals(control))
			check = false;
		if(!card4.getSuit().equals(control))
			check = false;
		if(!card5.getSuit().equals(control))
			check = false;
		return check;
	}
	private boolean isFullHouse(int[] values)
	{
		boolean check = false;
		if(values[0] == values[1] && values[0] == values[2] && values[3] == values[4])
			check = true;
		if(values[1] == values[0] && values[2] == values[3] && values[2] == values[4])
			check = true;
		return check;
	}
	public boolean isStraightFlush()
	{
		boolean check = true;
		String control = card1.getSuit();
		if(!card2.getSuit().equals(control))
			check = false;
		if(!card3.getSuit().equals(control))
			check = false;
		if(!card4.getSuit().equals(control))
			check = false;
		if(!card5.getSuit().equals(control))
			check = false;
		if(check=true)
			if(card1.getValue() == card2.getValue()-1
			&& card2.getValue() == card3.getValue()-1
			&& card3.getValue() == card4.getValue()-1
			&& card4.getValue() == card5.getValue()-1)
				check = true;
			else
				check = false;
		return check;
	}
	public boolean isRoyalFlush()
	{
		boolean check = true;
		String control = card1.getSuit();
		if(!card2.getSuit().equals(control))
			check = false;
		if(!card3.getSuit().equals(control))
			check = false;
		if(!card4.getSuit().equals(control))
			check = false;
		if(!card5.getSuit().equals(control))
			check = false;
		if(check=true)
		if(card1.getValue() == 1 && 
				card2.getValue() == 10 &&
				card3.getValue() == 11 &&
				card4.getValue() == 12 &&
				card5.getValue() == 13)
					check = true;
		else
			check = false;
		return check;
	}
	public String whatIsHand()
	{
		if (handDetermined == false)
			handDetermine();
		if(handValue > 900)
			return "This Hand is a Royal Flush!";
		if(handValue > 800)
			return "This hand is a Straight Flush with a high of " + high;
		if(handValue > 700)
			return "This hand is a Four of Kind of " + high;
		if(handValue > 600)
			return "This hand is a Full House!";
		if(handValue > 500)
			return "This hand is a Flush!";
		if(handValue > 400)
			return "This hand is a Straight!";
		if(handValue > 300)
			return "This hand is a Three of Kind!";
		if(handValue > 200)
			return "This hand is a Two Pair!";
		if(handValue > 100)
			return "This hand is a Pair!";
		else
			return "This hand is a " + high + " high!"; 
	}
	public int getHandValue()
	{
		if (handDetermined == false)
			handDetermine();
		return handValue;
	}
}

