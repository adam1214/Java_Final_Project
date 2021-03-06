package map;

//import java.util.ArrayList;
import java.lang.Math;
public class Map {
	public int mapDirX= 0, mapDirY= 0;
	public static final int UP= 1, RIGHT= 2, DOWN= 3, LEFT= 4;
	public MapUnit [][] mapUnit= new MapUnit[3][3];
	private final int speedX= 2, speedY= 2;
	public int mapX= -50, mapY= -50;
	public int preX, preY;
	public Map(){
		preX= 0;
		preY= 0;
		for(int i=0; i<3; i++) {
			for(int j= 0; j < 3; j++) {
				mapUnit[i][j]= new Map1010(-1250 + (1200 * i), -1250 + (1200 * j));	
			}			
		}
	}
	
	public int getMapX() {
		return mapX;
	}
	
	public int getMapY() {
		return mapY;
	}
	
	public void mapUp() {
		mapY-= speedY;
		for(int i=0; i<3; i++) {
			for(int j= 0; j < 3; j++) {
				mapUnit[i][j].setImgPosition(mapUnit[i][j].getX(), mapUnit[i][j].getY() + speedY);
			}
		}
		checkMapRange();
	}
	public void mapDown() {
		mapY+= speedY;
		for(int i=0; i<3; i++) {
			for(int j= 0; j < 3; j++) {
				mapUnit[i][j].setImgPosition(mapUnit[i][j].getX(), mapUnit[i][j].getY() - speedY);
			}
		}
		checkMapRange();
	}
	public void mapRight() {
		mapX+= speedX;
		for(int i=0; i < 3; i++) {
			for(int j= 0; j < 3; j++) {
				mapUnit[i][j].setImgPosition(mapUnit[i][j].getX() - speedX, mapUnit[i][j].getY());
			}
		}
		checkMapRange();
	}
	public void mapLeft() {
		mapX-= speedX;
		for(int i=0; i < 3; i++) {
			for(int j= 0; j < 3; j++) {
				mapUnit[i][j].setImgPosition(mapUnit[i][j].getX() + speedX, mapUnit[i][j].getY());
			}
		}
		checkMapRange();
	}
	
	public double checkDistance(double x1, double x2) {
		//System.out.println(Math.sqrt((x1 - x2) * (x1 - x2)));
		return Math.sqrt((x1 - x2) * (x1 - x2));
	}
	
	public void checkMapRange() {
		int xFlag= mapUnit[1][1].x;
		int yFlag= mapUnit[1][1].y;
		
		if(xFlag > 1200) {
			for(int i=1; i>=0; i--) {
				for(int j= 0; j<3; j++) {
					mapUnit[i+1][j]= mapUnit[i][j];
				}
			}
			for(int j=0; j<3; j++) {
				mapUnit[0][j]= new Map1010(mapUnit[1][j].x - 1200, mapUnit[1][j].y);
			}
		}
		
		if(xFlag < -1200) {
			for(int i= 0; i < 2; i++) {
				for(int j= 0; j < 3; j++) {
					mapUnit[i][j]= mapUnit[i+1][j];
				}
			}
			for(int j=0; j<3; j++) {
				mapUnit[2][j]= new Map1010(mapUnit[1][j].x + 1200, mapUnit[1][j].y);
			}
		}
		
		if(yFlag > 1200) {
			for(int j = 1; j >= 0; j--) {
				for(int i= 0; i < 3; i++) {
					mapUnit[i][j+1]= mapUnit[i][j];
				}
			}
			for(int i= 0; i < 3; i++) {
				mapUnit[i][0]= new Map1010(mapUnit[i][1].x, mapUnit[i][1].y - 1200);
			}
		}
		
		if(yFlag < -1200) {
			for(int j= 0; j < 2; j++) {
				for(int i= 0; i < 3; i++) {
					mapUnit[i][j]= mapUnit[i][j+1];
				}
			}
			for(int i= 0; i < 3; i++) {
				mapUnit[i][2]= new Map1010(mapUnit[i][1].x, mapUnit[i][1].y + 1200);
			}
		}
	}
	
}
