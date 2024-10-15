/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import controller.Controller;
import java.awt.Graphics;
import model.pieces.AbstractPiece;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.BoardPanel;

/**
 *
 * @author Win 10
 */
public class WhitePawn extends AbstractPiece {

    private int numberOfMoves = 0;
    private int firstMove;
    
    public WhitePawn() {
        super("/WPawn.png");
        setIsblack(false);
        
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public int getFirstMove() {
        return firstMove;
    }

    public void setFirstMove(int firstMove) {
        this.firstMove = firstMove;
    }
    
    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
        
             if(piece.getCoordinates().getX() == 0){
                 //System.out.println("kraj");
                 //Controller.openPromotionForm();
                 return new ArrayList<>();
           }
        
            ArrayList<Coordinates> pm = new ArrayList<Coordinates>();
           BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
            
            
           //pravolinijsko kretanje
           if(piece.getCoordinates().getX() == 6){
               
               if(!(BoardGrid[5][piece.getCoordinates().getY()].isOcupied()))
               pm.add(new Coordinates(5, piece.getCoordinates().getY()));
               if(!(BoardGrid[4][piece.getCoordinates().getY()].isOcupied()))
               pm.add(new Coordinates(4, piece.getCoordinates().getY()));
               
           }else if(!(BoardGrid[piece.getCoordinates().getX()-1][piece.getCoordinates().getY()].isOcupied())) {
               pm.add(new Coordinates(piece.getCoordinates().getX()-1, piece.getCoordinates().getY()));
           }
           
           //da li moze da pojede
           
           //nalevo
           if(!(piece.getCoordinates().getY() == 0)){
               
           BoardPanel targetPanel = BoardGrid[piece.getCoordinates().getX()-1][piece.getCoordinates().getY()-1];
          
           if(targetPanel.isOcupied() && targetPanel.getPiece().isIsblack() != piece.isIsblack() ){
                
               pm.add(new Coordinates(piece.getCoordinates().getX()-1, piece.getCoordinates().getY()-1));
            }
           }

           //nadesno
           if( !(piece.getCoordinates().getY() == 7) ){
               
           BoardPanel targetPanel = BoardGrid[piece.getCoordinates().getX()-1][piece.getCoordinates().getY()+1];
           
           if(targetPanel.isOcupied() && targetPanel.getPiece().isIsblack() != piece.isIsblack() ){
                
               pm.add(new Coordinates(piece.getCoordinates().getX()-1, piece.getCoordinates().getY()+1));
            }
           }
          
            
           
           
           //En passant nalevo
           if(enPassantLeft(piece)){
               //System.out.println("En Passant nalevo");   
           BlackPawn bp = (BlackPawn) BoardGrid[3][piece.getCoordinates().getY()-1].getPiece();
           if(bp.getFirstMove() == Controller.brojPoteza -1 &&  !(BoardGrid[piece.getCoordinates().getX()-1][piece.getCoordinates().getY()-1].isOcupied()))
               //System.out.println();
               pm.add(new Coordinates(piece.getCoordinates().getX()-1, piece.getCoordinates().getY()-1));
           
           }
           
           //En passant nadesno
           if(enPassantRight(piece)){
                //System.out.println("En Passant nadesno");
           BlackPawn bp = (BlackPawn) BoardGrid[3][piece.getCoordinates().getY()+1].getPiece();
              
           if(bp.getFirstMove() == Controller.brojPoteza -1 &&  !(BoardGrid[piece.getCoordinates().getX()-1][piece.getCoordinates().getY()+1].isOcupied()))
               //System.out.println();
               pm.add(new Coordinates(piece.getCoordinates().getX()-1, piece.getCoordinates().getY()+1));
           
           }

            //System.out.println();
            possibleMoves = pm;
            return pm;
    }

    
    public void move(AbstractPiece piece, int toX, int toY){
        numberOfMoves++;
        
        if(toX == 0){
                 
                 Controller.getInstance().openPromotionForm(this);
                 
                 controller.Controller.board.getBoardGrid()[coordinates.getX()][coordinates.getY()].removeAll();
                 controller.Controller.board.getBoardGrid()[coordinates.getX()][coordinates.getY()].setPiece(null);
                 this.setCoordinates(new Coordinates(toX, toY));
                 controller.Controller.board.getBoardGrid()[toX][toY].removeAll();
                 controller.Controller.board.getBoardGrid()[toX][toY].setPiece(null);
                 return;
                 
                 
                // return new ArrayList<>();
           }
        
        if(numberOfMoves == 1){
            firstMove = Controller.brojPoteza;
           
        }
        BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
        
        //en passant potez
        if(enPassantLeft(piece)){
        BlackPawn bp = (BlackPawn) BoardGrid[3][piece.getCoordinates().getY()-1].getPiece();
            
        if(bp.getFirstMove() == Controller.brojPoteza -1 && !(controller.Controller.board.getBoardGrid()[toX][toY].isOcupied())){
            
            controller.Controller.board.getBoardGrid()[toX+1][toY].removeAll();
            controller.Controller.board.getBoardGrid()[toX+1][toY].setPiece(null);  
        }
        }
        
        if(enPassantRight(piece)){
        BlackPawn bp = (BlackPawn) BoardGrid[3][piece.getCoordinates().getY()+1].getPiece();
            
            
            
        if(bp.getFirstMove() == Controller.brojPoteza -1 && !(controller.Controller.board.getBoardGrid()[toX][toY].isOcupied())){
            
            controller.Controller.board.getBoardGrid()[toX+1][toY].removeAll();
            controller.Controller.board.getBoardGrid()[toX+1][toY].setPiece(null);  
        }
        }
        
        
        
        super.move(piece, toX, toY);
         
         
    }

    private boolean enPassantLeft(AbstractPiece piece) {
         BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
        
        if(piece.getCoordinates().getY() != 0)
        return piece.getCoordinates().getX() == 3 
                   &&BoardGrid[3][piece.getCoordinates().getY()-1].getPiece() != null 
                && BoardGrid[3][piece.getCoordinates().getY()-1].getPiece().getClass().equals(BlackPawn.class);
        return false;
    }

    private boolean enPassantRight(AbstractPiece piece) {
        BoardPanel [][] BoardGrid = controller.Controller.getInstance().board.getBoardGrid();
        if(piece.getCoordinates().getY() != 7)
        return piece.getCoordinates().getX() == 3 &&
                  BoardGrid[3][piece.getCoordinates().getY()+1].getPiece() != null 
                && BoardGrid[3][piece.getCoordinates().getY()+1].getPiece().getClass().equals(BlackPawn.class);
         return false;
    }
   
    
    
    
}
