import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
		    loadImage ("rocket.png");
		}
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
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		
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
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
} 
}
