import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;
import role.*;
import tetris.*;
import map.*;
import connect.*;

public class Mainwindow extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int width = 1200, height = 800;
	private boolean btnRight, btnLeft, btnUp, btnDown;
	private DrawPicture player, otherPlayer;
	private Map map;
	private TetrisPanel tetrisPanel;
	private EnemyTetrisPanel enemyTetrisPanel;
	private int keyboardFlag, con = 0;
	private DrawPicture[][] blackSquare = new DrawPicture[6][4];
	private int blackSquareX = 0, blackSquareY = 0;
	private boolean blackFlag = false;
	private PetSeq petSeq= new PetSeq();
	private Data playerData, enemyData;
	private int state= Data.MODE_MAP;
	private int winLabelCounter;
	
	//<Mark>
	private int type;
	SendServer server = new SendServer();
	SendClient client = new SendClient();
	
	//<Mark>
	public Mainwindow(int i) {
		this();
		type = i;
		if (type == Data.SERVER) {
			try {
				server.start();
			} catch (Exception e) {
				System.out.println(e);
			}
		}else if(type== Data.CLIENT) {
			Timer time02 = new Timer(10, new sendMess());
			time02.start();
		}
	}
	
	public Mainwindow() {
		setTitle("Game");
		setSize(width, height);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnRight = false;
		btnLeft = false;
		btnUp = false;
		btnDown = false;
		keyboardFlag = Data.MAP_CONTROL;
		addKeyListener(this);
		DrawPicture.panel = new JPanel(null);
		DrawPicture.panel.setBounds(0, 0, width, height);
		enemyTetrisPanel= new EnemyTetrisPanel();
		add(enemyTetrisPanel);
		add(DrawPicture.panel);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				blackSquare[i][j] = new DrawPicture("Black.jpg", i * 200, j * 200, 200, 200);
				blackSquare[i][j].setVisible(false);
			}
		}

		player = new DrawPicture("pikachu.png", 550, 350, 100, 100);
		Role player = new Player();
		otherPlayer = new DrawPicture(player.getPath(), -1200, -1200, 100, 100);
		// obj2= new DrawPicture("map1010.jpg", 0, 0, 1200, 1200);
		map = new Map();
		playerData= new Data();
		enemyData= new Data();
		Timer time = new Timer(10, new TimeChange());
		time.start();
		winLabelCounter= 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (keyboardFlag == Data.MAP_CONTROL) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				btnLeft = true;
				break;
			case KeyEvent.VK_S:
				btnDown = true;
				break;
			case KeyEvent.VK_D:
				btnRight = true;
				break;
			case KeyEvent.VK_W:
				btnUp = true;
				break;
			}
		} else if (keyboardFlag == Data.TETRIS_CONTROL) {
			tetrisPanel.keyPressed1(e);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (keyboardFlag == Data.MAP_CONTROL) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				btnLeft = false;
				break;
			case KeyEvent.VK_S:
				btnDown = false;
				break;
			case KeyEvent.VK_D:
				btnRight = false;
				break;
			case KeyEvent.VK_W:
				btnUp = false;
				break;
			case KeyEvent.VK_F:
				blackFlag = true;
				break;
			}
		}

	}

	public void mapChangeInteger() {
		int disX = player.x - map.getMapX();
		int disY = player.y - map.getMapY();
		if ((disY % 100) != 0) {
			switch (map.mapDirY) {
			case Map.UP:
				map.mapUp();
				break;
			case Map.DOWN:
				map.mapDown();
				break;
			}
		} else {
			map.mapDirY = 0;
		}

		if ((disX % 100) != 0) {
			switch (map.mapDirX) {
			case Map.RIGHT:
				map.mapRight();
				break;
			case Map.LEFT:
				map.mapLeft();
				break;
			}
		} else {
			map.mapDirX = 0;
		}
	}

	public boolean mapChange() {
		boolean changeFlag = false;
		if (btnUp) {
			// obj2.setImgPosition(obj2.getX(), obj2.getY() + 1);
			map.mapUp();
			map.mapDirY = Map.UP;
			changeFlag = true;
		}
		if (btnDown) {
			// obj2.setImgPosition(obj2.getX(), obj2.getY() - 1);
			map.mapDown();
			map.mapDirY = Map.DOWN;
			changeFlag = true;
		}
		if (btnLeft) {
			// obj2.setImgPosition(obj2.getX() + 1, obj2.getY());
			map.mapLeft();
			map.mapDirX = Map.LEFT;
			changeFlag = true;
		}
		if (btnRight) {
			// obj2.setImgPosition(obj2.getX() - 1, obj2.getY());
			map.mapRight();
			map.mapDirX = Map.RIGHT;
			changeFlag = true;
		}

		if (changeFlag)
			return true;

		return false;
	}

	public void openTetris() {
		if(enemyData.getState() == Data.MODE_MAP && state == Data.MODE_MAP) {
			if(enemyData.getX() == map.mapX && enemyData.getY() == map.mapY) {
				blackFlag = true;
				btnRight = false;
				btnLeft = false;
				btnUp = false;
				btnDown = false;
				keyboardFlag = Data.NONE;
				state = Data.MODE_BATTLE_ANIMATION;
			}
		}
		else if(enemyData.getState() == Data.MODE_BATTLE_ANIMATION && state == Data.MODE_MAP) {
			blackFlag = true;
			btnRight = false;
			btnLeft = false;
			btnUp = false;
			btnDown = false;
			keyboardFlag= Data.NONE;
			state= Data.MODE_BATTLE_ANIMATION;
		}
		if(state == Data.MODE_MAP) {
			int num = 0;
			int disX = player.x - map.getMapX();
			int disY = player.y - map.getMapY();
			if ((disX % 100 == 50) || (disY % 100 == 50)) {
				Random ran = new Random();
				num = ran.nextInt(100);
			}
			if (num > 90) {
				blackFlag = true;
				btnRight = false;
				btnLeft = false;
				btnUp = false;
				btnDown = false;
				keyboardFlag = Data.NONE;
				state = Data.MODE_PERSONAL_TETRIS_ANIMATION;
			}
		}

	}
	
	public void checkTetrisState() {
		if(state == Data.MODE_END) {
			if(map.checkDistance(map.mapX, map.preX) >= 100 || map.checkDistance(map.mapY, map.preY) >= 100)
			state= Data.MODE_MAP;
		}
		if(keyboardFlag == Data.MAP_CONTROL)
			return;
		if(TetrisPanel.gametime == true && (state == Data.MODE_PERSONAL_WIN || state == Data.MODE_PERSONAL_LOSE)) {
			setSize(width, height);
			tetrisPanel.setVisible(false);
			tetrisPanel= null;
			
			keyboardFlag= Data.MAP_CONTROL;
			state= Data.MODE_MAP;
			clearBlackAnimate();
			state= Data.MODE_END;
			return;
		}
		else if(TetrisPanel.gametime == true && (state == Data.MODE_BATTLE_WIN || state == Data.MODE_BATTLE_LOSE)) {
			setSize(width, height);
			tetrisPanel.setVisible(false);
			tetrisPanel= null;
			
			keyboardFlag= Data.MAP_CONTROL;
			state= Data.MODE_MAP;
			clearBlackAnimate();
			state= Data.MODE_END;

			return;
		}
		if(TetrisPanel.gametime == false) {
			winLabelCounter++;
			state= tetrisPanel.getState();			
			if(winLabelCounter > 300) {
				TetrisPanel.gametime= true;
				winLabelCounter= 0;
				state= tetrisPanel.getState();
			}
		}
	}

	public void clearBlackAnimate() {
		for(int i= 0; i < 6; i++) {
			for(int j= 0; j < 4; j++) {
				blackSquare[i][j].setVisible(false);
			}
		}
	}
	
	public void drawOtherPlayer() {	
		int x, y;
		
		x= enemyData.getX();
		y= enemyData.getY();
		otherPlayer.setImgPosition(x - map.mapX + 550, y - map.mapY + 350);
	}
	
	public void setData() {
		//player data set

		playerData.setState(state);
		playerData.setX(map.mapX);
		playerData.setY(map.mapY);
		
		if(state == Data.MODE_BATTLE) {
			//System.out.println("start send!!");
			playerData.setMap(tetrisPanel.map);
			playerData.setDmg(tetrisPanel.getDmg());
		}
			
		
		if(type == Data.SERVER) {
			server.setMyData(playerData);
			enemyData= server.getClientData();
			enemyTetrisPanel.setData(playerData, enemyData);
		}
		else if(type == Data.CLIENT) {
			
		}
	}
	
	public void battle() {
		if(state == Data.MODE_PERSONAL_TETRIS_INITIAL) {
		setSize(1200, 800);
		tetrisPanel = new TetrisPanel(petSeq);
		tetrisPanel.setVisible(true);
		tetrisPanel.setBounds(0, 0, 1200, 800);
		add(tetrisPanel);
		keyboardFlag = Data.TETRIS_CONTROL;
		state= Data.MODE_PERSONAL_TETRIS;
		}
		else if(state == Data.MODE_BATTLE_TETRIS_INITIAL) {
			setSize(2000, 800);
			tetrisPanel = new BattleTetris(petSeq);
			tetrisPanel.setVisible(true);
			tetrisPanel.setBounds(0, 0, 1200, 800);
			add(tetrisPanel);
			keyboardFlag = Data.TETRIS_CONTROL;
			state= Data.MODE_BATTLE;
			tetrisPanel.setData(enemyData);
		}
		else if(state == Data.MODE_BATTLE) {
			map.preX= map.mapX;
			map.preX= map.mapY;
			tetrisPanel.setData(enemyData);
		}
		else {
			return;
		}
	}
	
	class TimeChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setData();
			
			if (!mapChange())
				mapChangeInteger();
			blackAnimate();
			openTetris();
			checkTetrisState();
			drawOtherPlayer();
			battle();
			//enemyTetrisPanel.repaintMap();
			
		}

	}
	
	//<Mark>
	class sendMess implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			(new SendClient(enemyData, playerData)).start();
			enemyData= client.getServerData();
			enemyTetrisPanel.setData(playerData, enemyData);
		}
	}
	
	public void blackAnimate() {
		if(!blackFlag)
			return;
		con++;
		if (con == 1) {
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 2) // 1 0
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 3) // 2 0
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 4) // 3 0
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 5) // 4 0
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 6) // 5 0
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareY++;
			return;
		}
		if (con == 7) // 5 1
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareY++;
			return;
		}
		if (con == 8) // 5 2
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareY++;
			return;
		}
		if (con == 9) // 5 3
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 10) // 4 3
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 11) // 3 3
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 12) // 2 3
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 13) // 1 3
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 14) // 0 3
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareY--;
			return;
		}
		if (con == 15) // 0 2
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareY--;
			return;
		}
		if (con == 16) // 0 1
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 17) // 1 1
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 18) // 2 1
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 19) // 3 1
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX++;
			return;
		}
		if (con == 20) // 4 1
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareY++;
			return;
		}
		if (con == 21) // 4 2
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 22) // 3 2
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 23) // 2 2
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX--;
			return;
		}
		if (con == 24) // 1 2
		{
			blackSquare[blackSquareX][blackSquareY].setVisible(true);
			blackSquareX = 0;
			blackSquareY = 0;
			// blackFlag= false;
			return;
		}
		if (con == 25) {
			//System.out.println("123");
			blackSquareX = 0;
			blackSquareY = 0;
			blackFlag = false;
			
			if(state == Data.MODE_PERSONAL_TETRIS_ANIMATION)
				state= Data.MODE_PERSONAL_TETRIS_INITIAL;
			else if(state == Data.MODE_BATTLE_ANIMATION)
				state= Data.MODE_BATTLE_TETRIS_INITIAL;
			
			con = 0;
			return;
		}
	}
}
