package role;

public  class Enemy extends Role{
	
	
	public Enemy() {
		super();
		super.setIcon("./image/player.png");
	}

	public int attack() {
		int de_hp;
		int random=(int)(Math.random()*10+1);  //1~10 
		de_hp=level*random;
		
		return de_hp;
	}
	
}
