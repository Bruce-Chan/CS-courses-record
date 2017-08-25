public class Maximum {
   public static int max(int[] m) {
       int len = m.length;
       int i = 0;
       int max = 0;
       while (i < len){
       	if (m[i]>max){
       		max = m[i];
       	}
       	i = i + 1;
       }
       return max;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
      System.out.println(max(numbers));      
   }
}