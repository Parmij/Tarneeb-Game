import GameBI.Card;
import GameBI.Game;
import GameBI.Player;
import Network.Server;


public class Program {
	
	public static void main(String [] args){
		/// Create Connection
		Server server = new Server(5555);
		
		/// Create new game
		Game game = new Game();
		
		/// Recieve Names
		String[] n = server.recieve();
		game.setPlayerName(n);
		String[] names = new String[4];
		names[0] = n[0]+"|"+n[1]+"|"+n[2]+"|"+n[3];
		names[1] = n[1]+"|"+n[2]+"|"+n[3]+"|"+n[0];
		names[2] = n[2]+"|"+n[3]+"|"+n[0]+"|"+n[1];
		names[3] = n[3]+"|"+n[0]+"|"+n[1]+"|"+n[2];
		server.sendmsg("Names");
		server.send(names);
		
		
		do
		{
			/// Distribute Card until valid Daman
			int sum = 0;
			do{
				game.disCard();
				String[] cardNames = new String[4];
				for (int i = 0; i < 4; i++) {
					cardNames[i] = "";
					for (int j = 0; j < 13; j++) {
						cardNames[i] += game.getPlayer(i).getCard(j).getNumber()+"_"+
								game.getPlayer(i).getCard(j).getType()+"|";
					}
					/// Send Tarneeb Type
					cardNames[i] += game.getTarneebType();
				}
				server.sendmsg("NewCard");
				server.send(cardNames);
				sum = 0;
				String[] daman = server.recieve();
				
				for (int i = 0; i < 4; i++) {
					sum += Integer.parseInt(daman[i]);
				} 
				if(game.validation(sum, 11, 50))
				{
					
					for (int i = 0; i < 4; i++) {
						game.getPlayer(i).setDaman(Integer.parseInt(daman[i]));
					}
					break;
				}
			}
			while(true);
			
			int index = 0; 
			String[] turn = new String[4];
			
			String[] putCardOnTable = new String[4];
			String[] XTable = new String[]{"215","320","215","120"};
			String[] YTable = new String[]{"200","100","3","100"};
			int j;
			
			int round = game.getnumCardsForEachPlayer();
			while(round-- > 0)
			{
				int roundTable = 4;
				Card[] table = new Card[4];
				
				j = index;
				for (int i = 0; i < 4; i++, j++) {
					if(j == 4)
						j = 0;
					turn[j] = String.valueOf(i);
				}
				
				while(roundTable-- > 0){
					/// Send Player Turn
					server.sendmsg("PlayerTurn");
					server.send(turn);
					
					String nameCard = ""; 
					do{
						nameCard = server.recieve(index);
					}
					while(!game.validateInputCard(index, nameCard));
					
					String[] card = nameCard.split("\\_");
					table[index] = new Card(card[1], Integer.parseInt(card[0]));
					j = index;
					for (int i = 0; i < 4; i++, j++) {
						if(j == 4)
							j = 0;
						putCardOnTable[j] = nameCard + "|" + XTable[i] + "|" + YTable[i];
					}
					server.sendmsg("CardPosition");
					server.send(putCardOnTable);
					
					String tmp = turn[3];
					for (int i = 3; i > 0; i--) {
						turn[i] = turn[i-1];
					}
					turn[0] = tmp;
					
					index++;
					if(index == 4)
						index = 0;
					System.out.println("jjj");
				}
				index = game.winnerPlayerRound(table);
				server.sendmsg("Clear");
			}
			game.calcScore();
		}
		while(!game.ifgetTotalScore());
	}
}
