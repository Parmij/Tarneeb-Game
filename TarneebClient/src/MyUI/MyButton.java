package MyUI;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton {
 
	public MyButton(String name,int sizeX , int sizeY ,int locationX , int locationY)
	{
		super(name);
		this.setLayout(null);
		this.setLocation(locationX, locationY);
		this.setSize(sizeX,sizeY);
		this.setVisible(true);
	}
	
	public MyButton(String name,int sizeX , int sizeY ,int locationX , int locationY, boolean enable)
	{
		super(name);
		this.setLayout(null);
		this.setLocation(locationX, locationY);
		this.setEnabled(enable);
		this.setSize(sizeX,sizeY);
		this.setVisible(true);
	}
	
	public MyButton(int sizeX , int sizeY ,int locationX , int locationY,String url)
	{
		super();
		this.setLayout(null);
		this.setLocation(locationX, locationY);
		this.setSize(sizeX,sizeY);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		this.setIcon(new ImageIcon(img));
		this.setVisible(true);
	}
	
	public MyButton(int sizeX , int sizeY ,int locationX , int locationY,String url,boolean enable)
	{
		super();
		this.setLayout(null);
		this.setLocation(locationX, locationY);
		this.setSize(sizeX,sizeY);
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setEnabled(enable);
		Image img = kit.createImage(url);
		this.setIcon(new ImageIcon(img));
		this.setVisible(true);
	}
	
	
	public MyButton(String name, int sizeX , int sizeY ,int locationX , int locationY,String url)
	{
		super();
		this.setLayout(null);
		this.setName(name);
		this.setLocation(locationX, locationY);
		this.setSize(sizeX,sizeY);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		this.setIcon(new ImageIcon(img));
		this.setVisible(true);
	}
	public MyButton(int number , String type, int locationX ,int locationY, int width, int heigh , String url)
	{
		super();
		this.setLocation(locationX, locationY);
		this.setSize(width,heigh);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		this.setIcon(new ImageIcon(img));
		this.setVisible(true);
	}
}
