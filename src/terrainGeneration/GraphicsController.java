package terrainGeneration;

import java.awt.Color;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GraphicsController extends JPanel{
	
	public final static int WIDTH = 500;
	public final static int HEIGHT = 100;
	
	private static MidpointBisection2D mb2;
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
	   
		super.paintComponent(g);  // paint background
		  // setBackground(Color.BLACK);
		g.setColor(Color.BLACK);
		mb2.draw(g);
	
	}
   
	
	public static void main(String args[]){
		
		GraphicsController gc = new GraphicsController();
		mb2 = new MidpointBisection2D();
		
		JFrame frame = new JFrame("Recursive Midpoint Bisection");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH*2, HEIGHT*4);
        frame.setVisible(true);
        frame.add(gc);
        
        gc.setSize(WIDTH*2, 4*HEIGHT);
        gc.setVisible(true);
        gc.setDoubleBuffered(true);
        gc.setFocusable(true);
        
        mb2.generateMap(WIDTH, HEIGHT);
        int x = 0;
        
        
		
	}

   
}
