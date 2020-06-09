package irena.test.campaigns.services;

import irena.test.campaigns.CampaignsApplication;
import irena.test.campaigns.entities.Category;
import irena.test.campaigns.entities.Product;
import irena.test.campaigns.exceptions.CategoryNotFoundException;
import irena.test.campaigns.repos.CategoryRepository;
import irena.test.campaigns.repos.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getProducts(String sellerId, String categoryId) {
        log.info("getProducts");
        if (sellerId == null && categoryId == null) {
           return productRepository.findAll();
        } else if (sellerId != null && categoryId != null) {
           return productRepository.findAllBySellerIdAndCategoryId(Long.parseLong(sellerId), Long.parseLong(categoryId));
        }
        else if (sellerId != null) {
           return productRepository.findAllBySellerId(Long.parseLong(sellerId));
        }
        else {
           return productRepository.findAllByCategoryId(Long.parseLong(categoryId));
        }
     }

    public Product getProductById(long productId) {
        log.info("getProduct");
        return productRepository.findById(productId).orElseThrow();
    }

    public Category getCategoryById(long categoryId) {
        log.info("getCategory");
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public List<Category> getCategories() {
        log.info("getCategories");
        return categoryRepository.findAll();
    }

    public List<Product> getProducts() {
        return getProducts(null, null);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    public List<Product> getProducts(Long categoryId) {
        return getProducts(null, categoryId.toString());
    }
}
