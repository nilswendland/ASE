import java.util.Scanner;

public class UserScanner{
    private static Scanner scanner;
    private static final UserScanner userScanner = new UserScanner();

    private UserScanner() {
        scanner = new Scanner(System.in);
    }

    public static UserScanner getInstance() {
        return userScanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String nextLineToLowerCase() {
        return scanner.nextLine().toLowerCase();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public String next() {
        return scanner.next();
    }
}
