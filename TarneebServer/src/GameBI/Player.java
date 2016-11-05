package GameBI;

import java.util.ArrayList;

public class Player {
	private String name;
	  private int score=0;
	  private int scoreRound=0;
	  private ArrayList<Card>cards = new ArrayList<Card>();
	  private int daman;
	  
	  public void setName(String name)
	  {
		   this.name=name;
	  }
	  public void setScore(int score)
	  {
		   this.score=score;
	  }
	  public void setScoreRound(int scoreRound)
	  {
		   this.scoreRound=scoreRound;
	  }
	  public void setDaman(int daman)
	  {
		  this.daman=daman;
	  }
	  public String getName()
	  {
		   return name;
	  }
	  public int getScore()
	  {
		   return score;
	  }
	  public int getScoreRound()
	  {
		   return scoreRound;
	  }
	  public int getDaman()
	  {
		  return daman;
	  }
	  public void setCard(Card card)
	  {
		  this.cards.add(card);
	  }
	  public Card getCard(int index)
	  {
		  return cards.get(index);
	  }
	  public ArrayList<Card> printCard()
	  {
		  return cards;
	  }
	  public void clearCard()
	  {
		 cards.clear();
		 
	  }
	  
	  public void inputCard(Card c)
	  {
  		for (int i = 0; i < 13; i++) 
  		{
			if(cards.get(i).getNumber() == c.getNumber() && 
					cards.get(i).getType().equals(c.getType()))
			{
				  cards.get(i).setType("");
				  cards.get(i).setNumber(0); 
			}
  		}
	  }
	  public ArrayList<Card> getAllCard()
	  {
		  return cards;
	  }
}
