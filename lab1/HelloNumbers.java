public class HelloNumbers{
	public static void main(String[] args){
		int total = 0;
		int num = 0;
		while (num < 10){
			total = num +total;
			System.out.print(total + " ");
			num = num + 1;
		}
	}
}