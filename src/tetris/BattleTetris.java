package tetris;

import role.PetSeq;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import connect.*;

public class BattleTetris extends TetrisPanel{
	//private Data enemyData;
	private Image player, blood, blood_red;
	private int totalHp= 100;
	public BattleTetris(PetSeq petSeq1) {
		super(petSeq1);
		player= Toolkit.getDefaultToolkit().getImage("./image/player.png");
		blood= Toolkit.getDefaultToolkit().getImage("./image/enemy/blood.jpg");
		blood_red= Toolkit.getDefaultToolkit().getImage("./image/enemy/blood_red.jpg");
		state= Data.MODE_BATTLE;
		// TODO Auto-generated constructor stub
	}
	
	public void blood(int sum) {
		if (sum >= 100) {
			win();
		} else {
		}
	}
	
	public void modeWin() {
		gametime = false;
		state= Data.MODE_BATTLE_WIN;
	}
	public void modeLose() {
		gametime=false;
		state= Data.MODE_BATTLE_LOSE;
	}
	
	public void paintCharacter(Graphics g) {
		System.out.println(state);
		//System.out.println("hello");
		final int playerX= 1000, playerY= 100;
		final int playerHeight= 200, playerWidth= 200;
		g.drawImage(player, playerX, playerY, playerWidth, playerHeight, null);
    	g.drawImage(blood_red, playerX, playerY + playerHeight + 10, playerWidth, 10, null);
    	if(totalHp >= enemyData.getDmg()) {
    		g.drawImage(blood, playerX, playerY + playerHeight + 10, playerWidth * (totalHp - enemyData.getDmg()) / totalHp, 10, null);
    	}else {
    		newBlock();
    		modeLose();
    	}
    	if(total > 100) {
    		win();
    		modeWin();
    	}
    	//System.out.println(enemyData.getState());
    	if((enemyData.getState() == Data.MODE_BATTLE_LOSE) && (state != Data.MODE_BATTLE_WIN)) {
    		win();
    		gametime = false;
    		state= Data.MODE_BATTLE_WIN;
    	}    	
    	else if((enemyData.getState() == Data.MODE_BATTLE_WIN) && (state != Data.MODE_BATTLE_LOSE)) {
    		newBlock();
    		modeLose();
    	}
	}
	

}
