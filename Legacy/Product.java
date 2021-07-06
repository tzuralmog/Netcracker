package Legacy;
import java.util.ArrayList;
/* Product class has fields :
	parent object - reference to agreement or parent product.
	products - collection of children products.
	name - string with the product name.
	price - number with product's price. Can be non integer.
*/

public class Product {
// public variables
    Product Parent;
    String Name;
    Double Price;
    ArrayList<Product> Children = new ArrayList<Product>();

    Product(Product Parent, String Name, Double Price, ArrayList<Product> Children){
        this.Parent = Parent;
        this.Name = Name;
        this.Price = Price;
        this.Children.addAll(Children);
    }

    Product(String Name, Double Price){
        this.Name = Name;
        this.Price = Price;
    }

    void Moo( ){
        System.out.println("Moo");
    }
}
