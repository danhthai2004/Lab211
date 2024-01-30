package controller;

import java.util.Scanner;
import view.Menu;

public class ComputerProgram extends Menu{

    private static String[] mc = {"Normal Calculator", "BMI Calculator", "Exit"};

    private Scanner sc = new Scanner(System.in);

    public ComputerProgram() {
        super("========= Calculator Program =========", mc);
    }

    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1:
                normalCalculator();
                break;
            case 2:
                BMICalculator();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public static void normalCalculator() {
        double memory;
        System.out.print("Enter number: ");
        memory = Validation.checkInputDouble();
        while (true) {
            System.out.print("Enter operator: ");
            String operator = Validation.checkInputOperator();
            if (operator.equalsIgnoreCase("+")) {
                memory += Validation.inputNumber();
                System.out.println("Memory: " + memory);
            }
            if (operator.equalsIgnoreCase("-")) {
                memory -= Validation.inputNumber();
                System.out.println("Memory: " + memory);
            }
            if (operator.equalsIgnoreCase("*")) {
                memory *= Validation.inputNumber();
                System.out.println("Memory: " + memory);
            }
            if (operator.equalsIgnoreCase("/")) {
                memory /= Validation.inputNumber();
                System.out.println("Memory: " + memory);
            }
            if (operator.equalsIgnoreCase("^")) {
                memory = Math.pow(memory, Validation.inputNumber());
                System.out.println("Memory: " + memory);
            }
            if (operator.equalsIgnoreCase("=")) {
                System.out.println("Result: " + memory);
                return;
            }
        }

    }

    //display result BMI status
    public static String BMIStatus(double bmi) {
        if (bmi < 19) {
            return "Under-standard.";
        } else if (bmi >= 19 && bmi < 25) {
            return "Standard.";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight.";
        } else if (bmi >= 30 && bmi < 40) {
            return "Fat-should lose weight";
        } else {
            return "Very fat - should lose weight immediately";
        }
    }

    //allow user BMI calculator
    public static void BMICalculator() {
        System.out.print("Enter Weight(kg): ");
        double weight = Validation.checkInputDouble();
        System.out.print("Enter Height(cm): ");
        double height = Validation.checkInputDouble();
        double bmi = weight * 10000 / (height * height);
        System.out.printf("BMI number: %.2f\n", bmi);
        System.out.println("BMI Status: " + BMIStatus(bmi));
    }
}
