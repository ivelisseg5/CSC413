package edu.sfsu.csc413.chess;

import edu.sfsu.csc413.chess.view.BoardView;
import edu.sfsu.csc413.chess.view.ConsoleView;
import edu.sfsu.csc413.chess.view.GameController;
import edu.sfsu.csc413.chess.view.EmojiView;
import edu.sfsu.csc413.chess.engine.Game;

public final class Main {

    public static void main(String[] args) {

        //M6
        Game game = new Game();

        BoardView view = args.length > 0 && args[0].equalsIgnoreCase("emoji")
                ? new EmojiView()
                : new ConsoleView();

        new GameController(game, view).start();

    }

    private Main() {
    }
}
