package physicsCollisions;

public class VertletEdge {
	public VertletPoint p1, p2;
	
	public double initLength;
	
	public VertletEdge(VertletPoint p1, VertletPoint p2){
		this.p1 = p1;
		this.p2 = p2;
		
		initLength = Math.sqrt(Math.pow(p2.position.x - p1.position.x, 2) + Math.pow(p2.position.y - p1.position.y, 2 ));
		
	}
}
