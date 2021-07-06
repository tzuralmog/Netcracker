package JavaTest;

public class Netcracker {
    
}
class Gauss {

    public static double GetPi( int Iter){
        double a = 1, b = 1/Math.sqrt(2), t = .25, p =1;
        for(int i = 0 ; i < Iter; i++ ){
            double aI = a, bI = b, tI = t, pI =p;
            a = (aI + bI) / 2;
            b = Math.sqrt(aI * bI);
            t = tI - pI * ((aI - a) *(aI - a)) ;
            p = 2 * pI;
        }
        return ((a + b)* (a + b))/ (4* t) ;
    }

    public static void main(String[] args) {
        
        
        // int x = 5;
        System.out.println("Hello, World.");
        System.out.println("Pi = " + GetPi(5) );
        
    }
}