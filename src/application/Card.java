package application;

public class Card implements Comparable
{
String suit;
int value;

public Card (String suit, int value)
{
	this.suit = suit.toUpperCase();
	this.value = value;
}

public String getSuit()
{
	return suit;
}

public int getValue()
{
	return value;
}
public void setValue(int value)
{
	this.value = value;
}
public void setSuit(String suit)
{
	this.suit = suit;
}

@Override
public int compareTo(Object compareCard) {
	int compareValue = (((Card) compareCard).getValue());
	return value-compareValue;
}

}