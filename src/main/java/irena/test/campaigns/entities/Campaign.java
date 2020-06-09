package irena.test.campaigns.entities;

import irena.test.campaigns.dto.CreateCampaignDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity{

    private String name;

    private BigDecimal bid;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Campaign(CreateCampaignDto dto) {
        this.bid = dto.getBid();
        this.name = dto.getName();
        this.status = CampaignStatus.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public Campaign setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public Campaign setBid(BigDecimal bid) {
        this.bid = bid;
        return this;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public Campaign setStatus(CampaignStatus status) {
        this.status = status;
        return this;
    }

    public Seller getSeller() {
        return seller;
    }

    public Campaign setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public Campaign() {
    }
}
