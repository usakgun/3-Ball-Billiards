import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class mainMenu extends JFrame implements ActionListener {
	GridLayout g;
	JLabel player1;
	JLabel player2;
	protected JTextField player1TextArea;
	protected JTextField player2TextArea;
	JButton playSinglePlayerButton;
	JButton playMultiplayerButton;
	JButton clearButton;
	JButton cancelButton;
	static String namePlayer1;
	static String namePlayer2;

	public void createMainMenu() {
		g = new GridLayout(8, 1);
		setLayout(g);
		setSize(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		player1 = new JLabel(" Player 1 Name : ");
		player1TextArea = new JTextField();
		player2 = new JLabel(" Player 2 Name : ");
		player2TextArea = new JTextField();

		playSinglePlayerButton = new JButton("Singleplayer Mode");
		playMultiplayerButton = new JButton("Multiplayer Mode");

		clearButton = new JButton("Clear");
		cancelButton = new JButton("Cancel");

		playSinglePlayerButton.addActionListener(this);
		playMultiplayerButton.addActionListener(this);
		clearButton.addActionListener(this);
		cancelButton.addActionListener(this);

		add(player1);
		add(player1TextArea);
		add(player2);
		add(player2TextArea);
		add(playSinglePlayerButton);
		add(playMultiplayerButton);
		add(clearButton);
		add(cancelButton);

	}

	@Override
	public void actionPerformed(ActionEvent clickAction) {
		if (clickAction.getSource() == clearButton) {
			player1TextArea.setText("");
			player2TextArea.setText("");

		} else if (clickAction.getSource() == cancelButton) {
			setVisible(false);

		} else if (clickAction.getSource() == playSinglePlayerButton) {
			namePlayer1 = player1TextArea.getText();
			setVisible(false);
			JFrame jf = new JFrame("Singleplayer Session");
			singleplayerBallPanel session = new singleplayerBallPanel();
			jf.add(session);
			jf.setSize(800, 800);
			jf.setVisible(true);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} else if (clickAction.getSource() == playMultiplayerButton) {
			namePlayer1 = player1TextArea.getText();
			namePlayer2 = player2TextArea.getText();
			setVisible(false);
			JFrame jf = new JFrame("Multiplayer Session");
			multiplayerBallPanel session = new multiplayerBallPanel();
			jf.add(session);
			jf.setSize(800, 800);
			jf.setVisible(true);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
	}
}