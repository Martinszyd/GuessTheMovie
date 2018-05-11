import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Game titles = new Game();
        titles.showAll(titles.getAllTitles());
    }
}
