import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class PaintPanel extends JPanel{

	public static Scanner kbr = new Scanner(System.in);
	public static Scanner kbr2 = new Scanner(System.in);
	
	public static final int PERLIN_SCALE = 50,
							WINDOW_SIZE = 600;
	
	private PerlinNoise p;

	public PaintPanel() {
		p = new PerlinNoise(PERLIN_SCALE, WINDOW_SIZE);
		JFrame easel = new JFrame();
		easel.setSize(WINDOW_SIZE, WINDOW_SIZE);
		easel.add(this);
		easel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		easel.setVisible(true);
		repaint();
	}

	public void paintComponent(Graphics g) {
		p.draw(g);
	}

	public static void main(String[] args) {
		PaintPanel t = new PaintPanel();
	}
}
