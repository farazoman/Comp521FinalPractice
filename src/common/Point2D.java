package common;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D>{
	
	private int x;
	private int y;
	
	public Point2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point2D(Point2D copyPoint){
		this.x = copyPoint.getX();
		this.y = copyPoint.getY();
	}
	
	public Point2D(){
		this.x = 0;
		this.y = 0;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	@Override
	public boolean equals(Object o2){
		if(o2 == null){
			return false;
		}
	    if (!Point2D.class.isAssignableFrom(o2.getClass())) {
	        return false;
	    }
		
		final Point2D p2 = (Point2D)o2;
		
		return this.x == p2.x && this.y == p2.y;
	}

	@Override
	public int compareTo(Point2D p2) {
		if(this.x < p2.x){
			return -1;
		}else if(this.x > p2.x){
			return 1;
		}
		return 0;
	}
	
	public static Comparator<Point2D> getXComparator() {
	        return new Comparator<Point2D>() {
	            public int compare(Point2D p1, Point2D p2){
	            	
	            	if( p1.x < p2.x ){
	            		return -1;
	            	}else if(p1.x > p2.x){
	            		return 1;
	            	}else{
	            		return 0;
	            	}
	            }
	        };
    }
	 
	public static Comparator<Point2D> getYComparator() {
	        return new Comparator<Point2D>() {
	            public int compare(Point2D p1, Point2D p2){
	            	
	            	if( p1.y < p2.y ){
	            		return -1;
	            	}else if(p1.y > p2.y){
	            		return 1;
	            	}else{
	            		return 0;
	            	}
	            }
	        };
	}
	
}
