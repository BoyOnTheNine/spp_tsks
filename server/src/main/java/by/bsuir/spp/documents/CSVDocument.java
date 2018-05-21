package by.bsuir.spp.documents;

import by.bsuir.spp.entities.User;
import by.bsuir.spp.entities.UserOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class CSVDocument {

    private static final char DEFAULT_SEPARATOR = ';';
    private static final char END_OF_LINE = '\n';

    private static final String[] orderInfoColumns = {"Order name", "Date", "Price", "Description"};
    private static final String[] userInfoColumns = {"First name", "Last name", "Email", "Phone number"};

    public static ByteArrayInputStream buildCSVDocument(UserOrder order) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder();

        for (String string : orderInfoColumns) {
            sb.append(string);
            sb.append(DEFAULT_SEPARATOR);
        }
        sb.append(END_OF_LINE);

        sb.append(order.getOffer().getName());
        sb.append("     ");
        sb.append(DEFAULT_SEPARATOR);
        sb.append(order.getOffer().getDate().toString());
        sb.append("     ");
        sb.append(DEFAULT_SEPARATOR);
        sb.append(order.getOffer().getPrice());
        sb.append(DEFAULT_SEPARATOR);
        sb.append(order.getOffer().getDescription());
        sb.append(END_OF_LINE);

        sb.append("Customer info");
        sb.append(END_OF_LINE);

        for (String string : userInfoColumns) {
            sb.append(string);
            sb.append(DEFAULT_SEPARATOR);
        }
        sb.append(END_OF_LINE);

        sb.append(order.getOffer().getCustomer().getFirstName());
        sb.append(DEFAULT_SEPARATOR);
        sb.append(order.getOffer().getCustomer().getLastName());
        sb.append(DEFAULT_SEPARATOR);
        sb.append(order.getOffer().getCustomer().getEmail());
        sb.append(DEFAULT_SEPARATOR);
        sb.append(order.getOffer().getCustomer().getPhoneNumber());
        sb.append(END_OF_LINE);

        sb.append("Workers info");
        sb.append(END_OF_LINE);

        for (String string : userInfoColumns) {
            sb.append(string);
            sb.append(DEFAULT_SEPARATOR);
        }
        sb.append(END_OF_LINE);

        for (User user : order.getWorkers()) {
            sb.append(user.getFirstName());
            sb.append(DEFAULT_SEPARATOR);
            sb.append(user.getLastName());
            sb.append(DEFAULT_SEPARATOR);
            sb.append(user.getEmail());
            sb.append(DEFAULT_SEPARATOR);
            sb.append(user.getPhoneNumber());
            sb.append(END_OF_LINE);
        }

        out.write(sb.toString().getBytes());

        return new ByteArrayInputStream(out.toByteArray());
    }
}
