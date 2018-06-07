package map;

public class Map1010 extends MapUnit{
	private int mapStatueCopy[][]= {
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1}};
	
	Map1010(int positionX, int positionY) {
		super("map1010.jpg", positionX, positionY);
		// TODO Auto-generated constructor stub
		for(int i= 0; i<12; i++) {
			for(int j= 0; j<8; j++) {
				mapStatue[i][j]= mapStatueCopy[j][i];
			}
		}
	}
	
}