package application;
import java.io.*;
import java.util.*;

public class tableBuilder
{
	static File file = new File("output.txt");
	
	public static void createTable() throws IOException
	{
		ArrayList<Integer> tableBuild = new ArrayList<Integer>();
		ArrayList<String> handTable = new ArrayList<String>();
		int handValue = 0;
		int[][] appearances = new int[92][2];
		for(int i = 0; i< 92 ; i++)
			appearances[i][0] =0;
		int value1 = 0;
		int value2 = 0;
		int value3 = 0;
		int value4 = 0;
		int value5 = 0;
		boolean contains = false;
		String suit1 = "";
		String suit2 = "";
		String suit3 = "";
		String suit4 = "";
		String suit5 = "";
		if(!file.exists())
			file.createNewFile();
		PrintStream fileStream = new PrintStream(file);
		for(int i = 1; i<=52; i++)
		{
			for(int j = 1; j<=52; j++)
			{
				for(int k = 1; k<=52; k++)
				{
					for(int l = 1; l<=52; l++)
					{
						for(int m = 1; m<=52; m++)
						{
							value1= i%13+1;
							value2= j%13+1;
							value3= k%13+1;
							value4= l%13+1;
							value5= m%13+1;
							if(i<=13)
								suit1 = "Spades";
							else if(i<=26 && i>13)
								suit1 = "Hearts";
							else if(i<=39 && i>26)
								suit1 = "Clubs";
							else if(i<=52 && i>39)
								suit1 = "Diamonds";
							if(j<=13)
								suit2 = "Spades";
							else if(j<=26 && j>13)
								suit2 = "Hearts";
							else if(j<=39 && j>26)
								suit2 = "Clubs";
							else if(j<=52 && j>39)
								suit2 = "Diamonds";
							if(k<=13)
								suit3 = "Spades";
							else if(k<=26 && k>13)
								suit3 = "Hearts";
							else if(k<=39 && k>26)
								suit3 = "Clubs";
							else if(k<=52 && k>39)
								suit3 = "Diamonds";
							if(l<=13)
								suit4 = "Spades";
							else if(l<=26 && l>13)
								suit4 = "Hearts";
							else if(l<=39 && l>26)
								suit4 = "Clubs";
							else if(l<=52 && l>39)
								suit4 = "Diamonds";
							if(m<=13)
								suit5 = "Spades";
							else if(m<=26 && m>13)
								suit5 = "Hearts";
							else if(m<=39 && m>26)
								suit5 = "Clubs";
							else if(m<=52 && m>39)
								suit5 = "Diamonds";
							Card card1 = new Card(suit1, value1);
							Card card2 = new Card(suit2, value2);
							Card card3 = new Card(suit3, value3);
							Card card4 = new Card(suit4, value4);
							Card card5 = new Card(suit5, value5);
							Hand hand = new Hand(card1, card2, card3, card4, card5);
							hand.setHand(card1, card2, card3, card4, card5);
							hand.sortHand();
							hand.handDetermine();
							handValue = hand.getHandValue();
							if(hand.isValid() == true)
							{
								boolean used = false;
								for(int z = 0; z< tableBuild.size(); z++)
								{
									if(tableBuild.get(z)==hand.getHandValue())
										contains = true;
									for(int x = 0; x< 92 && used == false ; x++)
									{
										if (contains == true && used == false)
										{
											if (appearances[x][0] == 0)
											{
												appearances[x][0] = hand.getHandValue();
												appearances[x][1] = 1;
												used = true;
											}
											else if (appearances [x][0] == hand.getHandValue()) 
											{
												appearances[x][1]++;
											}
										}
									}
								}
								if (contains == false)
								{
									tableBuild.add(hand.getHandValue());
									System.out.println(tableBuild.size());
								}
								contains = false;
								handValue = 0;
							}
						}
					}
					
				}
			}
			System.out.println(i + " Out of 52");
		}
		Collections.sort(tableBuild);
		for(int i = 0; i< tableBuild.size(); i++)
		{
			fileStream.println(tableBuild.get(i) + " " +appearances[i][1]);
			System.out.println(i + " Out of 52");
		}
		fileStream.close();
	}
}
