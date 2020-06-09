package irena.test.campaigns.exceptions;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException(Long id) {
        super("Campaign with id " + id + " not found");
    }
}
