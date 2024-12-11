public class PasswordChecker {
    public static boolean isStrongPassword(String password, SeparateChainingHashST<String, Integer> hashTable, boolean useOldHash) {
        if (password.length() < 8) {
            return false;
        }

        if (hashTable.get(password, useOldHash) != null) {
            return false;
        }

        if (password.matches("(\\w+)[0-9]$")) {
            String baseWord = password.replaceAll("\\d+$", "");
            if (hashTable.get(baseWord, useOldHash) != null) {
                return false;
            }
        }

        return true;
    }
}
