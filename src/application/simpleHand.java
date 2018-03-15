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
Card fullCard1;
Card fullCard2;
Card fullCard3;
Card fullCard4;
Card fullCard5;
int handValue = 0;
int critId1= 0;
int critId2= 0;
boolean handDetermined = false;
private boolean pairFound= false;
private boolean twoPair= false;
private boolean threeOfKind= false;
private boolean fourOfKind= false;
private boolean straight= false;
private boolean flush= false;
private boolean fullHouse= false;
private boolean straightFlush= false;
private boolean royal= false;
private int high = 0;
private byte suitId1 = 0;
private byte suitId2 = 0;
private byte suitId3 = 0;
private byte suitId4 = 0;
private byte suitId5 = 0;

public simpleHand(Card card1, Card card2, Card card3,Card card4, Card card5)
{
	this.card1 = card1.simpleCard();
	this.card2 = card2.simpleCard();
	this.card3 = card3.simpleCard();
	this.card4 = card4.simpleCard();
	this.card5 = card5.simpleCard();
	if(card1.getSuit().equals("CLUBS"))
		suitId1= 1;
	if(card2.getSuit().equals("CLUBS"))
		suitId2= 1;
	if(card3.getSuit().equals("CLUBS"))
		suitId3= 1;
	if(card4.getSuit().equals("CLUBS"))
		suitId4= 1;
	if(card5.getSuit().equals("CLUBS"))
		suitId5= 1;
	if(card1.getSuit().equals("DIAMONDS"))
		suitId1= 2;
	if(card2.getSuit().equals("DIAMONDS"))
		suitId2= 2;
	if(card3.getSuit().equals("DIAMONDS"))
		suitId3= 2;
	if(card4.getSuit().equals("DIAMONDS"))
		suitId4= 2;
	if(card5.getSuit().equals("DIAMONDS"))
		suitId5= 2;
	if(card1.getSuit().equals("HEARTS"))
		suitId1= 3;
	if(card2.getSuit().equals("HEARTS"))
		suitId2= 3;
	if(card3.getSuit().equals("HEARTS"))
		suitId3= 3;
	if(card4.getSuit().equals("HEARTS"))
		suitId4= 3;
	if(card5.getSuit().equals("HEARTS"))
		suitId5= 3;
	if(card1.getSuit().equals("SPADES"))
		suitId1= 4;
	if(card2.getSuit().equals("SPADES"))
		suitId2= 4;
	if(card3.getSuit().equals("SPADES"))
		suitId3= 4;
	if(card4.getSuit().equals("SPADES"))
		suitId4= 4;
	if(card5.getSuit().equals("SPADES"))
		suitId5= 4;
	System.out.println(suitId1);
	System.out.println(suitId2);
	System.out.println(suitId3);
	System.out.println(suitId4);
	System.out.println(suitId5);
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
	fourOfKind = isFourOfKind();
	fullHouse = isFullHouse();
	flush = isFlush();
	straight = isStraight();
	threeOfKind = isThreeOfKind();
	twoPair = isTwoPair();
	pairFound = isPair();
	System.out.println(card1);
	System.out.println(card2);
	System.out.println(card3);
	System.out.println(card4);
	System.out.println(card5);
	if(royal == true) 
	{
		if(suitId1 == 1)
			critId1=0x1;
		if(suitId1 == 2)
			critId1=0x2;
		if(suitId1 == 3)
			critId1=0x3;
		if(suitId1 == 4)
			critId1=0x4;
		handValue = 0xA00 + critId1;
		System.out.println(String.format("0x%03X", handValue));
	}
	if(straight == true && flush==true && handValue == 0)
	{
		critId1 = card5-100;
		handValue = 0x900 + critId1;
	}
	if(fourOfKind == true && handValue == 0)
	{
		handValue = 0x800 + critId1+critId2;
	}
	if(fullHouse == true && handValue == 0)
	{
		handValue = 0x700 + critId1+critId2;
	}
	if(flush == true && handValue == 0)
	{
		critId1 = (card5-100)*10;
		critId2 = card4-100;
		handValue = 0x600 + critId1+ critId2;
	}
	if(straight == true && handValue == 0)
	{
		critId1 = card5-100;
		handValue = 0x500 + critId1;
	}
	if(threeOfKind == true && handValue == 0)
	{
		handValue = 0x400 + critId1 + critId2;
	}
	if(twoPair == true && handValue == 0)
	{
		handValue = 0x300 + critId1 + critId2;
	}
	if(pairFound == true && handValue == 0)
	{
		handValue = 0x200 + critId1;
	}
	if(handValue == 0)
	{
		critId1 = card5-100;
		handValue = 0x100 + critId1;
	}
	handDetermined = true;
}
private boolean isPair()
{
	boolean check = false;
	if(card1%100 == card2%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card1-100;
		}
	}
	if(card2%100 == card3%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card2-100;
		}
	}
	if(card3%100 == card4%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card3-100;
		}
	}
	if(card4%100 == card5%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card4-100;
		}
	}
	return check;
}
private boolean isTwoPair()
{
	boolean check = false;
	if(card1%100 == card2%100 && card3%100 == card4%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card1-100;
			critId2= card3-100;
		}
	}
	if(card2%100 == card3%100 && card4%100 == card5%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card2-100;
			critId2= card4-100;
		}
	}
	if(card1%100 == card2%100 && card5%100 == card4%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= card1-100;
			critId2= card4-100;
		}
	}
	return check;
}
private boolean isThreeOfKind()
{
	boolean check = false;
	if(card2%100 == card1%100 && card1%100 == card3%100) {
		check = true;
	if(critId1==0)
	{
		critId1= (card1-100)*10;
		critId2= card5-100;
	}
	}
	if(card2%100 == card3%100 && card2%100 == card4%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= (card2-100)*10;
			critId2= card5-100;
		}
	}
	if(card3%100 == card4%100 && card3%100 == card5%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= (card3-100)*10;
			critId2= card2-100;
		}
	}
	return check;
}
private boolean isFourOfKind()
{
	boolean check = false;
	if(card1%100 == card2%100 && card2%100 == card3%100 && card1%100 == card4%100)
	{
		check = true;
		high = card5;
		critId1= (card1-100)*10;
		critId2= card5-100;
	}
	if(card2%100 == card3%100 && card2%100 == card4%100 && card2%100 == card5%100)
	{
		check = true;
		high = card5;
		critId1= (card5-100)*10;
		critId2= card1-100;
	}
	return check;
}
private boolean isStraight()
{
	boolean check = false;
	if(card1 == card2-1
	&& card2 == card3-1
	&& card3 == card4-1
	&& card4 == card5-1)
		check = true;
	return check;
}
private boolean isFlush()
{
	boolean check = false;
	if(suitId1 == suitId2 && suitId1 == suitId3 && suitId1 == suitId4 && suitId1 == suitId5)
		check = true;
	return check;
}
private boolean isFullHouse()
{
	boolean check = false;
	if(card1%100 == card2%100 && card1%100 == card3%100 && card5%100 == card4%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= (card1-100)*10;
			critId2= card5-100;
		}
	}
	if(card1%100 == card2%100 && card3%100 == card4%100 && card3%100 == card5%100)
	{
		check = true;
		if(critId1==0)
		{
			critId1= (card1-100)*10;
			critId2= card5-100;
		}
	}
	return check;
}
private boolean isStraightFlush()
{
	boolean check = false;
	if(suitId1 == suitId2 && suitId1 == suitId3 && suitId1 == suitId4 && suitId1 == suitId5)
	if(card1 == card2-1
	&& card2 == card3-1
	&& card3 == card4-1
	&& card4 == card5-1
	)
		check = true;
	return check;
}
private boolean isRoyalFlush()
{
	boolean check = false;
	if(suitId1 == suitId2 && suitId1 == suitId3 && suitId1 == suitId4 && suitId1 == suitId5)
	if(card1%100 == 1
	&& card2%100 == 10
	&& card3%100 == 11
	&& card4%100 == 12
	&& card5%100 == 13)
		check = true;
	return check;
}
public String whatIsHand()
{
	if (handDetermined == false)
		handDetermine();
	if(handValue > 0xA00)
		return "This Hand is a Royal Flush!";
	if(handValue > 0x900)
		return "This hand is a Straight Flush with a high of " + high;
	if(handValue > 0x800)
		return "This hand is a Four of Kind of " + critId1/10;
	if(handValue > 0x700)
		return "This hand is a Full House!";
	if(handValue > 0x600)
		return "This hand is a Flush!";
	if(handValue > 0x500)
		return "This hand is a Straight!";
	if(handValue > 0x400)
		return "This hand is a Three of Kind!";
	if(handValue > 0x300)
		return "This hand is a Two Pair!";
	if(handValue > 0x200)
		return "This hand is a Pair!";
	else
		return "This hand is a " + (high-100) + " high!"; 
}
public int getHandValue()
{
	if (handDetermined == false)
		handDetermine();
	return handValue;
}
}



