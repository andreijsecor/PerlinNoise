import java.awt.*;
import javax.swing.*;

public class PerlinNoise{
	
	private float[][] gradients;
	private int[][] colors;
	
	public PerlinNoise(int gridSize, int panelSize) {
		gradients = new float[gridSize+1][gridSize+1];
		colors = new int[panelSize][panelSize];
		for (int i = 0; i < gradients.length; i++) {
			for (int j = 0; j < gradients[i].length; j++) {
				gradients[i][j] = (float)(Math.random()*2*Math.PI);
			}
		}
		for (int i = 0; i < panelSize; i++) {
			for (int j = 0; j < panelSize; j++) {
				colors[i][j] = (int)(128 + 127*getPerlinNoise(gridSize*i/(float)panelSize, gridSize*j/(float)panelSize));
			}
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				g.setColor(new Color(colors[i][j], colors[i][j], colors[i][j]));
				g.fillRect(i, j, 1, 1);
			}
		}
	}
	
	private static double interp(double o, double f, float d) {
		return (float)(o + (-Math.sin(Math.PI*2*d)/(2*Math.PI) + d)*(f-o));
	}
	
	public float getPerlinNoise(float x, float y) {
		float dx = x-(int)x;
		float dy = y-(int)y;
		double dotProduct1 = (dx)*Math.cos(gradients[(int)x][(int)y]) + (dy)*Math.sin(gradients[(int)x][(int)y]);
		double dotProduct2 = (dx-1)*Math.cos(gradients[(int)x+1][(int)y]) + (dy)*Math.sin(gradients[(int)x+1][(int)y]);
		double dotProduct3 = (dx)*Math.cos(gradients[(int)x][(int)y+1]) + (dy-1)*Math.sin(gradients[(int)x][(int)y+1]);
		double dotProduct4 = (dx-1)*Math.cos(gradients[(int)x+1][(int)y+1]) + (dy-1)*Math.sin(gradients[(int)x+1][(int)y+1]);
		double inter1 = interp(dotProduct1, dotProduct2, dx);
		double inter2 = interp(dotProduct3, dotProduct4, dx);
		double returned = interp(inter1, inter2, dy);
		//System.out.println(returned);
		return (float)returned;
	}
	
}
