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
import org.w3c.dom.NodeList;

// import javax.xml.*;

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

        System.out.println(gottenAgre.ProductList.get(1).ProductList.get(0).Parent);

    }

}

class API {
    DocumentBuilder dBuilder;
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
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Element addElement(Node Parent, String ChildName) {
        Element newElement = doc.createElement(ChildName);
        Parent.appendChild(newElement);
        return newElement;
    }

    void addAttribute(Element Parent, String Name, String Value) {
        Attr attr = doc.createAttribute(Name);
        attr.setValue(Value);
        Parent.setAttributeNode(attr);
        // return attr;

    }

    void addChildProducts(Element XMLParent, Base javaParent) {

        for (Product javaProduct : javaParent.ProductList) {
            // System.out.println(javaProduct.Name);
            String tagName = javaProduct.ProductList.isEmpty() ? "Product" : "ProductParent";
            Element XMLProduct = addElement(XMLParent, tagName);
            addAttribute(XMLProduct, "Name", javaProduct.Name);
            addAttribute(XMLProduct, "Price", String.valueOf(javaProduct.Price));
            addChildProducts(XMLProduct, javaProduct);
        }

    }

    void getChildProducts(Element XMLParent, Base javaParent) {

            // NodeList x =  XMLParent.getElementsByTagName("Product");
            NodeList x = XMLParent.getChildNodes();
            for (int i = 0; i < x.getLength(); i++) {
                Element XMLProduct = (Element)x.item(i);
                
                Product javaProduct = new Product(XMLProduct.getAttribute("Name"), Double.parseDouble(XMLProduct.getAttribute("Price")) );

                getChildProducts(XMLProduct, javaProduct);
                // checks if parent is not an agreement.
                if(!(javaParent instanceof Agreement)){
                    // System.out.println("Agrrement parent");
                    javaProduct.Parent = javaParent;
                }
                javaParent.ProductList.add(javaProduct);
            }
    }

    Boolean storeAgreement(Agreement Omega) {
        File agreementPath = new File(System.getProperty("user.dir") + "\\Agreements\\" + Omega.Name);
        // System.out.println(agreementPath);
        // Files.createFile(agreementPath,null);
        buildXMLDocument();
        try {
            if (agreementPath.createNewFile()) {
                System.out.println("Created new agreement");
            }

            // root element
            Element Agreement = addElement(doc, "Agreement");
            // Attr attr = addAttribute(Agreement, "Name", "Value");
            addAttribute(Agreement, "Name", Omega.Name);
            addAttribute(Agreement, "SignedBy", Omega.SignedBy);

            // add children
            addChildProducts(Agreement, Omega);

            // write the document into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(agreementPath);
            transformer.transform(source, result);
        } catch (Exception e) {
            // System.out.println(e);
            e.printStackTrace();
            return false;
        }

        return true;

    }

    Agreement getAgreement(String path) {
        buildXMLDocument();
        Agreement result;
        try {

            File file = new File(path);
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            Element Agreement = doc.getDocumentElement();
            ArrayList<Product> ProductList = new ArrayList<Product>();
            result = new Agreement(Agreement.getAttribute("SignedBy"), ProductList);
            result.Name = Agreement.getAttribute("Name");
            getChildProducts(Agreement, result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
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

    Product(String Name, Double Price, ArrayList<Product> ProductList) {
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
            // System.out.println(product.Name);
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
