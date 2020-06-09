package irena.test.campaigns.dto;

import java.math.BigDecimal;

public class CreateCampaignDto {
    private String name;
    private BigDecimal bid;
    private long sellerId;

    public String getName() {
        return name;
    }

    public CreateCampaignDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public CreateCampaignDto setBid(BigDecimal bid) {
        this.bid = bid;
        return this;
    }

    public long getSellerId() {
        return sellerId;
    }

    public CreateCampaignDto setSellerId(long sellerId) {
        this.sellerId = sellerId;
        return this;
    }
}
