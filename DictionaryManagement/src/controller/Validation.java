
package controller;


import java.util.Scanner;

public class Validation {

    private final static Scanner sc = new Scanner(System.in);

  
    //check user input string
    public String checkInputString() {
        //loop until user input correct
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

}