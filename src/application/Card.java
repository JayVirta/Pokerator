package application;

public class Card implements Comparable
{
String suit;
int value;
int simpleCard;

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
public int simpleCard()
{
	System.out.println(suit);
	switch(suit){
		case "HEARTS":
			simpleCard += 100;
			break;
		case "DIAMONDS":
			simpleCard += 100;
			break;
		case "CLUBS":
			simpleCard += 100;
			break;
		case "SPADES":
			simpleCard += 100;
			break;}
	simpleCard = simpleCard + value;
	return simpleCard;
	
}
@Override
public int compareTo(Object compareCard) {
	int compareValue = (((Card) compareCard).getValue());
	return value-compareValue;
}

}