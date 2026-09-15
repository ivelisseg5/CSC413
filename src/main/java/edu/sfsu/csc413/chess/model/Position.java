package edu.sfsu.csc413.chess.model;

public record Position(int file, int rank){

    public static final int BOARD_SIZE = 8;

    public static boolean isOnBoard(int file, int rank){
        return file >= 0 && file < BOARD_SIZE && rank >= 0 && rank < BOARD_SIZE;
        /*
        checking to see if a chess piece is on the board & what the position of that
        piece is
         */
    }

    public Position{
        if(!isOnBoard(file, rank)){
            throw new IllegalArgumentException("Position off board: file=" + file + ", rank=" + rank);
        }
        // if it's not on the board return an exception w/ an error handling message
    }

    public static Position parse(String algebraic){

        if(algebraic == null || algebraic.length() != 2) {
            throw new IllegalArgumentException("Invalid algebraic notation: " + algebraic);
        }

        int file = algebraic.charAt(0) - 'a';
        int rank = algebraic.charAt(1) - '1';

        return new Position(file,rank);

        /*
        first check to see if the string is exactly 2 characters, if it's not 2 chars or it is
        less than 2 chars in the string then we throw an exception

        at index 0 and 1 we're checking the range of characters. index 0 has to be a char, index 1
        has to be a number

        lastly, we return the position of the piece on the board
         */
    }

    public Position offsetOrNull(int fileDelta, int rankDelta){
        int newFile = file + fileDelta;
        int newRank = rank + rankDelta;

        if(!isOnBoard(newFile, newRank)){
            return null;
        }

        return new Position(newFile, newRank);
        // returns the destination of the chess piece if it were to move
    }

    @Override
    public String toString(){
        return "" + (char) ('a' + file) + (char) ('1' + rank);
    }
}
