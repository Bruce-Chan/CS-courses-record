public class NBody {
	/**
	  * Return a double corresponding to the radius of the
	  * universe in that file.
	  */
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int num = in.readInt();
		return in.readDouble();
	}
	/**
	  * Return an arrays of the planets in the
	  * universe in that file.
	  */
	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int leng = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[leng];
		for(int i = 0; i < leng; i++){
			planets[i] = new Planet(in.readDouble(), in.readDouble(), 
				                    // x and y-coordinates of the initial position
				                    in.readDouble(), in.readDouble(),
				                    // x and y-components of the initial velocity 
				                    in.readDouble(), in.readString() );
			                        // mass and image file
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);

		double min = -1 * radius;
		double max = 1 *radius;
		double size = max*2;

		/** Sets up the universe with the radius.*/
		StdDraw.setScale(min,max);

        /** Draw the image starfield.jpg as the background.*/
		StdDraw.picture(0, 0, "images/starfield.jpg", size, size);

		for(int i=0; i < planets.length; i++){
			planets[i].draw();
		}

		double time = 0;

		StdAudio.play("audio/2001.mid");

		//Animation
		while (time<T){
			int len = planets.length;
			double[] xForces = new double[len];
			double[] yForces = new double[len];
			//Calculate the net x and y forces for each planet.
			for(int i=0; i < len; i++){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			//Call update on each of the planets.
			for(int i=0; i < len; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
            
            //Draw the background image.
			StdDraw.picture(0, 0, "images/starfield.jpg", size, size);
            
            //Draw all of the planets.
			for(int i=0; i < len; i++){
				planets[i].draw();
			}

            //Pause the animation for 10 milliseconds.
            StdDraw.show(10);
            time = time + dt;
		}

		
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
        	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, 
   		    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
        }




	}

}
