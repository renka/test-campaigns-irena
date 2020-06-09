package irena.test.campaigns.repos;

import irena.test.campaigns.entities.Campaign;
import irena.test.campaigns.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllBySellerId(long parseLong);
}
