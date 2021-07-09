
import java.util.ArrayList;


public class Netcracker {
    public static void main(String[] args) {

        Product Alpha = new Product("Alpha", 1.0);
        // System.out.println("Name = " + Alpha.Name + " Price = " + Alpha.Price);

        ArrayList<Product> ProductList = new ArrayList<Product>();
        Product Beta = new Product(Alpha, "Beta", 2.0, ProductList);
        // System.out.println("Parent name = " + Beta.Parent.Name + " Name = " +
        // Beta.Name + " Price = " + Beta.Price);

        ArrayList<Product> ProductList2 = new ArrayList<Product>();
        ProductList2.add(Alpha);
        ProductList2.add(Beta);
        Product Gamma = new Product("Gamma", 3.0, ProductList2);
        // System.out.println("Parent name = " + Beta.Parent.Name + " Name = " +
        // Beta.Name + " Price = " + Beta.Price);

        ArrayList<Product> ProductList3 = new ArrayList<Product>();
        ProductList3.add(Alpha);
        ProductList3.add(Beta);
        ProductList3.add(Gamma);
        Agreement Omega = new Agreement("Tzur", ProductList3);
        // System.out.println("Name = " + Omega.Name + " Signed By = " + Omega.SignedBy
        // + " Number of products = "+ Omega.ProductList.size());

        // System.out.println(System.getProperty("user.dir"));
        API test = new API();
        test.storeAgreement(Omega);

        Agreement gottenAgre = test.getAgreement("C:\\Users\\Tzur\\Desktop\\Java\\NetCracker\\Legacy\\Agreement 09-07-2021");

        test.storeAgreement(gottenAgre);

        // System.out.println(gottenAgre.ProductList.get(1).ProductList.get(0).Parent);

    }

}




