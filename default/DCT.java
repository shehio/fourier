import java.util.Arrays;

public class DCT {
	Complex[] input;

	public DCT(Complex[] input) {
		this.input = input;
	}

	public Complex[] calculate() {
		if (input == null || input.length < 2)
			return input;
		input = adjust(input);
		System.out.println(Arrays.toString(input));
		input = reverseAddAndConcatinate(input);
		FFT instance = new FFT(input);
		Complex [] ret = instance.run();
		return ret;
	}

	private Complex[] reverseAddAndConcatinate(Complex[] input) {
		int n = input.length;
		Complex[] newInput = new Complex[n * 2];
		for (int i = 0; i < n; i++)
			newInput[i] = input[i];
		for (int i = 0; i < n; i++)
			newInput[n + i] = input[(n - 1) - i];
		return newInput;
	}

	private Complex[] adjust(Complex[] input) {
		int n = input.length;
		Complex[] newInput;
		int missing = missings(n);
		newInput = new Complex[missing];
		for (int j = 0; j < n; j++)
			newInput[j] = input[j];
		for (int j = n; j < missing; j++)
			newInput[j] = new Complex(0, 0);
		input = newInput;
		return input;
	}

	// n = 1 << log2(n)
	private int missings(int n) {
		if ((n & n - 1) == 0)
			return n;
		int i = 0;
		while (n > 0) {
			n = n >> 1;
			i++;
		}
		return (int) (Math.pow(2, i) - n);
	}

	public static void main(String[] args) {
		Complex[] input = new Complex[5];
		for (int i = 0; i < input.length; i++) {
			input[i] = new Complex(i, 0);
		}

		DCT instance = new DCT(input);
		Complex[] output = instance.calculate();
		
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(output));
	}

}
