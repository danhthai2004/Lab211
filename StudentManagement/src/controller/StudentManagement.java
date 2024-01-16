package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import model.Student;
import view.Menu;

public class StudentManagement extends Menu {

    Scanner sc = new Scanner(System.in);
    ArrayList<Student> studentlist = new ArrayList<>();

    public StudentManagement(String td, String[] mc) {
        super(td, mc);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createStudent(n, studentlist);
                break;
            case 2:
                findAndsortStudent(studentlist);
                break;
            case 3:
                updateOrDelete(n, studentlist);
                break;
            case 4:
                report(studentlist);
                break;
            case 5:
                System.exit(0);
        }
    }

    public static void createStudent(int count, ArrayList<Student> studentList) {
        while (count < 10) {
            System.out.print("Enter id: ");
            String id = Validator.checkInputString();
            System.out.print("Enter name student: ");
            String name = Validator.checkInputString();

            if (!Validator.checkIdExist(studentList, id, name)) {
                System.err.println("Id has existed for a student. Please re-input.");
                continue;
            }

            System.out.print("Enter semester: ");
            String semester = Validator.checkInputString();

            String course = getValidCourse();
            if (course == null) {
                continue;
            }

            if (!Validator.checkStudentExist(studentList, id, name, semester, course)) {
                studentList.add(new Student(id, name, semester, course));
                count++;
                System.out.println("Add student success.");
            } else {
                System.err.println("Student with the same ID, name, semester, and course already exists.");
            }
        }

        System.out.print("Do you want to continue (Y/N): ");
        if (!Validator.checkInputYN()) {
            return;
        }
    }

    private static String getValidCourse() {
        System.out.println("Available courses:");
        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C/C++");
        System.out.print("Enter course number: ");

        int choice = Validator.checkInputIntLimit(1, 3);

        switch (choice) {
            case 1:
                return "Java";
            case 2:
                return ".Net";
            case 3:
                return "C/C++";
            default:
                System.err.println("Invalid course number. Please enter a valid number.");
                return null;
        }
    }

    public ArrayList<Student> stuListfindbyname(ArrayList<Student> ls, String name) {
        ArrayList<Student> stuListfindbyname = new ArrayList<>();
        for (Student student : ls) {
            if (student.getStudentName().contains(name)) {
                stuListfindbyname.add(student);
            }
        }
        return stuListfindbyname;
    }

    public void findAndsortStudent(ArrayList<Student> stuList) {
        if (stuList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            System.out.print("Enter name to search: ");
            String name = Validator.checkInputString();
            ArrayList<Student> stuListfindbyname = stuListfindbyname(stuList, name);
            if (stuListfindbyname.isEmpty()) {
                System.out.println("Not found!");
            } else {
                Collections.sort(stuListfindbyname, Comparator.comparing(Student::getStudentName));

                for (Student student : stuListfindbyname) {
                    System.out.printf("%-15s%-15s%-15s\n",
                            student.getStudentName(), student.getSemester(), student.getCourseName());
                }
            }
        }
    }

    public static ArrayList<Student> stuListbyID(ArrayList<Student> stuList, String id) {
        ArrayList<Student> stuListbyID = new ArrayList<>();
        for (Student student : stuList) {
            if (id.equalsIgnoreCase(student.getId())) {
                stuListbyID.add(student);
            }
        }
        return stuListbyID;
    }

    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;

        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }

        System.out.print("Enter student number: ");
        int choice = Validator.checkInputIntLimit(1, listStudentFindByName.size());

        return listStudentFindByName.get(choice - 1);
    }

    public void updateOrDelete(int count, ArrayList<Student> stuList) {
        if (stuList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }

        System.out.print("Enter id: ");
        String id = Validator.checkInputString();
        ArrayList<Student> listStudentFindById = stuListbyID(stuList, id);

        if (listStudentFindById.isEmpty()) {
            System.err.println("Not found student.");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindById);
            System.out.print("Do you want to update (U) or delete (D) student: ");

            if (Validator.checkInputUD()) {
                System.out.print("Enter ID: ");
                String idStudent = Validator.checkInputString();
                System.out.print("Enter name student (press Enter to keep old value): ");
                String name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    name = student.getStudentName();
                }

                System.out.print("Enter semester (press Enter to keep old value): ");
                String semester = sc.nextLine().trim();
                if (semester.isEmpty()) {
                    semester = student.getSemester();
                }

                System.out.print("Enter name course (press Enter to keep old value): ");
                String course = sc.nextLine().trim();
                if (course.isEmpty()) {
                    course = student.getCourseName();
                }

                if (!Validator.checkChangeInfomation(student, id, name, semester, course)) {
                    System.err.println("Nothing changed.");
                }

                if (Validator.checkStudentExist(stuList, id, name, semester, course)) {
                    student.setId(idStudent);
                    student.setStudentName(name);
                    student.setSemester(semester);
                    student.setCourseName(course);
                    System.err.println("Update success.");
                }
                return;
            } else {
                stuList.remove(student);
                count--;
                System.err.println("Delete success.");
                return;
            }
        }
    }

    public static void report(ArrayList<Student> stuList) {
        if (stuList.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Report> lr = new ArrayList<>();

        for (Student student : stuList) {
            String id = student.getId();
            String courseName = student.getCourseName();
            String studentName = student.getStudentName();
            int total = countTotal(stuList, id, courseName);

            if (Validator.checkReportExist(lr, studentName, courseName, total)) {
                lr.add(new Report(student.getStudentName(), courseName, total));
            }
        }

        for (Report report : lr) {
            System.out.printf("%-15s|%-10s|%-5d\n", report.getStudentName(),
                    report.getCourseName(), report.getTotalCourse());
        }
    }

    private static int countTotal(ArrayList<Student> stuList, String id, String courseName) {
        int total = 0;
        for (Student student : stuList) {
            if (id.equalsIgnoreCase(student.getId()) && courseName.equalsIgnoreCase(student.getCourseName())) {
                total++;
            }
        }
        return total;
    }

}
