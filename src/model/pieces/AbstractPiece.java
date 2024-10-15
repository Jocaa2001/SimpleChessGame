/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public abstract class AbstractPiece extends Component {
    
    //Apstrakna klasa koju ce nasledjivati svaka figura(work in progres ne znam sta ce sve biti apsraktno)
    boolean isblack;
    Coordinates coordinates;
    BufferedImage image;
    ArrayList<Integer> possibleMoves;
    
    
    public abstract ArrayList<Integer> findPossibleMoves(AbstractPiece piece);
   // public abstract boolean
    public abstract boolean move(AbstractPiece piece,int fromX, int toX, int fromY, int toY);
    
    
    
    
}
