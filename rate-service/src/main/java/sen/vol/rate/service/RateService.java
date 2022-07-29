package sen.vol.rate.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RateService {

    private final RestTemplate restTemplate;


    public RateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RateResponceDTO getRateBtsToUah(){
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=uah";
        return this.restTemplate.getForObject(url, RateResponceDTO.class);
    }
}
