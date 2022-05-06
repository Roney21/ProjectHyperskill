package contacts;

import java.time.LocalDateTime;

public class Person implements Contact {
    private String name;
    private String surName;
    private String birthDate = "[no data]";
    private String gender = "[no data]";
    private String number;

    LocalDateTime createdTime = LocalDateTime.now();
    LocalDateTime editedTime = createdTime;

    @Override
    public String getFullname() {
        return name+" "+surName;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate != null && birthDate.length() != 0)
            this.birthDate = birthDate;
        else System.out.println("Bad birth date!");
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equals("M") || gender.equals("F"))
            this.gender = gender;
        else System.out.println("Bad gender!");
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
                "Name: " + name + "\n"+
                        "Surname: " + surName + "\n" +
                        "Birth date: " + birthDate + "\n" +
                        "Gender: " + gender + "\n" +
                        "Number: " + number;
    }
}
