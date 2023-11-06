import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean gameOver = false;

    public static void main(String[] args) {
        char[][] board = new char[3][3];

        for (char[] cell : board) {
            Arrays.fill(cell, ' ');
        }

        char player = 'X';
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWELCOME TO CODER'S TIC-TAC-TOE");
        System.out.println("Mark the position according (row, column) manner, value between 0-2");

        while (!isBoardFull(board) && !gameOver) { // making the condition true to run the while
            printBoard(board);

            int row, col;
            do {
                System.out.println("Player " + player + " enter!");
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                    System.out.println("Enter the input value between 0-2. As first one is Row no. and second one is Column no.");
                }
            } while (row < 0 || row >= 3 || col < 0 || col >= 3);

            if (board[row][col] == ' ') {
                board[row][col] = player; // place the element

                gameOver = haveWon(board, player);

                if (!gameOver) {
                    player = (player == 'X') ? 'O' : 'X';
                } else {
                    printBoard(board);
                    System.out.println("Player " + player + " has won the game!");
                    playAgain(board);
                }

            } else {
                System.out.println("Position already taken. Try again!");
            }

            if (isBoardFull(board) && !gameOver) {
                printBoard(board);
                System.out.println("Tie!!");
                playAgain(board);
            }
        }

    }


    private static boolean haveWon(char[][] board, char player) {
        // check the rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // check for columns
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // check for diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
            return true;
        }

        return false;
    }

    private static void playAgain(char[][] board) {
        Scanner check = new Scanner(System.in);

        System.out.print("\nWant to play again?(y/n) ");
        String playAgain = check.next();

        if (playAgain.charAt(0) == 'y') {
            for (char[] cell : board) {
                Arrays.fill(cell, ' ');
            }
            gameOver = false;
        } else {
            System.out.println("\nTHANKS FOR PLAYING THE GAME :)");
        }
    }

    private static boolean isBoardFull(char[][] board) {
        for (char[] rowCells : board) {
            for (char eachCell : rowCells) {
                if (eachCell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(char[][] board) {
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);

                if (col < board[row].length - 1) {
                    System.out.print(" | ");
                } else {
                    System.out.println();
                }
            }
        }
    }
}