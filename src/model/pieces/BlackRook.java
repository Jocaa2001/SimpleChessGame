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
public class BlackRook extends AbstractPiece{

    private int numberOfMoves = 0;
    
    public BlackRook() {
        super("/brook.png");
        setIsblack(true);
    }

    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
           
        
        ArrayList<Coordinates> pm = new ArrayList<Coordinates>();
        
        for (int i = piece.getCoordinates().getY()+1; i < 8; i++) {
            BoardPanel targetPanel = controller.Controller.getInstance().board.getBoardGrid()[piece.getCoordinates().getX()][i];
           
            if (targetPanel.isOcupied()) {
         AbstractPiece targetPiece = targetPanel.getPiece();
        
            // Provera da li je figura suprotnog tima
            if (targetPiece.isIsblack() != piece.isIsblack()) {
                pm.add(new Coordinates(piece.getCoordinates().getX(), i));
            }
             break; // Prekini kada naiđeš na prepreku
    }
            pm.add(new Coordinates(piece.getCoordinates().getX(), i));
        }
        
         for (int i = piece.getCoordinates().getY()-1; i >= 0; i--) {
            BoardPanel targetPanel = controller.Controller.getInstance().board.getBoardGrid()[piece.getCoordinates().getX()][i];
           
            if (targetPanel.isOcupied()) {
         AbstractPiece targetPiece = targetPanel.getPiece();
        
            // Provera da li je figura suprotnog tima
            if (targetPiece.isIsblack() != piece.isIsblack()) {
                pm.add(new Coordinates(piece.getCoordinates().getX(), i));
            }
             break; // Prekini kada naiđeš na prepreku
    }
            pm.add(new Coordinates(piece.getCoordinates().getX(), i));
        }
         
        for (int i = piece.getCoordinates().getX()-1; i >= 0; i--) {
            BoardPanel targetPanel = controller.Controller.getInstance().board.getBoardGrid()[i][piece.getCoordinates().getY()];
           
            if (targetPanel.isOcupied()) {
         AbstractPiece targetPiece = targetPanel.getPiece();
        
            // Provera da li je figura suprotnog tima
            if (targetPiece.isIsblack() != piece.isIsblack()) {
                pm.add(new Coordinates(i, piece.getCoordinates().getY()));
            }
             break; 
    }
            pm.add(new Coordinates(i, piece.getCoordinates().getY()));
        } 
        
        for (int i = piece.getCoordinates().getX()+1; i < 8; i++) {
           BoardPanel targetPanel = controller.Controller.getInstance().board.getBoardGrid()[i][piece.getCoordinates().getY()];
    
    if (targetPanel.isOcupied()) {
        AbstractPiece targetPiece = targetPanel.getPiece();
        
        // Provera da li je figura suprotnog tima
        if (targetPiece.isIsblack() != piece.isIsblack()) {
            pm.add(new Coordinates(i, piece.getCoordinates().getY()));
        }
        break; // Prekini kada naiđeš na prepreku
    }
    pm.add(new Coordinates(i, piece.getCoordinates().getY()));
        } 
        
         
        //System.out.println(pm);
        possibleMoves = pm;
        return pm;
    }

    public void move(AbstractPiece piece, int toX, int toY){
        super.move(piece, toX, toY);
        numberOfMoves++;
        //System.out.println(numberOfMoves);
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
   
    
    
}
