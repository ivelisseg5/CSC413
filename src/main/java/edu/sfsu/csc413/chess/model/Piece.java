package edu.sfsu.csc413.chess.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private final PieceType type;

    protected Piece(Color color, PieceType type){
        this.color = color;
        this.type = type;
    }

    public Color color(){
        return color;
    }

    public PieceType type(){
        return type;
    }

    public char symbol(){
        char letter = type.symbol();
        return color == Color.WHITE ? letter : Character.toLowerCase(letter);
    }

    @Override
    public String toString(){
        return String.valueOf(symbol());
    }

    public abstract List<Move> pseudoLegalMoves(Board board, Position from);

    public boolean attacks(Board board, Position from, Position target){
        for(Move move : pseudoLegalMoves(board, from)){
            if (move.to().equals(target)) {
                return true;
            }
        }
        return false;
    }

    /*
        sliding movements are basically for pieces with directional vectors such as the
        rook, bishop or queen. the arraylist is an empty array that collects all the
        valid moves and the for loop iterates through each direction each piece can
        go in
     */
    protected List<Move> slidingMoves(Board board, Position from, int[][] directions){
        List<Move> moves = new ArrayList<>();
        for(int[] direction : directions){
            Position step = from;
            while(true){
                step = step.offsetOrNull(direction[0], direction[1]);
                if(step == null){
                    break;
                }
                Piece occupant = board.pieceAt(step);
                if(occupant == null){
                    moves.add(Move.quiet(from, step, this));
                }else{
                    if(occupant.color() != color){
                        moves.add(Move.capture(from, step, this, occupant));
                    }
                    break;
                }
            }
        }

        return moves;
    }

    /*
        this functions used for single-step movements, for example the knight only
        moves in an L shape, the king can only move 1 square in every direction
     */
    protected List<Move> steppingMoves(Board board, Position from, int[][] offsets){
        List<Move> moves = new ArrayList<>();
        for(int[] offset : offsets){
            Position to = from.offsetOrNull(offset[0], offset[1]);
            if(to == null){
                continue;
            }
            Piece occupant = board.pieceAt(to);
            if(occupant == null){
                moves.add(Move.quiet(from, to, this));
            }else if(occupant.color() != color){
                moves.add(Move.capture(from, to, this, occupant));
            }
        }
        return moves;
    }

}
