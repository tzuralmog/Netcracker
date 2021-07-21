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
            if (product.getParent() == null) {

                this.ProductList.add(product);
                product.setParent(this);

            }
        }
    }

    Agreement(String SignedBy) {
        this.Name = "Agreement " + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());
        this.SignedBy = SignedBy;
    }

    public String getSignedBy() {
        return SignedBy;
    }

    public void setSignedBy(String signedBy) {
        SignedBy = signedBy;
    }

}
