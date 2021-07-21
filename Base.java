import java.util.ArrayList;


public class Base {
    
    String Name;
    ArrayList<Product> ProductList = new ArrayList<Product>();

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Product> getProductList() {
        return ProductList;
    }

    public void setProductList(ArrayList<Product> productList) {
        ProductList = productList;
    }

}
