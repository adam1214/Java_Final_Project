package connect;
import java.io.Serializable;

import role.*;

public class Data implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MAP_CONTROL= 1, TETRIS_CONTROL= 2, NONE= 3;
	public static final int SERVER= 1, CLIENT= 2;
	public static final int MODE_NONE= 0, MODE_MAP= 1, MODE_PERSONAL_TETRIS= 2, MODE_BATTLE= 3, MODE_PERSONAL_TETRIS_ANIMATION= 4, MODE_BATTLE_ANIMATION= 5, MODE_BATTLE_TETRIS_INITIAL= 6, MODE_PERSONAL_TETRIS_INITIAL= 7, MODE_BATTLE_WIN= 8, MODE_BATTLE_LOSE= 9, MODE_BATTLE_END= 10;
	private int state;  // 1 map mode  2 personal Tetris 3 battle  10 start battle
	private int x;
	private int y;
	private int dmg;
	private int map[][];
	private PetSeq petSeq;
	//public int testNum= 10;
	
	public Data() {
		x= -1200;
		y= -1200;
		state= MODE_NONE;
		map= new int[10][20];
		for(int i= 0; i < 10; i++) {
			for(int j= 0; j < 20; j++) {
				map[i][j]= -1;
			}
		}
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		//System.out.println(x);
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public PetSeq getPetSeq() {
		return petSeq;
	}
	public void setPetSeq(PetSeq petSeq) {
		this.petSeq = petSeq;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
	public void setDmg(int dmg) {
		this.dmg= dmg;
	}
	public int getDmg() {
		return this.dmg;
	}
	
}
