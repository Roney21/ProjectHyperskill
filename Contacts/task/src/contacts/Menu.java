package contacts;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {

    PersonFactory pf = new PersonFactory();
    OrganizationFactory of = new OrganizationFactory();




    List<Contact> contacts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Pattern p = Pattern.compile
            // "\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\w{1,9}"
                    ("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*",
                            Pattern.CASE_INSENSITIVE);

    public void menu_init() {
        System.out.println("\nEnter action (add, remove, edit, count, info, exit):");
        switch (sc.nextLine()) {
            case "add":
                add();
                menu_init();
                break;
            case "remove":
                remove();
                menu_init();
                break;
            case "edit":
                edit();
                menu_init();
                break;
            case "count":
                count();
                menu_init();
                break;
            case "info":
                info();
                menu_init();
                break;
            case "exit":
                break;
        }
    }

    private String validateNumber(String number) {
        if (number != null && number.matches(p.pattern()))
            return number;
        else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }

    public void add() {
        String type;

        System.out.println("Enter the type (person, organization):");
        type = sc.nextLine();

        if (type.equals("person")) {
            Person newPerson = pf.createPerson();
            System.out.println("Enter the name of the person:");
            newPerson.setName(sc.nextLine());
            System.out.println("Enter the surname of the person:");
            newPerson.setSurName(sc.nextLine());
            System.out.println("Enter the birth date:");
            newPerson.setBirthDate(sc.nextLine());
            System.out.println("Enter the gender:");
            newPerson.setGender(sc.nextLine());
            System.out.println("Enter the number:");
            newPerson.setNumber(validateNumber(sc.nextLine()));
            contacts.add(newPerson);
            System.out.println("The record added.");
        } else if (type.equals("organization")) {
            Organization newOrg = of.createOrganization();
            System.out.println("Enter the organization name:");
            newOrg.setName(sc.nextLine());
            System.out.println("Enter the address:");
            newOrg.setAddress(sc.nextLine());
            System.out.println("Enter the number:");
            newOrg.setNumber(validateNumber(sc.nextLine()));
            contacts.add(newOrg);
            System.out.println("The record added.");
        }

    }

    public void remove() {
        if (!contacts.isEmpty()) {
            list();
            System.out.println("Select a record:");
            int choice = sc.nextInt() - 1;
            contacts.remove(choice);
            System.out.println("The record removed!");
            list();
        } else System.out.println("No records to remove");
    }

    public void list() {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i) instanceof Person) {
                System.out.println(i + 1 + ". " + ((Person) contacts.get(i)).getName()
                        + " " + ((Person) contacts.get(i)).getSurName());
            } else if (contacts.get(i) instanceof Organization) {
                System.out.println(i + 1 + ". " + ((Organization) contacts.get(i)).getName());
            }
        }
    }

    public void edit() {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit");
            return;
        }
        list();
        System.out.println("Select a record:");
        int choice = Integer.parseInt(sc.nextLine()) - 1;

        if (contacts.get(choice) instanceof Person) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
            switch (sc.nextLine()) {
                case "name":
                    System.out.println("Enter name:");
                    String newName = sc.nextLine();
                    ((Person) contacts.get(choice)).setName(newName);
                    break;
                case "surname":
                    System.out.println("Enter surname:");
                    String newSurname = sc.nextLine();
                    ((Person) contacts.get(choice)).setSurName(newSurname);
                    break;
                case "birth":
                    System.out.println("Enter birth date:");
                    String newBirthdate = sc.nextLine();
                    ((Person) contacts.get(choice)).setBirthDate(newBirthdate);
                    break;
                case "gender":
                    System.out.println("Enter gender:");
                    String newGender = sc.nextLine();
                    ((Person) contacts.get(choice)).setGender(newGender);
                    break;
                case "number":
                    System.out.println("Enter number:");
                    String newNumber = validateNumber(sc.nextLine());
                    ((Person) contacts.get(choice)).setNumber(newNumber);
                    break;
            }

        } else if (contacts.get(choice) instanceof Organization) {
            System.out.println("Select a field (name, number, address):");
            switch (sc.nextLine()) {
                case "name":
                    System.out.println("Enter name:");
                    String newName = validateNumber(sc.nextLine());
                    ((Organization) contacts.get(choice)).setName(newName);
                    break;
                case "number":
                    System.out.println("Enter number:");
                    String newNumber = sc.nextLine();
                    ((Organization) contacts.get(choice)).setNumber(newNumber);
                    break;
                case "address":
                    System.out.println("Enter address:");
                    String newAddress = sc.nextLine();
                    ((Organization) contacts.get(choice)).setAddress(newAddress);
                    break;
            }

        }
        contacts.get(choice).setTimeEdit(LocalDateTime.now());
        System.out.println("The record updated!");
    }

    public void info() {
        list();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        System.out.println("Select a record:");
        int choice = Integer.parseInt(sc.nextLine()) - 1;
        String tCreate = contacts.get(choice).getTimeCreated();
        String tEdit = contacts.get(choice).getTimeEdit();
        LocalDateTime ldt = LocalDateTime.parse(tCreate);
        LocalDateTime ldt2 = LocalDateTime.parse(tEdit);
        System.out.println(contacts.get(choice).toString());
        System.out.println("Time created: "+ ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        System.out.println("Time last edit: "+ldt2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

    }
/*
    public void edit() {
        list();
        if (contacts.isEmpty())
            System.out.println("No records to edit");
        System.out.println("Select a record:");
        String choice = sc.nextLine();
        if (choice.equals("exit"))
            System.exit(1);
        System.out.println("Select a field (name, surname, number):");
        switch (sc.nextLine()) {
            case "name":
                System.out.println("Enter name:");
                String newName = sc.nextLine();
                contacts.get(Integer.parseInt(choice) - 1).setFirstName(newName);
                break;
            case "surname":
                System.out.println("Enter surname:");
                String surName = sc.nextLine();
                contacts.get(Integer.parseInt(choice) - 1).setLastName(surName);
                break;
            case "number":
                System.out.println("Enter number:");
                String newNumber = sc.nextLine();
                contacts.get(Integer.parseInt(choice) - 1).setNumber(validateNumber(newNumber));
                break;
        }
    }*/

    public void count() {
        if (contacts.size() == 0)
            System.out.println("0 records");
        else
            System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

}
