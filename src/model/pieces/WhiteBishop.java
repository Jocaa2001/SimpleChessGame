/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import java.util.ArrayList;
import view.BoardPanel;

/**
 *
 * @author Win 10
 */
public class WhiteBishop extends AbstractPiece {

    public WhiteBishop() {
        super("/wbishop.png");
        setIsblack(false);
    }

    
    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
         ArrayList<Coordinates> pm = new ArrayList<>();

      for (int i = piece.getCoordinates().getX() + 1, j = piece.getCoordinates().getY() + 1; i <= 7 && i >= 0&& j >= 0 && j <= 7; i++, j++) {
        BoardPanel targetSquare = controller.Controller.board.getBoardGrid()[i][j];
    
        if (targetSquare.isOcupied()) {
         AbstractPiece targetPiece = targetSquare.getPiece();
        
            // Provera da li je figura suprotnog tima
            if (targetPiece.isIsblack() != piece.isIsblack()) {
                pm.add(new Coordinates(i, j));
            }
             break; // Prekini kada naiđeš na prepreku
    }

    pm.add(new Coordinates(i, j)); // Prazan kvadrat
}
      
      
        for (int i = piece.getCoordinates().getX() - 1, j = piece.getCoordinates().getY() + 1;i <= 7 && i >= 0&& j >= 0 && j <= 7; i--, j++) {
    BoardPanel targetSquare = controller.Controller.board.getBoardGrid()[i][j];
    
    if (targetSquare.isOcupied()) {
        AbstractPiece targetPiece = targetSquare.getPiece();
        if (targetPiece.isIsblack() != piece.isIsblack()) {
            pm.add(new Coordinates(i, j));
        }
        break;
    }
    pm.add(new Coordinates(i, j));
}
        
       for (int i = piece.getCoordinates().getX() + 1, j = piece.getCoordinates().getY() - 1; i <= 7 && i >= 0&& j >= 0 && j <= 7; i++, j--) {
    BoardPanel targetSquare = controller.Controller.board.getBoardGrid()[i][j];
    
    if (targetSquare.isOcupied()) {
        AbstractPiece targetPiece = targetSquare.getPiece();
        if (targetPiece.isIsblack() != piece.isIsblack()) {
            pm.add(new Coordinates(i, j));
        }
        break;
    }
    pm.add(new Coordinates(i, j));
}
        
       for (int i = piece.getCoordinates().getX() - 1, j = piece.getCoordinates().getY() - 1; i <= 7 && i >= 0&& j >= 0 && j <= 7; i--, j--) {
    BoardPanel targetSquare = controller.Controller.board.getBoardGrid()[i][j];
    
    if (targetSquare.isOcupied()) {
        AbstractPiece targetPiece = targetSquare.getPiece();
        if (targetPiece.isIsblack() != piece.isIsblack()) {
            pm.add(new Coordinates(i, j));
        }
        break;
    }
    pm.add(new Coordinates(i, j));
}
       possibleMoves = pm;
       
        
        return pm;
        
    }

    

   
}
