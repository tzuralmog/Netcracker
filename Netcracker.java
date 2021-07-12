
import java.util.ArrayList;


public class Netcracker {
    public static void main(String[] args) {

        Product Alpha = new Product("Alpha", 1.0);

        ArrayList<Product> ProductList = new ArrayList<Product>();
        Product Beta = new Product(Alpha, "Beta", 2.0, ProductList);


        ArrayList<Product> ProductList2 = new ArrayList<Product>();
        ProductList2.add(Alpha);
        ProductList2.add(Beta);
        Product Gamma = new Product("Gamma", 3.0, ProductList2);


        ArrayList<Product> ProductList3 = new ArrayList<Product>();
        ProductList3.add(Alpha);
        ProductList3.add(Beta);
        ProductList3.add(Gamma);
        Agreement Omega = new Agreement("Tzur", ProductList3);

        API test = new API();
        test.storeAgreement(Omega);

        Agreement Delta = test.getAgreement(System.getProperty("user.dir") +"\\Test\\Agreement 10-07-2021");

        test.storeAgreement(Delta);

    }

}




