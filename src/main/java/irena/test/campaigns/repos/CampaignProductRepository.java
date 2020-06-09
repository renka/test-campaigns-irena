package irena.test.campaigns.repos;

import irena.test.campaigns.entities.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignProductRepository extends JpaRepository<CampaignProduct, Long> {

    List<CampaignProduct> findByProductIn(List<Long> products);

}
