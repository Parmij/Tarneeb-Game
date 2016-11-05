package MyUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	public MyPanel(int width, int height, int locX, int locY, Color color)
	{
		super();
		this.setLayout(null);
		this.setSize(width, height);
		this.setLocation(locX, locY);
		this.setBackground(color);
		this.setVisible(true);
	}
	
	public MyPanel(int width, int height, int locX, int locY, String url)
	{
		super();
//		this.setLayout(null);
		this.setSize(width, height);
		this.setLocation(locX, locY);
		ImageIcon image = new ImageIcon(url);
		JLabel label = new JLabel("", image, JLabel.CENTER);
		new JPanel(new BorderLayout());
		this.setBackground(new Color(226,234,242));
		this.add( label, BorderLayout.CENTER );
		this.setVisible(false);
	}
	public MyPanel(Color color)
	{
		super();
		this.setLayout(null);
		this.setBackground(color);
		this.setVisible(true);
	}
}
