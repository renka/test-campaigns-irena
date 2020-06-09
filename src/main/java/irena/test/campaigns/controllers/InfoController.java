package irena.test.campaigns.controllers;

import irena.test.campaigns.entities.Campaign;
import irena.test.campaigns.entities.Category;
import irena.test.campaigns.entities.Product;
import irena.test.campaigns.entities.Seller;
import irena.test.campaigns.services.CampaignService;
import irena.test.campaigns.services.ProductService;
import irena.test.campaigns.services.SellerService;
import irena.test.campaigns.utils.BaseResponse;
import irena.test.campaigns.utils.ErrorHandlingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoController {

    private static final Logger log = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private CampaignService campaignService;

    @GetMapping("products")
    public BaseResponse<List<Product>> getProducts(@RequestParam(value = "seller", required = false) String seller, @RequestParam(value = "category", required = false) String category) {
        List<Product> products = productService.getProducts(seller, category);
        return getListResponse(products);
    }

    @GetMapping("products/{id}")
    public BaseResponse<Product> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return new BaseResponse<>(product);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "No product found", log);
        }
    }

    @GetMapping("categories")
    public BaseResponse getCategories() {
        List<Category> categories = productService.getCategories();
        return getListResponse(categories);
    }

    @GetMapping("categories/{id}")
    public BaseResponse<Category> getCategory(@PathVariable Long id) {
        try {
            Category category = productService.getCategoryById(id);
            return new BaseResponse<>(category);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "No category found", log);
        }
    }

    @GetMapping("sellers")
    public BaseResponse getSellers() {
        List<Seller> sellers = sellerService.getSellers();
        return getListResponse(sellers);
    }

    @GetMapping("sellers/{id}")
    public BaseResponse<Category> getSeller(@PathVariable Long id) {
        try {
            Seller seller = sellerService.getSellerBtId(id);
            return new BaseResponse(seller);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "No seller found", log);
        }
    }

    @GetMapping("campaigns")
    public BaseResponse getCampaigns(@RequestParam(value = "seller", required = false) String seller) {
        List<Campaign> campaigns = campaignService.getCampaigns(seller);
        return getListResponse(campaigns);
    }

    @GetMapping("campaigns/{id}")
    public BaseResponse<Campaign> getCampaign(@PathVariable Long id) {
        try {
            Campaign campaign = campaignService.getCampaign(id);
            return new BaseResponse<>(campaign);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "No category found", log);
        }
    }

    private BaseResponse getListResponse(List data) {
        if (data.isEmpty()) {
            return new BaseResponse<>(null,"No items found");
        }
        return new BaseResponse<>(data, "Success");
    }
}
