import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

public class Polynomial {

	double [] coefficients;
	int [] exponents;

	public Polynomial(){
		coefficients = new double[1];
		exponents = new int[1];
		coefficients[0] = 0;
		exponents[0] = 0;
	}

	public Polynomial(double[] coefficients, int[] exponents){
		int i = coefficients.length;
		this.coefficients = new double[i];
		this.exponents = new int[i];
		for (int j = 0; j < i; j++){
			if(!(coefficients[j] == 0)){
				this.coefficients[j] = coefficients[j];
				this.exponents[j] = exponents[j];
			}
		}
	}

	public Polynomial(File file) throws Exception{
		String filePath = file.getPath();
		BufferedReader b = new BufferedReader(new FileReader(filePath));
		String polyString = b.readLine();
		b.close();
		String [] polyList = polyString.split("[x+]");
		int len;
		if (polyList.length%2 == 0){
			len = polyList.length/2;
			exponents = new int[len];
			coefficients = new double[len];
			for (int i = 0; i < len; i = i++){
				exponents[i] = Integer.parseInt(polyList[2*i + 1]);
				coefficients[i] = Double.parseDouble(polyList[2*i]);
			}
		}
		else{
			len = polyList.length/2 + 1;
			exponents = new int[len];
			coefficients = new double[len];
			exponents[0] = 0;
			coefficients[0] = Double.parseDouble(polyList[0]);
			for (int i = 1; i < len; i++){
				exponents[i] = Integer.parseInt(polyList[2*i]);
				coefficients[i] = Double.parseDouble(polyList[2*i - 1]);
			}
		}
	}

	public Polynomial add(Polynomial poly){
		int i = coefficients.length;
		int p = poly.coefficients.length;
		double [] newCo = new double[i + p];
		int [] newEx = new int[i + p];
		int j1 = 0;
		int j2 = 0;
		int k = 0;
		while (j1 < i && j2 < p){
			int ex1 = exponents[j1];
			int ex2 = poly.exponents[j2];
			double co1 = coefficients[j1];
			double co2 = poly.coefficients[j2];
			if (ex1 == ex2){
				newEx[k] = ex1;
				newCo[k] = co1 + co2;
				k++;
				j1++;
				j2++;
			}
			else if (ex1 > ex2){
				newEx[k] = ex1;
				newCo[k] = co1;
				k++;
				j1++;
			}
			else {
				newEx[k] = ex2;
				newCo[k] = co2;
				k++;
				j2++;
			}
		}
		for (int c = j1; c < i; c++){
			newEx[k] = exponents[c];
			newCo[k] = coefficients[c];
			k++;
		}
		for (int c = j2; c < p; c++){
			newEx[k] = poly.exponents[c];
			newCo[k] = poly.coefficients[c];
		}
		return new Polynomial(newCo, newEx);
	}

	public Polynomial multiply(Polynomial poly){
		int l1 = coefficients.length;
		int l2 = poly.coefficients.length;
		double [] newCo = new double[l1 + l2];
		int [] newEx = new int[l1 + l2];
		for(int i = 0; i < l1; i++){
			for(int j = 0; j < l2; j++){
				double tempCo = coefficients[i]*poly.coefficients[j];
				int tempEx = exponents[i] + poly.exponents[j];
				for (int k = 0; k < newCo.length; k++){
					if(newCo[k] == 0){
						newEx[k] = tempEx;
						newCo[k] = tempCo;
						System.out.println("ran1");
						break;
					}
					else if (tempEx == newEx[k]){
						newCo[k] = newCo[k] + tempCo;
						System.out.println("ran2 and newCo:" + newCo[k]);
						break;
					}
				}
			}
		}
		for (int i = 0; i < newCo.length; i++){
			System.out.println(newCo[i]);
		}
		return new Polynomial(newCo, newEx);
	}

	public double evaluate(double x){
		double answer = 0;
		for (int i = 0; i < coefficients.length; i++){
			answer = answer + coefficients[i]*Math.pow(x,exponents[i]);
		}
		return answer;
	}

	public boolean hasRoot(double x){
		return evaluate(x) == 0;
	}

	public void saveToFile(String fileName) throws Exception{
		PrintStream ps = new PrintStream(fileName);
		String line = "";
		for (int i = 0; i < coefficients.length; i++){
			if (exponents[i] == 0){
				line = line + coefficients[i];
			}
			else{
				line = line + "+" + coefficients[i] + "x" + exponents[i];
			}
		}
		ps.println(line);
		ps.close();
	}
	/*
	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	public Polynomial(double[] poly) {
		int i = poly.length;
		coefficients = new double[i];
		for (int j = 0; j < i; j++) {
			coefficients[j] = poly[j];
		}
	}
	public Polynomial add(Polynomial poly2) {
		if (coefficients.length < poly2.coefficients.length) {
			int i = coefficients.length;
			int p = poly2.coefficients.length;
			double [] polyArray = new double[p];
			for (int j = 0; j < i; j++) {
				polyArray[j] = coefficients[j] + poly2.coefficients[j];
			}
			for (int q = i; q < p; q++){
				polyArray[q] = poly2.coefficients[q];
			}
			Polynomial newPoly = new Polynomial(polyArray);
			return newPoly;
		}
		int p = coefficients.length;
		int i = poly2.coefficients.length;
		double[] polyArray = new double[p];
		for (int j = 0; j < i; j++) {
			polyArray[j] = coefficients[j] + poly2.coefficients[j];
		}
		for (int q = i; q < p; q++){
			polyArray[q] = coefficients[q];
		}
		Polynomial newPoly = new Polynomial(polyArray);
		return newPoly;
	}
	public double evaluate(double x){
		double result = 0;
		int i = coefficients.length;
		for (int j = 0; j < i; j++){
			result = result + (Math.pow(x,j)*coefficients[j]);
		}
		return result;
	}
	public boolean hasRoot(double x){
		double result = 0;
		int i = coefficients.length;
		for (int j = 0; j < i; j++){
			result = result + (Math.pow(x,j)*coefficients[j]);
		}
		return (result > -0.000001) && (result < 0.000001);
	}
	*/
}