package edu.sfsu.csc413.chess.model;

public enum PieceType {

    PAWN('P'),
    KNIGHT('N'),
    BISHOP('B'),
    ROOK('R'),
    QUEEN('Q'),
    KING('K');

    private final char symbol;

    PieceType(char symbol){
        this.symbol = symbol;
    }

    public char symbol(){
        return symbol;
    }

    // accept the chess piece names as either uppercase or lowercase
    public static PieceType fromSymbol(char letter) {
        char upper = Character.toUpperCase(letter);
        for(PieceType type : values()){
            if(type.symbol == upper){
                return type;
            }
        }

        throw new IllegalArgumentException("no piece type for this letter: " + letter);
    }
}
