package controller;

import java.util.HashMap;
import java.util.Scanner;
import view.Menu;

/**
 *
 * @author phuct
 */
public class Programming extends Menu {

    private HashMap<String, String> hm;
    Scanner sc = new Scanner(System.in);

    public Programming(String td, String[] mc) {
        super(td, mc);
        hm = new HashMap<>();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                addWord();
                break;
            case 2:
                deleteWord();
                break;
            case 3:
                translateWord();
                break;
            case 4:
                System.exit(0);
        }
    }

    public void addWord() {
        System.out.println("------------- Add -------------");
        System.out.print("Enter English: ");
        String en = sc.nextLine();
        if (hm.containsKey(en)) {
            System.out.println("Word already exists in the dictionary!");
            return;
        }
        System.out.print("Enter Vietnamese: ");
        String vn = sc.nextLine();
        hm.put(en, vn);
        System.out.println("Added success!!");
    }

    public void deleteWord() {
        System.out.println("------------ Delete ----------------");
        System.out.print("Enter English: ");
        String en = sc.nextLine();
        if (!hm.containsKey(en)) {
            System.out.println("Word not found in the dictionary!");
            return;
        }
        hm.remove(en);
        System.out.println("Delete success!!");
    }

    public void translateWord() {
        System.out.println("------------- Translate ------------");
        System.out.print("Enter English: ");
        String en = sc.nextLine();
        if (!hm.containsKey(en)) {
            System.out.println("Word not found in the dictionary!");
            return;
        }
        System.out.println("Vietnamese: " + hm.get(en));
    }
}
