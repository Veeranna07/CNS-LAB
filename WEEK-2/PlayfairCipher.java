import java.util.Scanner;

public class PlayfairCipher {
    private static final char[][] matrix = new char[5][5];
    private static final char[] alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the key: ");
        String key = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        System.out.print("Enter the message: ");
        String message = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        generateMatrix(key);

        String encrypted = encrypt(message);
        System.out.println("Encrypted message: " + encrypted);
        sc.close();
    }

    private static void generateMatrix(String key) {
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = alphabet[k++];
            }
        }
        for (char c : key.toCharArray()) {
            addToMatrix(c);
        }
    }

    private static void addToMatrix(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    matrix[i][j] = '*';
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == '*') {
                    matrix[i][j] = c;
                    return;
                }
            }
        }
    }

    private static String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();

        // split the message into pairs of characters
        char[] pair = new char[2];
        for (int i = 0, j = 0; i < message.length(); i++, j = (j + 1) % 2) {
            pair[j] = message.charAt(i);
            if (j == 1) {
                // encrypt the pair
                encrypted.append(encryptPair(pair));
            }
        }

        // add an 'X' to the end if the message has an odd number of characters
        if (pair[1] == 0) {
            pair[1] = 'X';
            encrypted.append(encryptPair(pair));
        }

        return encrypted.toString();
    }

private static String encryptPair(char[] pair) {
    // check if the pair has the same character
    if (pair[0] == pair[1]) {
        pair[1] = 'X';
    }

    // find the indices of the characters in the matrix
    int[] indices1 = findInMatrix(pair[0]);
    int[] indices2 = findInMatrix(pair[1]);

    // check if the characters are in the same row or column
    if (indices1[0] == indices2[0]) {
        // same row
        pair[0] = matrix[indices1[0]][(indices1[1] + 1) % 5];
        pair[1] = matrix[indices2[0]][(indices2[1] + 1) % 5];
    } else if (indices1[1] == indices2[1]) {
        // same column
        pair[0] = matrix[(indices1[0] + 1) % 5][indices1[1]];
        pair[1] = matrix[(indices2[0] + 1) % 5][indices2[1]];
    } else {
        // different row and column
        pair[0] = matrix[indices1[0]][indices2[1]];
        pair[1] = matrix[indices2[0]][indices1[1]];
    }

    return new String(pair);
}

private static int[] findInMatrix(char c) {
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            if (matrix[i][j] == c) {
                return new int[] {i, j};
            }
        }
    }
    return new int[] {-1, -1};
    }
}

/*
	Enter the key: MONARCHY
	Enter the message: ATTACK
	Encrypted message: DQQDEH
 */
