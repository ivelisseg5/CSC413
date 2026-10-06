package edu.sfsu.csc413.chess;

import java.util.List;

import edu.sfsu.csc413.chess.factory.BoardFactory;
import edu.sfsu.csc413.chess.model.Board;
import edu.sfsu.csc413.chess.model.Position;
import edu.sfsu.csc413.chess.model.Piece;
import edu.sfsu.csc413.chess.model.PieceType;
import edu.sfsu.csc413.chess.model.Color;
import edu.sfsu.csc413.chess.view.PieceGlyphs;
import edu.sfsu.csc413.chess.view.TextBoardRenderer;
import edu.sfsu.csc413.chess.engine.Game;

public final class Main {

    public static void main(String[] args) {

        //M3
        Game game = new Game();
        TextBoardRenderer renderer = new TextBoardRenderer(PieceGlyphs.LETTERS);
        System.out.println(renderer.render(game.board()));

        for (String notation : List.of("e2e4", "e7e5")) {
            game.play(game.findLegalMove(notation).orElseThrow());
        }
        System.out.println(renderer.render(game.board()));

        game.undoLastMove();
        System.out.println(renderer.render(game.board()));

    }

    private Main() {
    }
}
