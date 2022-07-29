package sen.vol.subscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sen.vol.subscription.controller.dto.EmailRequestDTO;
import sen.vol.subscription.service.HTTPResponseDTO;
import sen.vol.subscription.service.SubscriptionService;

@RestController
public class SubscriptionController {

    @Autowired
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeEmail(@RequestBody EmailRequestDTO emailRequestDTO){

        HTTPResponseDTO<String> httpResponseDTO = subscriptionService.saveEmail(emailRequestDTO.getEmail());
        return ResponseEntity.status(httpResponseDTO.getCode()).body(httpResponseDTO.getMessage());
//        boolean successResult = subscriptionService.saveEmail(emailRequestDTO.getEmail());
//
//        if (successResult) {
//            return ResponseEntity.ok("E-mail додано");
//        }
//        return ResponseEntity.status(409).body("E-mail  вже є в базі данних");
    }

    @GetMapping("/sendEmails")
    public String testPath(){
        return "Success";
    }

}
