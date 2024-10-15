/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import controller.Controller;
import static controller.Controller.board;
import model.pieces.AbstractPiece;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import view.BoardPanel;

/**
 *
 * @author Win 10
 */
public class BlackPawn extends AbstractPiece {

    private int numberOfMoves = 0;
    private int firstMove;
    
    public BlackPawn() {
        super("/bpawn.png");
        setIsblack(true);
    }

    
    

    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
         if(piece.getCoordinates().getX() == 7){
                 
                 return null;
           }
        
            ArrayList<Coordinates> pm = new ArrayList<Coordinates>();
           BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
            
            
           //pravolinijsko kretanje
           if(piece.getCoordinates().getX() == 1){
               
               if(!(BoardGrid[2][piece.getCoordinates().getY()].isOcupied()))
               pm.add(new Coordinates(2, piece.getCoordinates().getY()));
               if(!(BoardGrid[3][piece.getCoordinates().getY()].isOcupied()))
               pm.add(new Coordinates(3, piece.getCoordinates().getY()));
               
           }else if(!(BoardGrid[piece.getCoordinates().getX()+1][piece.getCoordinates().getY()].isOcupied())) {
               pm.add(new Coordinates(piece.getCoordinates().getX()+1, piece.getCoordinates().getY()));
           }
           
           //da li moze da pojede
            if(!(piece.getCoordinates().getY() == 0)){
           BoardPanel targetPanel = BoardGrid[piece.getCoordinates().getX()+1][piece.getCoordinates().getY()-1];
           if(targetPanel.isOcupied() && targetPanel.getPiece().isIsblack() != piece.isIsblack() ){
               pm.add(new Coordinates(piece.getCoordinates().getX()+1, piece.getCoordinates().getY()-1));
           }
            }
            
            if(!(piece.getCoordinates().getY() == 7)){
           BoardPanel targetPanel = BoardGrid[piece.getCoordinates().getX()+1][piece.getCoordinates().getY()+1];
           if(targetPanel.isOcupied() && targetPanel.getPiece().isIsblack() != piece.isIsblack() ){
               pm.add(new Coordinates(piece.getCoordinates().getX()+1, piece.getCoordinates().getY()+1));
           }
            }
            
            //En passant nalevo
           if(enPassantLeft(piece)){
               System.out.println();
           WhitePawn wp = (WhitePawn) BoardGrid[4][piece.getCoordinates().getY()-1].getPiece();
           if(wp.getFirstMove() == Controller.brojPoteza -1 &&  !(BoardGrid[piece.getCoordinates().getX()+1][piece.getCoordinates().getY()-1].isOcupied()))
               pm.add(new Coordinates(piece.getCoordinates().getX()+1, piece.getCoordinates().getY()-1));
           
           }
           
           //En passant nadesno
           if(enPassantRight(piece)){
           System.out.println();
           WhitePawn wp = (WhitePawn) BoardGrid[4][piece.getCoordinates().getY()+1].getPiece();
              
           if(wp.getFirstMove() == Controller.brojPoteza -1 &&  !(BoardGrid[piece.getCoordinates().getX()+1][piece.getCoordinates().getY()+1].isOcupied()))
               pm.add(new Coordinates(piece.getCoordinates().getX()+1, piece.getCoordinates().getY()+1));
           
           }
           
           
            //System.out.println(pm);
            possibleMoves = pm;
            return pm;
    }

    
     public void move(AbstractPiece piece, int toX, int toY){
         numberOfMoves++;
          if(toX == 7){
                 Controller.getInstance().openPromotionForm(this); 
                 controller.Controller.board.getBoardGrid()[coordinates.getX()][coordinates.getY()].removeAll();
                 controller.Controller.board.getBoardGrid()[coordinates.getX()][coordinates.getY()].setPiece(null);
                 this.setCoordinates(new Coordinates(toX, toY));
                 controller.Controller.board.getBoardGrid()[toX][toY].removeAll();
                 controller.Controller.board.getBoardGrid()[toX][toY].setPiece(null);
                 return;
           }
         
         if(numberOfMoves == 1){
            firstMove = Controller.brojPoteza;
        }
         
        BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
        
        //en passant potez
        if(enPassantLeft(piece)){
        WhitePawn wp = (WhitePawn) BoardGrid[4][piece.getCoordinates().getY()-1].getPiece();
        if(wp.getFirstMove() == Controller.brojPoteza -1 && !(controller.Controller.board.getBoardGrid()[toX][toY].isOcupied())){
            
            controller.Controller.board.getBoardGrid()[toX-1][toY].removeAll();
            controller.Controller.board.getBoardGrid()[toX-1][toY].setPiece(null);  
        }
        }
        
        if(enPassantRight(piece)){
        WhitePawn wp = (WhitePawn) BoardGrid[4][piece.getCoordinates().getY()+1].getPiece();
        if(wp.getFirstMove() == Controller.brojPoteza -1 && !(controller.Controller.board.getBoardGrid()[toX][toY].isOcupied())){
            
            controller.Controller.board.getBoardGrid()[toX-1][toY].removeAll();
            controller.Controller.board.getBoardGrid()[toX-1][toY].setPiece(null);  
        }
        }
        
        
        
         super.move(piece, toX, toY);
        
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int getFirstMove() {
        return firstMove;
    }

    private boolean enPassantLeft(AbstractPiece piece) {
          BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
        
        if(piece.getCoordinates().getY() != 0)
        return piece.getCoordinates().getX() == 4 
                   &&BoardGrid[4][piece.getCoordinates().getY()-1].getPiece() != null 
                && BoardGrid[4][piece.getCoordinates().getY()-1].getPiece().getClass().equals(WhitePawn.class);
        return false;
    }

    private boolean enPassantRight(AbstractPiece piece) {
          BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
        if(piece.getCoordinates().getY() != 7)
        return piece.getCoordinates().getX() == 4 &&
                  BoardGrid[4][piece.getCoordinates().getY()+1].getPiece() != null 
                && BoardGrid[4][piece.getCoordinates().getY()+1].getPiece().getClass().equals(WhitePawn.class);
        return false;
    }
   
    
      
}
