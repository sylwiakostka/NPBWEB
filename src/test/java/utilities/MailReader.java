package utilities;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import java.util.Properties;

public class MailReader {

    public static final String EMAIL = System.getenv("EMAIL_USERNAME");
    public static final String PASSWORD = System.getenv("EMAIL_PASSWORD");

    public static void readMail() throws Exception {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", EMAIL, PASSWORD);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);


        // search for all "unseen" messages
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
        Message messages[] = folder.search(unseenFlagTerm);

        System.out.println("Number of unread mails = " + messages.length);

        // creates a search criterion
        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().contains("Kod weryfikacyjny")) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };

        // performs search through the folder
        Message[] foundMessages = folder.search(searchCondition);

        for (int i = 0; i < foundMessages.length; i++) {
            Message message = foundMessages[i];
            String subject = message.getSubject();
            System.out.println("Found message #" + i + ": " + subject);
        }

        // disconnect
        folder.close(false);
        store.close();

    }




    public static void main(String[] args) throws Exception {
        readMail();
    }
}
