package sen.vol.notification.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sen.vol.notification.rest.SubscriptionServiceClient;

import java.util.List;

@Service
public class NotificationService {

    private static final String TOPIC_EXCHANGE_RATE = "js.rate.notify.exchange";
    private static final String ROUTING_KEY_RATE = "js.key.rate";

    private final RabbitTemplate rabbitTemplate;
    private final SubscriptionServiceClient subscriptionServiceClient;

    @Autowired
    public NotificationService(RabbitTemplate rabbitTemplate, SubscriptionServiceClient subscriptionServiceClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.subscriptionServiceClient = subscriptionServiceClient;
    }

    public void sendEmail(){
        List<String> emails = subscriptionServiceClient.getEmails();
        ObjectMapper objectMapper = new ObjectMapper();
        for (String email:emails){
//            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_RATE, ROUTING_KEY_RATE, objectMapper.writeValueAsString(email));

        }
    }
}
