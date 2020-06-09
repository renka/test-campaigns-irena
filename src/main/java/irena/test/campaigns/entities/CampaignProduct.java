package irena.test.campaigns.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CampaignProductId.class)
@Table(name = "campaigns_products")
public class CampaignProduct {
    @Id
    @Column(name = "campaign_id")
    private long campaign;

    @Id
    @Column(name = "product_id")
    private long product;

    public long getCampaign() {
        return campaign;
    }

    public CampaignProduct setCampaign(long campaign) {
        this.campaign = campaign;
        return this;
    }

    public long getProduct() {
        return product;
    }

    public CampaignProduct setProduct(long product) {
        this.product = product;
        return this;
    }

    public CampaignProduct(long campaign, long product) {
        this.campaign = campaign;
        this.product = product;
     }

    public CampaignProduct() {
    }
}


class CampaignProductId implements Serializable {
    private long campaign;

    private long product;

    public long getCampaign() {
        return campaign;
    }

    public CampaignProductId setCampaign(long campaign) {
        this.campaign = campaign;
        return this;
    }

    public long getProduct() {
        return product;
    }

    public CampaignProductId setProduct(long product) {
        this.product = product;
        return this;
    }

    public CampaignProductId(long campaign, long product) {
        this.campaign = campaign;
        this.product = product;
    }

    public CampaignProductId() {
    }
}