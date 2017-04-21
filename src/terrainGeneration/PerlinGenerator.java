package terrainGeneration;

import java.awt.Graphics;

import common.MapGenerator;

public class PerlinGenerator extends MapGenerator {
	private final int gap = 1;
	private final int amplitude = 255;
	private int line[];
	private int height, width;
	
	@Override
	public void generateMap(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
		
		line = new int[width/gap];
		double random = Math.random();
		double temp;
	
		
		for(int i = 0; i < width/gap; i++){
			temp = (amplitude * PerlinNoise.octavePerlin((float)i / width + random * 100 , 0,0, 6, 1));
			line[i] = (int) temp;
		}

	}

	@Override
	public void draw(Graphics g) {
		int x1, x2, y1, y2;
		
		if(line != null){
			for(int i = 0; i < line.length - 1; i++){
				x1 = i*gap;
				x2 = i*gap + gap;
				y1 = height*2 - line[i] + 50;
				y2 = height*2 - line[i+1] + 50 ;
	
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

}
