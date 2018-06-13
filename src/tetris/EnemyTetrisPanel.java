package tetris;
import connect.Data;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class EnemyTetrisPanel extends JPanel{
	private final int totalHp= 100;
	private int hp;
	private Image enemy, blood, blood_red;
	private EnemyTetrisPanelLabel tetrisMap[][] = new EnemyTetrisPanelLabel[10][20];
	private JPanel[][] panel= new JPanel[10][20];
	private int preMap[][]= new int[10][20];
	private String tetrisMapPath[]= {"./image/tetris/background1.png", "./image/tetris/background2.png", "./image/tetris/blue.png", "./image/tetris/deepblue.png", "./image/tetris/green.png", "./image/tetris/orange.png", "./image/tetris/pink.png", "./image/tetris/red.png", "./image/tetris/yellow.png"};
	private Data enemyData= new Data(), myData= new Data();
	private final int boundX= 10, boundY= 0;
	private boolean changeFlag= false;
	private Color tetrisColor[]= {Color.cyan, Color.green, Color.red, Color.blue, Color.yellow, Color.orange, Color.pink};
	
	public EnemyTetrisPanel(){
		hp= totalHp;
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setBounds(1200, 0, 800, 800);
		EnemyTetrisPanelLabel.panel= this;
		 enemy= Toolkit.getDefaultToolkit().getImage("./image/enemy/enemy.jpg");
		 blood= Toolkit.getDefaultToolkit().getImage("./image/enemy/blood.jpg");
		 blood_red= Toolkit.getDefaultToolkit().getImage("./image/enemy/blood_red.jpg");
		//otherPlayer= new EnemyTetrisPanel();
		
		for(int i= 0; i < 10; i++) {
			for(int j= 0; j < 20; j++) {
				tetrisMap[i][j]= new EnemyTetrisPanelLabel(tetrisMapPath[0], 33 * i + boundX, 33 * j + boundY, 30, 30);
				preMap[i][j]= 0;
			}
		}
		Timer time = new Timer(10, new TimeChange());
		time.start();
		changeFlag= true;
	}
	
	
	public void  setData(Data myData, Data enemyData) {
		if(changeFlag) {
			changeFlag= false;
			this.enemyData= enemyData;
			this.myData= myData;
			repaint();
			changeFlag= true;
		}else {
			return;
		}
	}
	
	public void paint(Graphics g) {
		final int enemyX= 400, enemyY= 100;
		final int enemyHeight= 200, enemyWidth= 200;
		
		int map[][]= new int[10][20];
		map= enemyData.getMap();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);
        for(int i= 0; i < 10; i++) {
        	for(int j= 0; j < 20; j++) {
        		//System.out.print(map[i][j]);
        		switch(map[i][j]) {
        		case -1:
        			g.setColor(Color.GRAY);
        			break;
        		case 0:
        			g.setColor(Color.GRAY);
        			break;
        		default:
        			g.setColor(tetrisColor[map[i][j] - 1]);
        			break;
        		}
        		
        		g.fillRect(33 * i + boundX, 33 * j + boundY, 30, 30);     		
        	}
        	
        	g.drawImage(enemy, enemyX, enemyY, enemyWidth, enemyHeight, null);
        	g.drawImage(blood_red, enemyX, enemyY + enemyHeight + 10, enemyWidth, 10, null);
        	if(totalHp >= myData.getDmg()) {
        		g.drawImage(blood, enemyX, enemyY + enemyHeight + 10, enemyWidth * (totalHp - myData.getDmg()) / totalHp, 10, null);
        	}
        	//System.out.println(enemyData.getDmg());
        }
	}
	
	class TimeChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//repaintMap();			
		}

	}
}
