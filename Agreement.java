import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Agreement extends Base {
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
