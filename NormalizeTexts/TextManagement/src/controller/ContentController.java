package controller;

import model.Content;

public class ContentController {

    public String formatSpacesBetweenWords(String line, String character) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] words = line.split("\\s*\\" + character + "\\s*");
        for (String word : words) {
            stringBuilder.append(word).append(" ").append(character).append(" ");
        }
        return stringBuilder.toString().trim().substring(0, stringBuilder.length() - 3);
    }

    public String formatSpaces(String line) {
        line = line.toLowerCase().replaceAll("\\s+", " ");
        line = formatSpacesBetweenWords(line, ".");
        line = formatSpacesBetweenWords(line, ",");
        line = formatSpacesBetweenWords(line, ":");
        line = formatSpacesBetweenWords(line, "\"");
        return line.trim();
    }

    public String removeSpacesAroundSpecialCharacters(String line) {
        StringBuilder stringBuilder = new StringBuilder(line);
        for (int i = 0; i < stringBuilder.length() - 1; i++) {
            if (stringBuilder.charAt(i) == ' '
                    && (stringBuilder.charAt(i + 1) == '.' || stringBuilder.charAt(i + 1) == ','
                            || stringBuilder.charAt(i + 1) == ':')) {
                stringBuilder.deleteCharAt(i);
            }
        }
        return stringBuilder.toString().trim();
    }

    public String capitalizeAfterDot(String line) {
        StringBuilder stringBuilder = new StringBuilder(line);
        int lengthLine = stringBuilder.length();
        for (int i = 0; i < lengthLine - 2; i++) {
            if (stringBuilder.charAt(i) == '.') {
                char afterDot = stringBuilder.charAt(i + 2);
                stringBuilder.setCharAt(i + 2, Character.toUpperCase(afterDot));
            }
        }
        return stringBuilder.toString().trim();
    }

    public String removeSpacesAroundQuotes(String line) {
        StringBuilder stringBuilder = new StringBuilder(line);
        int countQuotes = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '"' && countQuotes % 2 == 0) {
                stringBuilder.deleteCharAt(i + 1);
                countQuotes++;
            } else if (stringBuilder.charAt(i) == '"' && countQuotes % 2 == 1 && i != 0) {
                stringBuilder.deleteCharAt(i - 1);
                countQuotes++;
            }
        }
        return stringBuilder.toString().trim();
    }

    public String capitalizeFirstLetter(String line) {
    if (!line.isEmpty() && Character.isLetter(line.charAt(0))) {
        line = Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
    return line.trim();
}


    public static String addDotAtEnd(String line) {
        return line.endsWith(".") ? line : line + ".";
    }

    public void formatContent(Content content) {
        content.setText(formatSpaces(content.getText()));
        content.setText(removeSpacesAroundSpecialCharacters(content.getText()));
        content.setText(capitalizeAfterDot(content.getText()));
        content.setText(removeSpacesAroundQuotes(content.getText()));
        content.setText(capitalizeFirstLetter(content.getText()));
        content.setText(addDotAtEnd(content.getText()));
    }
}
