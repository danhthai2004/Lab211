
import controller.Programming;

public class main {
    public static void main(String[] args) {
        String mChon[] = {"Add Word","Delete Word","Transalte", "Exit"};
        Programming program = new Programming("======== Dictionary program ========", mChon);
        program.run();
    }
}
