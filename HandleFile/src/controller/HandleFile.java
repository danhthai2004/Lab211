package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import model.Person;
import view.Menu;

public class HandleFile extends Menu {

    private static String[] mc = {"Find person info", "Copy text to new File", "Exit."};

    public Scanner sc = new Scanner(System.in);

    public HandleFile() {
        super("========= File Processing =========", mc);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                findPersonInfo();
                break;
            case 2:
                coppyNewFile();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
    //allow user find person info

    public void findPersonInfo() {
        ArrayList<Person> lp = new ArrayList<>();
        if (lp == null) {
            return;
        }
        String pathFile = Validation.checkInputPathFile();
        lp = getListPerson(pathFile);
        double money = Validation.checkInputMoney();
        printListPerson(lp, money);
    }

    //allow user copy text to new file
    public void coppyNewFile() {
        String pathFileInput = Validation.checkInputPathFile();
        String pathFileOutput = Validation.checkInputPathFile();
        String content = getNewContent(pathFileInput);
        System.out.println(content);
        writeNewContent(pathFileOutput, content);
    }

    //get list person by path file
    public ArrayList<Person> getListPerson(String pathFile) {
        ArrayList<Person> lp = new ArrayList<>();
        boolean fileExists = false;

        while (!fileExists) {
            File file = new File(pathFile);

            if (!file.exists() || !file.isFile()) {
                System.err.println("File path is not valid or does not exist.");
                pathFile = Validation.checkInputPathFile();
            } else {
                try (FileReader fileReader = new FileReader(file); BufferedReader bufferReader = new BufferedReader(fileReader)) {

                    String line;
                    while ((line = bufferReader.readLine()) != null) {
                        String[] infoPerson = line.split(";");
                        lp.add(new Person(infoPerson[0], infoPerson[1], getSalary(infoPerson[2])));
                    }
                    fileExists = true; // Set flag to exit the loop
                } catch (IOException e) {
                    System.err.println("Can't read file.");
                    pathFile = Validation.checkInputPathFile();
                }
            }
        }

        return lp;
    }

    //get salary 
    public static double getSalary(String salary) {
        double salaryResult = 0;
        try {
            salaryResult = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salaryResult = 0;
        } finally {
            return salaryResult;
        }
    }

    //display list person
    public void printListPerson(ArrayList<Person> lp, double money) {
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Address", "Money");
        for (Person person : lp) {
            if (person.getMoney() >= money) {
                System.out.printf("%-20s%-20s%-20.1f\n", person.getName(),
                        person.getAddress(), person.getMoney());
            }
        }
        Collections.sort(lp);
        System.out.println("Max: " + lp.get(0).getName());
        System.out.println("Min: " + lp.get(lp.size() - 1).getName());
    }

    //get new content
    public String getNewContent(String pathFileInput) {
        HashSet<String> newContent = new HashSet<>();
        File file = new File(pathFileInput);
        try {
            Scanner input = new Scanner(file);
            int count = 0;
            while (input.hasNext()) {
                String word = input.next();
                newContent.add(word + " ");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Can’t read file");
        }
        String content = "";
        for (String line : newContent) {
            content += line;
        }
        return content;
    }

    //write new content to file
    public void writeNewContent(String pathFileOutput, String content) {
        FileWriter fileWriter = null;
        File file = new File(pathFileOutput);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            bufferWriter.close();
            System.err.println("Write successful");
        } catch (IOException ex) {
            System.err.println("Can’t write file");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
