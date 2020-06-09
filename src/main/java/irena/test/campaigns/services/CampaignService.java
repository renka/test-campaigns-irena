package irena.test.campaigns.services;

import irena.test.campaigns.dto.CampaignForProductsDto;
import irena.test.campaigns.dto.CreateCampaignDto;
import irena.test.campaigns.dto.ServeAdDto;
import irena.test.campaigns.entities.*;
import irena.test.campaigns.exceptions.CampaignNotFoundException;
import irena.test.campaigns.exceptions.ProductNotFoundException;
import irena.test.campaigns.exceptions.SellerNotFoundException;
import irena.test.campaigns.repos.CampaignProductRepository;
import irena.test.campaigns.repos.CampaignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignProductRepository campaignProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    public Campaign createCampaign(CreateCampaignDto dto) throws SellerNotFoundException {
        log.info("create Campaign");
        Seller seller = sellerService.getSellerBtId(dto.getSellerId());
        Campaign campaign = new Campaign(dto).setSeller(seller);
        return campaignRepository.save(campaign);
    }

    public Campaign getCampaign(Long id) {
        return campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(id));
    }

    public void assignCampaign(CampaignForProductsDto campaignForProductsDto) {
        Campaign campaign = getCampaign(campaignForProductsDto.getCampaignId());
        for (long productId : campaignForProductsDto.getProducts()) {
            Product product = productService.getProductById(productId);
            if (campaign.getSeller().getId() != product.getSeller().getId()) {
                throw new RuntimeException("This product belongs to another seller, you cannot apply current campaign on it");
            } else {
                campaignProductRepository.save(new CampaignProduct(campaign.getId(), product.getId()));
            }
        }
    }

    public void updateStatus(Long id, CampaignStatus status) {
        campaignRepository.save(getCampaign(id).setStatus(status));
    }

    public List<Campaign> getCampaigns(String sellerId) {
        log.info("getCampaigns");
        if (sellerId == null) {
            return campaignRepository.findAll();
        } else {
            return campaignRepository.findAllBySellerId(Long.parseLong(sellerId));
        }
    }

    public Product serveAd(ServeAdDto serveAdDto) {
        long categoryId;
        try {
            categoryId = Long.parseLong(serveAdDto.getCategory());
        } catch (NumberFormatException e) {
            categoryId = productService.getCategoryByName(serveAdDto.getCategory()).getId();
        }
        List<Product> products = productService.getProducts(categoryId);

        List<CampaignProduct> campaignProducts = campaignProductRepository.findByProductIn(products.stream().map(BaseEntity::getId).collect(Collectors.toList()));
        List<CampaignProduct> filteredCampaigns = filterOnlyActive(campaignProducts);
        if (filteredCampaigns.isEmpty()) {
            filteredCampaigns = filterOnlyActive(campaignProductRepository.findAll());
             // If there are no promoted products for the matching category simply return a promoted product with the highest bid
        }
         if (filteredCampaigns.isEmpty()) {
            try {
                return productService.getProducts().get(0);
            } catch (Exception e) {
                throw new ProductNotFoundException();
            }
        }

        List<CampaignProduct> sortedCampaigns = filteredCampaigns.stream().sorted(Comparator.comparing(a -> getCampaign(a.getCampaign()).getBid())).collect(Collectors.toList());
        CampaignProduct maxCampaign = sortedCampaigns.get(sortedCampaigns.size() - 1);

        return productService.getProductById(maxCampaign.getProduct());
    }

    private List<CampaignProduct> filterOnlyActive(List<CampaignProduct> campaignProducts) {
        return campaignProducts.stream().filter(a -> CampaignStatus.ACTIVE.equals(getCampaign(a.getCampaign()).getStatus())).collect(Collectors.toList());
    }
}
