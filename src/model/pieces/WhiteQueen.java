/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public class WhiteQueen extends AbstractPiece {

    public WhiteQueen() {
        super("/wqueen.png");
        setIsblack(false);
    }

    
    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
         ArrayList<Coordinates> pm = new WhiteBishop().findPossibleMoves(this);
         ArrayList<Coordinates> pm2 = new WhiteRook().findPossibleMoves(this);
         pm.addAll(pm2);
         //System.out.println();
         possibleMoves = pm;
         return pm;
    }

    
   
}
