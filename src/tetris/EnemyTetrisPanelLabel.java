package tetris;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//import Mainwindow.TimeChange;

public class EnemyTetrisPanelLabel {
	public static JPanel panel;
	public int height, width;
	public int x, y;
	private ImageIcon icon;
	private String res;
	private JLabel label;
	public EnemyTetrisPanelLabel(String picturePath, int positionX, int positionY, int pictureWidth, int pictureHeight){
		resetImg(picturePath);
		setImgPosition(positionX, positionY);
		setImgSize(pictureWidth, pictureHeight);

	}
	
	public EnemyTetrisPanelLabel() {
		this("./image/tetris/blackground1.png", 0, 0, 30, 30);
	}

	
	public synchronized void reset(String res) {
		panel.remove(label);
		resetImg(res);
		setImgPosition(x, y);
		setImgSize(width, height);
	}
	
	public void resetImg(String res) {
		this.res= res;
		icon= new ImageIcon(res);
		label= new JLabel(icon);
		//panel.add(label);
	}
	
	public void setImgPosition(int x, int y) {
		this.x= x;
		this.y= y;
		label.setLocation(x, y);
		//panel.repaint();
	}
	
	public void setImgSize(int width, int height) {
		this.width= width;
		this.height= height;
		panel.remove(label);
		Image image = icon.getImage(); // transform itu
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);
		label= new JLabel(icon);
		label.setSize(width, height);
		setImgPosition(x, y);		
		panel.add(label);
		//panel.repaint();
	}
	
	public String getImgPath() {
		return res;
	}
	
	public void setVisible(boolean visibleFlag) {
		label.setVisible(visibleFlag);
	}
	
	public void removeImg() {
		panel.remove(label);
	}
	
	public void addImg() {
		panel.add(label);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
