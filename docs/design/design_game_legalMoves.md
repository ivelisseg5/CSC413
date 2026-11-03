sequenceDiagram 
    autonumber
    actor C as Caller
    participant G as game : Game
    participant MG as MoveGenerator (static)
    participant B as board : Board
    participant P as piece : Piece

    C->>G: legalMoves()
    activate G
    G->>MG: legalMoves(board, sideToMove)
    activate MG
    MG->>MG: Create empty legal list

    MG->>MG: pseudoLegalMoves(board, color)
    MG->>B: positionsOf(color)
    B-->>MG: Piece positions

    loop Each friendly position from
        MG->>B: pieceAt(from)
        B-->>MG: piece
        MG->>P: pseudoLegalMoves(board, from)
        P-->>MG: Piece's candidate moves
        MG->>MG: Add moves to candidate list
    end

    loop Each candidate move
        MG->>B: apply(candidate)
        Note over MG,B: Temporarily change the board

        MG->>MG: isInCheck(board, color)
        MG->>B: kingPosition(color)
        B-->>MG: King position or null

        alt King position is null
            MG->>MG: Check result = false
        else King exists
            MG->>MG: isAttacked(board, king, color.opposite())
            MG->>B: positionsOf(attacker)
            B-->>MG: Opponent positions

            loop Each opponent position, stopping at first attack
                MG->>B: pieceAt(from)
                B-->>MG: Opponent piece
                MG->>P: attacks(board, from, king)
                P-->>MG: true or false  
            end
            
            Note right of MG: Attack result is true if any piece attacks. Or false after checking all pieces"
            MG->>MG: Check result = attack result
        end
        
        MG->>B: undo(candidate)
        Note over MG,B: Restore the board before filtering

        alt Own king is not in check
            MG->>MG: legal.add(candidate)
        else Own king is in check   
            Note right of MG: Exclude candidate
        end
    end

    MG-->>G: legal moves
    deactivate MG
    G-->>C: legal moves
    deactivate G