package map;

public class MapUnit extends DrawPicture{
	static final int MAP0000= 0;
	public int [][] mapStatue= new int[12][8];
	MapUnit(String picturePath, int positionX, int positionY) {
		super(picturePath, positionX, positionY, 1200, 1200);
		// TODO Auto-generated constructor stub
	}

}