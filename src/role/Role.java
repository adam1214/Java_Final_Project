package role;

//import window.*;

import java.awt.FlowLayout;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Role implements Serializable{
	protected int level;
	private String name;
	ImageIcon icon ; 
	String path;
	
	
	public Role() {
		level=1;

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
	
	public void setLevel(int level) {
		this.level=level;
	}
	
	public int getLevel() {
		return level;
	}
	
	
}