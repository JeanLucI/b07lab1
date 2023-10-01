import java.io.File;
public class Driver {
    public static void main(String [] args) throws Exception{
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {1, 1};
        int [] e1 = {0, 1};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {1, 1};
        int [] e2 = {0, 1};
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        Polynomial m = p1.multiply(p2);
        System.out.println("Exponents: " + s.exponents[0] + ", " + s.exponents[1] + " Coefficients: " + s.coefficients[0] + ", " + s.coefficients[1]);
        System.out.println("Exponents: " + m.exponents[0] + ", " + m.exponents[1] + ", " + m.exponents[2] + " Coefficients: " + m.coefficients[0] + ", " + m.coefficients[1] + ", " + m.coefficients[2]);
        File file = new File("test.txt");
        Polynomial p3 = new Polynomial(file);
        System.out.println("Exponents: " + p3.exponents[0] + ", " + p3.exponents[1] + ", " + p3.exponents[2] + " Coefficients: " + p3.coefficients[0] + ", " + p3.coefficients[1] + ", " + p3.coefficients[2]);
        p3.saveToFile("test.txt");
    }
}
