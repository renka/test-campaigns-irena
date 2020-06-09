package irena.test.campaigns.exceptions;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException() {
        super("Seller not found");
    }
}
