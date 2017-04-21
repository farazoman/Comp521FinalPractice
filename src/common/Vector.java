package common;

public class Vector {
	private double [] coordinates;
	private int dimensions;
	
	public Vector(double [] coordinates, int dimensions){
		if(coordinates == null){
			throw new IllegalArgumentException("coordinates can't be null");
		}
		if(coordinates.length != dimensions){
			throw new IllegalArgumentException("size of coordinates doesn't match dimensions");
		}
		
		this.coordinates = coordinates;
		this.dimensions = dimensions;
	}
	
	public double getCoordAt(int index){
		if(index > 0 && index < dimensions){
			return coordinates[index];
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void setCoordAt(int index, double value){
		if(index > 0 && index < dimensions){
			coordinates[index] = value;
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}
}
