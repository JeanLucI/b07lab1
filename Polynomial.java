public class Polynomial {
	double [] coefficients;
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
}