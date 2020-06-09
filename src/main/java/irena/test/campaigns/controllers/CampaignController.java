package irena.test.campaigns.controllers;

import irena.test.campaigns.dto.CampaignForProductsDto;
import irena.test.campaigns.dto.CreateCampaignDto;
import irena.test.campaigns.dto.ServeAdDto;
import irena.test.campaigns.entities.Campaign;
import irena.test.campaigns.entities.CampaignStatus;
import irena.test.campaigns.entities.Product;
import irena.test.campaigns.exceptions.SellerNotFoundException;
import irena.test.campaigns.services.CampaignService;
import irena.test.campaigns.utils.BaseResponse;
import irena.test.campaigns.utils.ErrorHandlingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("campaign")
public class CampaignController {

    private static final Logger log = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;

    @PostMapping("setCampaignForProducts")
    public BaseResponse setCampaignForProducts(@RequestBody CampaignForProductsDto campaignForProductsDto) {
        try {
            campaignService.assignCampaign(campaignForProductsDto);
            return new BaseResponse<>(null, "Campaign was assigned to products");
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, e.getMessage(), log);
        }
    }

    @GetMapping("delete/{id}")
    public BaseResponse deleteCampaign(@PathVariable Long id) {
        campaignService.updateStatus(id, CampaignStatus.DELETED);
        return new BaseResponse<>(null, "Campaign was deleted");
    }

    /// Main task starts here

    @PostMapping("create")
    public BaseResponse<Campaign> createCampaign(@RequestBody CreateCampaignDto campaignDto) {
        try {
            return new BaseResponse<>(campaignService.createCampaign(campaignDto), "Successfully created");
        } catch (SellerNotFoundException e) {
            return ErrorHandlingUtil.getErrorResponse(e, "Campaign was not created", log);
        }
    }

    @PostMapping("serveAd")
    public BaseResponse<Product> serveAd(@RequestBody ServeAdDto serveAdDto) {
        try {
            Product product = campaignService.serveAd(serveAdDto);
            return new BaseResponse<>(product, "Promoted product");
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, e.getMessage(), log);
        }
    }
}
