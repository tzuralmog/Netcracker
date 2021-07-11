import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Agreement extends Base {
    // public variables
    String SignedBy;

    Agreement(String SignedBy, ArrayList<Product> ProductList) {
        this.Name = "Agreement " + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
        this.SignedBy = SignedBy;
        for (Product product : ProductList) {
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
