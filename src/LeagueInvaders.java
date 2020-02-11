import javax.swing.JFrame;
import javax.swing.JPanel;
public class LeagueInvaders {

	JFrame frame;
	public static final int Width = 500;
	public static final int Height = 800;
	LeagueInvaders() {
frame = new JFrame();
	}
public static void main(String[] args) {
	LeagueInvaders invader = new LeagueInvaders();
	invader.setup();
}
void setup() {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(500,800);
	frame.setVisible(true);
}
}
