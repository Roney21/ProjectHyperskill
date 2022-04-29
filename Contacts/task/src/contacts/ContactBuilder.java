package contacts;

import java.util.regex.Pattern;

public class ContactBuilder implements Builder {
    private String firstName;
    private String lastName;
    private String number;

    Pattern p = Pattern.compile
            ("\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\w{1,9}",
                    Pattern.CASE_INSENSITIVE);

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getResult() {
        return new Contact(firstName, lastName, number);
    }
}
