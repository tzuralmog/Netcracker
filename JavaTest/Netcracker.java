package JavaTest;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
// import javax.xml.bind.JAXBContext;
// import javax.xml.bind.JAXBException;
// import javax.xml.bind.Marshaller;

// import javax.xml.*;

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
        Agreement Omega = new Agreement("Tzur", ProductList2);
        System.out.println("Name = " + Omega.Name + " Signed By = " + Omega.SignedBy + " Number of products = "
                + Omega.ProductList.size());

        // System.out.println(System.getProperty("user.dir"));
        API test = new API();
        test.storeAgreement(Omega);

    }

}

class API {
    Document doc;

    API() {
        // make aggreement folder

        makeAgreementHome();
        // init variables
        
        // Create XML Document
        buildXMLDocument();
    }

    Boolean makeAgreementHome() {
        File newDirectory = new File("Agreements");
        if (newDirectory.isDirectory()) {
            return true;
        } else {
            return newDirectory.mkdir();
        }
    }
    Boolean buildXMLDocument() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    Boolean addElement(Node Parent, String ChildName) {
        return false;
    }

    Boolean addAttribute() {
        File newDirectory = new File("Agreements");
        if (newDirectory.isDirectory()) {
            return true;
        } else {
            return newDirectory.mkdir();
        }
    }

    Boolean storeAgreement(Agreement Omega) {
        File agreementPath = new File(System.getProperty("user.dir") + "\\Agreements\\" + Omega.Name);
        System.out.println(agreementPath);
        // Files.createFile(agreementPath,null);
        try {
            if (!agreementPath.createNewFile()) {
                System.out.println("Could not create new file");
            } 

            // root element
            Element Agreement = doc.createElement("Agreement");
            doc.appendChild(Agreement);
            Attr attr = doc.createAttribute("company");
            attr.setValue("Ferrari");
            Agreement.setAttributeNode(attr);

            // write the document into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(agreementPath);
            transformer.transform(source, result);
        } catch (Exception e) {
            // TODO: handle exception
            // System.out.println(e);
            e.printStackTrace();
            return false;
        }
        return false;

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

    Agreement(String SignedBy, ArrayList<Product> ProductList) {
        // DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        this.Name = "Agreement " + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
        // this.Name = this.Name.replace("\\", "/");
        this.SignedBy = SignedBy;
        // this.ProductList.addAll(ProductList);
        for (Product product : ProductList) {
            System.out.println(product.Name);
            if (product.Parent == null) {

                this.ProductList.add(product);
            }
        }
    }

    Agreement(String SignedBy) {
        this.Name = "Agreement " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        this.SignedBy = SignedBy;
    }

}
