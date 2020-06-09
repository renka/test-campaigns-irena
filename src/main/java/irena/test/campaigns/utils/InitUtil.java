package irena.test.campaigns.utils;

import com.github.javafaker.Faker;
import irena.test.campaigns.entities.Category;
import irena.test.campaigns.entities.Product;
import irena.test.campaigns.entities.Seller;
import irena.test.campaigns.repos.CategoryRepository;
import irena.test.campaigns.repos.ProductRepository;
import irena.test.campaigns.repos.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Component
public class InitUtil {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public void init() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            categoryRepository.save(new Category(faker.beer().style()));
        }
        for (int i = 0; i < 5; i++) {
            sellerRepository.save(new Seller(faker.name().fullName()));
        }

        List<Category> categories = categoryRepository.findAll();
        List<Seller> sellers = sellerRepository.findAll();
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            Category randomCategory = categories.get(rand.nextInt(categories.size()));
            Seller randomSeller = sellers.get(rand.nextInt(sellers.size()));
            BigDecimal randomPrice = BigDecimal.valueOf(Math.random());
            productRepository.save(
                    new Product(faker.beer().name(),
                                randomPrice,
                                randomCategory,
                                randomSeller));
        }
    }
}
