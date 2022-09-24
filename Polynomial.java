public class Polynomial {
	double [] coefficients;
	
	public Polynomial() {
		coefficients = new double [1];
		coefficients[0] = 0;
	}
	
	public Polynomial(double [] array) {
		
		coefficients = new double [array.length];
		for (int i = 0; i < coefficients.length; i++) {
			coefficients[i] = array[i];
		}
	}
	
	public Polynomial add(Polynomial obj) {
		
		int max_length = Math.max(obj.coefficients.length, coefficients.length);
		
		double added [] = new double [max_length];
		for (int i = 0; i < added.length; i++) {
			added[i] = 0;
		}
		//initialize zero array
		
		for (int i = 0; i < obj.coefficients.length; i++) {
			added[i] = obj.coefficients[i];
		}
		
		for (int i = 0; i < coefficients.length; i++) {
			added[i] += coefficients[i];
		}
		
		Polynomial n = new Polynomial(added);
		return n;
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++) {
			sum += coefficients[i]*(Math.pow(x, i));
		}
		return sum;
	}
	
	public boolean hasRoot(double x) {
		
		return evaluate(x) == 0;
	}
	}