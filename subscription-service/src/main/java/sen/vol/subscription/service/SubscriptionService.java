package sen.vol.subscription.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class SubscriptionService {

    File emails = new File("email.txt");

    public boolean saveEmail(String email){
         if (lookIfEmailInTheList(email)) {
             return false;

         } else {
             saveEmailToFile(email);
             return true;
         }
    }
    public List<String> getEmails(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(emails));

            List<String> results = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                results.add(line);
                line = reader.readLine();
            }
            return results;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private void saveEmailToFile(String email) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(emails, true)));

            writer.println(email);
            writer.flush();
            writer.close();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    private Boolean lookIfEmailInTheList(String email) {
        try {
            Scanner scanner = new Scanner(emails);

            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if (line.equals(email)){
                    scanner.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }



}
