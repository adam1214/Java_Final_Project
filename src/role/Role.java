package role;


import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Role {
	protected int level;
	private String name;
	ImageIcon icon ; 
	String path;
	
	
	public Role() {

//		setSize(800,600);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new FlowLayout());
//		icon=new ImageIcon(getClass().getResource("/images/pet_pika.jpg"));
//		icon=new ImageIcon("./image/pet_pika.jpg");
//		ImageIcon im=new ImageIcon("./image/pet_pika.jpg");
//		Image image = im.getImage();
//		Image  newIm=image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
//		
//		im = new ImageIcon(newIm);
//		JLabel lb=new JLabel(im);
//		lb.setSize(10,10);
//		add(lb);
	}
	
	public void setIcon(String path) {
		icon=new ImageIcon(path);
		this.path=path;
		
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	public String getPath() {
		return path;
	}
	
	
}
