package irena.test.campaigns.repos;

import irena.test.campaigns.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {
    List<Product> findAllBySellerId(long sellerId);

    List<Product> findAllByCategoryId(long categoryId);

    List<Product> findAllBySellerIdAndCategoryId(Long sellerId, Long categoryId);
}
