package ab1_adts;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		AverageVariance av = new AverageVariance();
		for (int i = 0; i < 10; i++) {
			av.addValues(Arrays.asList(6.0, 3.0, 8.0, 5.0, 3.0));
			System.out.println(av);
		}
	}

}
