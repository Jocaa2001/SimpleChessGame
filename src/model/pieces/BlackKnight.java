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
public class BlackKnight extends AbstractPiece{

    public BlackKnight() {
        super("/bknight.png");
        setIsblack(true);
    }

    
    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
      
        ArrayList<Coordinates> pm = new ArrayList<Coordinates>();
        
        int[][] moves = {
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };
        
        
        for (int[] move : moves) {
            int toX = piece.getCoordinates().getX() + move[0];
            int toY = piece.getCoordinates().getY() + move[1];
            
            // Proveri da li su nove koordinate unutar granica table
        if (toX >= 0 && toX < 8 && toY >= 0 && toY < 8) {
            BoardPanel targetPanel = controller.Controller.getInstance().board.getBoardGrid()[toX][toY];

            // Ako polje nije zauzeto, dodaj ga kao mogući potez
            if (!targetPanel.isOcupied()) {
                pm.add(new Coordinates(toX, toY));
            } 
            // Ako je polje zauzeto protivničkom figurom, dodaj ga kao mogući potez
            else if (targetPanel.getPiece().isIsblack() != piece.isIsblack()) {
                pm.add(new Coordinates(toX, toY));
            }
        }
            
        }
        //System.out.println(pm);
        possibleMoves = pm;
        return pm;
    }

 

    
    
}
