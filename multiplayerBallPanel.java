import java.awt.geom.Ellipse2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class multiplayerBallPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	double size = 40;
	double whiteX = 2, whiteY = 2;
	double RedX = 0, RedY = 0;
	double YellowX = 0, YellowY = 0;
	double velocity = 3;

	int scorePlayer1 = 0;
	int scorePlayer2 = 0;
	boolean isscored, c1, c2, c3 = false;

	int cusionCounter = 0;
	boolean stick = false;
	boolean mouse = false;
	short ballSpeed;

	Ellipse2D.Double white = new Ellipse2D.Double(300, 250, size, size);
	Ellipse2D.Double red = new Ellipse2D.Double(500, 150, size, size);
	Ellipse2D.Double yellow = new Ellipse2D.Double(500, 350, size, size);

	double mouseX, mouseY;
	boolean selectTurn = true;
	Timer tmr = new Timer(10, this);

	multiplayerBallPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void positionUpdater() {
		double difx = white.x - red.x;
		double dify = white.y - red.y;

		double difx2 = red.x - white.x;
		double dify2 = red.y - white.y;

		double difx3 = white.x - yellow.x;
		double dify3 = white.y - yellow.y;

		double difx4 = yellow.x - white.x;
		double dify4 = yellow.y - white.y;

		double difx5 = red.x - yellow.x;
		double dify5 = red.y - yellow.y;

		double difx6 = yellow.x - red.x;
		double dify6 = yellow.y - red.y;

		double Emagnitude = Math.hypot(difx, dify);
		double Emagnitude2 = Math.hypot(difx2, dify2);
		double Emagnitude3 = Math.hypot(difx3, dify3);
		double Emagnitude4 = Math.hypot(difx4, dify4);
		double Emagnitude5 = Math.hypot(difx5, dify5);
		double Emagnitude6 = Math.hypot(difx6, dify6);

		if (white.x < 30 || white.x > 690) {
			whiteX = -whiteX;
			cusionCounter++;
		}
		if (white.y < 70 || white.y > 470) {
			whiteY = -whiteY;
			cusionCounter++;
		}
		if (red.x < 30 || red.x > 690) {
			RedX = -RedX;
		}
		if (red.y < 70 || red.y > 470) {
			RedY = -RedY;
		}
		if (yellow.x < 30 || yellow.x > 690) {
			if (selectTurn) {
				cusionCounter++;
			}
			YellowX = -YellowX;
		}
		if (yellow.y < 70 || yellow.y > 470) {
			if (selectTurn) {
				cusionCounter++;
			}
			YellowY = -YellowY;
		}

		whiteX = whiteX * 0.99;
		whiteY = whiteY * 0.99;

		RedX = RedX * 0.99;
		RedY = RedY * 0.99;

		YellowX = YellowX * 0.99;
		YellowY = YellowY * 0.99;

		if (Math.pow(whiteX, 2) + Math.pow(whiteY, 2) < 0.1 && Math.pow(RedX, 2) + Math.pow(RedY, 2) < 0.1
				&& Math.pow(YellowX, 2) + Math.pow(YellowY, 2) < 0.1) {
			whiteX = 0;
			whiteY = 0;
			tmr.stop();
		}

		if (Math.hypot(whiteX, whiteY) > Math.hypot(RedX, RedY)) {
			if (Emagnitude <= white.width) {

				double VrMag = (whiteX * difx + whiteY * dify) / Emagnitude;
				double VRedX = VrMag * difx / Emagnitude;
				double VRedY = VrMag * dify / Emagnitude;

				double Vtx = whiteX - VRedX;
				double Vty = whiteY - VRedY;

				whiteX = Vtx;
				whiteY = Vty;
				RedX = RedX + VRedX;
				RedY = RedY + VRedY;
				c1 = true;
			}
		}

		else {
			if (Emagnitude2 <= red.width) {

				double VrMag2 = (RedX * difx2 + RedY * dify2) / Emagnitude2;
				double VRedX2 = VrMag2 * difx2 / Emagnitude2;
				double VRedY2 = VrMag2 * dify2 / Emagnitude2;

				double Vtx2 = RedX - VRedX2;
				double Vty2 = RedY - VRedY2;

				RedX = Vtx2;
				RedY = Vty2;
				whiteX = whiteX + VRedX2;
				whiteY = whiteY + VRedY2;
				c1 = true;
			}
		}
		if (Math.hypot(whiteX, whiteY) > Math.hypot(YellowX, YellowY)) {
			if (Emagnitude3 <= white.width) {

				double VrMag2 = (whiteX * difx3 + whiteY * dify3) / Emagnitude3;
				double VRedX3 = VrMag2 * difx3 / Emagnitude3;
				double VRedY3 = VrMag2 * dify3 / Emagnitude3;

				double Vtx3 = whiteX - VRedX3;
				double Vty3 = whiteY - VRedY3;

				whiteX = Vtx3;
				whiteY = Vty3;
				YellowX = YellowX + VRedX3;
				YellowY = YellowY + VRedY3;
				c2 = true;
			}
		}

		else {
			if (Emagnitude4 <= yellow.width) {

				double VrMag4 = (YellowX * difx4 + YellowX * dify4) / Emagnitude4;
				double VRedX4 = VrMag4 * difx4 / Emagnitude4;
				double VRedY4 = VrMag4 * dify4 / Emagnitude4;

				double Vtx4 = YellowX - VRedX4;
				double Vty4 = YellowY - VRedY4;

				YellowX = Vtx4;
				YellowY = Vty4;
				whiteX = whiteX + VRedX4;
				whiteY = whiteY + VRedY4;
				c2 = true;
			}
		}

		if (Math.hypot(YellowX, YellowY) > Math.hypot(RedX, RedY)) {
			if (Emagnitude5 <= yellow.width) {

				double VrMag5 = (YellowX * difx5 + YellowY * dify5) / Emagnitude5;
				double VRedX5 = VrMag5 * difx5 / Emagnitude5;
				double VRedY5 = VrMag5 * dify5 / Emagnitude5;

				double Vtx5 = YellowX - VRedX5;
				double Vty5 = YellowY - VRedY5;

				YellowX = Vtx5;
				YellowY = Vty5;
				RedX = RedX + VRedX5;
				RedY = RedY + VRedY5;
				c3 = true;
			}
		}

		else {
			if (Emagnitude6 <= red.width) {

				double VrMag6 = (RedX * difx6 + RedY * dify6) / Emagnitude6;
				double VRedX6 = VrMag6 * difx6 / Emagnitude6;
				double VRedY6 = VrMag6 * dify6 / Emagnitude6;

				double Vtx6 = RedX - VRedX6;
				double Vty6 = RedY - VRedY6;

				RedX = Vtx6;
				RedY = Vty6;
				YellowX = YellowX + VRedX6;
				YellowY = YellowY + VRedY6;
				c3 = true;
			}
		}
		white.x += whiteX;
		white.y += whiteY;

		red.x += RedX;
		red.y += RedY;

		yellow.x += YellowX;
		yellow.y += YellowY;
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		positionUpdater();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gr2d = (Graphics2D) g;
		gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr2d.setColor(Color.BLACK);
		gr2d.fillRect(10, 50, 20, 480);
		gr2d.fillRect(20, 50, 730, 60);
		gr2d.fillRect(730, 50, 20, 480);
		gr2d.fillRect(30, 470, 700, 60);
		gr2d.setColor(Color.GREEN);
		gr2d.fillRect(30, 70, 700, 440);
		gr2d.setColor(Color.WHITE);
		gr2d.fill(white);
		gr2d.setColor(Color.RED);
		gr2d.fill(red);
		gr2d.setColor(Color.YELLOW);
		gr2d.fill(yellow);
		gr2d.setColor(Color.BLACK);
		gr2d.drawString(mainMenu.namePlayer1 + "'s score :  " + scorePlayer1 + "", 10, 10);
		gr2d.drawString(mainMenu.namePlayer2 + "'s score :  " + scorePlayer2 + "", 10, 40);
		gr2d.drawString("Ball speed " + Integer.toString((int) Math.hypot(whiteX, whiteY)), 150, 10);

		if (stick) {
			if (selectTurn) {
				Line2D.Double cue = new Line2D.Double(white.getCenterX(), white.getCenterY(), mouseX, mouseY);
				gr2d.setColor(Color.BLACK);
				gr2d.setStroke(new BasicStroke(10));
				gr2d.draw(cue);
			}
			if (!selectTurn) {
				Line2D.Double cue = new Line2D.Double(white.getCenterX(), white.getCenterY(), mouseX, mouseY);
				gr2d.setColor(Color.BLACK);
				gr2d.setStroke(new BasicStroke(10));
				gr2d.draw(cue);
			}
		}

		if (scorePlayer1 >= 50) {
			super.paint(g);
			g.drawString(mainMenu.namePlayer1 + " won the game!", 500, 250);
		} else if (scorePlayer2 >= 50) {
			super.paint(g);
			g.drawString(mainMenu.namePlayer2 + " won the game!", 500, 250);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!tmr.isRunning()) {
			if (selectTurn) {
				stick = false;
				double x2 = white.x + white.width / 2;
				double y2 = white.y + white.height / 2;
				double dx = x2 - mouseX;
				double dy = y2 - mouseY;

				whiteX = (velocity * dx / Math.sqrt(dx * dx + dy * dy)) * Math.hypot(dx, dy) * 0.03;
				whiteY = (velocity * dy / Math.sqrt(dx * dx + dy * dy)) * Math.hypot(dx, dy) * 0.03;

				ballSpeed = (short) Math.hypot(whiteX, whiteY);
				selectTurn = false;
			}

			else {
				stick = false;
				double x2 = white.x + white.width / 2;
				double y2 = white.y + white.height / 2;
				double dx = x2 - mouseX;
				double dy = y2 - mouseY;

				whiteX = (velocity * dx / Math.sqrt(dx * dx + dy * dy)) * Math.hypot(dx, dy) * 0.03;
				whiteY = (velocity * dy / Math.sqrt(dx * dx + dy * dy)) * Math.hypot(dx, dy) * 0.03;

				ballSpeed = (short) Math.hypot(whiteX, whiteY);
				selectTurn = true;
			}
		}
		tmr.start();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!tmr.isRunning()) {
			stick = true;
			mouseX = e.getX();
			mouseY = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
}
