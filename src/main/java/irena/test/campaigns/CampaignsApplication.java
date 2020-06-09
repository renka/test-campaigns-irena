package irena.test.campaigns;

import irena.test.campaigns.utils.InitUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;

@SpringBootApplication
public class CampaignsApplication {

	private static final Logger log = LoggerFactory.getLogger(CampaignsApplication.class);

	@Autowired
	private InitUtil initUtil;

	public static void main(String[] args) {
		SpringApplication.run(CampaignsApplication.class, args);
	}

	@PostConstruct
	private void init() {
		log.info("generating ...");
		initUtil.init();

	}
}
