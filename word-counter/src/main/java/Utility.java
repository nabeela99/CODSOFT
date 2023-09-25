import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Utility {
    static int n = 0;
    public static String takeInput() throws IOException, IllegalAccessException {
        var input = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to enter a sentence or a file?" + "\n"+ "press 1 for sentence" + "\n" + "press 2 for file");
        n = sc.nextInt();
        sc.nextLine();
        switch (n) {
            case 1 -> {
                System.out.println("Enter the sentence :");
                input = sc.nextLine();
                Validation.checkInput(input);
            }
            case 2 -> {
                System.out.print("Enter input file path and name:");
                String inFile = sc.next();
                var inputFile = new File(inFile);
                if (inputFile.isFile()) {
                    Path fileName = Path.of(inFile);
                    input = Files.readString(fileName);
                    Validation.checkInput(input);
                } else {
                    throw new IllegalArgumentException("Invalid File path given");
                }
            }
            default -> {
                System.out.println("Please Enter 1 or 2 only");
                input = Utility.takeInput();
            }
        }
        return input;
    }
}

