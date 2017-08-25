public class HorribleSteve {
	public static void main (String [] args) {
		for (int i = 0; i < 500; ++i) {
			if (!Flik.isSameNumber(i, i)) {
				System.out.println("Error: when testing " + i);
				break;
			}
		}
		System.out.println("test finished");
	}
} 