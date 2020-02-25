import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	GamePanel panel;
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;

	LeagueInvaders() {
		panel = new GamePanel();
		frame = new JFrame();
	}

	public static void main(String[] args) {
		LeagueInvaders invader = new LeagueInvaders();
		invader.setup();
	}

	void setup() {
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 800);
		frame.setVisible(true);
		frame.addKeyListener(panel);
	}
}
