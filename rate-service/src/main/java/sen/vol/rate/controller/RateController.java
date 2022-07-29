package sen.vol.rate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sen.vol.rate.service.RateResponceDTO;
import sen.vol.rate.service.RateService;

@RestController
public class RateController {

    @Autowired
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/")
    public Long getRate(){
        return rateService.getRateBtsToUah().getBitcoin().getUah();
    }
}
