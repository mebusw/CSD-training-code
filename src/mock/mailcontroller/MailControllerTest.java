package mock.mailcontroller;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class MailControllerTest {

    @Tested
    MailController mailController;

    @Injectable
    private MailService mailService;


    @Test
    public void expection_of_private_method() throws Exception {
        new Expectations() {
            {
                mailService.send("Hello");
                result = "Echo Hello";
            }
        };

        String result = mailController.send("Hello");

        assertEquals("Echo Hello", result);
    }

    @Test
    public void mock_a_static_method() {
        assertEquals("Only abc", MailController.only("abc"));

        new MockUp<MailController>() {
            @Mock
            String only(String msg) {
                return "Besides " + msg;
            }
        };

        assertEquals("Besides abc", MailController.only("abc"));
    }

    //    @Mocked(stubOutClassInitialization = true) final MailUtils unused = null;
    @Mocked
    MailUtils unused;

    @Test
    public void verify_a_static_method() {

        mailController.displayTwice("xyz");

        new Verifications() {{
            MailUtils.show(anyString);
            times = 2;
        }};

        mailController.displayTwice("abc");

        new Verifications() {{
//            MailUtils.show((String)any);
            MailUtils.show("abc");
            times = 2;
        }};
    }

    @Test
    public void verify_a_any_object() throws Exception {

        mailController.display(null);

        new Verifications() {{
            mailController.display((Box) any);
        }};

        mailController.send("Hello");
        new Verifications() {{
            String b1;
            mailController.send(b1 = withCapture());
            assertEquals("Hello", b1);
        }};
    }

    @Ignore
    @Test
    public void verify_a_any_object_with_capture(@Mocked Box mockedBox) throws Exception {

        Box box1 = new Box("zoo1");
        mailController.display(new Box("zoo"));
        mailController.display(box1);

        new Verifications() {{
            List<Box> boxesInstantiated = withCapture(new Box(anyString));
            List<Box> boxesCreated = new ArrayList<>();

            mailController.display(withCapture(boxesCreated));

            assertEquals(boxesInstantiated, boxesCreated);
        }};

    }


}