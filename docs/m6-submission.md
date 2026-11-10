Ivelisse Garcia </br>
CSC-413-01 </br>
11/09/2026

1. Checkmate is detected in the MoveGenerator.status(), it answers the first question</br>in session 16 because it's a rule. It checks to see if there are any legal moves left.</br> Meanwhile, GameController.announce() communicates with the view to show the </br> players the message after performing those checks behind the scenes. Therefore, </br> it answers question the third question. </br>
</br>
2. Since we're currently running our program through the Main.java file, only main imports </br>all the files in the view folder. The views mostly display all the changes happening, the </br> backend functions don't need to import anything from the view folders. Besides, the model </br> files are the ones doing the heavy lifting. For example ConsoleView calls game.board(), </br> game.sideToMove(), and game.status() but never game.play(). </br>
</br>
3. I personally don't believe that GameController should change because if we want to swing the view </br> over then we're only changing the view. I'm thinking maybe we can call a helper function in BoardView </br> to display all the legal moves by highlighting squares on the board. Not too sure yet honestly but I hope </br> my thought process is on the right track.