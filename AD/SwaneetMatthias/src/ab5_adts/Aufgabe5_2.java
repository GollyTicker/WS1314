package ab5_adts;

public class Aufgabe5_2 {
	
	// Aufgabe 5.2.1:
	// f [0..9]=[1,1,1,,6,26,66,151,361,861]
	
	
	public static void main(String[] args) {
	}

	// Aufgabe 5.2.2:
	private static int f(int n){
		if(n<0){
			System.out.println("Whoops! N is too small!");
			return -1;
		}

//		f (0|1|2) = 1
//		f n = f (n-1) + (2 * f (n-2)) + (3 * f (n-3))
		if(n<3){
			return 1;
		}
		else{
			return f(n-1) + 2*f(n-2) + 3*f(n-3);
		}
	}
}
