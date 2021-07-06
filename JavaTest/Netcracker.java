package JavaTest;
import java.util.ArrayList;


public class Netcracker {
    public static void main(String[] args) {
        
        
        System.out.println("Hello, World.");
        Product Alpha = new Product("Alpha", 1.0);
        System.out.println("Name = "+Alpha.Name + " Price = "+Alpha.Price);

        ArrayList<Product> Children = new ArrayList<Product>();
        Product Beta = new Product(Alpha ,"Beta", 2.0, Children);
        System.out.println("Parent name = "+Beta.Parent.Name + " Name = "+Beta.Name + " Price = "+Beta.Price);

    }
}

class Product {
    // public variables
        Product Parent;
        String Name;
        Double Price;
        ArrayList<Product> Children = new ArrayList<Product>();
    
        Product(Product Parent, String Name, Double Price, ArrayList<Product> Children){
            this.Parent = Parent;
            this.Name = Name;
            this.Price = Price;
            this.Children.addAll(Children);
        }
    
        Product(String Name, Double Price){
            this.Name = Name;
            this.Price = Price;
        }
    
        void Moo( ){
            System.out.println("Moo");
        }
    }
// class Gauss {

//     public static double GetPi( int Iter){
//         double a = 1, b = 1/Math.sqrt(2), t = .25, p =1;
//         for(int i = 0 ; i < Iter; i++ ){
//             double aI = a, bI = b, tI = t, pI =p;
//             a = (aI + bI) / 2;
//             b = Math.sqrt(aI * bI);
//             t = tI - pI * ((aI - a) *(aI - a)) ;
//             p = 2 * pI;
//         }
//         return ((a + b)* (a + b))/ (4* t) ;
//     }

    
// }