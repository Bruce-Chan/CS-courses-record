public class Planet {
	/** Class Planet */
	double xxPos, yyPos, xxVel, yyVel, mass;
	String imgFileName;
	static double G = 6.67 * Math.pow(10,-11);

    /** Constructor A */
	public Planet(double xP, double yP, double xV,
		          double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName="images/"+img;
	}
	/** Constructor A */
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	/** Calculates the distance between two planets */
	public double calcDistance(Planet p){
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}

	/** 
	  * Calculates the force exerted on this planet 
	  * by the given planet.
	  */
	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		double force = Planet.G * mass * p.mass / (r*r);
		return force;
	}

	/** 
	  * Calculates the x direction force exerted on this planet 
	  * by the given planet.
	  */
	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - xxPos;
		double r = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		return force * dx / r;
	}
	/** 
	  * Calculates the y direction force exerted on this planet 
	  * by the given planet.
	  */
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - yyPos;
		double r = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		return force * dy / r;
	}

	/** 
	  * Calculates the x direction net force exerted on this 
	  * planet by the given planets.
	  */
	public double calcNetForceExertedByX(Planet[] planets){
		int len = planets.length;
		int i = 0;
		double netForceX = 0;
		while (i < len){
			Planet p = planets[i];
			if (this.equals(p)!=true){
				double force = this.calcForceExertedByX(p);
				netForceX = netForceX + force;
			}
			i = i+1;
		}
		return netForceX;
	}

	/** 
	  * Calculates the y direction net force exerted on this 
	  * planet by the given planets.
	  */
	public double calcNetForceExertedByY(Planet[] planets){
		int len = planets.length;
		int i = 0;
		double netForceY = 0;
		while (i < len){
			Planet p = planets[i];
			if (this.equals(p)!=true){
				double force = this.calcForceExertedByY(p);
				netForceY = netForceY + force;
			}
			i = i+1;
		}
		return netForceY;
	}

	/**
	  * determines how much the forces exerted on the planet 
	  * will cause that planet to accelerate, and the resulting 
	  * change in the planet's velocity and position in a small 
	  * period of time dt
	  */
	public void update(double dt, double fx, double fy){
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel = ax * dt + xxVel;
		yyVel = ay * dt + yyVel;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}
    /*  uses the StdDraw API to draw the Planet's img 
     *  at the Planet's position
     */
  	public void draw(){
		StdDraw.picture(xxPos,yyPos,imgFileName);
	}


}
