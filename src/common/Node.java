package common;

import java.util.ArrayList;

public class Node {
	private Point2D value;
	private Node parent;
	private ArrayList<Node> children;
	
	public Node(Point2D value, Node parent){
		this.value = value;
		this.parent = parent;
		if(parent != null){
			
			//parent.addChild(this);
		}
		children = new ArrayList<Node>();
	}
	
	public void addChild(Node node){
	
			
				children.add(node);
			
		
	}
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public Point2D getValue(){
		return value;
	}
	
	public void changeParent(Node p){
		parent = p;
	}
	
	@Override
	public boolean equals(Object o2){
		if(o2 == null){
			return false;
		}
	    if (!Node.class.isAssignableFrom(o2.getClass())) {
	        return false;
	    }
		
		final Node n2 = (Node)o2;
		
		//don't want to check nested parents because only one value of instance will exist
		/**if(this.parent == null && n2.parent == null){
			return this.value.equals(n2.value);
		}else if(this.parent == null || n2.parent == null){
			return false;
		}
		return (this.value.equals(n2.value) && this.parent.value.equals(n2.parent.value));
		**/
		return (this.value.equals(n2.value));
	}
	
	public void clearChildren(){
		children = new ArrayList<Node>();
	}
}
