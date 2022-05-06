package contacts;

import java.time.LocalDateTime;

public class Organization implements Contact {
    private String name;
    private String address;
    private String number;

    LocalDateTime createdTime = LocalDateTime.now();
    LocalDateTime editedTime = createdTime;

    @Override
    public String getFullname() {
        return name;
    }

    @Override
    public String getTimeCreated() {
        return createdTime.toString();
    }

    @Override
    public void setTimeEdit(LocalDateTime editedTime) {
        this.editedTime = editedTime;
    }

    @Override
    public String getTimeEdit() {
        return editedTime.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return
                "Organization name: " + name + '\n' +
                        "Address: " + address + '\n' +
                        "Number: " + number;
    }
}
