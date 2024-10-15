/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import controller.Controller;
import static controller.Controller.board;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import view.BoardPanel;

/**
 *
 * @author Win 10
 */
public abstract class AbstractPiece extends JComponent {
    
    //Apstrakna klasa koju ce nasledjivati svaka figura(work in progres ne znam sta ce sve biti apsraktno)
    boolean black;
    Coordinates coordinates;
    BufferedImage img;
    ArrayList<Coordinates> possibleMoves;
    
    public AbstractPiece(String path){
       
           try {
               img = ImageIO.read(getClass().getResource(path));
               
                       } catch (IOException ex) {
               Logger.getLogger(AbstractPiece.class.getName()).log(Level.SEVERE, null, ex);
           }
           //setPreferredSize(new Dimension(100,100));
          
          setAlignmentX(CENTER_ALIGNMENT);
          setAlignmentX(TOP_ALIGNMENT);
          
    }
    
    
    public abstract ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece);
    
    public boolean isMovePossible(Coordinates coord){
        return possibleMoves.contains(coord);        
    }
    
    public void move(AbstractPiece piece, int toX, int toY){
        Controller.brojPoteza++;
       
        BoardPanel[][] boardGrid = board.getBoardGrid();

        
        
        if(isMovePossible(new Coordinates(toX,toY))){
            boardGrid[piece.getCoordinates().getX()][piece.getCoordinates().getY()].removeAll(); 
            boardGrid[piece.getCoordinates().getX()][piece.getCoordinates().getY()].setPiece(null);
            if(boardGrid[toX][toY].isOcupied())
                boardGrid[toX][toY].removeAll();
            boardGrid[toX][toY].add(piece);
        }else{
            System.out.println("potez nije moguc");
            Controller.brojPoteza--;
           
        }
        
       
      piece.setCoordinates(new Coordinates(toX,toY));
        board.repaint();
        board.validate();
        
    }

    public boolean isIsblack() {
        return black;
    }

    public void setIsblack(boolean isblack) {
        this.black = isblack;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void paint(Graphics g) {
       g.drawImage(img, 20,20,50,50,this);
    }  

    public ArrayList<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }
    
}
