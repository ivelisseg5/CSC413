classDiagram
    direction TB
    
    namespace engine{
        class Game{
            -Board board
            -List~Move~ history
            -Color sideToMove
            +Game()
            +Game(Board, Color)
            +board() Board
            +sideToMove() Color
            +history() List~Move~
            +legalMoves() List~Move~
            +findLegalMove(String) Optional~Move~
            +play(Move) void
            +undoLastMove() Optional~Move~
        }

        class MoveGenerator{
            -MoveGenerator()
            +legalMoves(Board, Color)$ List~Move~
            ~pseudoLegalMoves(Board, Color)$ List~Move~
            +isInCheck(Board, Color)$ boolean
            +isAttacked(Board, Position, Color)$ boolean
        }
    }

    namespace model{
        class Board
        class Move
        class Color
        class Position
        class Piece
    }

    namespace factory{
        class BoardFactory
    }

    Game "1" --> "1" Board : board
    Game "1" --> "0..*" Move : history
    Game --> "1" Color : sideToMove
    Game ..> MoveGenerator : calls legalMoves
    Game ..> BoardFactory : calls standard

    MoveGenerator ..> Board: queries, applies and undo
    MoveGenerator ..> Piece : asks for moves and attacks
    MoveGenerator ..> Move : generator + filter
    MoveGenerator ..> Position : finds the king
    MoveGenerator ..> Color : side and opponent