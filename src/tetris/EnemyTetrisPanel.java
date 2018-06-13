package tetris;
import connect.Data;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class EnemyTetrisPanel extends JPanel{
	private EnemyTetrisPanelLabel tetrisMap[][] = new EnemyTetrisPanelLabel[10][20], otherPlayer;
	private JPanel[][] panel= new JPanel[10][20];
	private int preMap[][]= new int[10][20];
	private String tetrisMapPath[]= {"./image/tetris/background1.png", "./image/tetris/background2.png", "./image/tetris/blue.png", "./image/tetris/deepblue.png", "./image/tetris/green.png", "./image/tetris/orange.png", "./image/tetris/pink.png", "./image/tetris/red.png", "./image/tetris/yellow.png"};
	private static Data enemyData= new Data();
	private final int boundX= 10, boundY= 0;
	private boolean changeFlag= false;
	private Color tetrisColor[]= {Color.cyan, Color.green, Color.red, Color.blue, Color.yellow, Color.orange, Color.pink};
	public EnemyTetrisPanel(){
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setBounds(1200, 0, 1600, 800);
		EnemyTetrisPanelLabel.panel= this;
		
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
	/*
	public void repaintMap() {
		
		for(int i=0; i<9; i++)
			tetrisMap[i][1].reset(tetrisMapPath[i]);
		int map[][]= new int[10][20];
		map= enemyData.getMap();
		for(int i= 0; i < 10; i++) {
			for(int j= 0; j < 20; j++) {
				System.out.print(map[i][j]);
				if( preMap[i][j] == map[i][j] ) {
					
				}
				else  if(map[i][j] == 0 )
					tetrisMap[i][j].reset(tetrisMapPath[(i + j) % 2]);
				else if(map[i][j] == -1) {
				}
				else {
					//tetrisMap[i][j].reset(tetrisMapPath[2]);
					tetrisMap[i][j].removeImg();
					tetrisMap[i][j]= new EnemyTetrisPanelLabel(tetrisMapPath[6], 33 * i + boundX, 33 * j + boundY, 30, 30);
					//System.out.println("orther tetris");
				}
				preMap[i][j]= map[i][j];
			}
			System.out.println();
		}
		System.out.println();
	}
	*/
	
	
	public void  setData(Data data) {
		if(changeFlag) {
			changeFlag= false;
			enemyData= data;
			repaint();
			changeFlag= true;
		}else {
			return;
		}
			
		//repaintMap();
	}
	
	public void paint(Graphics g) {
		int map[][]= new int[10][20];
		map= enemyData.getMap();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 800);
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
        	//System.out.println();
        }
	}
	
	class TimeChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//repaintMap();			
		}

	}
}
