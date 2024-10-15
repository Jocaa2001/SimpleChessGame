/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import model.pieces.AbstractPiece;
import model.pieces.BlackBishop;
import model.pieces.BlackKing;
import model.pieces.BlackKnight;
import model.pieces.BlackPawn;
import model.pieces.BlackQueen;
import model.pieces.BlackRook;
import model.pieces.Coordinates;
import model.pieces.WhiteBishop;
import model.pieces.WhiteKing;
import model.pieces.WhiteKnight;
import model.pieces.WhitePawn;
import model.pieces.WhiteQueen;
import model.pieces.WhiteRook;
import model.player.Player;
import view.Board;
import view.BoardPanel;
import view.PanelListener;
import view.PromotionForm;

/**
 *
 * @author Win 10
 */
public class Controller {
    public static int brojPoteza = 0;
    public static int Clicked = 0;
    private static Controller instance;
    public static Board board;
    private AbstractPiece promotionPiece;
    private AbstractPiece promotedPawn;
    private PromotionForm pf;
    private Player player1;
    private Player player2;
    //ArrayList<Coordinates> findPossibleMoves;
   static AbstractPiece temp;

    

    
    private Controller() {
        player1 = new Player(true,true);//player 1 je uvek beli igrac.Igra prvi
        player2 = new Player(false,false);//player 2 je uvek crni igrac. Igra drugi
    }

    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void makeMove(AbstractPiece p, int endingX, int endingY) {
        
       p.move(p, endingX, endingY);
    }

    public void setStartingPosition() {
        Clicked = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                board.getBoardGrid()[i][j].removeAll();
                board.getBoardGrid()[i][j].setPiece(null);
            }
        }
     
        //postavljanje pijuna
        for (int i = 0; i < Board.column; i++) {
            board.getBoardGrid()[1][i].add(new BlackPawn());
        }
         for (int i = 0; i < Board.column; i++) {
            board.getBoardGrid()[6][i].add(new WhitePawn());
        }
        //topovi
         board.getBoardGrid()[0][0].add(new BlackRook());
         board.getBoardGrid()[0][7].add(new BlackRook());
         board.getBoardGrid()[7][7].add(new WhiteRook());
         board.getBoardGrid()[7][0].add(new WhiteRook());
         
         //konji
         board.getBoardGrid()[0][1].add(new BlackKnight());
         board.getBoardGrid()[0][6].add(new BlackKnight());
         board.getBoardGrid()[7][1].add(new WhiteKnight());
         board.getBoardGrid()[7][6].add(new WhiteKnight());
         
         //lovci
         board.getBoardGrid()[0][2].add(new BlackBishop());
         board.getBoardGrid()[0][5].add(new BlackBishop());
         board.getBoardGrid()[7][2].add(new WhiteBishop());
         board.getBoardGrid()[7][5].add(new WhiteBishop());
         
         //kraljice
         board.getBoardGrid()[0][3].add(new BlackQueen());
         board.getBoardGrid()[7][3].add(new WhiteQueen());
         
         board.getBoardGrid()[0][4].add(new BlackKing());
         board.getBoardGrid()[7][4].add(new WhiteKing());
         
         setCoordinates();
         
         board.repaint();
         board.validate();
    }
    
    
    public void findPossibleMoves(AbstractPiece temp) {
        
         temp.findPossibleMoves(temp);
        
    }
    
    public boolean isKingChecked(){
        
        
        return false;
        
    }

    private void setCoordinates() {
        
          for (int i = 0; i < 8; i++) {
             for (int j = 0; j < 8; j++) {
                 if(board.getBoardGrid()[i][j].getPiece() != null){
                     AbstractPiece p = board.getBoardGrid()[i][j].getPiece();  
                     p.setCoordinates(new Coordinates(i, j));
                 }
             }
        }
        
    }

    public void highlightPossibleMoves(AbstractPiece temp) {
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        for (Coordinates coord : temp.getPossibleMoves()) {
           board.getBoardGrid()[coord.getX()][coord.getY()].setOpaque(true);
           board.getBoardGrid()[coord.getX()][coord.getY()].setBackground(new Color(0,255,0,100));
           board.getBoardGrid()[coord.getX()][coord.getY()].setBorder(blackBorder);
           board.repaint();
           board.revalidate();
        }
        
    }

    public void unhighlight() {
        
        Color lightColor = new Color(255, 255, 255);  
        Color darkColor = new Color(139, 69, 19);     
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardPanel square = board.getBoardGrid()[row][col];
                
               
                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }
                
               
                
            }
         }
        board.repaint();
        board.revalidate();
    }
    
    public void openPromotionForm(AbstractPiece promotedPawn) {
        this.promotedPawn = promotedPawn.isIsblack() ? (BlackPawn) promotedPawn:(WhitePawn) promotedPawn;
        pf = new PromotionForm(promotedPawn.isIsblack());
        pf.setVisible(true);
    }

    public void setPromotionPiece(AbstractPiece piece) {
        promotionPiece = piece;
        board.getBoardGrid()[promotedPawn.getCoordinates().getX()][promotedPawn.getCoordinates().getY()].add(piece);
        board.repaint();
        board.revalidate();
        pf.dispose();
    }

    public AbstractPiece getPromotionPiece() {
        return promotionPiece;
    }

    public AbstractPiece getPromotedPawn() {
        return promotedPawn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

   

    public void move(BoardPanel square) {
        int endingX;
      int endingY;
       
        
      
      
        switch (controller.Controller.Clicked) {
            case 0:
                
                temp = square.getPiece();
                
                if(temp == null){
                    System.out.println("nema figure na ovom polju");
                    return;
                }
                
                
                temp.setCoordinates(new Coordinates(square.getCoordX(),square.getCoordY()));
               
                controller.Controller.getInstance().findPossibleMoves(temp);
                controller.Controller.getInstance().highlightPossibleMoves(temp);
                controller.Controller.Clicked = 1;
                break;
            case 1:
                
                endingX = square.getCoordX();
                endingY = square.getCoordY();
                controller.Controller.Clicked = 0;
                
              
                controller.Controller.getInstance().makeMove(temp, endingX,endingY);
               controller.Controller.getInstance().unhighlight();
               
               Player p1 = controller.Controller.getInstance().getPlayer1();
            Player p2 = controller.Controller.getInstance().getPlayer2();
            
          
            break;
               
              
            default:
                throw new AssertionError();
        }
       
       endingX = -1;
       endingY = -1;
    }

    public void p1Move(BoardPanel square) {
        
              AbstractPiece p = square.getPiece();
        
    }
    
    
   
    
    
}
