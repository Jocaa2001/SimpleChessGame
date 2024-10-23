/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import controller.Controller;
import java.util.ArrayList;
import view.BoardPanel;

/**
 *
 * @author Win 10
 */
public class WhiteKing extends AbstractPiece {

    private int numberOfMoves = 0;
    private boolean checked;
    
    
    public WhiteKing() {
        super("/wk.png");
        setIsblack(false);
    }

    
    
    @Override
    public ArrayList<Coordinates> findPossibleMoves(AbstractPiece piece) {
        ArrayList<Coordinates> pm = new ArrayList<Coordinates>();
        
        int[][] moves = {
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1},
        {0,1}, {1,0}, {-1, 0}, {0, -1}
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
        
        if(checkForShortCastle()){
            pm.add(new Coordinates(7, 6));
        }
        
        if(checkForLongCastle()){
            pm.add(new Coordinates(7,2));
        }
        
        // todo napraviti ilegalna polja za kralja
        
        numberOfMoves++;
        
        //System.out.println();
        possibleMoves = pm;
        possibleMoves.removeAll(findOpponentsPossibleMoves());
        return pm;
    }

    private boolean checkForShortCastle() {
            if(controller.Controller.getInstance().board.getBoardGrid()[7][7].getPiece()!=null){
          WhiteRook wr = (WhiteRook) controller.Controller.getInstance().board.getBoardGrid()[7][7].getPiece();
                System.out.println(numberOfMoves == 0 && wr.getNumberOfMoves() == 0 && !(controller.Controller.getInstance().board.getBoardGrid()[7][6].isOcupied())
                && !(controller.Controller.getInstance().board.getBoardGrid()[7][5].isOcupied()));
        return (numberOfMoves == 0 && wr.getNumberOfMoves() == 0 && !(controller.Controller.getInstance().board.getBoardGrid()[7][6].isOcupied())
                && !(controller.Controller.getInstance().board.getBoardGrid()[7][5].isOcupied()));
            }
            return false;
    }

    private boolean checkForLongCastle() {
        if(controller.Controller.getInstance().board.getBoardGrid()[7][0].getPiece() != null){
         WhiteRook wr = (WhiteRook) controller.Controller.getInstance().board.getBoardGrid()[7][0].getPiece();
        return (wr.getNumberOfMoves() == 0 && this.numberOfMoves == 0 && !(controller.Controller.getInstance().board.getBoardGrid()[7][1].isOcupied())
                && !(controller.Controller.getInstance().board.getBoardGrid()[7][2].isOcupied()) && !(controller.Controller.getInstance().board.getBoardGrid()[7][3].isOcupied()));
        }
        return false;
    }

    
      public void move(AbstractPiece piece, int toX, int toY){
        super.move(piece, toX, toY);
        
        //shortCastle
        if(checkForShortCastle() && toX == 7 && toY == 6){
             WhiteRook wr = (WhiteRook) controller.Controller.getInstance().board.getBoardGrid()[7][7].getPiece();
              controller.Controller.getInstance().board.getBoardGrid()[7][5].add(wr);
            
              controller.Controller.getInstance().board.getBoardGrid()[7][7].removeAll();
              controller.Controller.getInstance().board.getBoardGrid()[7][7].setPiece(null);  
        }
        
        //longCastle
        if(checkForLongCastle() && toX == 7 && toY == 2){
             WhiteRook wr = (WhiteRook) controller.Controller.getInstance().board.getBoardGrid()[7][0].getPiece();
              controller.Controller.getInstance().board.getBoardGrid()[7][3].add(wr);
            
              controller.Controller.getInstance().board.getBoardGrid()[7][0].removeAll();
              controller.Controller.getInstance().board.getBoardGrid()[7][0].setPiece(null);  
        }
        
        numberOfMoves++;
        
        controller.Controller.board.repaint();
        controller.Controller.board.revalidate();
    }
      
    public ArrayList<Coordinates> findOpponentsPossibleMoves(){
         BoardPanel[][] boardGrid = controller.Controller.board.getBoardGrid();
        ArrayList<Coordinates> possibleOpponentMoves = new ArrayList<>();
           
         for (int i = 0; i < 8; i++) {
             for (int j = 0; j < 8; j++) {
                 if(boardGrid[i][j].getPiece() != null && boardGrid[i][j].getPiece().isIsblack() != this.isIsblack()){
                     AbstractPiece p = boardGrid[i][j].getPiece();
                     if(p instanceof WhiteKing || p instanceof BlackKing) {
                    continue; // Preskoči kralja
                }
                     if(p instanceof BlackPawn){
                         possibleOpponentMoves.addAll(((BlackPawn) p).returnThreatSquares());
                         continue;
                     }
                     
                     ArrayList<Coordinates> pos = p.findPossibleMoves(p);
                     possibleOpponentMoves.addAll(pos);
                     
                     //System.out.println("koordinate " + i + j + " Coordinates " + p.getCoordinates().getX() + p.getCoordinates().getY());
                     

                 }
             }
        }
         
        return possibleOpponentMoves;
    }

    public boolean isChecked() {
        return checked;
    }
    
     public boolean isKingChecked(){
        
        //ides kroz svaku protivnicku figuru i proveravas da li njeni moguci potezi sadrze koordinate crnog kralja.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BoardPanel bp = Controller.board.getBoardGrid()[i][j];
                if(bp.getPiece() instanceof WhiteKing || bp.getPiece() instanceof BlackKing) {
                    continue; // Preskoči kralja
                }
                //System.out.println(bp.getPiece().getPossibleMoves() + "iz klase beli kralj");
                if(bp.getPiece() != null && bp.getPiece().isIsblack() && bp.getPiece().findPossibleMoves(bp.getPiece()).contains(this.coordinates)){
                    checked = true;
                   return true;
            }
        }
        
        
        
    }
        checked = false;
   return false;
}
    
    
}
