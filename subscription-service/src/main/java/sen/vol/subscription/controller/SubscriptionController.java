package sen.vol.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sen.vol.subscription.controller.dto.EmailRequestDTO;
import sen.vol.subscription.service.SubscriptionService;

@RestController
public class SubscriptionController {

    @Autowired
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public String subscribeEmail(@RequestBody EmailRequestDTO emailRequestDTO){

        return subscriptionService.saveEmail(emailRequestDTO.getEmail());
    }

    @GetMapping("/sendEmails")
    public String testPath(){
        return "Success";
    }

}
