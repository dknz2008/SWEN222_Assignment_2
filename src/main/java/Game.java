import com.rits.cloning.Cloner;

import java.util.*;

/**
 * Created by TML_TEST on 25/07/2017.
 */
public class Game {


    public enum parseReturnState{
        SUCCESS, FAIL, UNDO
    }
    public enum GameState {
        CREATION, MOVEMENT, DONE
    }

    Player currentTurn;
    Board board;
    Player yellowPlayer;
    Player greenPlayer;

    private Stack<SavedGameState> savedGameStates;
    List<Piece> movedPieces;

    public Game() {
        savedGameStates = new Stack<>();
        movedPieces = new ArrayList<>();
        this.board = new Board();

        greenPlayer = new Player(3 - 1, 3 - 1, Color.GREEN);
        yellowPlayer = new Player(8 - 1, 8 - 1, Color.YELLOW);
        greenPlayer.populatePieceList();
        yellowPlayer.populatePieceList();
        currentTurn = yellowPlayer;

    }

    /**
     * Draws Barracks
     * @param grid, the grid of pieces
     * @param player, the current player
     */
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


    /**
     * Draws the Cemetery
     * @param grid the grid of pieces
     * @param player the current player
     */
    private void drawCemetery(Piece[][] grid, Player player){

        System.out.println(player.getColor().toString() + " Cemetery:");
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
                if(i >=player.cemetery.size()){
                    p = null;
                }else{
                    p = player.cemetery.get(i++);
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
                if(i >=player.cemetery.size()){
                    p = null;
                }else{
                    p = player.cemetery.get(i++);
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
                if(i >=player.cemetery.size()){
                    p = null;
                }else{
                    p = player.cemetery.get(i++);
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

    /**
     * Draws the grid of pieces
     * @param grid grid of pieces
     */
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


    /**
     * Returns the associated symbol with the type
     * @param t the type
     * @param orientation the orientation of the type
     * @return String
     */
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


    /**
     * returns the player has has won if any,
     * or null if there is no current winning
     * player
     * @return Player
     */
    public Player hasWon(){
        if(board.attackingGreenFace(yellowPlayer) || board.attackingGreenFace(greenPlayer)){
            return yellowPlayer;
        }

        if(board.attackingYellowFace(yellowPlayer) || board.attackingYellowFace(greenPlayer)){
            return greenPlayer;
        }
        return null;
    }

    /**
     * the main gameloop
     */
    public void gameLoop() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        while(hasWon() == null){
            parseTurnLoop(reader);
        }

        System.out.println(hasWon().getColor() + " Player Wins!!!!");
    }

    private GameState state = GameState.CREATION;


    /**
     * parses the turn loop
     * @param reader the scanner
     */
    public void parseTurnLoop(Scanner reader){
        System.out.println(currentTurn.getColor() + "'s Turn");
        state = GameState.CREATION;
        this.movedPieces = new ArrayList<>();
        while(state != GameState.DONE){
            drawGrid(board.getGrid());
            parseTurn(reader, movedPieces);
        }
        currentTurn = getOpponent(currentTurn);
    }

    /**
     * parses the players turn
     * @param reader the scanner
     * @param movedPieces a list of the pieces that have been moved so far
     */
    public void parseTurn(Scanner reader, List<Piece> movedPieces) {
        if (state == GameState.CREATION) {
            if(currentTurn.isCreationTileFree(board)){
                if(parseAskForCreatePiece(reader) == parseReturnState.SUCCESS){
                    drawBarracks(board.getGrid(), currentTurn);
                    drawCemetery(board.getGrid(), currentTurn);
                    parseReturnState check = parseCreatePiece(reader, currentTurn);
                    while(check == parseReturnState.FAIL){
                        check = parseCreatePiece(reader, currentTurn);
                    }
                    if (check == parseReturnState.UNDO) return;
                }
            }

            state = GameState.MOVEMENT;
        }
        if (state == GameState.MOVEMENT) {
            parseStageTwo(reader,currentTurn,movedPieces);
        }
    }

    private parseReturnState parseAskForCreatePiece(Scanner s) {
        System.out.println("Do you want to create a piece (y/n)");

        String input = s.nextLine();

        while (!input.matches("y|Y|n|N|undo")) {
            System.out.println("Do you want to create a piece (y/n)");
            input = s.nextLine();
        }

        if(input.equalsIgnoreCase("undo")){
            Undo();
            return parseReturnState.UNDO;
        }

        if (input.equalsIgnoreCase("y")) {
            return parseReturnState.SUCCESS;
        } else {
            return parseReturnState.FAIL;
        }
    }

    public parseReturnState parseCreatePiece(Scanner s, Player player){
        System.out.println("type: 'create <letter> <0/90/180/270>' to create a piece");

        String input = s.nextLine();
        String[] creationInput = input.split(" ");

        if(input.equalsIgnoreCase("undo")){
            Undo();
            return parseReturnState.UNDO;
        }

        if(creationInput.length != 3 || !(input.matches("create [A-z] (0|90|180|270)"))){
            return parseReturnState.FAIL;
        }
        if(player.getBarracks().contains(player.findPiece((creationInput[1])))){
            Clone(true,false);
            Piece piece = player.makePiece((creationInput[1]), Integer.parseInt(creationInput[2]));
            player.createPieceOnBoard(board, piece);
            parseReactions(s, piece);
            drawGrid(board.getGrid());
            return parseReturnState.SUCCESS;
        }
        return parseReturnState.FAIL;
    }


    /**
     * Parses user input for either passing the turn or developing pieces
     * @param s the scanner
     * @param player the current player
     * @return parseReturnState - the return state whether it is successful or not (or undo)
     */
    public parseReturnState parseStageTwo(Scanner s, Player player, List<Piece> movedPieces){
        System.out.println("Either 'pass' or type input name of piece you would like to develop: '<letter>'");

        String input = s.nextLine();

        if(input.equalsIgnoreCase("pass")){
            Clone(false,true);
            state = GameState.DONE;
            return parseReturnState.SUCCESS;
        }
        if(input.equalsIgnoreCase("undo")){
            Undo();
            return parseReturnState.UNDO;
        }
        else if(!(input.matches("[A-z]"))){
            return parseReturnState.FAIL;
        } else if(player.pieceCurrentlyPlayed(input) == null){
            return parseReturnState.FAIL;
        }else if(!player.getInPlay().contains(player.pieceCurrentlyPlayed(input))){
            System.out.println("This piece is not on the board");
            return parseReturnState.FAIL;
        }else {
            return parsePieceDevelopment(s, player.pieceCurrentlyPlayed(input), player, movedPieces);
        }

    }

    private parseReturnState parsePieceDevelopment(Scanner s, Piece piece, Player player, List<Piece> movedPieces){
        System.out.println("Either choose to 'move <up|down|left|right>' the piece or 'rotate <0|90|180|270>'");

        String input = s.nextLine();
        String[] creationInput = input.split(" ");

        if(creationInput.length != 2){
            return parseReturnState.FAIL;
        } else if(creationInput[0].equals("move")){
            return parseMove(s, player, piece, creationInput[1], movedPieces);
        }else if(creationInput[0].equals("rotate")){
            return parseRotation(s, player, piece, creationInput[1], movedPieces);
        } else{
            return parseReturnState.FAIL;
        }

    }

    private parseReturnState parseMove(Scanner s, Player player, Piece piece, String direction, List<Piece> movedPieces){
        if(!(direction.matches("up|down|left|right"))){
            return parseReturnState.FAIL;
        } else {
            if(!movedPieces.contains(piece)){
                Clone(false, false);
                piece.move(direction, board);
                movedPieces.add(piece);
                parseReactions(s, piece);
                return parseReturnState.SUCCESS;
            }else{
                System.out.println("Piece selected has already been moved before");
                return parseReturnState.FAIL;
            }
        }
    }

    private parseReturnState parseRotation(Scanner s, Player player, Piece piece, String rotation, List<Piece> movedPieces){
        if(!(rotation.matches("0|90|180|270"))){
            return parseReturnState.FAIL;
        } else {
            if(!movedPieces.contains(piece)){
                Clone(false,false);
                piece.rotate(Integer.valueOf(rotation));
                movedPieces.add(piece);
                parseReactions(s, piece);
                return parseReturnState.SUCCESS;
            }else{
                System.out.println("Piece selected has already been moved before");
                return parseReturnState.FAIL;
            }
        }
    }

    /**
     *
     * Parses reactions, if it is one reaction
     * it executes it automatically, otherwise
     * calls the next method which
     * allows the user to enter in numbers for
     * the associated reactions
     *
     * @param s the scanner
     * @param piece current piece
     */
    public void parseReactions(Scanner s, Piece piece){
        //1. work out the reactions and add them to a list
        //iterate over all the reactions
        if((piece.getPlayer().pieceCurrentlyPlayed(piece.getName())) != null){
            ReactionManager reactionManager = new ReactionManager();
            List<Reaction> reactions = reactionManager.workOutReactions(piece.getX(), piece.getY(), board);
            if(reactions.size() == 1){
                System.out.println("auto executing 1 reaction");
                System.out.println(reactionManager.printReactionInformation(reactions.get(0)));
                reactions.get(0).executeReaction(board);
                parseReactions(s, reactions.get(0).getReactive());
            }else if(reactions.size() > 1){
                drawGrid(board.getGrid());
                int i = 0;
                for(Reaction reaction: reactions){
                    System.out.println(i + ": " + reactionManager.printReactionInformation(reaction));
                    i++;
                }

                parseReturnState check = parseReactionInput(s, reactions.size(), reactionManager, reactions);
                while(check == parseReturnState.FAIL){
                    check = parseReactionInput(s, reactions.size(), reactionManager, reactions);
                }
                parseReactions(s, piece);
            }
        }

    }

    /**
     * Parses reaction input,
     * allows the user to enter in numbers for
     * the associated reactions
     *
     * @param s scanner
     * @param i the number of reactions
     * @param reactionManager works out reactions
     * @param reactions stores the reactions
     * @return the success state
     */
    public parseReturnState parseReactionInput(Scanner s, int i, ReactionManager reactionManager, List<Reaction> reactions){
        System.out.println("What reaction do you want to apply '<number>'");

        String input = s.nextLine();

        int choice = -1;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("you didn't supply a numerical input");
            return parseReturnState.FAIL;
        }
        System.out.println(choice);
        if(choice >= 0 && choice <= i){
            System.out.println("Executing the reaction:");
            System.out.println(reactionManager.printReactionInformation(reactions.get(choice)));
            reactions.get(choice).executeReaction(board);
//            parseReactions(s, reactions.get(choice).getReactive());
            drawGrid(board.getGrid());
            return parseReturnState.SUCCESS;
        }
        return parseReturnState.FAIL;
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
    }

    /**
     * Deepclones the game information
     * @param isCreation if player is in creation state
     * @param isPass if player has passed or not
     */
    private void Clone(boolean isCreation, boolean isPass){
        Cloner cloner = new Cloner();
        Board boardclone = cloner.deepClone(board);
        Player yellowPlayerClone = cloner.deepClone(yellowPlayer);
        Player greenPlayerClone = cloner.deepClone(greenPlayer);
        List<Piece> movedPiecesClone = cloner.deepClone(movedPieces);

        savedGameStates.push(new SavedGameState(boardclone, yellowPlayerClone, greenPlayerClone, movedPiecesClone, cloner.deepClone(currentTurn),isCreation,isPass));
    }

    /**
     * undoes move (assuming >= 1 move has been made thus far)
     */
    private void Undo(){
        if(savedGameStates.size() >= 1){
            SavedGameState savedGame = savedGameStates.pop();
            savedGame.UndoGame(this);

            System.out.println("Undid move");
            drawGrid(board.getGrid());
            if (savedGame.isWasCreation()) {
                state = GameState.CREATION;
            }
            if (savedGame.isWasPass()) {
                state = GameState.MOVEMENT;
            }
        }else{
            System.out.println("There is nothing to undo currently");
        }

    }

}
