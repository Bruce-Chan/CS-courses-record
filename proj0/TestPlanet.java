public class TestPlanet{
	public static void main(String[] args){
		Planet sun = new Planet(Math.pow(10,12), 2*Math.pow(10,11), 
			                    0, 0, 2*Math.pow(10,30), "jupiter.gif");
		Planet saturn = new Planet(2.3*Math.pow(10,12), 9.5*Math.pow(10,11), 
			                    0, 0, 6*Math.pow(10,26), "jupiter.gif");
		System.out.println("Force exerted on Sun by Saturn: " + 
			sun.calcForceExertedBy(saturn) );
		System.out.println("Force exerted on Saturn by Sun: " + 
			saturn.calcForceExertedBy(sun) );
	}
}