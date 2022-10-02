import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Polynomial {
	double [] coefficients;
	int [] exponents;
	
	public Polynomial() {
		coefficients = new double [1];
		exponents = new int [1];
		exponents[0] = 1;
		coefficients[0] = 0;
	}
	
	public Polynomial(double [] coefficients, int [] exponents) {
		
		this.coefficients = new double[coefficients.length];
		this.exponents = new int [exponents.length];

		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		}
	}

	public Polynomial(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String line = input.nextLine();

		String terms [] = line.split("(?=-)|\\+");
		//If "+" or "-" splits but splits before the minus

		coefficients = new double [terms.length];
		exponents = new int [terms.length];

		for (int i = 0; i < terms.length; i++){
			//loop through each element
			if (terms[i].contains("x")){
				//make everything at the front coeff and back exp
				int x_pos = terms[i].indexOf("x");
				String coeff = terms[i].substring(0, x_pos);
				String exp = terms[i].substring(x_pos + 1);

				if (coeff.equals("")){
					coefficients[i] = 1;
				}
				else {
					coefficients[i] = Double.parseDouble(coeff);
				}

				if (exp.equals("")){
					exponents[i] = 1;
				}
				else {
					exponents[i] = Integer.parseInt(exp);
				}

			}
			else {
				//Case where no x in term
				exponents[i] = 0;
				coefficients[i] = Double.parseDouble(terms[i]);
			}
		}

	}

	public void SaveToFile(String file) throws IOException{

		//fist do the output thing to file 
		//Need to check for 0 exponents and 1 coefficients
		//Also check for negatives so we can see whether to add a plus or not

		String Poly = "";
		if (coefficients[0] != 1){
			Poly = Poly + coefficients[0];
		}

		if (exponents[0] != 0){
			if (exponents[0] == 1) {
				Poly = Poly + "x";
			}
			else {
				Poly = Poly + "x" + exponents[0];
			}
		}
		else if (exponents[0] == 0 && coefficients[0] == 1){
			Poly = "1";
		}


		for (int i = 1; i < exponents.length; i++){
			if (coefficients[i] > 0){
				Poly += "+";
				if (coefficients[i] != 1) Poly += coefficients[i];
			}
			else {
				Poly += coefficients[i];
			}

			//exponent part

			if (exponents[i] != 0){
				if (exponents[i] == 1) {
					Poly = Poly + "x";
				}
				else {
					Poly += "x" + exponents[i];
				}
			}
			else if (exponents[i] == 0 && coefficients[i] == 1){
				Poly += "1";
			}
		}
		FileWriter output = new FileWriter(file, false);
		output.write(Poly);
		output.close();
	}
	/* If hashmap is not allowed use the methods to just convert and convert back (simple fix)
	private double [] format(double [] coefficients, int [] exponents){
		//converts back to old format
		m = 0;
		for (int i = 0; i < exponents.length; i++){
			m = Math.max(m, exponents);
		}

		double array [] = new double [m];

		for (int i = 0; i < m; m++){
			array[exponents[i]] = coefficients[i];
		}

		return array;
	}
	private double [] unformat(double [] array) {
		//converts to new format
		for (i = 0; i < exponents.length; i++){
			coefficients[i] = array[exponents[i]];
		}
	}
	*/

	public Polynomial add(Polynomial obj) {
		
		Map <Integer, Double> map = new HashMap<>();

		for (int i = 0; i < coefficients.length; i++) {
			map.put(exponents[i], coefficients[i]);
		}

		for (int i = 0; i < obj.exponents.length; i++) {
			if (map.containsKey(obj.exponents[i])){
				map.put(obj.exponents[i], map.get(obj.exponents[i]) + obj.coefficients[i]);
			}
			else {
				map.put(obj.exponents[i], obj.coefficients[i]);
			}
		}

		double coeff [] = new double [map.size()];
		int exp [] = new int [map.size()];

		int i = 0;
		for (int value: map.keySet()){
			coeff[i] = map.get(value);
			exp[i] = value;
			i++;
		}
		
		return new Polynomial(coeff, exp);
	}

	public Polynomial multiply(Polynomial obj) {

		Map <Integer, Double> map = new HashMap<>();

		for (int i = 0; i < exponents.length; i++){
			for (int j = 0; j < obj.exponents.length; j++){

				if (map.containsKey(exponents[i] + obj.exponents[j])){
				map.put(exponents[i] + obj.exponents[j], map.get(exponents[i] + obj.exponents[j]) + coefficients[i]*obj.coefficients[j]);
				}
				else {
				map.put(exponents[i] + obj.exponents[j], coefficients[i]*obj.coefficients[j]);
				}
			}
		}

		double coeff [] = new double [map.size()];
		int exp [] = new int [map.size()];

		int i = 0;
		for (int value: map.keySet()){
			coeff[i] = map.get(value);
			exp[i] = value;
			i++;
		}
		
		return new Polynomial(coeff, exp);

	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++) {
			sum += coefficients[i]*(Math.pow(x, exponents[i]));
		}
		return sum;
	}
	
	public boolean hasRoot(double x) {
		
		return evaluate(x) == 0;
	}
	}