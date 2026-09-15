package edu.sfsu.csc413.chess.model;

/**
 * The two sides in a game of chess.
 *
 * <p>An enum rather than a boolean or an int: the compiler now rejects
 * meaningless values, and {@code switch} statements over it can be checked for
 * exhaustiveness.
 *
 * <p>The four method contracts below are fixed — later milestones call them.
 * The bodies are yours to write: that is M0b.
 */
public enum Color {
    WHITE,
    BLACK;

    /** The side whose turn it is after this one moves. */
    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
        // if the white pieces go first, black ones go second, & the turns keep switching
    }

    /**
     * The direction pawns of this color advance, measured in ranks.
     * White moves up the board (+1), black moves down (-1).
     */
    public int pawnDirection() {
        return this == WHITE ? 1 : -1;
        // white pieces go first, they're moving up the chess board
        // black pieces go second, they move down the board
    }

    /** The rank pawns of this color start on (0-based). */
    public int pawnStartRank() {
        return this == WHITE ? 1 : Position.BOARD_SIZE - 2;
        // pawns can move either 1 or 2 spaces when they're at their starting positon on the board
    }

    /** The rank a pawn of this color must reach to promote (0-based). */
    public int promotionRank() {
        return this == WHITE ? Position.BOARD_SIZE - 1: 0;
        /*
        the board is 8 x 8 starting at index 0, so when a white pawn reaches the
        top of the board (index 7) or when a black pawn reaches the bottom (index 0).
        it can be promoted to a different piece (in most instances it's a queen, rook,
        bishop or knight)
        */
    }
}
