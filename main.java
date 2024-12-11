import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public static void main(String[] args) throws FileNotFoundException {
    if (args.length < 1) {
        System.out.println("Usage: java PasswordStrengthChecker <password>");
        return;
    }

    String password = args[0];
    SeparateChainingHashST<String, Integer> separateChainingTable = new SeparateChainingHashST<>();
    LinearProbingHashST<String, Integer> linearProbingTable = new LinearProbingHashST<>();

    int lineNumber = 1;

    Scanner fileScanner = new Scanner(new File("/Users/rhiltz/IdeaProjects/Week12/dictionary/wordlist.txt"));
    while (fileScanner.hasNextLine()) {
        String word = fileScanner.nextLine().trim();
        separateChainingTable.put(word, lineNumber, true);
        linearProbingTable.put(word, lineNumber, true);
        separateChainingTable.put(word, lineNumber, false);
        linearProbingTable.put(word, lineNumber, false);
        lineNumber++;
    }

    fileScanner.close();

    // Check password strength using both hash functions
    boolean isStrongOldHash = PasswordChecker.isStrongPassword(password, separateChainingTable, true);
    boolean isStrongCurrentHash = PasswordChecker.isStrongPassword(password, separateChainingTable, false);

    System.out.println("Password: " + password);
    System.out.println("Is strong (Old Hash): " + isStrongOldHash);
    System.out.println("Is strong (Current Hash): " + isStrongCurrentHash);

    // Measure search costs for both hash functions
    int separateChainingCostOld = separateChainingTable.getSearchCost(password, true);
    int linearProbingCostOld = linearProbingTable.getSearchCost(password, true);

    int separateChainingCostCurrent = separateChainingTable.getSearchCost(password, false);
    int linearProbingCostCurrent = linearProbingTable.getSearchCost(password, false);

    System.out.println("Search cost (Separate Chaining, Old Hash): " + separateChainingCostOld);
    System.out.println("Search cost (Linear Probing, Old Hash): " + linearProbingCostOld);

    System.out.println("Search cost (Separate Chaining, Current Hash): " + separateChainingCostCurrent);
    System.out.println("Search cost (Linear Probing, Current Hash): " + linearProbingCostCurrent);
}

