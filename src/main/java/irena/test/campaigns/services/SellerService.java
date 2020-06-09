package irena.test.campaigns.services;

import irena.test.campaigns.entities.Seller;
import irena.test.campaigns.exceptions.SellerNotFoundException;
import irena.test.campaigns.repos.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    private static final Logger log = LoggerFactory.getLogger(SellerService.class);

    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getSellers() {
        log.info("getSellers");
        return sellerRepository.findAll();
    }

    public Seller getSellerBtId(Long id) {
        log.info("getSeller");
        return sellerRepository.findById(id).orElseThrow(SellerNotFoundException::new);
    }
}
