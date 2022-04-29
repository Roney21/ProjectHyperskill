package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    List<Contact> contacts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Pattern p = Pattern.compile
           // "\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\w{1,9}"
            ("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*",
                    Pattern.CASE_INSENSITIVE);
    public void menu_init(){
        System.out.println("Enter action (add, remove, edit, count, list, exit)");
        switch(sc.nextLine()){
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
            case "list":
                list();
                menu_init();
                break;
            case "exit":
                break;
        }
    }

    private String validateNumber(String number) {
        if (number != null && number.matches(p.pattern()))
            return number;
        else{
            System.out.println("Wrong number format!");
            return "[no number]";
    }}

    public void add() {
        String name;
        String surname;
        String number;

        System.out.println("Enter the name of the person:");
        name = sc.nextLine();
        System.out.println("Enter the surname of the person:");
        surname = sc.nextLine();
        System.out.println("Enter the number:");
        number = validateNumber(sc.nextLine());

        Contact newContact = new Contact(name, surname, number);
        contacts.add(newContact);

        System.out.println("A record added.");
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
            System.out.println
                    (i + 1 + ". " + contacts.get(i).getFirstName() + " "
                            + contacts.get(i).getLastName() + ", "
                            + contacts.get(i).getNumber());
        }
    }

    public void edit() {
        list();
        if(contacts.isEmpty())
            System.out.println("No records to edit");
        System.out.println("Select a record:");
        String choice = sc.nextLine();
        if(choice.equals("exit"))
            System.exit(1);
        System.out.println("Select a field (name, surname, number):");
        switch (sc.nextLine()) {
            case "name":
                System.out.println("Enter name:");
                String newName = sc.nextLine();
                contacts.get(Integer.parseInt(choice)-1).setFirstName(newName);
                break;
            case "surname":
                System.out.println("Enter surname:");
                String surName = sc.nextLine();
                contacts.get(Integer.parseInt(choice)-1).setLastName(surName);
                break;
            case "number":
                System.out.println("Enter number:");
                String newNumber = sc.nextLine();
                contacts.get(Integer.parseInt(choice)-1).setNumber(validateNumber(newNumber));
                break;
        }
    }
    public void count(){
        System.out.println("The Phone Book has "+ contacts.size()+" records.");
    }
}
