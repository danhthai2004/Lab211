package controller;

import model.Content;
import java.io.*;
import java.util.Scanner;

public class Programming {

    private Scanner scanner;
    private Library library;
    private ContentController contentController;

    public Programming() {
        this.scanner = new Scanner(System.in);
        this.library = new Library();
        this.contentController = new ContentController();
    }

    public void run() {
        // Read file
        System.out.print("Enter input file name: ");
        String inputFilename = scanner.nextLine();
        Content content = library.readFile(inputFilename);

        // Check if the content is empty
        if (content.isEmpty()) {
            System.out.println("The input file is empty.");
        } else {
            // Display original content
            System.out.println("Original content:");
            System.out.print(content.getText());

            // Format content
            contentController.formatContent(content);

            // Display formatted content
            System.out.println("\nFormatted content:");
            System.out.println(content.getText());

            // Write formatted content to another file
            System.out.print("Enter output file name: ");
            String outputFilename = scanner.nextLine();
            library.writeFile(outputFilename, content.getText(), false);
            System.out.println("Formatted content has been written to " + outputFilename);
        }
    }

    public static void main(String[] args) {
        Programming program = new Programming();
        program.run();
    }
}
