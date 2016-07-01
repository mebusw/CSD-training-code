package mock.mailcontroller;

/**
 * Created by jacky on 16/1/18.
 */
public class MailController {
    private MailService mailService;

    public String send(String content) throws Exception {
        String result = mailService.send(content);
        return result;
    }

    public static String only(String msg) {
        return "Only " + msg;
    }

    public String displayTwice(String text) {
        MailUtils.show(text);
        MailUtils.show(text);
        return "shown";
    }

    public void display(Box box){

    }
}

class Box {
    public String name;

    public Box(String name) {
        this.name=name;
    }

}
