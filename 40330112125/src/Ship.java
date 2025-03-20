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
        String input;
        boolean valid = false;
        int[] sizeOfShips = null;
        if (Board.getSizeOfBoard() >= 5) {
            sizeOfShips = new int[]{2, 3, 4, 5};
        } else if (Board.getSizeOfBoard() >= 2) {
            sizeOfShips = new int[]{2};
        } else if (Board.getSizeOfBoard() >= 3) {
            sizeOfShips = new int[]{2, 3};
        } else if (Board.getSizeOfBoard() >= 4) {
            sizeOfShips = new int[]{2, 3, 4};
        }

            System.out.println("  Mannual placement , enter Ship Size : ");
            int shipSize ;
            board.printGrid();
        do {
            shipSize = scanner.nextInt();
            scanner.nextLine();
            if (shipSize < 1 || shipSize > sizeOfShips[sizeOfShips.length - 1] || shipSize == 1 ) {
                System.out.println("Invalid Ship Size, enter again");
            }
        } while (shipSize < 1 || shipSize > sizeOfShips[sizeOfShips.length - 1] || shipSize==1);


        while (!valid) {
            System.out.println("Enter your (col & row ex = B6): ");
            input = scanner.nextLine().toUpperCase();
            if (!utils.isValid(input, Board.getSizeOfBoard())) {
                System.out.println("Invalid input, try again");
                continue;
            }
            for(int size : sizeOfShips) {
                boolean placed = false;
                while (!placed) {


                    System.out.println("Horizontal : 2 " + "Vertical : 1");
                    int horizontal2 = scanner.nextInt();
                    scanner.nextLine();
                    boolean horizontal1 = horizontal2 == 2;
                    int col = input.charAt(0) - 'A';
                    int row = input.charAt(1) - '0';
                    if (canPlaceShips(board, row, col, sizes, horizontal1)) {
                        for (int i = 0; i < sizes; i++) {
                            if (horizontal1) {
                                board.getBoard()[row][col + i] = Board.getShip();
                            } else {
                                board.getBoard()[row + i][col] = Board.getShip();
                            }
                        }

                        System.out.println("Ship placed successfully ");
                        valid = true;
                    } else {
                        System.out.println("Ship can not be placed ");

                    }
                    placed = true;
                }
            }
        }
            }


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
