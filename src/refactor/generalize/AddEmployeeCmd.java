package refactor.generalize;/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Aug 1, 2002
 * Time: 9:34:33 AM
 * To change template for new class use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddEmployeeCmd extends Command {
    private List<String> params;
    String name;
    String address;
    String city;
    String state;
    String yearlySalary;

    private static final byte[] commandChar = {0x02};

    private int getSize() {
        return header.length + SIZE_LENGTH + CMD_BYTE_LENGTH + footer.length +
                name.getBytes().length + 1 +
                address.getBytes().length + 1 +
                city.getBytes().length + 1 +
                state.getBytes().length + 1 +
                yearlySalary.getBytes().length + 1;
    }

    public AddEmployeeCmd(String name, String address, String city, String state, int yearlySalary) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.yearlySalary = Integer.toString(yearlySalary);

        this.params = Arrays.asList(name, address, city, state, Integer.toString(yearlySalary));
    }

    public void write(OutputStream outputStream) throws Exception {
        writeHeader(outputStream);
        writeBody(outputStream);
        writeFooter(outputStream);
    }

    private void writeBody(OutputStream outputStream) throws IOException {
        outputStream.write(getSize());
        outputStream.write(commandChar);
        outputStream.write(params.get(0).getBytes());
        outputStream.write(0x00);
        outputStream.write(params.get(1).getBytes());
        outputStream.write(0x00);
        outputStream.write(params.get(2).getBytes());
        outputStream.write(0x00);

        outputStream.write(params.get(3).getBytes());
        outputStream.write(0x00);

        outputStream.write(params.get(4).getBytes());
        outputStream.write(0x00);
    }

    private void writeFooter(OutputStream outputStream) throws IOException {
        outputStream.write(footer);
    }

    private void writeHeader(OutputStream outputStream) throws IOException {
        outputStream.write(header);
    }

}

