public class DrawTriangle {
   public static void main(String[] args) {
   	drawTriangle(10);
      
   }

   public static void drawTriangle(int N) {
   	String stars = "*";
   	while (N > 0){
   		System.out.println(stars);
   		stars = stars + "*";
   		N = N - 1;
   	}
   }
}