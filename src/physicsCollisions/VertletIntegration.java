package physicsCollisions;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import common.Drawable;


//ref: http://codeflow.org/entries/2010/sep/01/hard-constraints-easy-solutions/#all-roads-lead-to-verlet
public class VertletIntegration implements Drawable {
	private VertletPoint [] points;
	private int height, width;
	private int [][] initCoords = {{50,50}, {100, 50},{150, 25}};
	private VertletEdge [] edges;
	
	public VertletIntegration(int height, int width){
		this.points = new VertletPoint[initCoords.length];
		this.edges = new VertletEdge[initCoords.length];
		this.height = height;
		this.width = width;
		
		//make this a fixed point or something?
		//maybe need to use a special constructor
		points[0] = new VertletPoint(initCoords[0][0], initCoords[0][1], 25);
		
		for(int i = 1; i < initCoords.length; i++){
			points[i] = new VertletPoint(initCoords[i][0], initCoords[i][1]);
		}
		
		for(int i = 0; i < edges.length - 1; i++){
			edges[i] = new VertletEdge(points[i], points[i+1]);
		}
		edges[edges.length-1] = new VertletEdge(points[points.length-1], points[0]);
		
	}
	
	public void updateConstraints(){
		 for( int i = 0; i < edges.length; i++ ) {
			    VertletEdge e = edges[ i ];

			    //Calculate the vector mentioned above
			    Point2D.Double p1_p2 = new Point2D.Double(e.p1.position.x - e.p2.position.x, e.p1.position.y - e.p2.position.y);

			    //Calculate the current distance
			    double p1p2Length = magnitude(p1_p2); 
			    
			    //Calculate the difference from the original length
			    double diff = p1p2Length - e.initLength; 
			    
			    if(Math.abs(diff) > 2){
				    normalize(p1_p2);
				    diff-=2;
				    //Push both vertices apart by half of the difference respectively 
				    //so the distance between them equals the original length
				    e.p1.position.x -= p1_p2.x*diff*0.5f; 
				    e.p1.position.y -= p1_p2.y*diff*0.5f; 
				    e.p2.position.x += p1_p2.x*diff*0.5f; 
				    e.p2.position.y += p1_p2.y*diff*0.5f;
			    }
			  }
	}
	
	private double magnitude(Double p1_p2) {
		return Math.sqrt(p1_p2.x*p1_p2.x + p1_p2.y * p1_p2.y);
	}
	
	private void normalize(Point2D.Double p){
		double len = 1.0/magnitude(p);

		p.x *= len;
		p.y *= len;
	}

	public void update(){
		//right now this makes the first point fixed;
		updateConstraints();
		
		for(int i = 0; i < points.length; i++){
			points[i].step();
		}
		
	
	}
	
	@Override
	public void draw(Graphics g) {
		int x1, x2, y1, y2;
		
		//draws the line segments and fills the area under it with black
		for (int i = 0; i < points.length - 1; i++) {
			x1 = (int) points[i].position.getX();
			x2 = (int) points[i + 1].position.getX();
			y1 = height - (int) points[i].position.getY() + 50;
			y2 = height - (int) points[i + 1].position.getY() + 50 ;

			g.drawLine(x1, y1, x2, y2);
			//g.fillPolygon(new int[] { x1, x2, x2, x1 }, new int[] { y1, y2,50+height, 50+height }, 4);
		}	
		x1 = (int) points[points.length-1].position.getX();
		x2 = (int) points[0].position.getX();
		y1 = height - (int) points[points.length-1].position.getY() + 50;
		y2 = height - (int) points[0].position.getY() + 50 ;

		g.drawLine(x1, y1, x2, y2);
		
	}
	
	
	
}
