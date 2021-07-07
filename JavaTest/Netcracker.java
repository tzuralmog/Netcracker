package JavaTest;

import java.util.ArrayList;

public class Netcracker {
    public static void main(String[] args) {

        System.out.println("Hello, World.");
        Product Alpha = new Product("Alpha", 1.0);
        System.out.println("Name = " + Alpha.Name + " Price = " + Alpha.Price);

        ArrayList<Product> ProductList = new ArrayList<Product>();
        Product Beta = new Product(Alpha, "Beta", 2.0, ProductList);
        System.out.println("Parent name = " + Beta.Parent.Name + " Name = " + Beta.Name + " Price = " + Beta.Price);

        ArrayList<Product> ProductList2 = new ArrayList<Product>();
        Agreement Omega = new Agreement("Omega", "Tzur", ProductList);
        System.out.println("Name = " + Omega.Name + " Signed By = " + Beta.Name );

    }
}

class Base {
    String Name;
    ArrayList<Product> ProductList = new ArrayList<Product>();
}

class Product extends Base {
    // public variables
    Base Parent;
    Double Price;

    Product(Product Parent, String Name, Double Price, ArrayList<Product> ProductList) {
        this.Parent = Parent;
        this.Name = Name;
        this.Price = Price;
        this.ProductList.addAll(ProductList);
    }

    Product(String Name, Double Price) {
        this.Name = Name;
        this.Price = Price;
    }

}

class Agreement extends Base {
    // public variables
    String SignedBy;

    Agreement( String Name, String SignedBy, ArrayList<Product> ProductList) {
        this.Name = Name;
        this.SignedBy = SignedBy;
        this.ProductList.addAll(ProductList);
    }

    Agreement(String Name, String SignedBy) {
        this.Name = Name;
        this.SignedBy = SignedBy;
    }

}
