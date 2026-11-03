package edu.sfsu.csc413.chess;

import java.util.List;

import edu.sfsu.csc413.chess.engine.MoveGenerator;
import edu.sfsu.csc413.chess.factory.BoardFactory;
import edu.sfsu.csc413.chess.model.Board;
import edu.sfsu.csc413.chess.model.Position;
import edu.sfsu.csc413.chess.model.Piece;
import edu.sfsu.csc413.chess.model.PieceType;
import edu.sfsu.csc413.chess.model.Color;
import edu.sfsu.csc413.chess.view.PieceGlyphs;
import edu.sfsu.csc413.chess.view.TextBoardRenderer;
import edu.sfsu.csc413.chess.engine.Game;

import static edu.sfsu.csc413.chess.engine.MoveGenerator.*;

public final class Main {

    public static void main(String[] args) {

        //M3
        Game game = new Game();
        TextBoardRenderer renderer = new TextBoardRenderer(PieceGlyphs.LETTERS);
        System.out.println(renderer.render(game.board()));

        //M5
        for(String notation : List.of("f2f3", "e7e5", "g2g4", "d8h4")){
            game.play(game.findLegalMove(notation).orElseThrow());
        }

        System.out.println(("\nAfter " + game.history() + ":"));
        System.out.println(renderer.render(game.board()));

        System.out.println("\nWhite: " + game.sideToMove());
        System.out.println("Check: " + isInCheck(game.board(), game.sideToMove()));
        System.out.println("Legal Moves: " + game.legalMoves());
        System.out.println("Checkmate: " + (game.legalMoves().isEmpty() && isInCheck(game.board(), game.sideToMove())));
    }

    private Main() {
    }
}
