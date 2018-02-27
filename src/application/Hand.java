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
	
	public void handDetermine()
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
		int[] values = new int[] {sortedHand.get(0).getValue(), sortedHand.get(1).getValue(), sortedHand.get(2).getValue(), sortedHand.get(3).getValue(),sortedHand.get(4).getValue()};
		high = values[4];
		royal = isRoyalFlush(values);
		fullHouse = isFullHouse(values);
		flush = isFlush();
		straight = isStraight(values);
		fourOfKind = isFourOfKind(values);
		threeOfKind = isThreeOfKind(values);
		twoPair = isTwoPair(values);
		pairFound = isPair(values);
		if(royal == true)
			handValue = 900 + values[4];
		if(straight == true && flush==true && handValue == 0)
			handValue = 800 + values[4];
		if(fourOfKind == true && handValue == 0)
			handValue = 700 + values[4];
		if(fullHouse == true && handValue == 0)
			handValue = 600 + values[4];
		if(flush == true && handValue == 0)
			handValue = 500 + values[4];
		if(straight == true && handValue == 0)
			handValue = 400 + values[4];
		if(threeOfKind == true && handValue == 0)
			handValue = 300 + values[4];
		if(twoPair == true && handValue == 0)
			handValue = 200 + values[4];
		if(pairFound == true && handValue == 0)
			handValue = 100 + values[4];
		if(handValue == 0)
			handValue = values[5];
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
	private boolean isFlush()
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
	private boolean isStraightFlush(int[] values)
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
			if(values[0] == values[1]-1
			&& values[1] == values[2]-1
			&& values[2] == values[3]-1
			&& values[3] == values[4]-1)
				check = true;
			else
				check = false;
		return check;
	}
	private boolean isRoyalFlush(int[] values)
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
		if(values[0] == 1 && 
				values[1] == 10 &&
				values[2] == 11 &&
				values[3] == 12 &&
				values[4] == 13)
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
}

