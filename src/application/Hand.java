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
boolean handDetermined = false;
private boolean pairFound= false;
private boolean twoPair= false;
private boolean threeOfKind= false;
private boolean fourOfKind= false;
private boolean Straight= false;
private boolean Flush= false;
private boolean fullHouse= false;
private boolean royal= false;


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
	}
	private boolean isPair(int[] values)
	{
		boolean check = false;
		if(values[1] == values[2])
			check = true;
		if(values[2] == values[3])
			check = true;
		if(values[3] == values[4])
			check = true;
		if(values[4] == values[5])
			check = true;
		return check;
	}
	private boolean isTwoPair(int[] values)
	{
		boolean check = false;
		if(values[1] == values[2] && values[3] == values[4])
			check = true;
		if(values[2] == values[3] && values[4] == values[5])
			check = true;
		if(values[1] == values[2] && values[4] == values[5])
			check = true;
		return check;
	}
	private boolean isThreeOfKind(int[] values)
	{
		boolean check = false;
		if(values[1] == values[2] && values[1] == values[3])
			check = true;
		if(values[2] == values[3] && values[2] == values[4])
			check = true;
		if(values[3] == values[4] && values[3] == values[5])
			check = true;
		return check;
	}
	private boolean isFourOfKind(int[] values)
	{
		boolean check = false;
		if(values[1] == values[2] && values[1] == values[3] && values[1] == values[4])
			check = true;
		if(values[2] == values[3] && values[2] == values[4] && values[2] == values[5])
			check = true;
		return check;
	}
	private boolean isStraight(int[] values)
	{
		boolean check = false;
		if(values[1] == values[2]-1
				&& values[2] == values[3]-1
				&& values[3] == values[4]-1
				&& values[4] == values[5]-1)
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
		if(values[1] == values[2] && values[1] == values[3] && values[4] == values[5])
			check = true;
		if(values[2] == values[1] && values[3] == values[4] && values[3] == values[5])
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
			if(values[1] == values[2]-1
			&& values[2] == values[3]-1
			&& values[3] == values[4]-1
			&& values[4] == values[5]-1)
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
		if(values[1] == 1 && 
				values[2] == 10 &&
				values[3] == 11 &&
				values[4] == 12 &&
				values[5] == 13)
					check = true;
		else
			check = false;
		return check;
	}
}

