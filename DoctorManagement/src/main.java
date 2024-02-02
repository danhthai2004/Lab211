
import controller.Programming;

public class main {
     public static void main(String[] args) {
        String mChon[] = {"Add Doctor","Update Doctor","Delete Doctor","Search Doctor", "Exit"};
        Programming program = new Programming("========= Doctor Management ==========", mChon);
        program.run();
    }
}
