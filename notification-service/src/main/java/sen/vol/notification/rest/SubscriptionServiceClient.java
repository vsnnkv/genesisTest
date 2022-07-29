package sen.vol.notification.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "subscription-service")
public interface SubscriptionServiceClient {

    @RequestMapping(value = "/subscription/", method = RequestMethod.GET)
    List<String> getEmails();

}
