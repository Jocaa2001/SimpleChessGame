/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pieces;

import controller.Controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.shape.StrokeLineCap;
import view.BoardPanel;
import java.util.stream.Collectors;
/**
 *
 * @author Win 10
 */
public class BlackKing extends AbstractPiece {

    private int numberOfMoves = 0;
    private boolean checked;
    //private ArrayList<Coordinates> opponentsPossibleMoves = new ArrayList<Coordinates>();
    
    public BlackKing() {
        super("/bking.png");
        setIsblack(true);
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
            pm.add(new Coordinates(0, 6));
        }
        //System.out.println(checkForLongCastle());
        if(checkForLongCastle()){
            pm.add(new Coordinates(0,2));
        }
        
        // todo napraviti ilegalna polja za kralja
       
       
        
        
        possibleMoves = pm;
        possibleMoves.removeAll(findOpponentsPossibleMoves());
       // System.out.println(findOpponentsPossibleMoves());
        
       
       
        //System.out.println("lista mogucih poteza kralja" + list);
        //System.out.println("Possible king moves" + possibleMoves);
        return possibleMoves;
    }

    private boolean checkForShortCastle() {
        BlackRook br = (BlackRook) controller.Controller.getInstance().board.getBoardGrid()[0][7].getPiece();
        
        return (br.getNumberOfMoves() == 0 && this.numberOfMoves == 0 && !(controller.Controller.getInstance().board.getBoardGrid()[0][6].isOcupied())
                && !(controller.Controller.getInstance().board.getBoardGrid()[0][5].isOcupied()));
        
    }

    
    @Override
    public void move(AbstractPiece piece, int toX, int toY){
        
        super.move(piece, toX, toY);
        
        //shortCastle
        if(toX == 0 && toY == 6){
             BlackRook br = (BlackRook) Controller.board.getBoardGrid()[0][7].getPiece();
              Controller.board.getBoardGrid()[0][5].add(br);
            
              Controller.board.getBoardGrid()[0][7].removeAll();
              Controller.board.getBoardGrid()[0][7].setPiece(null);  
        }
        
        //longCastle
        if(toX == 0 && toY == 2){
             BlackRook br = (BlackRook) Controller.board.getBoardGrid()[0][7].getPiece();
              Controller.board.getBoardGrid()[0][3].add(br);
            
              Controller.board.getBoardGrid()[0][0].removeAll();
              Controller.board.getBoardGrid()[0][0].setPiece(null);  
        }
        
        numberOfMoves++;
       //System.out.println("\nmoguci potezi protivnika " + findOpponentsPossibleMoves().size());
        
        
        controller.Controller.board.repaint();
        controller.Controller.board.revalidate();
        
    }

    private boolean checkForLongCastle() {
        if(Controller.board.getBoardGrid()[0][0].getPiece() != null){
        BlackRook br = (BlackRook) Controller.board.getBoardGrid()[0][0].getPiece();
        return (br.getNumberOfMoves() == 0 && this.numberOfMoves == 0 && !(Controller.board.getBoardGrid()[0][1].isOcupied())
                && !(Controller.board.getBoardGrid()[0][2].isOcupied()) && !(controller.Controller.getInstance().board.getBoardGrid()[0][3].isOcupied()));
        }
        return false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
   
    public ArrayList<Coordinates> findOpponentsPossibleMoves(){
        
         //pravi se lista svih mogucih poteza belog igraca
        //ako trenutne koordinate postoje u toj listi vraca se true jer je kralj u sahu
        //prolazi se kroz ceo board i proveravaju se sve bele figure
        
        
        BoardPanel[][] boardGrid = controller.Controller.board.getBoardGrid();
        ArrayList<Coordinates> possibleOpponentMoves = new ArrayList<>();
           
         for (int i = 0; i < 8; i++) {
             for (int j = 0; j < 8; j++) {
                 if(boardGrid[i][j].getPiece() != null && boardGrid[i][j].getPiece().isIsblack() != this.isIsblack()){
                     AbstractPiece p = boardGrid[i][j].getPiece();
                     if(p instanceof WhiteKing || p instanceof BlackKing) {
                    continue; // Preskoči kralja
                }
                     ArrayList<Coordinates> pos = p.findPossibleMoves(p);
                     possibleOpponentMoves.addAll(pos);
                     
                     //System.out.println("koordinate " + i + j + " Coordinates " + p.getCoordinates().getX() + p.getCoordinates().getY());
                     

                 }
             }
        }
         
        return possibleOpponentMoves;
         
    }

    
   
}
