import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class API {
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

    }

    void addChildProducts(Element XMLParent, Base javaParent) {

        for (Product javaProduct : javaParent.ProductList) {
            String tagName = javaProduct.ProductList.isEmpty() ? "Product" : "ProductParent";
            Element XMLProduct = addElement(XMLParent, tagName);
            addAttribute(XMLProduct, "Name", javaProduct.Name);
            addAttribute(XMLProduct, "Price", String.valueOf(javaProduct.Price));
            addChildProducts(XMLProduct, javaProduct);
        }

    }

    void getChildProducts(Element XMLParent, Base javaParent) {

            NodeList x = XMLParent.getChildNodes();
            for (int i = 0; i < x.getLength(); i++) {
                Element XMLProduct = (Element)x.item(i);
                
                Product javaProduct = new Product(XMLProduct.getAttribute("Name"), Double.parseDouble(XMLProduct.getAttribute("Price")) );

                getChildProducts(XMLProduct, javaProduct);
                // checks if parent is not an agreement.
                if(!(javaParent instanceof Agreement)){
                    javaProduct.Parent = javaParent;
                }
                javaParent.ProductList.add(javaProduct);
            }
    }

    Boolean storeAgreement(Agreement Omega) {
        File agreementPath = new File(System.getProperty("user.dir") + "\\Agreements\\" + Omega.Name);
        buildXMLDocument();
        try {
            if (agreementPath.createNewFile()) {
                System.out.println("Created new agreement");
            }

            // root element
            Element Agreement = addElement(doc, "Agreement");
            addAttribute(Agreement, "Name", Omega.Name);
            addAttribute(Agreement, "SignedBy", Omega.SignedBy);

            // add children
            addChildProducts(Agreement, Omega);

            // write the document into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(agreementPath);
            transformer.transform(source, result);
        } catch (Exception e) {
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