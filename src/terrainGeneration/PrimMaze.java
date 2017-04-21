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
	private boolean [][] isRevealed;
	private static final int scale = 25;
	private static final int offset = 300;
	
	@Override
	public void generateMap(int width, int height) {

		//create graph
		this.width = width;
		this.height = height;
		
		isRevealed = new boolean[width][height];
		
		rootNode = new Node(new Point2D(0,0), null);
		prims(rootNode, null);
	}	
	
	public void prims(Node parentNode, ArrayList<Node> frontiers){
		
		Node rootNode = parentNode;
		Node newTile;
		Node tempParent;
		ArrayList<Node> children;
		
		
		if(frontiers == null){
			frontiers = new ArrayList<Node>();
		}
		
		int x = parentNode.getValue().getX();
		int y = parentNode.getValue().getY();
		
		isRevealed[x][y] = true;
		
		addFrontier(x-1, y, frontiers, parentNode);
		addFrontier(x+1, y, frontiers, parentNode);
		addFrontier(x, y-1, frontiers, parentNode);
		addFrontier(x, y+1, frontiers, parentNode);
		
		while(!frontiers.isEmpty()){
			newTile = frontiers.get((int)(frontiers.size()*Math.random()));
			
			x = newTile.getValue().getX();
			y = newTile.getValue().getY();
			
			isRevealed[x][y] = true;
			
			children = newTile.getChildren();
			if(children.size() != 0){
				tempParent = children.get((int)(children.size()*Math.random()));
				newTile.clearChildren();
				tempParent.addChild(newTile);
				if(newTile.getValue().getX() < 0){
					int j = 0;
				}
			}
			
			frontiers.remove(newTile);
			
			addFrontier(x-1, y, frontiers, newTile);
			addFrontier(x+1, y, frontiers, newTile);
			addFrontier(x, y-1, frontiers, newTile);
			addFrontier(x, y+1, frontiers, newTile);
			
			
			
		}
		

	}
	
	public void addFrontier(int x, int y, ArrayList<Node> frontiers, Node parent){
		
		Node frontier = new Node(new Point2D(x,y), parent);
		
		//TODO check if x and y correspond to height and width properly (in case it isn't working for some reason)
		if(x >= 0 && y >= 0 && x < width && y < height){
			if(isRevealed[x][y]){
				return;
			}
			if(frontiers.contains(frontier)){
				for(Node node : frontiers){
					if(node.equals(frontier)){
						node.addChild(parent);
					}
				}
			}else{
				frontier.addChild(parent);
				frontiers.add(frontier);
			}
		}
		
	}

	public void recDraw(Graphics g, Node parent, ArrayList<Node> children){
		int x1, x2, y1;
		int y2 = 0;
		
		//draws the line segments and fills the area under it with black
	
		for (int i = 0; i < children.size(); i++) {
			x1 = scale*children.get(i).getValue().getX() + offset;
			x2 = scale*parent.getValue().getX() + offset;
			y1 = -scale*children.get(i).getValue().getY() + offset;
			y2 = -scale*parent.getValue().getY() + offset;

			g.drawLine(x1, y1, x2, y2);
			//g.fillPolygon(new int[] { x1, x2, x2, x1 }, new int[] { y1, y2,50+height, 50+height }, 4);
			recDraw(g, children.get(i), children.get(i).getChildren());
		}
	
	}
	
	@Override
	public void draw(Graphics g) {
		if(rootNode != null){
			Node tempNode = rootNode;
			ArrayList<Node> tempChildren = rootNode.getChildren();
			recDraw(g, tempNode, tempChildren);
		}
		
	}

}
