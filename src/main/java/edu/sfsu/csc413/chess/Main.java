package edu.sfsu.csc413.chess;

import edu.sfsu.csc413.chess.factory.BoardFactory;
import edu.sfsu.csc413.chess.model.Board;
import edu.sfsu.csc413.chess.model.Position;
import edu.sfsu.csc413.chess.model.Piece;
import edu.sfsu.csc413.chess.model.PieceType;
import edu.sfsu.csc413.chess.model.Color;
import edu.sfsu.csc413.chess.view.PieceGlyphs;
import edu.sfsu.csc413.chess.view.TextBoardRenderer;

public final class Main {

    public static void main(String[] args) {

        //M2
        Board board = BoardFactory.standard();
        System.out.println(new TextBoardRenderer(PieceGlyphs.LETTERS).render(board));

    }

    private Main() {
    }
}
