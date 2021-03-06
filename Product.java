import java.util.ArrayList;

public class Product extends Base {
    // public variables
    Base Parent;
    Double Price;

    Product(Base Parent, String Name, Double Price, ArrayList<Product> ProductList) {
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

    Product()  {}

    public Double getPrice() {
        return Price;
    }

    public Base getParent() {
        return Parent;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setParent(Base parent) {
        Parent = parent;
    }

}
