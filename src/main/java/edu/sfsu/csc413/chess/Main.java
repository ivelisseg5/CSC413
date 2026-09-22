package edu.sfsu.csc413.chess;

import edu.sfsu.csc413.chess.model.Board;
import edu.sfsu.csc413.chess.model.Position;
import edu.sfsu.csc413.chess.model.Piece;
import edu.sfsu.csc413.chess.model.PieceType;
import edu.sfsu.csc413.chess.model.Color;
import edu.sfsu.csc413.chess.view.PieceGlyphs;
import edu.sfsu.csc413.chess.view.TextBoardRenderer;

public final class Main {

    // M0 & M0b milestone, idk if i should keep this here
    public static void main(String[] args) {
        System.out.println("CSC 413 Chess — environment OK.");

        /*
        at first i didn't really think the order of how the piece types were
        laid out really mattered, but i kept getting an error that it wasn't
        printing out so i had to order the pieces just like how they'd be laid
        out in an actual chess game
        */
        Board board = new Board();
        PieceType[] backRank = {
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.QUEEN,
                PieceType.KING,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK
        };

        // M1
        for(int file = 0; file < Position.BOARD_SIZE; file++){
            board.place(new Position(file, 0), new Piece(Color.WHITE, backRank[file]));
            board.place(new Position(file, 1), new Piece(Color.WHITE, PieceType.PAWN));
            board.place(new Position(file, 6), new Piece(Color.BLACK, backRank[file]));
            board.place(new Position(file, 7), new Piece(Color.BLACK, PieceType.PAWN));
        }

        System.out.println(new TextBoardRenderer(PieceGlyphs.LETTERS).render(board));

    }

    private Main() {
    }
}
