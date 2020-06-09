package irena.test.campaigns.controllers;

import irena.test.campaigns.utils.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ServiceController {

    @GetMapping("version")
    public BaseResponse<String> version() {
        return new BaseResponse<>("1.0", "TBD");
    }

    @GetMapping("healthy")
    public BaseResponse<Boolean> health() {
        return new BaseResponse<>(true);
    }
}
