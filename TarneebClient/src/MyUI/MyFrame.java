package MyUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Network.Client;

public class MyFrame extends JFrame implements Runnable{
	
	private Client clientSocket; 
	private MyPanel mainPanel;
	private MyPanel tablePanel;
	private MyTextField txtPlayerName;
	private MyButton lblPlayerName;
	private MyButton btnSaveName;
	private MyButton lblPlayer1;
	private MyButton lblPlayer2;
	private MyButton lblPlayer3;
	private MyButton lblPlayer4;
	private MyPanel bottomCards;
	private MyButton[] btnplayerCard = new MyButton[13];
	private MyButton btnTarneebType;
	private MyButton btnSetDaman;
	private JComboBox<Integer> cmbDaman = new JComboBox<Integer>();
	private MyPanel pnlPlayerTurn1;
	private MyPanel pnlPlayerTurn2;
	private MyPanel pnlPlayerTurn3;
	private MyPanel pnlPlayerTurn4;
	
	/// if set daman or not data member:
	private boolean ifsetDaman = false;
	
	
	public MyFrame(String ip, int port) {
		// TODO Auto-generated constructor stub
		super();
		clientSocket = new Client(ip, port);
		this.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MyFrame.MAXIMIZED_BOTH);
		
		createComponent();
		this.pack();
		this.setVisible(true);
	}
	
	public void createComponent()
	{
		mainPanel = new MyPanel(new Color(226,234,242));
		this.add(mainPanel);
		tablePanel = new MyPanel(500, 300, 400, 150, new Color(50,205,50));
		txtPlayerName = new MyTextField(80, 40, 180, 10);
		lblPlayerName = new MyButton("Player Name", 150, 40, 10, 10, false);
		btnSaveName = new MyButton("Join", 70, 40, 270, 10);
		btnSaveName.addActionListener(alEnterName);
		lblPlayer1 = new MyButton("___", 100, 40, 600, 450, false);
		lblPlayer2 = new MyButton("___", 100, 40, 300, 270, false);
		lblPlayer3 = new MyButton("___", 100, 40, 600, 110, false);
		lblPlayer4 = new MyButton("___", 100, 40, 900, 270, false);
		
		bottomCards = new MyPanel(700,  97, 290, 570, new Color(50,205,50));
		
		cmbDaman.setSize(80,20);
		cmbDaman.setLocation(180,80);
		for (int i = 2; i < 14; i++) {
			cmbDaman.addItem(i);
		}
		
		btnSetDaman = new MyButton("OK", 70, 40, 270, 80);
		btnSetDaman.addActionListener(alSetDaman);
		
		pnlPlayerTurn1 = new MyPanel(40,  50, 630, 500, "ImageCard/up.png");
		pnlPlayerTurn2 = new MyPanel(40,  50, 1010, 265, "ImageCard/left.png");
		pnlPlayerTurn3 = new MyPanel(40,  50, 630, 60, "ImageCard/down.png");
		pnlPlayerTurn4 = new MyPanel(40,  50, 250, 265, "ImageCard/right.png");
		
//		pnlPlayerTurn1.repaint();
		mainPanel.add(tablePanel);
		mainPanel.add(bottomCards);
		mainPanel.add(txtPlayerName);
		mainPanel.add(lblPlayerName);
		mainPanel.add(btnSaveName);
		mainPanel.add(lblPlayer1);
		mainPanel.add(lblPlayer2);
		mainPanel.add(lblPlayer3);
		mainPanel.add(lblPlayer4);
		mainPanel.add(cmbDaman);
		mainPanel.add(btnSetDaman);
		mainPanel.add(pnlPlayerTurn1);
		mainPanel.add(pnlPlayerTurn2);
		mainPanel.add(pnlPlayerTurn3);
		mainPanel.add(pnlPlayerTurn4);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void paintCards(String msg)
	{	
		int locX = -40;
		bottomCards.removeAll();
		String[] d = msg.split("\\|");
		for (int i = 0; i < 13; i++) {
			 String[] c = d[i].split("\\_");
			btnplayerCard[i] = new MyButton(d[i], 70, 95, locX+=50, 0, "ImageCard/"+c[0]+"_"+c[1]+".gif");
			bottomCards.add(btnplayerCard[i]);
		}
		btnTarneebType = new MyButton(70, 95, 20, 100, "ImageCard/14_"+d[13]+".gif", false);
		mainPanel.add(btnTarneebType);
		mainPanel.repaint();
		bottomCards.repaint();
		btnplayerCard[0].addActionListener(al0);
		btnplayerCard[1].addActionListener(al0);
		btnplayerCard[2].addActionListener(al0);
		btnplayerCard[3].addActionListener(al0);
		btnplayerCard[4].addActionListener(al0);
		btnplayerCard[5].addActionListener(al0);
		btnplayerCard[6].addActionListener(al0);
		btnplayerCard[7].addActionListener(al0);
		btnplayerCard[8].addActionListener(al0);
		btnplayerCard[9].addActionListener(al0);
		btnplayerCard[10].addActionListener(al0);
		btnplayerCard[11].addActionListener(al0);
		btnplayerCard[12].addActionListener(al0);
		
	}
	
	ActionListener alEnterName = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			clientSocket.send(txtPlayerName.getText());
		}
	};
	
ActionListener alSetDaman = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			clientSocket.send(String.valueOf(cmbDaman.getSelectedItem()));
			ifsetDaman = true;
		}
	};
	
	ActionListener al0 =new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(ifsetDaman)
			{
				if(turn == 0)
				{
					clientSocket.send(((MyButton)e.getSource()).getName());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
					    "You must set Daman all client before !.",
					    "Try again",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	

	private void playCard(String position){
		String[] tmp = position.split("\\|");
		String [] d = tmp[0].split("\\_");
		tablePanel.add(new MyButton(75, 97, Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), "ImageCard/"+d[0]+"_"+d[1]+".gif"));
		if(turn == 0){
			int x = getIndexCard(tmp[0]);
			if(x != -1){
				bottomCards.remove(btnplayerCard[x]);
				bottomCards.repaint();
			}
		}
		tablePanel.repaint();
	}
	
	private int getIndexCard(String s){
		for (int i = 0; i < 13; i++) {
			if(s.equals(btnplayerCard[i].getName()))
				return i;
		}
		return -1;
	}
	int turn = -1;
	
	//	class DamanThread extends Thread{
//		@Override
//		public void run(){
//			/// Recieve Player Cards
//			String msg = clientSocket.recieve();
//			if(msg.equals("PlayerTurn")){
//				/// Recieve Player Turn
//				while(true){
//					
//					
//					
//				}
//			}
//				
//			else
//				paintCards(msg);
//		}
//	}

	/// Recieve Player Names
	private void recieveNames(String msg){
		String [] allName = msg.split("\\|");
		lblPlayer1.setText(allName[0]);
		lblPlayer2.setText(allName[1]);
		lblPlayer3.setText(allName[2]);
		lblPlayer4.setText(allName[3]);
	}
	
	/// Recieve Player Turn
	private void recievePlayerTurn(String msg){
		turn = Integer.parseInt(msg);
		
		if(turn == 0){
			pnlPlayerTurn1.setVisible(true);
			pnlPlayerTurn2.setVisible(false);
			pnlPlayerTurn3.setVisible(false);
			pnlPlayerTurn4.setVisible(false);
		}
		else if(turn == 1){
			pnlPlayerTurn1.setVisible(false);
			pnlPlayerTurn2.setVisible(true);
			pnlPlayerTurn3.setVisible(false);
			pnlPlayerTurn4.setVisible(false);
		}
		else if(turn == 2){
			pnlPlayerTurn1.setVisible(false);
			pnlPlayerTurn2.setVisible(false);
			pnlPlayerTurn3.setVisible(true);
			pnlPlayerTurn4.setVisible(false);
		}
		else if(turn == 3){
			pnlPlayerTurn1.setVisible(false);
			pnlPlayerTurn2.setVisible(false);
			pnlPlayerTurn3.setVisible(false);
			pnlPlayerTurn4.setVisible(true);
		}
	}
	
	/// Clear Table
	private void clearTable(){
		tablePanel.removeAll();
		tablePanel.repaint();
	}
	
	@Override
	public void run(){
		while(true){
			String msg = clientSocket.recieve();
			
			if(msg.equals("Names")){
				recieveNames(clientSocket.recieve());
			}
			else if(msg.equals("NewCard")){
				paintCards(clientSocket.recieve());
			}
			else if(msg.equals("PlayerTurn")){
				recievePlayerTurn(clientSocket.recieve());
			}
			else if(msg.equals("CardPosition")){
				playCard(clientSocket.recieve());
			}
			else if(msg.equals("Clear")){
				clearTable();
			}
		}
	}
}
