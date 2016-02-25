import java.util.Arrays;

public class IFFT {
	Complex[] input;

	public IFFT(Complex[] input) {
		this.input = input;
	}

	public Complex[] calculate() {
		if (input == null || input.length < 2)
			return input;
		int n = input.length;
		for (int i = 0; i < n; i++)
			input[i] = input[i].conjugate();
		FFT instance = new FFT(input);
		Complex[] output = instance.run();
		System.out.println(input.length);
		System.out.println(output.length);
		for (int i = 0; i < n; i++)
			output[i] = output[i].conjugate().times(1.0 / n);
		return output;
	}

	public static void main(String[] args) {
		
		Complex[] input = new Complex[5];
		
		for (int i = 0; i < input.length; i++) {
			input[i] = new Complex(i, 0);
		}

		FFT instance = new FFT(input);
		Complex[] output = instance.run();
		Complex[] third = (new IFFT(output)).calculate();
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(output));
		System.out.println(Arrays.toString(third));
	}
}
