import java.util.Random;

public class AiPlayer  extends Player {
    public AiPlayer( int size) {
        super(size);
    }

    public String aiMove(int sizeOfBoard) {
        Random rand = new Random();
        int x = rand.nextInt(sizeOfBoard);
        int y = rand.nextInt(sizeOfBoard);
        char col = (char)('A' + x);
        int row = y ;
        return ""+ col+row;


    }
}
