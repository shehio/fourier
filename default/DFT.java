import java.util.Arrays;

public class DFT {
	Complex[] input;
	boolean forward;

	public DFT(Complex[] input, boolean forward) {
		this.input = input;
		this.forward = forward;
	}

	public Complex[] calculate() {
		if (input == null || input.length < 2)
			return input;
		int n = input.length;
		double twoPiOnN = 2 * Math.PI / n, twoPiKOnN = 0, twoPiKTOnN = 0;
		Complex[] output = new Complex[n];
		Complex aggregate, iter;
		Complex divide = new Complex(n, 0);
		for (int k = 0; k < n; k++) {
			twoPiKOnN = twoPiOnN * k;
			aggregate = new Complex(0, 0);
			for (int t = 0; t < n; t++) {
				twoPiKTOnN = twoPiKOnN * t;
				if (forward)
					iter = (new Complex(Math.cos(twoPiKTOnN), Math.sin(twoPiKTOnN))).conjugate();
				else
					iter = new Complex(Math.cos(twoPiKTOnN), Math.sin(twoPiKTOnN)).divides(divide);

				aggregate = aggregate.plus(iter.times(input[t]));
			}
			output[k] = aggregate;
		}

		return output;

	}

	public static void main(String[] args) {
		Complex[] input = new Complex[5];
		for (int i = 0; i < input.length; i++) {
			input[i] = new Complex(i, 0);
		}

		DFT instance = new DFT(input, true);
		Complex[] output = instance.calculate();
		instance = new DFT(output, false);
		Complex[] third = instance.calculate();
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(output));
		System.out.println(Arrays.toString(third));
	}

}
