package edu.sfsu.csc413.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Piece[][] squares;

    // initialize an empty board as a 2D array using the board size as the dimensions
    public Board(){
        squares = new Piece[Position.BOARD_SIZE][Position.BOARD_SIZE];
    }

    /*
    return the position of a piece on the board, the first array in the 2d array is the
    file, representing the vertical spaces on the board lettered from A through H.
    the second array is the rank, representing the horizontal rows, numbered from 1 through 8.
    */
    public Piece pieceAt(Position position){
        return squares[position.file()][position.rank()];
    }

    // if there is no piece at a specific location on the board, return null
    public boolean isEmpty(Position position){
        return pieceAt(position) == null;
    }

    // when a piece moves it goes to a different file & rank on the board
    public void place(Position position, Piece piece){
        squares[position.file()][position.rank()] = piece;
    }

    /*
    every single piece on the board has a color its assigned to, the point of this
    for loop is to identify which squares are holding either white chess pieces, or black
    chess pieces
     */
    public List<Position> positionsOf(Color color){
        List<Position> found = new ArrayList<>();
        for(int file = 0; file <Position.BOARD_SIZE; file++){
            for(int rank = 0; rank < Position.BOARD_SIZE; rank++){
                Piece piece = squares[file][rank];
                if(piece != null && piece.color() == color){
                    found.add(new Position(file, rank));
                }
            }
        }

        return found;
    }

    /*
    overriding toString using a StringBuilder is more efficient than simply concatenating
    everything together.
     */
    @Override
    public String toString(){
        StringBuilder fen = new StringBuilder();

        /*
        the for loop iterates through the rank & the inner one loops through the file to
        get the chess piece location at the current squares in the 2D array
         */
        for(int rank = Position.BOARD_SIZE - 1; rank >= 0; rank --){
            if(rank < Position.BOARD_SIZE - 1){
                fen.append('/');
            }
            int emptySquare = 0;
            for(int file = 0; file < Position.BOARD_SIZE; file++){
                Piece piece = squares[file][rank];
                if(piece == null){
                    emptySquare++;
                }else{
                    if(emptySquare > 0){
                        fen.append(emptySquare);
                        emptySquare = 0;
                    }
                    fen.append(piece.symbol());
                }
            }
            if(emptySquare > 0){
                fen.append(emptySquare);
            }
        }
        return fen.toString();
    }
}
