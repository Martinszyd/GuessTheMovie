import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private String allTitles = new String();
    private String randomTitle = new String();

    File file = new File("movies.txt");
    Scanner scanner;
    {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

        public void showAll(String allTitles){
        while (scanner.hasNextLine()) {
            allTitles = scanner.nextLine();
        }
    }

    public void showRandomTitle (String randomTitle){
        for (int i=0; i<=allTitles.length(); i++){
            randomTitle = scanner.;
        }
    }

    public String getAllTitles() {
        return allTitles;
    }

    public String getRandomTitle() {
        return randomTitle;
    }
}
