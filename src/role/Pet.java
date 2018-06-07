package role;

public class Pet extends Role{
	protected String name;
	protected int level;
	protected int exp;
	private int[] levelExp= {
			0,5,10,15,20,30,40,50,100,200,300,500
	};
	
	public Pet() {
		super();
		exp=0;
	}
	
	public void setExp(int e) {
		exp=e;
		while(exp>levelExp[level]) {
			exp=exp-levelExp[level];
			level++;
			//setLevel(level);
		}
	}
	
	public int getExp() {
		return exp;
	}

}
