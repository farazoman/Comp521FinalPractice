package terrainGeneration;

import java.awt.Graphics;
import java.util.ArrayList;

import common.*;
//Ref: http://www.somethinghitme.com/2013/11/11/simple-2d-terrain-with-midpoint-displacement/
public class MidpointBisection2D extends MapGenerator {
	
	private ArrayList<Point2D> mapSegments;
	private final double redFactor = .75;
	private int randFactorRange [] = {0,50};
	private int displace;
	private int delta = 1;

	private int width;
	private int height;
	
	public MidpointBisection2D(){
		mapSegments = new ArrayList<Point2D>();
	}
	
	public void generateMap(int width, int height){
		Point2D start = new Point2D(0, (int)(Math.random() * height));
		Point2D end = new Point2D(width, (int)(Math.random() * height));
		this.width = width;
		this.height = height;
		displace = height/4;
		midPointGen(start, end, displace);
		mapSegments.sort(Point2D.getXComparator());

	}
	
	public void midPointGen(Point2D start, Point2D end, int displace){
		int midX = (int)((start.getX() + end.getX())/2);
		int midY = (int)((start.getY() + end.getY())/2);

		Point2D newEnd;
		
		midY += (int)(Math.random()*(displace) );
		
		displace *= redFactor;
		
		
		
		newEnd = new Point2D(midX, midY);
		
		
		if(newEnd.getX()-start.getX() > delta){
			midPointGen(start, newEnd, displace);
			midPointGen(newEnd, end, displace);
			mapSegments.add(start);
			mapSegments.add(end);
		}
		
		mapSegments.add(newEnd);
						
	}
	
	public void draw(Graphics g) {
		int x1, x2, y1, y2;
		
		//draws the line segments and fills the area under it with black
		for (int i = 0; i < mapSegments.size() - 1; i++) {
			x1 = mapSegments.get(i).getX();
			x2 = mapSegments.get(i+1).getX();
			y1 = height*3 - mapSegments.get(i).getY() + 50;
			y2 = height*3 - mapSegments.get(i+1).getY() + 50 ;

			g.drawLine(x1, y1, x2, y2);
			//g.fillPolygon(new int[] { x1, x2, x2, x1 }, new int[] { y1, y2,50+height, 50+height }, 4);
		}

	}
	
	
	
}
