import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	int score = 0;

	int getScore() {
		return score;
	}

	ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
		
	}

	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
		for (Alien alien : aliens) {
			alien.update();
		}
		for (Projectile projectiles : projectiles) {
			projectiles.update();
		}
		checkCollision();
		purgeObjects();
	}

	void draw(Graphics g) {
		rocket.draw(g);
		for (Alien alien : aliens) {
			alien.draw(g);
		}
		for (Projectile projectiles : projectiles) {
			projectiles.draw(g);
		}
	}

	void purgeObjects() {
		for (int i = (aliens.size() - 1); i >= 0; i--) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		for (int i = (projectiles.size() - 1); i >= 0; i--) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
	}

	void checkCollision() {
		for (Alien alien : aliens) {
			if (rocket.collisionBox.intersects(alien.collisionBox)) {
				rocket.isActive = false;
				alien.isActive = false;
			}
			for (Projectile projectile : projectiles) {
				if (alien.collisionBox.intersects(projectile.collisionBox)) {
					score = score +1; 
					alien.isActive = false;
					projectile.isActive = false;
				}
			}
		}
	}

}
