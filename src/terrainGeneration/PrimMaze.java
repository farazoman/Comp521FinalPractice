package terrainGeneration;

import java.awt.Graphics;
import java.util.ArrayList;

import common.MapGenerator;
import common.Node;
import common.Point2D;

public class PrimMaze extends MapGenerator {
	private Node rootNode;
	private int width;
	private int height;
	
	@Override
	public void generateMap(int width, int height) {
		// TODO Auto-generated method stub
		//create graph
		this.width = width;
		this.height = height;
		
		rootNode = new Node(new Point2D(0,0), null);
		prims(rootNode, null);
	}
	
	public void prims(Node parentNode, ArrayList<Node> frontiers){
		
		if(frontiers == null){
			frontiers = new ArrayList<Node>();
		}
		
		
		
		for(Node child : parentNode.getChildren()){
			
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
