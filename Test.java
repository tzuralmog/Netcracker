import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        System.out.println("Testibng API storage");
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

        // This should be two as beta has a parent product and cant be a child of the agreement
        System.out.println(Omega.ProductList.size());
        
        API test = new API();
        test.storeAgreement(Omega);
        // File agreementPath = new File(System.getProperty("user.dir") + "\\Agreements\\" + Omega.Name);
        Path agreementPath = Paths.get(System.getProperty("user.dir") + "\\Agreements\\" + Omega.Name);

        
        try {
            String testXML = Files.readString(agreementPath);
            System.out.println(testXML);
            // String s1 = "X", s2 = "X", test = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>
            // <Agreement Name=\"" + Omega.Name+ "\" SignedBy=\"Tzur\">
            //     <Product Name=\"Alpha\" Price=\"1.0\"/>
            //     <ProductParent Name=\"Gamma\" Price=\"3.0\">
            //         <Product Name=\"Alpha\" Price=\"1.0\"/>
            //         <Product Name=\"Beta\" Price=\"2.0\"/>
            //     </ProductParent>
            // </Agreement>";
            System.out.println(test);
            assert s1.equals(s2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
// Product Alpha = new Product("Alpha", 1.0);

// ArrayList<Product> ProductList = new ArrayList<Product>();
// Product Beta = new Product(Alpha, "Beta", 2.0, ProductList);

// ArrayList<Product> ProductList2 = new ArrayList<Product>();
// ProductList2.add(Alpha);
// ProductList2.add(Beta);
// Product Gamma = new Product("Gamma", 3.0, ProductList2);

// ArrayList<Product> ProductList3 = new ArrayList<Product>();
// ProductList3.add(Alpha);
// ProductList3.add(Beta);
// ProductList3.add(Gamma);
// Agreement Omega = new Agreement("Tzur", ProductList3);

// API test = new API();
// test.storeAgreement(Omega);

// Agreement gottenAgre =
// test.getAgreement("C:\\Users\\Tzur\\Desktop\\Java\\NetCracker\\Legacy\\Agreement
// 09-07-2021");

// test.storeAgreement(gottenAgre);