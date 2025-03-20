import java.util.Scanner;

public class Game {
    Utils utils = new Utils();
    AiPlayer aiPlayer = null;
    Player player1;
    Player player2;

    public void startTheGame() {
        System.out.println("Welcome to the Battle Ship !");
        boolean playAgain;
        do {
            playGame();
            playAgain = askForReplay();


        } while (playAgain);
    }

    private boolean askForReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number \n 1 : Play Again ! \n 2 : Exit Game !");
        int askReplay = scanner.nextInt();
        scanner.nextLine();
        return askReplay == 1;


    }

    private void playGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Game has started!");
        System.out.println("Enter size of board (2 or higher) :  ");
        int sizeOfBoard ;
        do {
            sizeOfBoard = scanner.nextInt();
            if (sizeOfBoard < 2) {
                System.out.println("Please enter valid size of board (2 or higher) :    ");
            }
        }while (sizeOfBoard < 2);




        System.out.println(" Player 1: ");


        player1 = new Player(sizeOfBoard);
        System.out.println("Player 1: 1: Random placement \n2: Manual placement");
        int placementChoice;
        do {
            placementChoice = scanner.nextInt();


            if (placementChoice != 1 && placementChoice != 2) {
                System.out.println("Invalid placement number Try Again");
            }

        } while (placementChoice != 1 && placementChoice != 2);
        Ship ship1 = new Ship();
        if (placementChoice == 1) {
            ship1.placeShips(player1.getBoard(), sizeOfBoard);
        } else {
            ship1.placeMannuallyShips(player1.getBoard(), sizeOfBoard, utils);
        }
        System.out.println("1 : Against another player \n 2 : Against AI");
        int askPlayer = scanner.nextInt();
        boolean isAi = (askPlayer == 2);
        if (isAi) {
            System.out.println("AI is playing ");
            player2 = new AiPlayer( sizeOfBoard);

        } else {
            System.out.println(" Player 2");
            player2 = new Player( sizeOfBoard);

        }

        if (isAi) {
            Ship ship2 = new Ship();

            System.out.println("AI is placing Ships !");
            System.out.println("AI : 1: Random placement \n2: Manual placement ");
            int placementChoice2;
            do {
                placementChoice2 = scanner.nextInt();
                if (placementChoice2 != 1 && placementChoice2 != 2) {
                    System.out.println("Invalid placement number Try Again");

                }

            }while (placementChoice2 != 1 && placementChoice2 != 2);
            if (placementChoice2 == 1) {
                ship2.placeShips(player2.getBoard(), sizeOfBoard);
            }else{
                ship2.placeMannuallyShips(player2.getBoard(), sizeOfBoard, utils);
            }
        } else {
            System.out.println("Player 2: 1: Random placement \n2: Manual placement");
            int placementChoice2;
            do {
                placementChoice2 = scanner.nextInt();
                if (placementChoice2 != 1 && placementChoice2 != 2) {
                    System.out.println("Invalid placement number Try Again");
                }
            } while (placementChoice2 != 1 && placementChoice2 != 2);
            Ship ship2 = new Ship();
            if (placementChoice2 == 1) {
                ship2.placeShips(player2.getBoard(), sizeOfBoard);
            } else {
                ship2.placeMannuallyShips(player2.getBoard(), sizeOfBoard, utils);
            }
        }


        boolean playerturn1 = true;
        while (!isGmaeOver()) {
            if (playerturn1) {
                int Hit = 0;

                System.out.println("Player 1 turn");

                player1.getTrackingBoard().printGrid();

                playerTurn(player1, player2);

            } else {
                if (player2 instanceof AiPlayer) {
                    System.out.println("AI's turn");
                    String aiMove = ((AiPlayer) player2).aiMove(sizeOfBoard);
                    System.out.println("AI's move : " + aiMove);
                    player2.getTrackingBoard().printGrid();
                    aiTurn(player2, player1, aiMove);
                } else {
                    System.out.println("Player 2's turn:");
                    player2.getTrackingBoard().printGrid();
                    playerTurn(player2, player1);

                }

            }
            playerturn1 = !playerturn1;

        }


    }


    public void playerTurn(Player current, Player opponent) {
        System.out.println("Enter the target like ( A2 ) ! ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        if (!utils.isValid(input, Board.getSizeOfBoard())) {
            System.out.println("Invalid input!");
            return;
        }
        int col = input.charAt(0) - 'A';
        String row = input.substring(1);
        int row1 = Integer.parseInt(row);

        char[][] opponentGrid = opponent.getBoard().getBoard();
        char[][] trackingGrid = current.getTrackingBoard().getBoard();


        if (trackingGrid[row1][col] == Board.getMiss() | trackingGrid[row1][col] == Board.getHit()) {
            System.out.println("Already attacked !");
        }
        if (opponentGrid[row1][col] == Board.getShip()) {
            System.out.println("Hit !");
            opponentGrid[row1][col] = Board.getHit();
            trackingGrid[row1][col] = Board.getHit();



    }else if(opponentGrid[row1][col]==Board.getEmpty())

    {
        System.out.println("Miss !");
        opponentGrid[row1][col] = Board.getMiss();
        trackingGrid[row1][col] = Board.getMiss();
    }

}


    public boolean isGmaeOver() {
Ship ship = new Ship();
boolean player1Ships = ship.shipSunk(player1.getBoard());
boolean player2Ships = ship.shipSunk(player2.getBoard());
return player1Ships || player2Ships;


    }
    public void aiTurn(Player aiPlayer, Player opponent , String aiMove){
        int col = aiMove.charAt(0) - 'A';
        int row = aiMove.charAt(1) - '0';
        char[][] opponentGrid = opponent.getBoard().getBoard();
        char[][] trackingGrid = aiPlayer.getTrackingBoard().getBoard();

        if(trackingGrid[row][col]==Board.getHit() || trackingGrid[row][col]==Board.getMiss()){
            System.out.println("Already attacked !");
        }
        if (opponentGrid[row][col] == Board.getShip()){
            System.out.println(" AI Hit !");
            opponentGrid[row][col] = Board.getHit();
            trackingGrid[row][col] = Board.getHit();
        }else if (opponentGrid[row][col] == Board.getEmpty()){
            System.out.println("AI Miss !");
            opponentGrid[row][col] = Board.getMiss();
            trackingGrid[row][col] = Board.getMiss();
        }

    }
}
