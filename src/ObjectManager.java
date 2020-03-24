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
		for (int b = 0; b < aliens.size(); b++) {
			if (rocket.collisionBox.intersects(aliens.get(b).collisionBox)) {
				aliens.get(b).isActive = false;
			}
			for (int c = 0; c < aliens.size(); c++) {
				if (projectiles.get(c).collisionBox.intersects(aliens.get(c).collisionBox)) {
					projectiles.get(c).isActive = false;
					aliens.get(c).isActive = false;
				}
			}
		}
	}
}
