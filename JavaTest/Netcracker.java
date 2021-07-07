package JavaTest;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.*;

public class Netcracker {
    public static void main(String[] args) {

        
        Product Alpha = new Product("Alpha", 1.0);
        System.out.println("Name = " + Alpha.Name + " Price = " + Alpha.Price);

        ArrayList<Product> ProductList = new ArrayList<Product>();
        Product Beta = new Product(Alpha, "Beta", 2.0, ProductList);
        System.out.println("Parent name = " + Beta.Parent.Name + " Name = " + Beta.Name + " Price = " + Beta.Price);

        ArrayList<Product> ProductList2 = new ArrayList<Product>();
        ProductList2.add(Alpha);
        ProductList2.add(Beta);
        Agreement Omega = new Agreement( "Tzur", ProductList2);
        System.out.println("Name = " + Omega.Name + " Signed By = " + Omega.SignedBy + " Number of products = " + Omega.ProductList.size() );

        // System.out.println(System.getProperty("user.dir"));
        API test = new API();
        test.storeAgreement(Omega);
        
    }

    
}

class API{

    API(){
        makeAgreementHome();
    }

    Boolean makeAgreementHome(){
        File newDirectory = new File("Agreements");
        if(newDirectory.isDirectory()){
            return true;
        }else{
            return newDirectory.mkdir();
        }
    }

    Boolean storeAgreement(Agreement Omega){
        File agreementPath = new File(System.getProperty("user.dir")+"\\Agreements\\" + Omega.Name);
        System.out.println(agreementPath);
        // Files.createFile(agreementPath,null);
        try {
            if(agreementPath.createNewFile()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            //TODO: handle exception
            // System.out.println(e);
            e.printStackTrace();
            return false;
        }
        
        
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

    Agreement(  String SignedBy, ArrayList<Product> ProductList) {
        // DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()); 
        this.Name = "Agreement " + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
        // this.Name = this.Name.replace("\\", "/");
        this.SignedBy = SignedBy;
        // this.ProductList.addAll(ProductList);
        for (Product product : ProductList) {
            System.out.println(product.Name);
            if(product.Parent == null){
                
                this.ProductList.add(product);
            }
        }
    }

    Agreement( String SignedBy) {
        this.Name = "Agreement " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        this.SignedBy = SignedBy;
    }

}
