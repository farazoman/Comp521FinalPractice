package terrainGeneration;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.Drawable;
import physicsCollisions.VertletIntegration;



public class GraphicsController extends JPanel{
	
	public final static int WIDTH = 500;
	public final static int HEIGHT = 200;
	
	private static MidpointBisection2D mb2;
	private static PrimMaze prim;
	private static PerlinGenerator perlin;
	private static VertletIntegration d;
	
	public static final long delay = 200l;
	
	private static AnimationTimer aniTimer;
	
	public GraphicsController() {
		this.aniTimer = new AnimationTimer();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
	   
		super.paintComponent(g);  // paint background
		  // setBackground(Color.BLACK);
		g.setColor(Color.BLACK);
		//mb2.draw(g);
		//prim.draw(g);
		//perlin.draw(g);
		d.draw(g);
	}
   
	 class AnimationTimer extends TimerTask{ //timer task for the timer
		 
	 		public AnimationTimer() {
	 			super();
	 		}
			
			@Override
		    public void run() { //method called in timer and does all character animations and decisions and mostly contains the game logic
				d.update();
				repaint();
				 	
			}
			
	}
	
	public static void main(String args[]){
		
		Timer timer = new Timer();
		
		GraphicsController gc = new GraphicsController();
		mb2 = new MidpointBisection2D();
		prim = new PrimMaze();
		perlin = new PerlinGenerator();
		d = new VertletIntegration(HEIGHT, WIDTH);
		
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
        prim.generateMap(WIDTH, HEIGHT);
        perlin.generateMap(WIDTH, HEIGHT);
        
        timer.schedule(aniTimer, 0, delay);
        
       // gc.repaint();

        
		
	}

   
}
