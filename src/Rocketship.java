import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;

	}

	void update() {
		if (up) {
			up();
		}
		if (down) {
			down();
		}
		if (right) {
			right();
		}
		if (left) {
			left();
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	public void right() {
		if (x < 450) {
			x += speed;
		}
	}

	public void left() {
		if (x > 0) {
			x -= speed;
		}
	}

	public void down() {
		if (y <= 725) {
			y += speed;
		}
	}

	public void up() {
		if (y > 0) {
			y -= speed;
		}

	}
}
