package application;

public class Card implements Comparable
{
String suit;
int value;

public Card (String suit, int value)
{
	this.suit = suit;
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

@Override
public int compareTo(Object compareCard) {
	int compareValue = (((Card) compareCard).getValue());
	return value-compareValue;
}
}
