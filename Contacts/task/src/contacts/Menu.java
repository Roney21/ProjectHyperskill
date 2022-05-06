package contacts;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        System.out.println("\n[menu] Enter action (add, list, search, count, exit):");
        String choice = sc.nextLine();
        switch (choice) {
            case "add":
                add();
                menu_init();
                break;
            case "search":
                search();
//                menu_init();
                break;
            case "list":
                list();
//                menu_init();
                break;
            /*case "remove":
                remove();
                menu_init();
                break;*/
            /*case "edit":
                edit();
                menu_init();
                break;*/
            case "count":
                count();
                menu_init();
                break;
            /*case "info":
                info();
                menu_init();
                break;*/
            case "exit":
                break;
        }
    }

    void search() {
        System.out.println("Enter search query:");
        String searchLine = sc.nextLine();
        boolean isEnd = false;
        int count = 0;
        List<Contact> searchCont = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.toString().toLowerCase().contains(searchLine.toLowerCase())) {
                count++;
                searchCont.add(contact);
            }
        }
        System.out.println("Found " + count + " results:");
        for (int i = 0; i < searchCont.size(); i++) {
            System.out.println(i + 1 + ". " + searchCont.get(i).getFullname());
        }
        System.out.println("[search] Enter action ([number], back, again):");
        String action = sc.nextLine();
        switch (action) {
            case "back":
                menu_init();
                break;
            case "again":
                search();
                break;
            default:
                info(searchCont.get(Integer.parseInt(action) - 1));
                while (!isEnd) {
                    System.out.println("[record] Enter action (edit, delete, menu):");
                    String toDo = sc.nextLine();
                    switch (toDo) {
                        case "edit":
                            edit(searchCont.get(Integer.parseInt(action) - 1));
                            break;
                        case "menu":
                            isEnd = true;
                            menu_init();
                            break;
                        case "delete":
                            remove(searchCont.get(Integer.parseInt(action) - 1));
                            break;
                        default:
                            break;
                    }

                }
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

    private void remove(Contact contact) {
        if (!contacts.isEmpty()) {
            contacts.remove(contact);
        }
    }

    public void list() {
        boolean isEnd = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i) instanceof Person) {
                System.out.println(i + 1 + ". " + ((Person) contacts.get(i)).getName()
                        + " " + ((Person) contacts.get(i)).getSurName());
            } else if (contacts.get(i) instanceof Organization) {
                System.out.println(i + 1 + ". " + ((Organization) contacts.get(i)).getName());
            }
        }
        System.out.println("\n[list] Enter action ([number], back):");
        String choice = sc.nextLine();
        switch (choice) {
            case "back":
                menu_init();
                break;
            default:
                info(contacts.get(Integer.parseInt(choice) - 1));
                while (!isEnd) {
                    System.out.println("\n[record] Enter action (edit, delete, menu):");
                    String toDo = sc.nextLine();
                    switch (toDo) {
                        case "edit":
                            edit(contacts.get(Integer.parseInt(choice) - 1));
                            break;
                        case "menu":
                            isEnd = true;
                            menu_init();
                            break;
                        case "delete":
                            remove(contacts.get(Integer.parseInt(choice) - 1));
                            break;
                    }
                }


        }
    }

    private void edit(Contact contact) {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit");
            return;
        }

        if (contact instanceof Person) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
            switch (sc.nextLine()) {
                case "name":
                    System.out.println("Enter name:");
                    String newName = sc.nextLine();
                    ((Person) contact).setName(newName);
                    break;
                case "surname":
                    System.out.println("Enter surname:");
                    String newSurname = sc.nextLine();
                    ((Person) contact).setSurName(newSurname);
                    break;
                case "birth":
                    System.out.println("Enter birth date:");
                    String newBirthdate = sc.nextLine();
                    ((Person) contact).setBirthDate(newBirthdate);
                    break;
                case "gender":
                    System.out.println("Enter gender:");
                    String newGender = sc.nextLine();
                    ((Person) contact).setGender(newGender);
                    break;
                case "number":
                    System.out.println("Enter number:");
                    String newNumber = validateNumber(sc.nextLine());
                    ((Person) contact).setNumber(newNumber);
                    break;
            }

        } else if (contact instanceof Organization) {
            System.out.println("Select a field (name, number, address):");
            switch (sc.nextLine()) {
                case "name":
                    System.out.println("Enter name:");
                    String newName = validateNumber(sc.nextLine());
                    ((Organization) contact).setName(newName);
                    break;
                case "number":
                    System.out.println("Enter number:");
                    String newNumber = sc.nextLine();
                    ((Organization) contact).setNumber(newNumber);
                    break;
                case "address":
                    System.out.println("Enter address:");
                    String newAddress = sc.nextLine();
                    ((Organization) contact).setAddress(newAddress);
                    break;
            }

        }
        contact.setTimeEdit(LocalDateTime.now());
    }

    public void info(Contact contact) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        String tCreate = contact.getTimeCreated();
        String tEdit = contact.getTimeEdit();
        LocalDateTime ldt = LocalDateTime.parse(tCreate);
        LocalDateTime ldt2 = LocalDateTime.parse(tEdit);
        System.out.println(contact);
        System.out.println("Time created: " + ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        System.out.println("Time last edit: " + ldt2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

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
