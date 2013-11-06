package ab5_adts;

public class Aufgabe5_2 {
	
	// Aufgabe 5.2.1:
	// f [0..9]=[1,1,1,,6,26,66,151,361,861]
	
	
	public static void main(String[] args) {
		aufgabe5_2_3();
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

	// Aufgabe 5.2.3:
	private static void aufgabe5_2_3() {
		int prevResult = 1;
		for(int n=0; true; n++){
			int result = f(n);
			
			// check whether the current result is
			// smaller than the previous one for overflows
			// checking for negative numbers isn't sufficient
			// because the result is too big to become negative
			if(result < prevResult){
				System.out.println("overflow at n=" + n);
				break;
			}
			prevResult=result;
			System.out.println("n="+n+" works! result: " + result);
		}
	}
}
