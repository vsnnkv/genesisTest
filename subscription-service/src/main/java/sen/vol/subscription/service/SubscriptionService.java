package sen.vol.subscription.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class SubscriptionService {


    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";

    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final RabbitTemplate rabbitTemplate;


    File emails = new File("email.txt");

    @Autowired
    public SubscriptionService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public HTTPResponseDTO<String> saveEmail(String email) {
        try {
            if (lookIfEmailInTheList(email)) {
                return new HTTPResponseDTO<>("E-mail  вже є в базі данних", 409);
            }
            saveEmailToFile(email);
            return new HTTPResponseDTO<>("E-mail додано");
        } catch (Exception exception) {
            return new HTTPResponseDTO<>("Помилка сервера", 500);
        }
    }

    public List<String> getEmails() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(emails));

        List<String> results = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            results.add(line);
            line = reader.readLine();
        }
        return results;
    }

    private void saveEmailToFile(String email) throws IOException {

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(emails, true)));

        writer.println(email);
        writer.flush();
        writer.close();

    }

    private Boolean lookIfEmailInTheList(String email) throws FileNotFoundException {
        Scanner scanner = new Scanner(emails);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals(email)) {
                scanner.close();
                return true;
            }
        }

        return false;
    }

//    private RateResponseDTO createResponse(Integer uah, String email) {
//        DepositResponseDTO depositResponseDTO = new DepositResponseDTO(amount, accountResponseDTO.getEmail());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT,
//                    objectMapper.writeValueAsString(depositResponseDTO));
//        } catch (JsonProcessingException e) {
//            throw new DepositServiceException("Can`t send message to RabbitMQ");
//        }
//        return depositResponseDTO;
//    }

}
