package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.Information;
import view.Menu;

public class ContactManagement extends Menu {
    ArrayList<Information> lc = new ArrayList<>();
     private static String[] mc = {"Add a Contact", "Display all Contact", "Delete a Contact", "Exit"};

    private Scanner sc = new Scanner(System.in);

    public ContactManagement() {
        super("========= Contact Program =========", mc);
    }

    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1:
                createContact(lc);
                break;
            case 2:
                printAllContact(lc);
                break;
            case 3:
                deleteContactd(lc);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    public static void createContact(ArrayList<Information> lc) {
        System.out.println("---------Add a Contact---------");
        int contactId = lc.size() + 1;
        System.out.print("Enter name: ");
        String name = Validation.checkInputString();
        System.out.print("Enter group: ");
        String group = Validation.checkInputString();
        System.out.print("Enter address: ");
        String address = Validation.checkInputString();
        System.out.print("Enter phone: ");
        String phone = Validation.checkInputPhone();
        lc.add(new Information(contactId, name, group, address,
                phone, name.split(" ")[0], name.substring(name.lastIndexOf(" ") + 1)));
        System.err.println("Add successful.");
    }

    //allow user display all contact
    public static void printAllContact(ArrayList<Information> lc) {
        System.out.println("------------------Display all Contact-------------------");
        System.out.printf("%-5s%-25s%-20s%-20s%-20s%-20s%-20s\n", "Id", "Name",
                "First name", "Last name", "Group", "Address", "Phone");
        //print infomation of contact from first to last list contact
        for (Information info : lc) {
            System.out.printf("%-5d%-25s%-20s%-20s%-20s%-20s%-20s\n",
                    info.getContactID(), info.getFullName(),
                    info.getFirstName(), info.getLastName(),
                    info.getGroup(), info.getAddress(), info.getPhone());
        }
    }

    //allow user delete contact
    public static void deleteContactd(ArrayList<Information> lc) {
        System.out.println("---------Delete a Contact---------");
        System.out.print("Enter ID: ");
        int idDelete = Validation.checkInputInt();
        Information contactDelete = getContactById(lc, idDelete);
        if (contactDelete == null) {
            System.err.println("Not found contact.");
            return;
        } else {
            lc.remove(contactDelete);
        }
        System.err.println("Delete successful.");
    }

    //get contact by id
    public static Information getContactById(ArrayList<Information> lc, int idDelete) {
        for (Information info : lc) {
            if (info.getContactID() == idDelete) {
                return info;
            }
        }
        return null;
    }
}
