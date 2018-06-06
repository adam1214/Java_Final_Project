
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
import javax.swing.*;

public class Start extends JFrame implements ActionListener {

	JFrame f;

	public Start() {
		f = new JFrame("開始畫面");
		try {
			JButton btn = new JButton("開始遊戲");
			f.add(btn, BorderLayout.NORTH);
			btn.setLocation(550, 200);
			btn.setSize(100, 100);
			f.getContentPane().add(btn);

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
					new Playing();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
//		String bip = "Startmusic.mp3";
//		Media hit = new Media(new File(bip).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(hit);
//		mediaPlayer.play();
		//playMusic();
		Start graph = new Start();
	}

	public void actionPerformed(ActionEvent e) {
	}
	@SuppressWarnings("deprecation")
	static void playMusic(){ 
        try {  
            URL cb;  
            File f = new File("Startmusic.mp3"); 
            cb = f.toURL();  
            AudioClip aau;  
            aau = Applet.newAudioClip(cb);  
          
            aau.play();   
            aau.loop(); 
            System.out.println("可以播放");  
        } catch (MalformedURLException e) {  
              
            e.printStackTrace();  
        }  
    }     
}
