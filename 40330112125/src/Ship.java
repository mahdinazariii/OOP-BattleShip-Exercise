import java.util.Random;
import java.util.Scanner;

public class Ship {
    public void placeShips(Board board, int sizeOfBoard) {
        int[] size = null;
        if (Board.getSizeOfBoard() >= 5) {
            size = new int[]{2, 3, 4, 5};
        } else if (Board.getSizeOfBoard() >= 4) {
            size = new int[]{2, 3, 4};
        } else if (Board.getSizeOfBoard() >= 3) {
            size = new int[]{2, 3};
        } else if (Board.getSizeOfBoard() >= 2) {
            size = new int[]{2};
        }

        Random rand = new Random();
        for (int sizes : size) {
            boolean placed = false;
            while (!placed) {
                int row = rand.nextInt(sizeOfBoard);
                int col = rand.nextInt(sizeOfBoard);
                boolean horizontal = rand.nextBoolean();
                if (canPlaceShips(board, row, col, sizes, horizontal)) {
                    for (int i = 0; i < sizes; i++) {
                        if (horizontal) {
                            board.getBoard()[row][col + i] = Board.getShip();
                        } else {
                            board.getBoard()[row + i][col] = Board.getShip();
                        }
                    }
                    placed = true;
                }
            }
        }
        System.out.println("Random placement is done ");
    }
    public void placeMannuallyShips(Board board, int sizes, Utils utils) {
        Scanner scanner = new Scanner(System.in);
        int[] sizeOfShips = null;

        if (Board.getSizeOfBoard() >= 5) {
            sizeOfShips = new int[]{2, 3, 4, 5};
        } else if (Board.getSizeOfBoard() >= 4) {
            sizeOfShips = new int[]{2, 3, 4};
        } else if (Board.getSizeOfBoard() >= 3) {
            sizeOfShips = new int[]{2, 3};
        } else if (Board.getSizeOfBoard() >= 2) {
            sizeOfShips = new int[]{2};
        }

        for (int shipIndex = 0; shipIndex < sizeOfShips.length; shipIndex++) {
            int shipSize = sizeOfShips[shipIndex];
            boolean valid = false;

            System.out.println("Manual placement for Ship " + (shipIndex + 1) + " (Size: " + shipSize + ")");
            board.printGrid();

            while (!valid) {
                System.out.println("Enter the starting position (e.g., B6): ");
                String input = scanner.nextLine().toUpperCase();

                if (!utils.isValid(input, Board.getSizeOfBoard())) {
                    System.out.println("Invalid input, try again.");
                    continue;
                }

                System.out.println("Orientation: 1 for Vertical, 2 for Horizontal");
                int orientation = scanner.nextInt();
                scanner.nextLine();
                boolean horizontal = (orientation == 2);

                int col = input.charAt(0) - 'A';
                int row = input.charAt(1) - '0';

                if (canPlaceShips(board, row, col, shipSize, horizontal)) {
                    for (int i = 0; i < shipSize; i++) {
                        if (horizontal) {
                            board.getBoard()[row][col + i] = Board.getShip();
                        } else {
                            board.getBoard()[row + i][col] = Board.getShip();
                        }
                    }
                    System.out.println("Ship " + (shipIndex + 1) + " placed successfully.");
                    valid = true;
                } else {
                    System.out.println("Cannot place the ship , try again.");
                }
            }

            board.printGrid();
        }
    }
//999


   private boolean canPlaceShips(Board board, int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > Board.getSizeOfBoard()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (board.getBoard()[row][col + i] == Board.getShip()) {
                    return false;
                }
            }

        } else {
            if (row + size > Board.getSizeOfBoard()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (board.getBoard()[row + i][col] == Board.getShip()) {

                    return false;
                }
            }
        }
        return true;
    }

    public void isGameOver() {
    }

    public boolean shipSunk(Board board) {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard().length; j++) {
                if (board.getBoard()[i][j] == board.getShip()) {
                    return false;
                }


            }
        }
        return true;
    }

    }
