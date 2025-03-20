public class Board {
    private char[][] board;
    private static int sizeOfBoard;
    final private static char MISS = 'O';
    final private static char EMPTY = '~';
    final private static char HIT = 'X';
    final private static char SHIP = 'S';



    public Board( int sizeOfBoard) {
       this.sizeOfBoard = sizeOfBoard;
       this.board = new char[sizeOfBoard][sizeOfBoard];
       inizializeBoard(this);
    }


    public  static void inizializeBoard(Board board) {
        char[][] boardCopy = board.getBoard();
        for (int i = 0; i < getSizeOfBoard(); i++) {
            for (int j = 0; j < getSizeOfBoard(); j++) {
                boardCopy[i][j] = Board.getEmpty();
            }
        }
    }



    public  void printGrid() {
        char start = 'A';
        for (int i = 0; i < sizeOfBoard; i++) {
            System.out.printf("%4c", (char) (start + i));
        }
        System.out.println();
        for (int i = 0; i < sizeOfBoard; i++) {
            System.out.printf("%-2d ", i);
            for (int j = 0; j < sizeOfBoard; j++) {
                System.out.printf("%-4c", board[i][j]);

            }
            System.out.println();
        }
    }
    public char[][] getBoard() {
        return board;
    }
    public static char getShip() {
        return SHIP;
    }
    public  static char getMiss() {
        return MISS;
    }
    public static char getHit(){
        return HIT;
    }
    public boolean isHit(int row, int col) {
        return board[row][col] == HIT;
    }
    public boolean isShip(int row, int col) {
        return board[row][col] == SHIP;
    }
    public boolean isMiss(int row, int col) {
        return board[row][col] == MISS;
    }
    public static int getSizeOfBoard() {
        return sizeOfBoard;
    }
    public static char getEmpty(){
        return EMPTY;
    }
    }

