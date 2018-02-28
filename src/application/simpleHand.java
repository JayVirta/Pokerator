package application;

import java.util.ArrayList;
import java.util.Collections;

public class simpleHand 
{
int card1;
int card2;
int card3;
int card4;
int card5;
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

public simpleHand(Card card1, Card card2, Card card3,Card card4, Card card5)
{
	this.card1 = card1.simpleCard;
	this.card2 = card2.simpleCard;
	this.card3 = card3.simpleCard;
	this.card4 = card4.simpleCard;
	this.card5 = card5.simpleCard;
}

public void simpleSort()
{
	ArrayList<Integer> sort = new ArrayList<Integer>();
	sort.add(card1);
	sort.add(card2);
	sort.add(card3);
	sort.add(card4);
	sort.add(card5);
	Collections.sort(sort);
	card1 = sort.get(0);
	card2 = sort.get(1);
	card3 = sort.get(2);
	card4 = sort.get(3);
	card5 = sort.get(4);
}
public boolean isValid()
{
	boolean check = true;
	if(card1 == card2)
		check = false;
	if(card1 == card3)
		check = false;
	if(card1 == card4)
		check = false;
	if(card1 == card5)
		check = false;
	if(card2 == card3)
		check = false;
	if(card2 == card4)
		check = false;
	if(card2 == card5)
		check = false;
	if(card3 == card4)
		check = false;
	if(card3 == card5)
		check = false;
	if(card3 == card5)
		check = false;
	return check;
}
public void setHand(Card card1, Card card2, Card card3,Card card4, Card card5)
{
	this.card1 = card1.simpleCard;
	this.card2 = card2.simpleCard;
	this.card3 = card3.simpleCard;
	this.card4 = card4.simpleCard;
	this.card5 = card5.simpleCard;
	handDetermined = false;
}

public void handDetermine()
{
	simpleSort();
	high = card5;
	royal = isRoyalFlush();
	fullHouse = isFullHouse();
	flush = isFlush();
	straight = isStraight();
	fourOfKind = isFourOfKind();
	threeOfKind = isThreeOfKind();
	twoPair = isTwoPair();
	pairFound = isPair();
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
	if(card1%100 == card2%100)
		check = true;
	if(card2%100 == card3%100)
		check = true;
	if(card3%100 == card4%100)
		check = true;
	if(card4%100 == card5%100)
		check = true;
	return check;
}
private boolean isTwoPair(int[] values)
{
	boolean check = false;
	if(card1%100 == card2%100 && card3%100 == card4%100)
		check = true;
	if(card2%100 == card3%100 && card4%100 == card5%100)
		check = true;
	if(card1%100 == card2%100 && card5%100 == card4%100)
		check = true;
	return check;
}
private boolean isThreeOfKind(int[] values)
{
	boolean check = false;
	if(card2%100 == card1%100 && card1%100 == card3%100)
		check = true;
	if(card2%100 == card3%100 && card2%100 == card4%100)
		check = true;
	if(card3%100 == card4%100 && card3%100 == card5%100)
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
public int getHandValue()
{
	if (handDetermined == false)
		handDetermine();
	return handValue;
}
}


}
