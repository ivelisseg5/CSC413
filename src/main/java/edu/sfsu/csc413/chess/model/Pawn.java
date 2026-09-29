package edu.sfsu.csc413.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The pawn — the piece that breaks every rule the others follow.
 *
 * <p>It is the only piece that moves in just one direction, the only one whose
 * capture differs from its move, the only one with a special first move, and
 * the only one that turns into something else. It is worth noticing that all of
 * that awkwardness is contained in this one file. No other class in the engine
 * knows that pawns are strange. That containment is the payoff of polymorphism:
 * the irregular case costs one class, not a special case in every method that
 * touches a piece.
 *
 * <p>En passant is not handled here. Like castling, it depends on the previous
 * move rather than on the current board, so it waits for Week 15 when
 * {@code Game} owns the move history.
 */
public class Pawn extends Piece {

    /**
     * What a pawn may become on reaching the far rank.
     */
    private static final PieceType[] PROMOTION_CHOICES = { PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT };

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public List<Move> pseudoLegalMoves(Board board, Position from) {
        /*
            firstly, initialize an array list that returns all the moves a pawn can
            make from its current position
         */
        List<Move> moves = new ArrayList<>();
        int forward = color() == Color.WHITE ? 1 : -1;
        int startRank = color() == Color.WHITE ? 1 : 6;
        int promotionRank = color() == Color.WHITE ? 7 : 0;

        /*
            pawns have the option to either move one or two squares forward
            at the very start of a chess game when they're at their starting
            position, first check to see if the empty space exists in front
            of the pawn so it can move forward
         */
        Position oneStep = from.offsetOrNull(0, forward);
        if(oneStep != null && board.pieceAt(oneStep) == null){
            addArrivals(moves, from, oneStep, null, promotionRank);

            if(from.rank() == startRank){
                Position twoForward = from.offsetOrNull(0, 2 * forward);
                if(twoForward != null && board.pieceAt(twoForward) == null){
                    moves.add(Move.quiet(from,twoForward, this));
                }
            }
        }

        /*
            pawns can attack diagonally, -1 and 1 are the diagonal spaces on the
            board. the for loop checks to see if there are any pieces diagonal to
            the pawn that the pawn can attack. if the pawn can attack diagonally, it
            takes over the last piece that was there. unless it was null, it can only
            move forward
         */
        for(int fileDelta : new int[] {-1, 1}){
            Position diagonal = from.offsetOrNull(fileDelta, forward);
            if(diagonal == null){
                continue;
            }
            Piece occupant = board.pieceAt(diagonal);
            if(occupant != null && occupant.color() != color()){
                addArrivals(moves, from, diagonal, occupant, promotionRank);
            }
        }

        return moves;
    }

    /**
     * A pawn attacks the two squares diagonally ahead of it, whether or not
     * anything stands there.
     *
     * <p>This override exists because the inherited version answers "can this
     * piece move to that square", and for a pawn that is the wrong question.
     * An empty square in front of a pawn is a square the pawn can move to but
     * does <em>not</em> attack — which matters enormously for king safety: a
     * king may not be blocked from a square merely because a pawn could advance
     * onto it, but it certainly may not step onto a square a pawn guards.
     */
    @Override
    public boolean attacks(Board board, Position from, Position target) {
        int forward = color() == Color.WHITE ? 1 : -1;
        return Objects.equals(target, from.offsetOrNull(-1, forward))
                || Objects.equals(target, from.offsetOrNull(1, forward));
    }

    /*
        this is a helper method to add moves, based on whether the move reaches the
        promotion rank is a regular move or an attack. when a pawn reaches the end of
        the board it has the option to become either a queen, rook, bishop or knight
     */
    private void addArrivals(List<Move> moves, Position from, Position to, Piece captured, int lastRank){
         if(to.rank() == lastRank){
             for(PieceType promotesTo : PROMOTION_CHOICES){
                 moves.add(Move.promotion(from, to, this, captured, promotesTo));
             }
         }else if (captured == null){
             moves.add(Move.quiet(from, to, this));
         }else{
             moves.add(Move.capture(from, to, this, captured));
         }
    }
}
