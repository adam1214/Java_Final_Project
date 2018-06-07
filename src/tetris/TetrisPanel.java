package tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import role.*;

public class TetrisPanel extends JPanel  {
	public int[][] map = new int[10][20];
	Pet pet;
	Enemy enemy;
	JLabel l_enemy;
	JLabel[] l_pet=new JLabel[7];
	int total = 0;
	PetSeq petSeq;
	public ImageIcon icon1 = new ImageIcon("blood.png");
	public ImageIcon image_exp = new ImageIcon("exp.jpg");
	public JLabel blood[] = new JLabel[100];
	public JLabel exp[] = new JLabel[100];
	public JLabel attack_amount[] = new JLabel[7];
	public JLabel attack_amount_sum[] = new JLabel[7];
	public JLabel LEVEL[] = new JLabel[7];
	public JLabel EXP[] = new JLabel[7];
	public Pet[] pet_array = new Pet[8];
	public Enemy[] enemy_array = new Enemy[4];
	public int[] attack_list = new int[8];
	public int[] attack_list_sum = new int[8];
	private int blockType;
	private int turnState;
	private int x, y, hold, next, change;
	private int flag = 0;
	private Image b1, b2;
	int shf=320;
	private Image[] color = new Image[7];
	// [] S������ [] Rotate [] 4*4
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

	public TetrisPanel( PetSeq petSeq1 ) {
		petSeq=petSeq1;
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
		NEXT.setBounds(500+shf, 0, 200, 100);
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
		Timer timer = new Timer(800, new TimerListener());
		timer.start();
		JLabel attack_le=new JLabel("ATTACK");
		   attack_le.setFont(new Font("", Font.BOLD, 12));
		   attack_le.setBounds(shf-230,100,100,100);
		   attack_le.setForeground(Color.blue);
		   add(attack_le);
		   JLabel damage=new JLabel("DAMAGE");
		   damage.setFont(new Font("", Font.BOLD, 12));
		   damage.setBounds(shf-300,100,100,100);
		   damage.setForeground(Color.red);
		   add(damage);
		  for(int i=0;i<7;i++) {
		   Image image = petSeq.pet[i].getIcon().getImage(); // transform it
		   Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		   Icon icon = new ImageIcon(newimg);
		   
		   LEVEL[i] = new JLabel("LEVEL"+Integer.toString(petSeq.pet[i].getLevel()));
		    LEVEL[i].setFont(new Font("", Font.BOLD, 20));
		    LEVEL[i].setBounds(shf-100,110+80*i,100,100);
		    LEVEL[i].setForeground(Color.WHITE);
		    add(LEVEL[i]);
		    
		    attack_amount[i] = new JLabel("0");
		    attack_amount[i].setFont(new Font("", Font.BOLD, 20));
		    attack_amount[i].setBounds(shf-210,120+80*i,100,100);
		    attack_amount[i].setForeground(Color.blue);
		    add(attack_amount[i]);
		    
		    attack_amount_sum[i] = new JLabel("0");
		    attack_amount_sum[i].setFont(new Font("", Font.BOLD, 20));
		    attack_amount_sum[i].setBounds(shf-280,120+80*i,100,100);
		    attack_amount_sum[i].setForeground(Color.red);
		    add(attack_amount_sum[i]);
		    
		    
		    EXP[i] = new JLabel("EXP");
		    EXP[i].setFont(new Font("", Font.BOLD, 20));
		    EXP[i].setBounds(shf-100,140+80*i,100,100);
		    EXP[i].setForeground(Color.WHITE);
		    add(EXP[i]);
		   l_pet[i]= new JLabel(icon);
		   l_pet[i].setBounds(shf-200,130+80*i,100,100);
		   add(l_pet[i]);
		  }
		for (int k = 0; k < 100/* curblood*100/emeny.blood */; k++) {
			blood[k] = new JLabel(icon1);
			blood[k].setBounds(500+shf + k, 300, 1, 20);
			add(blood[k]);
		}
	}

	public void initattack() {
		//Sleep(500);
		for (int k = 1; k <= 7; k++) {
			attack_list[k] = 0;			
		}
		
	}

	public void attack() {
		System.out.print("attack_list:");
		for (int k = 1; k <= 7; k++) {
			total = total + attack_list[k];
			System.out.print(k + "=" + attack_list[k] + " ");
			attack_amount[k-1].setText(Integer.toString(attack_list[k]));
			attack_list_sum[k]=attack_list[k]+attack_list_sum[k];
			attack_amount_sum[k-1].setText(Integer.toString(attack_list_sum[k]));
		}

		System.out.println("total=" + total);
		blood(total);
		
		initattack();
	}
	public void level() {
		 for(int i=0;i<7;i++) {
			 LEVEL[i] = new JLabel("LEVEL"+Integer.toString(petSeq.pet[i].getLevel()));
			    LEVEL[i].setFont(new Font("", Font.BOLD, 20));
			    LEVEL[i].setBounds(shf-100,110+80*i,100,100);
			    LEVEL[i].setForeground(Color.WHITE);
			    add(LEVEL[i]);
		 }
	}
	public void win() {
		JLabel win = new JLabel("YOU WIN !!!");
		win.setFont(new Font("", Font.BOLD, 50));
		win.setBounds(170+shf, 200, 300, 100);
		win.setForeground(Color.white);
		add(win);
		get_exp();
		level();
		
	}
	public void draw_level() {
		for(int i=0;i<7;i++) {
		LEVEL[i].setText("LEVEL"+Integer.toString(petSeq.pet[i].getLevel()));
		}
	}
	public void get_exp() {
		for(int i=0;i<7;i++) {
		petSeq.pet[i].setExp(50);
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
		if (gameOver(x, y) == 1) {
			initMap();
			JLabel over = new JLabel("GAME OVER !!!");
			over.setFont(new Font("", Font.BOLD, 45));
			over.setBounds(150+shf, 200, 400, 200);
			over.setForeground(Color.white);
			add(over);
			// JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		repaint();
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
			if (i == 0 && attack_list[1] != 0) {
				attack();
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
						g.drawImage(b1, i * 30 + 3 * (i + 1) + 150+shf, j * 30 + 3 * (j + 1), null);
					else
						g.drawImage(b2, i * 30 + 3 * (i + 1) + 150+shf, j * 30 + 3 * (j + 1), null);
				} else
					g.drawImage(color[map[i][j] - 1], i * 30 + 3 * (i + 1) + 150+shf, j * 30 + 3 * (j + 1), null);
			}
		}
		if (flag == 0) {
			for (int i = 0; i < 16; i++) {
				if (shapes[blockType][turnState][i] == 1) {
					g.drawImage(color[blockType], (i % 4 + x) * 33 + 3 + 150+shf, (i / 4 + y) * 33 + 3, null);
				}
			}
		}
		if (hold >= 0) {
			for (int i = 0; i < 16; i++) {
				if (shapes[hold][0][i] == 1) {
					g.drawImage(color[hold], (i % 4) * 33 + 3+shf, (i / 4) * 33 + 3 + 80, null);
				}
			}
		}
		for (int i = 0; i < 16; i++) {
			if (shapes[next][0][i] == 1) {
				g.drawImage(color[next], (i % 4) * 33 + 530+shf, (i / 4) * 33 + 3 + 80, null);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed1(KeyEvent e) {
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
		case KeyEvent.VK_SHIFT:
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
}