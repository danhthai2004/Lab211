package controller;

import model.Content;
import java.io.*;

public class Library {

    public Content readFile(String filePath) {
        Content content = new Content();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            content.setText(stringBuilder.toString());
        } catch (FileNotFoundException ex) {
            System.err.println("Error: File not found - " + filePath);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Error reading file - " + filePath);
            ex.printStackTrace();
        }
        return content;
    }

    public void writeFile(String filePath, String content, boolean append) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, append)))) {
            pw.print(content);
        } catch (IOException ex) {
            System.err.println("Error writing to file - " + filePath);
            ex.printStackTrace();
        }
    }

    public static boolean isLineEmpty(String line) {
        return line.trim().isEmpty();
    }

    public static int countLines(String filePath) {
        int countLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!isLineEmpty(line)) {
                    countLines++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error: File not found - " + filePath);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Error reading file - " + filePath);
            ex.printStackTrace();
        }
        return countLines;
    }
}
