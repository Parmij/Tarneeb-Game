package GameBI;

import java.util.Arrays;

public class Card implements Comparable<Card> {
	private String type;
	 private int number;
	 
	   public Card(String type , int number)
	   { 
		   setType(type);
		   setNumber(number);
	   }
	   public Card(Card another) 
	   {
		    this.type = another.type;
		    this.number = another.number;
	   } 
	   public void setType(String type)
	   {
		   this.type=type;
	   }
	   public void setNumber(int number)
	   {
		   this.number=number;
	   }
	   public String getType()
	   {
		   return type;
	   }
	   public int getNumber()
	   {
		   return number;
	   }
	   
	   @Override
	   public int compareTo(Card card)
	   {
		   return (this.number - card.number);
	   }
	   
}
