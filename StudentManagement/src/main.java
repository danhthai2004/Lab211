
import controller.StudentManagement;



public class main {

    public static void main(String[] args) {
        String mChon[] = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
        StudentManagement studentManagement = new StudentManagement("WELCOME TO STUDENT MANAGEMENT", mChon);
        studentManagement.run();
    }
}
