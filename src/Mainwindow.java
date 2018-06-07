import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;
import role.*;
import tetris.*;
import map.*;

public class Mainwindow extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int width = 1200, height = 800;
	private boolean btnRight, btnLeft, btnUp, btnDown;
	private DrawPicture player, obj2;
	private Map map;
	private TetrisPanel terisPanel;
	private int keyFlag, con = 0;
	private DrawPicture[][] blackSquare = new DrawPicture[6][4];
	private int blackSquareX = 0, blackSquareY = 0;
	private boolean blackFlag = false;

	public Mainwindow() {
		setTitle("Game");
		setSize(width, height);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnRight = false;
		btnLeft = false;
		btnUp = false;
		btnDown = false;
		keyFlag = 0;
		addKeyListener(this);
		DrawPicture.panel = new JPanel(null);
		DrawPicture.panel.setBounds(0, 0, width, height);
		add(DrawPicture.panel);
		// DrawPicture.panel.setVisible(false);
		// remove(DrawPicture.panel);
		// 上面不用動

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				blackSquare[i][j] = new DrawPicture("Black.jpg", i * 200, j * 200, 200, 200);
				blackSquare[i][j].setVisible(false);
			}
		}

		// 在下面new 新的物件
		player = new DrawPicture("pikachu.png", 550, 350, 100, 100);
		Role player = new Player();
		DrawPicture d_player = new DrawPicture(player.getPath(), 100, 100, 100, 100);
		// obj2= new DrawPicture("map1010.jpg", 0, 0, 1200, 1200);
		map = new Map();

		Timer time = new Timer(10, new TimeChange());
		time.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (keyFlag == 0) {
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
		} else if (keyFlag == 1) {
			terisPanel.keyPressed1(e);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (keyFlag == 0) {
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
			map.mapDirY = map.UP;
			changeFlag = true;
		}
		if (btnDown) {
			// obj2.setImgPosition(obj2.getX(), obj2.getY() - 1);
			map.mapDown();
			map.mapDirY = map.DOWN;
			changeFlag = true;
		}
		if (btnLeft) {
			// obj2.setImgPosition(obj2.getX() + 1, obj2.getY());
			map.mapLeft();
			map.mapDirX = map.LEFT;
			changeFlag = true;
		}
		if (btnRight) {
			// obj2.setImgPosition(obj2.getX() - 1, obj2.getY());
			map.mapRight();
			map.mapDirX = map.RIGHT;
			changeFlag = true;
		}

		if (changeFlag)
			return true;

		return false;
	}

	public void blackAnimate() {
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
			System.out.println("123");
			blackSquareX = 0;
			blackSquareY = 0;
			blackFlag = false;
			PetSeq a=new PetSeq();
			terisPanel = new TetrisPanel(a);
			terisPanel.setVisible(true);
			terisPanel.setBounds(0, 0, 1200, 800);
			add(terisPanel);
			con = 1;
			return;
		}
	}

	public void openTetris() {
		int num = 0;
		int disX = player.x - map.getMapX();
		int disY = player.y - map.getMapY();
		if ((disX % 100 == 50) || (disY % 100 == 50)) {
			Random ran = new Random();
			num = ran.nextInt(50);
		}
		if (num > 40) {
			blackFlag = true;
			keyFlag = 1;
			btnRight = false;
			btnLeft = false;
			btnUp = false;
			btnDown = false;
			System.out.println("ok");
		}
	}

	class TimeChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!mapChange())
				mapChangeInteger();
			if (blackFlag)
				blackAnimate();

			openTetris();

		}

	}
}
