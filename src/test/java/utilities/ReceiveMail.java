package utilities;

import javax.mail.*;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.IOException;
import java.util.Properties;

public class ReceiveMail {

    public static void receiveAllMessages(String host, String user, String password) {
        try {
            //1) get the session object
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //2) create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            store.connect(host, user, password);

            //3) create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

//            4) retrieve the messages from the folder in an array and print it
//            Message[] allMessages = emailFolder.getMessages();
//            for (int i = 0; i < allMessages.length; i++) {
//                Message message = allMessages[i];
//                System.out.println("---------------------------------");
//                System.out.println("Email Number " + (i + 1));
//                System.out.println("Subject: " + message.getSubject());
//                System.out.println("From: " + message.getFrom()[0]);
//                System.out.println("Text: " + message.getContent().toString());
//            }

            // creates a search criterion
            SearchTerm searchTerm = new AndTerm(new SubjectTerm[]{new SubjectTerm("NPB")});
            Message[] messages = emailFolder.search(searchTerm);
            for (Message message : messages) {
                if (message.getSubject().contains("NPB")) {
                    System.out.println(("Found the Email with Subject : " + "NPB"));
                }
            }

            //5) close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
    }


    public static void receiveUnreadMessages(String host, String user, String password) {
        try {
            //1) get the session object
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //2) create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            store.connect(host, user, password);

            //3) create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

//            4) retrieve the unread messages from the folder in an array and print it


            Message[] unreadMessages = emailFolder.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (int i = 0; i < unreadMessages.length; i++) {
                Message message = unreadMessages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }


            //5) close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        String host = "pop.gmail.com";//change accordingly
        String username = "sylwia.kostka@itaxi.pl";
        String password = "Madrid12";//change accordingly

        receiveAllMessages(host, username, password);

    }
}



