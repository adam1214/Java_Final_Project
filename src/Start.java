
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Start extends JFrame implements ActionListener {

	JFrame f;

	public Start() {
		f = new JFrame("�}�l�e��");
		try {
			JButton btn = new JButton("��H�C��");
			f.add(btn, BorderLayout.NORTH);
			btn.setLocation(550, 200);
			btn.setSize(100, 100);
			f.getContentPane().add(btn);
			
			JButton btn1 = new JButton("���H���");
			f.add(btn1, BorderLayout.NORTH);
			btn1.setLocation(550, 350);
			btn1.setSize(100, 100);
			f.getContentPane().add(btn1);

//			ImageIcon icon = new ImageIcon("Startbackground.png");
//			JLabel lb = new JLabel(icon);
//			lb.setText(" ");
//			//lb.setLocation(0,0);
//			f.getContentPane().add(lb);
			
			ImageIcon icon = new ImageIcon(
					new URL("http://files.57gif.com/webgif/2/2/ae/ad765621779c1f275ced81dbccef5.gif"));
			JLabel lb = new JLabel(icon);
			lb.setText(" ");
			//lb.setLocation(0,0);
			f.getContentPane().add(lb);
			
//			ImageIcon icon2 = new ImageIcon(
//					new URL("https://imgur.dcard.tw/b0vpOJC.jpg"));
//			JLabel lb2 = new JLabel(icon2);
//			lb2.setText(" ");
//			//lb1.setLocation(0,0);
//			f.getContentPane().add(lb2);

			f.setSize(700, 500);
			f.getContentPane().setBackground(Color.PINK);
			f.show();
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.setVisible(false);
					Mainwindow m = new Mainwindow(1);
					m.setVisible(true);
				}
			});
			
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.setVisible(false);
					Mainwindow m = new Mainwindow(2);
					m.setVisible(true);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		playSound();
		Start graph = new Start();
	}

	public void actionPerformed(ActionEvent e) {
	}
	static public void playSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Startmusic.mp3").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}
