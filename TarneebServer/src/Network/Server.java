package Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private Socket[] clientSocket;
	private final int numberOfClient = 4;
	
	public Server(int port) {
		// TODO Auto-generated constructor stub
		try {
			serverSocket = new ServerSocket(port);
			clientSocket = new Socket[numberOfClient];
			for (int i = 0; i < numberOfClient; i++) {
				clientSocket[i] = serverSocket.accept();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		
	}
	public String[] recieve(){
		String[] names = new String[numberOfClient];
		try {
			for (int i = 0; i < numberOfClient; i++) {
				DataInputStream dis = new DataInputStream(clientSocket[i].getInputStream());
				names[i] = dis.readUTF();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}
	public String recieve(int index){
		
		try {
			DataInputStream dis = new DataInputStream(clientSocket[index].getInputStream());
			return dis.readUTF();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public void send(String[] names){
		try {
			for (int i = 0; i < names.length; i++) {
				DataOutputStream dos = new DataOutputStream(clientSocket[i].getOutputStream());
				dos.writeUTF(names[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendmsg(String msg){
		try {
			for (int i = 0; i < 4; i++) {
				DataOutputStream dos = new DataOutputStream(clientSocket[i].getOutputStream());
				dos.writeUTF(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
