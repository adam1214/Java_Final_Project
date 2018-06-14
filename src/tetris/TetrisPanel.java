package tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import role.*;
import connect.*;

public class TetrisPanel extends JPanel {
	public static boolean gametime = true;
	private boolean battle_finish = false;
	public int[][] map = new int[10][20];
	JButton btn;
	Pet pet;
	Enemy enemy;
	JLabel l_enemy;
	JLabel[] l_pet = new JLabel[7];
	int total = 0;
	int enemy_exp = 78;
	PetSeq petSeq;
	Timer timer = new Timer(800, new TimerListener());
	Timer timer_paint = new Timer(100, new TimerListener_paint());
	int keyflag = 0;
	int num = 0;
	public ImageIcon icon1 = new ImageIcon("blood.png");
	public ImageIcon image_exp = new ImageIcon("exp.jpg");
	public ImageIcon image_levelup = new ImageIcon("levelup.png");
	public JLabel blood[] = new JLabel[100];
	public JLabel exp[] = new JLabel[7];
	public JLabel attack_amount[] = new JLabel[7];
	public JLabel attack_amount_sum[] = new JLabel[7];
	public JLabel LEVEL[] = new JLabel[7];
	public JLabel EXP[] = new JLabel[7];
	public JLabel getexp[] = new JLabel[7];
	private int[] level_tem = new int[7];
	private int[] exp_tem = new int[7];
	public Pet[] pet_array = new Pet[8];
	public Enemy[] enemy_array = new Enemy[4];
	public int[] attack_list = new int[8];
	public int[] attack_list_sum = new int[8];
	int[] a = new int[7];
	private int blockType;
	private int turnState;
	private int x, y, hold, next, change;
	private int flag = 0;
	private Image b1, b2;
	int shf = 320;
	private int[] levelExp = { 0, 5, 10, 15, 20, 30, 40, 50, 100, 200, 300, 500 };
	private Image[] color = new Image[7];
	public Data enemyData; //<hong>
	protected int state;
	// [] S���蕭��頩�頩縈�頩�頩�頩蕭 [] Rotate [] 4*4
	private final int shapes[][][] = new int[][][] {
			// I
			{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
					{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
			// s
			{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
			// z
			{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
			// j
			{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// o
			{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// l
			{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// t
			{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

	public TetrisPanel(PetSeq petSeq1) {
		num = 0;
		petSeq = petSeq1;
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		b1 = Toolkit.getDefaultToolkit().getImage("background1.png");
		b2 = Toolkit.getDefaultToolkit().getImage("background2.png");
		color[0] = Toolkit.getDefaultToolkit().getImage("blue.png");
		color[1] = Toolkit.getDefaultToolkit().getImage("green.png");
		color[2] = Toolkit.getDefaultToolkit().getImage("red.png");
		color[3] = Toolkit.getDefaultToolkit().getImage("deepblue.png");
		color[4] = Toolkit.getDefaultToolkit().getImage("yellow.png");
		color[5] = Toolkit.getDefaultToolkit().getImage("orange.png");
		color[6] = Toolkit.getDefaultToolkit().getImage("pink.png");
		JLabel NEXT = new JLabel("NEXT");
		NEXT.setFont(new Font("", Font.BOLD, 50));
		NEXT.setBounds(500 + shf, 0, 200, 100);
		NEXT.setForeground(Color.white);
		add(NEXT);
		JLabel HOLD = new JLabel("HOLD");
		HOLD.setFont(new Font("", Font.BOLD, 50));
		HOLD.setBounds(shf, 0, 200, 100);
		HOLD.setForeground(Color.white);
		add(HOLD);
		initMap();
		initattack();
		newBlock();
		hold = -1;
		next = (int) (Math.random() * 7);
		timer.start();
		timer_paint.start();
		JLabel attack_le = new JLabel("ATTACK");
		attack_le.setFont(new Font("", Font.BOLD, 12));
		attack_le.setBounds(shf - 230, 100, 100, 100);
		attack_le.setForeground(Color.blue);
		add(attack_le);
		JLabel damage = new JLabel("DAMAGE");
		damage.setFont(new Font("", Font.BOLD, 12));
		damage.setBounds(shf - 300, 100, 100, 100);
		damage.setForeground(Color.red);
		add(damage);
		
		
		for (int i = 0; i < 7; i++) {
			a[i]=0;
			attack_list_sum[i+1]=0;
			LEVEL[i] = new JLabel();
			Image image = petSeq.pet[i].getIcon().getImage(); // transform it
			Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			Icon icon = new ImageIcon(newimg);
			l_pet[i] = new JLabel(icon);
			l_pet[i].setBounds(shf - 200, 130 + 80 * i, 100, 100);
			add(l_pet[i]);
			
			level_tem[i] = petSeq.pet[i].getLevel();
			exp_tem[i] = petSeq.pet[i].getExp();
			attack_amount[i] = new JLabel("0");
			attack_amount[i].setFont(new Font("", Font.BOLD, 20));
			attack_amount[i].setBounds(shf - 210, 120 + 80 * i, 100, 100);
			attack_amount[i].setForeground(Color.blue);
			add(attack_amount[i]);

			attack_amount_sum[i] = new JLabel("0");
			attack_amount_sum[i].setFont(new Font("", Font.BOLD, 20));
			attack_amount_sum[i].setBounds(shf - 280, 120 + 80 * i, 100, 100);
			attack_amount_sum[i].setForeground(Color.red);
			add(attack_amount_sum[i]);

			EXP[i] = new JLabel("EXP");
			EXP[i].setFont(new Font("", Font.BOLD, 20));
			EXP[i].setBounds(shf - 100, 140 + 80 * i, 100, 100);
			EXP[i].setForeground(Color.WHITE);
			add(EXP[i]);

			exp[i] = new JLabel(image_exp);
			System.out.print("pet" + i + "=" + petSeq.pet[i].getExp());
			int exp_percent = exp_tem[i] * 100 / levelExp[level_tem[i]];
			exp[i].setBounds(shf - 50, 180 + 80 * i, exp_percent, 15);
			add(exp[i]);
		}
		for (int k = 0; k < 100/* curblood*100/emeny.blood */; k++) {
			blood[k] = new JLabel(icon1);
			blood[k].setBounds(500 + shf + k, 300, 1, 20);
			add(blood[k]);
		}
		level();
		enemyData= new Data();
		state= Data.MODE_PERSONAL_TETRIS;
		// draw();
	}

	public TetrisPanel(PetSeq petSeq1,Enemy e) {
		this(petSeq1);
		Image image = e.getIcon().getImage(); // transform it
		Image newimg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		Icon icon = new ImageIcon(newimg);
		JLabel enemy_lb= new JLabel(icon);
		add(enemy_lb);
		enemy_lb.setBounds(shf,250 , 100, 100);
	}

	public void initattack() {
		// Sleep(500);
		for (int k = 1; k <= 7; k++) {
			attack_list[k] = 0;
		}

	}

	public void attack() {
		System.out.print("attack_list:");
		for (int k = 1; k <= 7; k++) {
			total = total + attack_list[k];
			System.out.print(k + "=" + attack_list[k] + " ");
			attack_amount[k - 1].setText(Integer.toString(attack_list[k]));
			attack_list_sum[k] = attack_list[k] + attack_list_sum[k];
			attack_amount_sum[k - 1].setText(Integer.toString(attack_list_sum[k]));
		}

		System.out.println("total=" + total);
		blood(total);

		initattack();
	}

	public void level() {
		for (int i = 0; i < 7; i++) {
			LEVEL[i].setText("LEVEL" + Integer.toString(level_tem[i]));
			LEVEL[i].setFont(new Font("", Font.BOLD, 20));
			LEVEL[i].setBounds(shf - 100, 110 + 80 * i, 100, 100);
			LEVEL[i].setForeground(Color.WHITE);
			add(LEVEL[i]);
		}
	}

	 public void win() {
		  JLabel win = new JLabel("YOU WIN !!!");
		  win.setFont(new Font("", Font.BOLD, 50));
		  win.setBounds(170 + shf, 200, 300, 100);
		  win.setForeground(Color.white);
		  add(win);
		  get_exp();
		  ImageIcon icon = null;
		  try {
		   icon = new ImageIcon(new URL("https://ws3.sinaimg.cn/large/9150e4e5jw1fcj532mtkvg206s05nmxv.gif"));
		  } catch (MalformedURLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  JLabel lb = new JLabel(icon);
		  lb.setBounds(485 + shf, 330, 350, 330);
		  add(lb);
		  //lb.setLocation(0,0);
		  battle_finish = true;
		  // draw_level();

		  timer.stop();

		 }

	public void get_exp() {
		for (int i = 0; i < 7; i++) {
			int k = attack_list_sum[i + 1] * enemy_exp / total;
			petSeq.pet[i].setExp(k);
			System.out.println(k);
			System.out.println(" pet" + i + " exp=" + petSeq.pet[i].getExp());
			System.out.println(" pet" + i + " level=" + petSeq.pet[i].getLevel());
			a[i]=0;
			getexp[i] = new JLabel("EXP+"+k);
			getexp[i].setFont(new Font("", Font.BOLD, 20));
			getexp[i].setBounds(shf +70, 140 + 80 * i, 100, 100);
			getexp[i].setForeground(Color.GREEN);
			add(getexp[i]);
			add(EXP[i]);
			if (attack_list_sum[i+1] == 0) {
				num++;
				a[i]=1;
			}
		}
	}

	public void draw() {
		for (int i = 0; i < 7; i++) {
			if (battle_finish) {
				//System.out.print(" a[]"+i+"="+a[i]);
				if (exp_tem[i] == levelExp[level_tem[i]]) {
					level_tem[i]++;
					exp_tem[i] = 0;
					JLabel levelup=new JLabel(image_exp);
					levelup.setBounds(10, 110+80*i,100,100);
					add(levelup);
					level();
					
				}
				int exp_percent = exp_tem[i] * 100 / levelExp[level_tem[i]];
				exp[i].setBounds(shf - 50, 180 + 80 * i, exp_percent, 15);
				add(exp[i]);
				if (petSeq.pet[i].getLevel() <= level_tem[i] && petSeq.pet[i].getExp() <= exp_tem[i] && a[i] != 1&&attack_list_sum[i+1]!=0) {
					a[i] = 1;
					num++;
					level();
					System.out.println(num);
					System.out.println("pet" + i + ":" + "exp_tem=" + exp_tem[i] + " exp=" + petSeq.pet[i].getExp());
					System.out.println(
							"pet" + i + ":" + "level_tem=" + level_tem[i] + " level=" + petSeq.pet[i].getLevel());
				}
				if (a[i] != 1) {
					exp_tem[i]++;
				}
				if (num == 7) {
					timer_paint.stop();
					num = 0;
					modeWin();
				}
			}
		}
	}

	public void blood(int sum) {
		if (sum >= 20) {
			win();
			for (int k = 0; k < 100/* curblood*100/emeny.blood */; k++) {
				remove(blood[k]);
			}
			// return 0;
		} else {
			for (int k = 0; k < sum/* curblood*100/emeny.blood */; k++) {
				remove(blood[99 - k]);
			}
		}
	}

	public void newBlock() {
		flag = 0;
		blockType = next;
		change = 1;
		next = (int) (Math.random() * 7);
		turnState = 0;
		x = 3;
		y = 0;
		repaint();
		if (gameOver(x, y) == 1) {
			int m=0;
			JLabel over = new JLabel("GAME OVER !!!");
			add(over);
			over.setFont(new Font("", Font.BOLD, 45));
			over.setBounds(150 + shf, 200, 400, 200);
			over.setForeground(Color.white);
			m++;
			timer.stop();
			 ImageIcon icon = null;
			  try {
			   icon = new ImageIcon(new URL("https://i2.kknews.cc/SIG=296gbns/111100029o2s4q080pr1.jpg"));
			  } catch (MalformedURLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
			  JLabel lb = new JLabel(icon);
			  lb.setBounds(485 + shf, 330, 350, 330);
			  add(lb);
			if(m==1) {
				modeLose();
			}
			
		}

	}

	public void setBlock(int x, int y, int type, int state) {
		flag = 1;
		for (int i = 0; i < 16; i++) {
			if (shapes[type][state][i] == 1) {
				map[x + i % 4][y + i / 4] = type + 1;
			}
		}
	}

	public int gameOver(int x, int y) {
		if (blow(x, y, blockType, turnState) == 0)
			return 1;
		return 0;
	}

	public int blow(int x, int y, int type, int state) {
		for (int i = 0; i < 16; i++) {
			if (shapes[type][state][i] == 1) {
				if (x + i % 4 >= 10 || y + i / 4 >= 20 || x + i % 4 < 0 || y + i / 4 < 0)
					return 0;
				if (map[x + i % 4][y + i / 4] != 0)
					return 0;
			}
		}
		return 1;
	}

	public void rotate() {
		int tmpState = turnState;
		tmpState = (tmpState + 1) % 4;
		if (blow(x, y, blockType, tmpState) == 1) {
			turnState = tmpState;
		}
		repaint();
	}

	public int r_shift() {
		int canShift = 0;
		if (blow(x + 1, y, blockType, turnState) == 1) {
			x++;
			canShift = 1;
		}
		repaint();
		return canShift;
	}

	public void l_shift() {
		if (blow(x - 1, y, blockType, turnState) == 1) {
			x--;
		}
		repaint();
	}

	public int down_shift() {

		int canDown = 0;
		if (blow(x, y + 1, blockType, turnState) == 1) {
			y++;
			canDown = 1;
		}
		repaint();
		if (blow(x, y + 1, blockType, turnState) == 0) {
			// Sleep(500);
			setBlock(x, y, blockType, turnState);
			newBlock();
			delLine();
			// attack();
			canDown = 0;
		}
		return canDown;
	}

	void delLine() {
		int idx = 19, access = 0;
		for (int i = 19; i >= 0; i--) {
			int cnt = 0;
			for (int j = 0; j < 10; j++) {
				if (map[j][i] != 0)
					cnt++;
			}
			if (cnt == 10) {
				access = 1;
				for (int j = 0; j < 10; j++) {
					System.out.print(map[j][i]);
					if (j == 9) {
						System.out.println("");
					}
					attack_list[map[j][i]]++;
					map[j][i] = 0;
				}
				// attack();
			} else {
				for (int j = 0; j < 10; j++) {
					map[j][idx] = map[j][i];
				}
				idx--;
			}
			for (int k = 1; k <= 7; k++) {
				if (i == 0 && attack_list[k] != 0) {
					attack();
					break;
				}
			}
		}
		/*
		 * if(access == 1) Sleep(500);
		 */
	}

	void initMap() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 20; j++)
				map[i][j] = 0;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if (map[i][j] == 0) {
					if ((i + j) % 2 == 0)
						g.drawImage(b1, i * 30 + 3 * (i + 1) + 150 + shf, j * 30 + 3 * (j + 1), null);
					else
						g.drawImage(b2, i * 30 + 3 * (i + 1) + 150 + shf, j * 30 + 3 * (j + 1), null);
				} else
					g.drawImage(color[map[i][j] - 1], i * 30 + 3 * (i + 1) + 150 + shf, j * 30 + 3 * (j + 1), null);
			}
		}
		if (flag == 0) {
			for (int i = 0; i < 16; i++) {
				if (shapes[blockType][turnState][i] == 1) {
					g.drawImage(color[blockType], (i % 4 + x) * 33 + 3 + 150 + shf, (i / 4 + y) * 33 + 3, null);
				}
			}
		}
		if (hold >= 0) {
			for (int i = 0; i < 16; i++) {
				if (shapes[hold][0][i] == 1) {
					g.drawImage(color[hold], (i % 4) * 33 + 3 + shf, (i / 4) * 33 + 3 + 80, null);
				}
			}
		}
		for (int i = 0; i < 16; i++) {
			if (shapes[next][0][i] == 1) {
				g.drawImage(color[next], (i % 4) * 33 + 530 + shf, (i / 4) * 33 + 3 + 80, null);
			}
		}
		paintCharacter(g);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	//<hong>
	public void paintCharacter(Graphics g) {
		
	}
	public void setData(Data enemyData) {
		this.enemyData= enemyData;
	}
	public int getState() {
		return state;
	}
	public int getDmg() {
		return total;
	}
	public void modeWin() {
		gametime = false;
		state= Data.MODE_PERSONAL_WIN;
	}
	public void modeLose() {
		gametime=false;
		state= Data.MODE_PERSONAL_LOSE;
	}
	
	public void keyPressed1(KeyEvent e) {
		if (gametime && gameOver(x, y) == 0) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				down_shift();
				break;
			case KeyEvent.VK_UP:
				rotate();
				break;
			case KeyEvent.VK_RIGHT:
				r_shift();
				break;
			case KeyEvent.VK_LEFT:
				l_shift();
				break;
			case KeyEvent.VK_SPACE:
				while (down_shift() == 1)
					;
				break;
			case KeyEvent.VK_Z:
				if (hold >= 0 && change == 1) {
					int tmp;
					tmp = hold;
					hold = blockType;
					blockType = tmp;
					x = 4;
					y = 0;
					change = 0;
				} else if (change == 1) {
					hold = blockType;
					newBlock();

				}
				break;
			}
		}
	}

	void Sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.out.println("Unexcepted interrupt");
			System.exit(0);
		}
	}

	class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			repaint();
			down_shift();
		}
	}

	class TimerListener_paint implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			draw();
		}
	}
}