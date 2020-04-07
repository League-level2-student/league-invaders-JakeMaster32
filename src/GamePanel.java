import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font play;
	Timer frameDraw;
	Timer alienSpawn;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager object = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		play = new Font("Arial", Font.PLAIN, 25);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
		rocket.update();
		object.update();
		if (rocket.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("LEAGUE INVADERS", 20, 100);
		g.setFont(play);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start", 120, 375);
		g.setFont(play);
		g.setColor(Color.WHITE);
		g.drawString("Press SPACE for instructions", 80, 550);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, 500, 800, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 500, 800);
		}
		object.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 100, 100);
		g.setFont(play);
		g.setColor(Color.BLACK);
		g.drawString("You killed "+ object.getScore()+" enemies", 120, 350);
		g.setFont(play);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to restart", 100, 550);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		repaint();
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
			System.out.println("Action");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				rocket = new Rocketship(250, 700, 50, 50);
				object = new ObjectManager(rocket);
				currentState = MENU;

			} else if (currentState == MENU) {
				currentState = GAME;
				startGame();
			}

			else {
				currentState = END;
				alienSpawn.stop();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("UP");
			rocket.up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("DOWN");

			rocket.down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");

			rocket.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");

			rocket.right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("SPACE");
			object.addProjectile(rocket.getProjectile());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("UP");
			rocket.up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("DOWN");

			rocket.down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");

			rocket.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");

			rocket.right = false;
		}
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void startGame() {
		alienSpawn = new Timer(1000, object);
		alienSpawn.start();
	}
}
