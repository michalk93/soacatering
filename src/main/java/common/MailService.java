package common;

import javax.ejb.Remote;

/**
 * Created by mkolbusz on 7/13/16.
 */
@Remote
public interface MailService {
    void sendMail(String to, String subject, String text);
    void sendMail(String[] to, String subject, String text);
}
