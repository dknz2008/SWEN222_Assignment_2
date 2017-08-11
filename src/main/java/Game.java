import java.util.*;

/**
 * Created by TML_TEST on 25/07/2017.
 */
public class Game {

    List<Player> playerList;
    Player currentTurn;
    Board board;
    Player yellowPlayer;
    Player greenPlayer;
    Player winPlayer = null;

    public Game() {
        playerList = new ArrayList<>();
        this.board = new Board();

        yellowPlayer = new Player(3 - 1, 3 - 1, Color.YELLOW);
        greenPlayer = new Player(8 - 1, 8 - 1, Color.GREEN);
        playerList.add(yellowPlayer);
        playerList.add(greenPlayer);
        greenPlayer.populatePieceList();
        yellowPlayer.populatePieceList();
        currentTurn = yellowPlayer;

    }

    private void drawBarracks(Piece[][] grid, Player player){

        System.out.println(player.getColor().toString() + " Barracks:");
        System.out.println("-----------------------------------------");

        int i = 0;
        int temp = 0;
        for(int y = 0; y < 3; y++){

            Piece p;

            for(int x = 0; x < grid[0].length; x++) {
                System.out.print("+");
                System.out.print("---");
            }
            System.out.println("+");

            for(int x = 0; x < grid[0].length; x++){
                if(i >=player.barracks.size()){
                    p = null;
                }else{
                    p = player.barracks.get(i++);
                }
                System.out.print("| ");
                if(p != null){
                    System.out.print(typeSymbol(p.getTop(), Orientation.TOP));
                    System.out.print(" ");
                }else{
                    System.out.print("  ");
                }
            }
            i = temp;
            System.out.println("|");

            for(int x = 0; x < grid[0].length; x++){
                if(i >=player.barracks.size()){
                    p = null;
                }else{
                    p = player.barracks.get(i++);
                }
                System.out.print("|");
               if(p != null){
                    System.out.print(typeSymbol(p.getLeft(), Orientation.LEFT));
                    System.out.print(p.getName());
                    System.out.print(typeSymbol(p.getRight(), Orientation.RIGHT));
                }else{
                    System.out.print("   ");
                }
            }
            i = temp;
            System.out.println("|");

            for(int x = 0; x < grid[0].length; x++){
                if(i >=player.barracks.size()){
                    p = null;
                }else{
                    p = player.barracks.get(i++);
                }
                System.out.print("| ");
                if(p != null){
                    System.out.print(typeSymbol(p.getBottom(), Orientation.BOTTOM));
                }else{
                    System.out.print(" ");
                }
                System.out.print(" ");
                temp = i;
            }


            System.out.println("|");
        }


        for(int x = 0; x < grid[0].length; x++) {
            System.out.print("+");
            System.out.print("---");
        }
        System.out.println("+");

        System.out.println("-----------------------------------------");
    }

    public void drawGrid(Piece[][] grid){

        for(int y = 0; y < grid.length; y++){

            for(int x = 0; x < grid[0].length; x++) {
                System.out.print("+");
                System.out.print("---");
            }
            System.out.println("+");

            for(int x = 0; x < grid[0].length; x++){
                Piece p = grid[y][x];
                System.out.print("| ");
                if(p == board.getEmptytile()){
                    System.out.print("X ");
                }else if(p == board.getYellowface() || p == board.getGreenface()){
                    System.out.print("  ");
                }else if(p != null){
                    System.out.print(typeSymbol(p.getTop(), Orientation.TOP));
                    System.out.print(" ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println("|");

            for(int x = 0; x < grid[0].length; x++){
                Piece p = grid[y][x];
                System.out.print("|");
                if(p == board.getEmptytile()){
                    System.out.print("XXX");
                }else if(p == board.getYellowface() || p == board.getGreenface()){
                    System.out.print(" O ");
                }else if(p != null){
                    System.out.print(typeSymbol(p.getLeft(), Orientation.LEFT));
                    System.out.print(p.getName());
                    System.out.print(typeSymbol(p.getRight(), Orientation.RIGHT));
                }else{
                    System.out.print("   ");
                }
            }

            System.out.println("|");

            for(int x = 0; x < grid[0].length; x++){
                Piece p = grid[y][x];
                System.out.print("| ");
                if(p == board.getEmptytile()){
                    System.out.print("X");
                }else if(p == board.getYellowface() || p == board.getGreenface()){
                    System.out.print(" ");
                }else if(p != null){
                    System.out.print(typeSymbol(p.getBottom(), Orientation.BOTTOM));
                }else{
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }


        for(int x = 0; x < grid[0].length; x++) {
            System.out.print("+");
            System.out.print("---");
        }
        System.out.println("+");

    }

    private void drawPiece() {
        for (Piece yp : board.getYellowPieces()) {
            System.out.print(" ");
            System.out.print(typeSymbol(yp.getTop(), Orientation.TOP));
            System.out.println("");
            System.out.print(typeSymbol(yp.getLeft(), Orientation.LEFT));
            System.out.print(yp.getName());
            System.out.print(typeSymbol(yp.getRight(), Orientation.RIGHT));
            System.out.println("");
            System.out.print(" ");
            System.out.print(typeSymbol(yp.getBottom(), Orientation.BOTTOM));
            System.out.println("");
        }

    }


    private String typeSymbol(Piece.Type t, Orientation orientation) {
        if (t == Piece.Type.SWORD) {
            if (orientation == Orientation.LEFT || orientation == Orientation.RIGHT) {
                return ("-");
            }
            if (orientation == Orientation.TOP || orientation == Orientation.BOTTOM) {
                return ("|");
            }
        } else if (t == Piece.Type.SHIELD) {
            return ("#");
        }
        if (t == Piece.Type.NOTHING) {
            return (" ");
        }
        return null;
    }

    private boolean hasWon(Player player){
        //TODO fill this in
        if(player == greenPlayer){
            for(Piece p: greenPlayer.getInPlay()){
                if(p.getX() == 1 && p.getY() == 1){
                    return true;
                }
            }
        }else if(player == yellowPlayer){
            for(Piece p: yellowPlayer.getInPlay()){
                if(p.getX() == 8 && p.getY() == 8){
                    return true;
                }
            }
        }
        return false;
    }


    public void gameLoop() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        drawGrid(board.getGrid());

        while(!hasWon(currentTurn)){
            parseTurn(currentTurn, reader);
        }


    }
    public void parseTurn(Player player, Scanner reader){

        System.out.println(player.getColor() + "'s Turn");

        if(player.isCreationTileFree(board)){
            if(parseAskForCreatePiece(reader)){
                drawBarracks(board.getGrid(), currentTurn);
                parseCreatePiece(reader, player);
            }
        }

        List<Piece> movedPieces = new ArrayList<>();
        while(currentTurn == player){
            drawGrid(board.getGrid());
            parseStageTwo(reader, player, movedPieces);
        }

    }

    private boolean parseAskForCreatePiece(Scanner s) {
        System.out.println("Do you want to create a piece (y/n)");

        String input = s.nextLine();
        while (!input.matches("y|Y|n|N")) {
            System.out.println("Do you want to create a piece (y/n)");
            input = s.nextLine();
        }

        if (input.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    private void parseCreatePiece(Scanner s, Player player){
        System.out.println("type: 'create <letter> <0/90/180/270>' to create a piece");

        String input = s.nextLine();
        String[] creationInput = input.split(" ");

        while(creationInput.length != 3 || !(input.matches("create [A-z] (0|90|180|270)"))){
            System.out.println("type: 'create <letter> <0/90/180/270>' to create a piece");
            input = s.nextLine();
            creationInput = input.split(" ");
        }

        if(player.getBarracks().contains(player.findPiece((creationInput[1])))){
            Piece piece = player.makePiece((creationInput[1]), Integer.parseInt(creationInput[2]));
            player.createPieceOnBoard(board, piece);
        }
    }


    /**
     * Parses user input for either passing the turn or developing pieces
     * @param s
     * @param player
     */
    private boolean parseStageTwo(Scanner s, Player player, List<Piece> movedPieces){
        System.out.println("Either 'pass' or type input name of piece you would like to develop: '<letter>'");

        String input = s.nextLine();

        if(input.equalsIgnoreCase("pass")){
            currentTurn = getOpponent(currentTurn);
            return true;
        }

        else if(!(input.matches("[A-z]"))){
            return false;
        } else if(player.pieceCurrentlyPlayed(input) == null){
            return false;
        }else if(!player.getInPlay().contains(player.pieceCurrentlyPlayed(input))){
            System.out.println("This piece is not on the board");
            return false;
        }else {
            return parsePieceDevelopment(s, player.pieceCurrentlyPlayed(input), player, movedPieces);
        }

    }

    private boolean parsePieceDevelopment(Scanner s, Piece piece, Player player, List<Piece> movedPieces){
        System.out.println("Either choose to 'move <up|down|left|right>' the piece or 'rotate <0|90|180|270>'");

        String input = s.nextLine();
        String[] creationInput = input.split(" ");

        if(creationInput.length != 2){
            return false;
        } else if(creationInput[0].equals("move")){
            return parseMove(s, player, piece, creationInput[1], movedPieces);
        }else if(creationInput[0].equals("rotate")){
            return parseRotation(s, player, piece, creationInput[1], movedPieces);
        } else{
            return false;
        }

    }

    private boolean parseMove(Scanner s, Player player, Piece piece, String direction, List<Piece> movedPieces){
        if(!(direction.matches("up|down|left|right"))){
            return false;
        } else {
            if(!movedPieces.contains(piece)){
                piece.move(direction, board);
                movedPieces.add(piece);
                parseReactions(s, piece);
            }else{
                System.out.println("Piece selected has already been moved before");
                return false;
            }
        }
        return false;
    }

    private boolean parseRotation(Scanner s, Player player, Piece piece, String rotation, List<Piece> movedPieces){
        if(!(rotation.matches("0|90|180|270"))){
            return false;
        } else {
            if(!movedPieces.contains(piece)){
                piece.rotate(Integer.valueOf(rotation));
                movedPieces.add(piece);
                parseReactions(s, piece);
            }else{
                System.out.println("Piece selected has already been moved before");
                return false;
            }
        }
        return false;
    }

    public void parseReactions(Scanner s, Piece piece){
        //1. work out the reactions and add them to a list
        //iterate over all the reactions
        ReactionManager reactionManager = new ReactionManager();
        List<Reaction> reactions = reactionManager.workOutReactions(piece.getX(), piece.getY(), board);
        if(reactions.size() == 1){
            reactionManager.printReactionInformation(reactions.get(0));
            reactions.get(0).executeReaction(board);
        }else if(reactions.size() > 1){
            int i = 0;
            for(Reaction reaction: reactions){
                System.out.println(i + ": " + reactionManager.printReactionInformation(reaction));
            }

            parseReactionInput(s, reactions.size());
        }
    }

    public String parseReactionInput(Scanner s, int i){

        System.out.println("What reaction do you want to apply '<number>'");

        String input = s.nextLine();
        String temp = "[0-$0]";
        System.out.println(temp);
//        temp = temp.replace("$0",i+"");
//        if (input.matches(temp)) {
//
//        }
        return null;
    }

    private Player getOpponent(Player p){
        if(p == yellowPlayer){
            return greenPlayer;
        }else{
            return yellowPlayer;
        }
    }
    public static void main(String[] args) {
        Game g = new Game();

        g.gameLoop();

//       g.createPiece();
//       g.drawPiece();

    }



}
