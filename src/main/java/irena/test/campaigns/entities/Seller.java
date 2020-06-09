package irena.test.campaigns.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public Seller setName(String name) {
        this.name = name;
        return this;
    }

    public Seller() {
    }

    public Seller(String name) {
        this.name = name;
    }

}
