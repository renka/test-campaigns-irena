package irena.test.campaigns.dto;

import java.util.List;

public class CampaignForProductsDto {
    private long campaignId;
    private List<Long> products;

    public long getCampaignId() {
        return campaignId;
    }

    public CampaignForProductsDto setCampaignId(long campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public List<Long> getProducts() {
        return products;
    }

    public CampaignForProductsDto setProducts(List<Long> products) {
        this.products = products;
        return this;
    }
}
