package physicsCollisions;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class VertletPoint {
	public Point2D.Double position;
	public Point2D.Double prevPos;
	public Point2D.Double acceleration;
	
	public final static double timeStep = 0.5;
	
	
	public VertletPoint(int x, int y){
		this.position = new Point2D.Double(x, y);
	    this.prevPos = new Point2D.Double(x, y);
	    this.acceleration = new Point2D.Double(0, -1);
	}
	
	public VertletPoint(Point point){
		int x = point.x;
		int y = point.y;
		this.position = new Point2D.Double(x, y);
	    this.prevPos = new Point2D.Double(x, y);
	    this.acceleration = new Point2D.Double(0, -1);
	    
	}
	
	public VertletPoint(Point2D.Double point){
		double x = point.x;
		double y = point.y;
		this.position = new Point2D.Double(x, y);
	    this.prevPos = new Point2D.Double(x, y);
	    this.acceleration = new Point2D.Double(0, -1);
	    
	}
	public VertletPoint(int x, int y, int var){
		this.position = new Point2D.Double(x, y);
	    this.prevPos = new Point2D.Double(x-var, y-var);
	    this.acceleration = new Point2D.Double(0, -1);
	}
	
	
	public void step(){
		


		//acceleration.x += acceleration.x;
		//acceleration.y += acceleration.y;
	    
	   	Point2D.Double tempPosition = (Double) position.clone();

	    tempPosition.x = 2 * tempPosition.x - prevPos.x + acceleration.x;
		tempPosition.y = tempPosition.y *2 - prevPos.y + acceleration.y;


	    this.prevPos = this.position;
	    this.position = tempPosition;
	    //this.acceleration.zero();
	    

	
		
	}


}
