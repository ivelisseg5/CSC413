classDiagram
    direction TB

    namespace model{
        class Board{
            -Piece[][] squares
            +Board()
            +pieceAt(Position position) Piece
            +isempty(Position position) boolean
            +place(Position posiiton, Piece piece) void
            +kingPosition(Color color) Position
            +positionsOf(Color color) List~Position~
            +apply(Move move) void
            +undo(Move move) void
            +toString() String
        }

        class Piece{

[//]: # (            <<abstract>>)
            -Color color
            -PieceType type
            +color() color
            +type() PieceType
            +symbol() char
            +pseudoLegalMoves(Board board, Position from) List~Move~*
            +attacks(Board board, Position from, Position target) boolean
            +toString() String
        }

        class Bishop
        class King
        class Knight
        class Pawn
        class Queen
        class Rook

        class Move{

[//]: # (            <<record>>)
            -Position from
            -Position to
            -Piece moved
            -Piece captured
            -PieceType promotesTo
            +Move(Position from, Position to, Piece moved, Piece captured, PieceType promotesTo)
            +from() position
            +to() position
            +moved() Piece
            +captured() Piece
            +promotesTo() PieceType
            +quiet(Position from, Position to, Piece moved) Move$
            +capture(Position from, Position to, Piece moved, Piece captured) Move$
            +promotion(Position from, Position to, Piece moved, Piece captured, PieceType promotesTo) Move$
            +isCapture() boolean
            +isPromotion() boolean
            +toString() String
            +equals(Object other) boolean
            +hashCode() int
        }

        class Position{

[//]: # (            <<record>>)
            +Position(int file, int rank)
            +file() int
            +rank() int
            +isOnBoard(int file, int rank) boolean$
            +parse(String algebraic) Position$
            +offsetOrNull(int fileDelta, int rankDelta) Position
            +toString() String
            +equals(Object other) boolean
            +hashCode() int
        }

        class Color{

[//]: # (            <<enumeration>>)
            WHITE
            BLACK
            +opposite() Color
            +pawnDirection() int
            +pawnStartRank() int
            +promotionRank() int
            +values() Color[]$
            +valuesOf(String name) Color$
        }
        
        class PieceType{
            <<enumeration>>
            PAWN
            KNIGHT
            BISHOP
            ROOK
            QUEEN
            KING
            +symbol() char
            +fromSymbol(char letter) PieceType$
            +values() PieceType[]$
            +valueOf(String name) PieceType$
        }
    }

    Piece <|-- Bishop
    Piece <|-- King
    Piece <|-- Knight
    Piece <|-- Pawn
    Piece <|-- Queen
    Piece <|-- Rook

    Board --> "0..64" Piece : squares
    Piece --> "1" Color : color
    Piece --> "1" PieceType : type

    Move --> "1" Position : from
    Move --> "1" Position : 10
    Move --> "1" Piece : moved
    Move --> "0..1" Piece : captured
    Move --> "0..1" PieceType : promotesTo

    Board ..> Position : addresses squares
    Board ..> Color : filters pieces
    Board ..> PieceType : checks king and promotion type
    Board ..> Move : applies & undo
    Board ..> Queen : creates on pawn promotion
    Board ..> Rook : creates on pawn promotion
    Board ..> Bishop : creates on pawn promotion
    Board ..> Knight : creates on pawn promotion

    Piece ..> Board : queries occupancy
    Piece ..> Position : calculates destinations
    Piece ..> Move : creates & examines
    Color ..> Position : reads BOARD_SIZE

    note for Piece "Each implements pseudoLegalMoves; Pawn also overrides attacks."
    note for Move "Record fields are private final with public accessors. Captured and promotesTo are optional."