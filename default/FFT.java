import java.util.Arrays;

public class FFT {
	Complex[] input;

	public FFT(Complex[] input) {
		this.input = adjust(input);

	}

	public Complex[] run() {
		return calculate(input);
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

	public Complex[] calculate(Complex[] input) {
		if (input == null || input.length < 2)
			return input;
		int n = input.length;
		Complex[] even = new Complex[n / 2];
		for (int j = 0; j < n / 2; j++)
			even[j] = input[2 * j];
		even = calculate(even);
		Complex[] odd = new Complex[n / 2];
		for (int j = 0; j < n / 2; j++)
			odd[j] = input[2 * j + 1];
		odd = calculate(odd);
		Complex[] output = new Complex[n];
		double twopiOnN = 2 * Math.PI / n, twoPiKOnN = 0;
		Complex Wk;
		for (int k = 0; k < n / 2; k++) {
			twoPiKOnN = twopiOnN * k;
			Wk = new Complex(Math.cos(twoPiKOnN), Math.sin(twoPiKOnN));
			output[k] = even[k].plus(Wk.times(odd[k]));
			output[k + n / 2] = even[k].minus(Wk.times(odd[k]));
		}
		System.out.println(output.length + " "  + Arrays.toString(output));
		return output;
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

		FFT instance = new FFT(input);
		Complex[] output = instance.run();

		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(output));

	}

}
