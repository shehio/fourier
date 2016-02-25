public class PSD {
	Complex[] input;
	PSD(Complex[] input){
		this.input = input;
	}
	double[] calculate(){
		if(input == null)
			return null;
		double[] psd = new double[input.length];
		for(int i = 0; i < input.length; i++)
			psd[i] = input[i].re() * input[i].re() + input[i].im() * input[i].im();
		return psd;
	}
	public static void main(String[] args) {

	}

}
