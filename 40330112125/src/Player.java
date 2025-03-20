public class Player {
    private final Board board;
    private final Board trackingBoard;
    public Player(int size ) {

        this.board = new Board(size);
        this.trackingBoard = new Board(size);


    }

    public Board getBoard() {
        return board;
    }
    public Board getTrackingBoard() {
        return trackingBoard;
    }
}
