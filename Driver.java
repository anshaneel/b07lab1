public class Driver { 
 public static void main(String [] args){ 

    Polynomial p = new Polynomial(); 
		System.out.println(p.evaluate(1000)); 
		  
		  double [] coeff = {-122,2.3,-5.4,7,4};
		  int [] exp = {0,1,3,5,11};
		  Polynomial p1 = new Polynomial(coeff, exp);
		  double [] coeff1 = {-122,2.3,-5.4,7,4};
		  int [] exp1 = {12,0,1,5,11};
		  Polynomial p2 = new Polynomial(coeff1, exp1);
		  
		  Polynomial p3 = p2.add(p1);
		  Polynomial p4 = p3.multiply(p1);
		  
		  System.out.println(p1.evaluate(1.34177));
		  System.out.println(p4.hasRoot(1.34177));
 } 
} 