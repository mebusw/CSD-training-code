package refactor.generalize;/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Jul 31, 2002
 * Time: 10:34:45 PM
 * To change template for new class use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;

public class CommandTests extends TestCase{
    public CommandTests(String name) {
        super(name);
    }

    public void testLoginCommandSentCorrectly() throws Exception {
        final byte knownGood [] = { (byte)0xde, (byte)0xad, 20, 0x01,
                                    'b', 'a', 'b', 0x00,
                                    'c', 'a', 'r', 'd', 'i', 'n', 'a', 'l', 's', 0x00,
                                    (byte)0xbe, (byte)0xef };

        LoginCommand cmd = new LoginCommand("bab", "cardinals");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        cmd.write(outputStream);

        for(int i = 0; i < knownGood.length; i++) {
            assertEquals( "comparison failed at byte number " + i, knownGood[i], outputStream.toByteArray()[i]);
        }

    }

    public void testAddEmployeeSentCorrectly() throws Exception {
        final byte knownGood [] = { (byte)0xde, (byte)0xad, 52, 0x02,
                                    'F', 'r', 'e', 'd', ' ', 'B', 'r', 'o', 'o', 'k', 's', 0x00,
                                    '1', '2', '3', ' ', 'M', 'y', ' ', 'H', 'o', 'u', 's', 'e', 0x00,
                                    'S', 'p', 'r', 'i', 'n', 'g', 'f', 'i', 'e', 'l', 'd', 0x00,
                                    'I', 'L', 0x00,
                                    '7', '2', '0', '0', '0', 0x00,
                                    (byte)0xbe, (byte)0xef };

        AddEmployeeCmd cmd = new AddEmployeeCmd("Fred Brooks", "123 My House", "Springfield", "IL", 72000);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        cmd.write(outputStream);

        for( int i = 0; i < knownGood.length; i++ ) {
            assertEquals( "comparison failed at byte number " + i, knownGood[i], outputStream.toByteArray()[i]);
        }

    }

}

