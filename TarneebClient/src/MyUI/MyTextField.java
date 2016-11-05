package MyUI;

import java.awt.TextField;

public class MyTextField extends TextField{
	public MyTextField(int sizeX , int sizeY ,int locationX , int locationY) {
		// TODO Auto-generated constructor stub
		super("h");
		this.setLocation(locationX, locationY);
		this.setSize(sizeX,sizeY);
		this.setVisible(true);
	}
}
