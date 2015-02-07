package refactor.generalize;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by jacky on 15/2/7.
 */
public class Command {
    protected static final byte[] header = {(byte)0xde, (byte)0xad};
    protected static final byte[] footer = {(byte)0xbe, (byte)0xef};
    protected static final int SIZE_LENGTH = 1;
    protected static final int CMD_BYTE_LENGTH = 1;

    public void write(OutputStream outputStream) throws Exception {

    }
}
