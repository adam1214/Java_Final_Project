package role;

public class PetSeq extends Pet {
	public Pet pet[]=new Pet[7];
	
	public PetSeq() {
		
		pet[0]=new PetBlue();
		pet[1]=new PetDeepBlue();
		pet[2]=new PetGreen();
		pet[3]=new PetOrange();
		pet[4]=new PetPink();
		pet[5]=new PetRed();
		pet[6]=new PetYellow();
	}
	
	public void setLevel(int index,int level) {
		pet[index].setLevel(level);
	}
	public int getLevel(int index) {
		return pet[index].getLevel();
	}

}
