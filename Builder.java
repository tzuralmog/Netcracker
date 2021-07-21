import java.util.ArrayList;

public class Builder {
    

    private String Name = null;
    private ArrayList<Product> ProductList = new ArrayList<Product>();
    private Base Parent = null;
    private Double Price = 0.0;
    private String SignedBy = null;

    public Agreement buildAgreement(){
        return new Agreement(SignedBy,ProductList);
    }


    public Product buildProduct(){
        return new Product(Parent,Name,Price,ProductList);
    }

    public Builder Name(String Name){
        this.Name = Name;
        return this;
    }
    public Builder ProductList(ArrayList<Product> ProductList){
        this.ProductList.clear();
        this.ProductList.addAll(ProductList);
        return this;
    }
    public Builder Parent(Base Parent){
        this.Parent = Parent;
        return this;
    }
    public Builder Price(Double Price){
        this.Price = Price;
        return this;
    }
    public Builder SignedBy(String SignedBy){
        this.SignedBy = SignedBy;
        return this;
    }

}
