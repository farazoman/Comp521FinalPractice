package terrainGeneration;

public final class PerlinNoise {

	 static final int p[] = new int[512];
	 static final int permutation[] = { 151,160,137,91,90,15,
			   131,13,201,95,96,53,194,233,7,225,140,36,103,30,69,142,8,99,37,240,21,10,23,
			   190, 6,148,247,120,234,75,0,26,197,62,94,252,219,203,117,35,11,32,57,177,33,
			   88,237,149,56,87,174,20,125,136,171,168, 68,175,74,165,71,134,139,48,27,166,
			   77,146,158,231,83,111,229,122,60,211,133,230,220,105,92,41,55,46,245,40,244,
			   102,143,54, 65,25,63,161, 1,216,80,73,209,76,132,187,208, 89,18,169,200,196,
			   135,130,116,188,159,86,164,100,109,198,173,186, 3,64,52,217,226,250,124,123,
			   5,202,38,147,118,126,255,82,85,212,207,206,59,227,47,16,58,17,182,189,28,42,
			   223,183,170,213,119,248,152, 2,44,154,163, 70,221,153,101,155,167, 43,172,9,
			   129,22,39,253, 19,98,108,110,79,113,224,232,178,185, 112,104,218,246,97,228,
			   251,34,242,193,238,210,144,12,191,179,162,241, 81,51,145,235,249,14,239,107,
			   49,192,214, 31,181,199,106,157,184, 84,204,176,115,121,50,45,127, 4,150,254,
			   138,236,205,93,222,114,67,29,24,72,243,141,128,195,78,66,215,61,156,180
			   };
	 
	 public static double octavePerlin(double x, double y, double z, int octaves, double persistence) {
			
		 double total = 0;
		 double frequency = 1;
		 double amplitude = 128;
		 double maxValue = 0;			// Used for normalizing result to 0.0 - 1.0
		
		 double [] coords = {x * frequency, y * frequency, z * frequency};
		
		 for(int i=0;i<512;i++) {
			 p[i] = permutation[i%256];
		 }		
		 
		 for(int i=0;i<octaves;i++) {
			 coords[0] = x * frequency;
			 coords[1] = y * frequency;
			 coords[2] = z * frequency;
			 //total += noise(x*frequency, y * frequency, z* frequency) * amplitude;
			 total += perlinNoise(coords, 3) * amplitude;
			
			 maxValue += amplitude;
			
			// amplitude *= persistence;
			// frequency *= 2;
				frequency *= 2;
				amplitude /= 2;
		 }
		
		 return total/maxValue;
	 }
	 
	 static public double noise(double x, double y, double z) {
	      int X = (int)Math.floor(x) & 255,                  // FIND UNIT CUBE THAT
	          Y = (int)Math.floor(y) & 255,                  // CONTAINS POINT.
	          Z = (int)Math.floor(z) & 255;
	      x -= Math.floor(x);                                // FIND RELATIVE X,Y,Z
	      y -= Math.floor(y);                                // OF POINT IN CUBE.
	      z -= Math.floor(z);
	      double u = fade(x),                                // COMPUTE FADE CURVES
	             v = fade(y),                                // FOR EACH OF X,Y,Z.
	             w = fade(z);
	      int A = p[X  ]+Y, AA = p[A]+Z, AB = p[A+1]+Z,      // HASH COORDINATES OF
	          B = p[X+1]+Y, BA = p[B]+Z, BB = p[B+1]+Z;      // THE 8 CUBE CORNERS,

	      return lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ),  // AND ADD
	                                     grad(p[BA  ], x-1, y  , z   )), // BLENDED
	                             lerp(u, grad(p[AB  ], x  , y-1, z   ),  // RESULTS
	                                     grad(p[BB  ], x-1, y-1, z   ))),// FROM  8
	                     lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  // CORNERS
	                                     grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
	                             lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
	                                     grad(p[BB+1], x-1, y-1, z-1 ))));
	   }
	 

	 
	 public static double perlinNoise(double [] coords, int dimensions){
		 double noiseValue = 0;
		 int unitSquare [] = new int[dimensions];
		 double fade [] = new double[dimensions];
	//	 double gradients [] = new double[dimensions];
	//	 double hash [] = new double[(int) Math.pow(2, dimensions)];
		 
		 if(coords.length != dimensions){
			 throw new IllegalArgumentException("the value for dimensions does not match the size of the coordinates. plz fix");
		 }
	 
		 for(int i = 0; i < dimensions; i++){
			 unitSquare[i] = (int)Math.floor(coords[i]) & 255;
		 }
		 
		 //calculate relative location of point in unitSquare
		 for(int i = 0; i < dimensions; i++){
			 coords[i] -= Math.floor(coords[i]);
		 }
		 
		 for(int i = 0; i < dimensions; i++){
			 fade[i] = fade(coords[i]);
		 }
		 

		 
		 int A = p[unitSquare[0]  ]+unitSquare[1];
		 int AA = p[A]+unitSquare[2];
		 int AB = p[A+1]+unitSquare[2];
		 int B = p[(unitSquare[0]+1)]+unitSquare[1];
		 int BA = p[B]+unitSquare[2];
		 int BB = p[B+1]+unitSquare[2]; 
		 
		 double x = coords[0];
		 double y = coords[1];
		 double z = coords[2];
		 
		 double u = fade[0];
		 double v = fade[1];
		 double w = fade[2];
		 
		 noiseValue =  lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ),  // AND ADD
							                 grad(p[BA  ], x-1, y  , z   )), // BLENDED
							         lerp(u, grad(p[AB  ], x  , y-1, z   ),  // RESULTS
							                 grad(p[BB  ], x-1, y-1, z   ))),// FROM  8
							 lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  // CORNERS
							                 grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
							         lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
							                 grad(p[BB+1], x-1, y-1, z-1 ))));
			 
		 return noiseValue;
	 }
	 
	 static double fade(double t) {
		 return t * t * t * (t * (t * 6 - 15) + 10); 
	 }
	 
	 public static double grad(int hash, double x, double y, double z)
	 {
	     switch(hash & 0xF)
	     {
	         case 0x0: return  x + y;
	         case 0x1: return -x + y;
	         case 0x2: return  x - y;
	         case 0x3: return -x - y;
	         case 0x4: return  x + z;
	         case 0x5: return -x + z;
	         case 0x6: return  x - z;
	         case 0x7: return -x - z;
	         case 0x8: return  y + z;
	         case 0x9: return -y + z;
	         case 0xA: return  y - z;
	         case 0xB: return -y - z;
	         case 0xC: return  y + x;
	         case 0xD: return -y + z;
	         case 0xE: return  y - x;
	         case 0xF: return -y - z;
	         default: return 0; // never happens
	     }
	 }
	 
	 static double lerp(double t, double a, double b) { 
		 return a + t * (b - a); 
	 }
	 
}
