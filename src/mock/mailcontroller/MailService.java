package mock.mailcontroller;

/**
 * Created by jacky on 16/1/18.
 */
public class MailService {
    public String send(String content) {
        return "echo " + content;
    }
}
