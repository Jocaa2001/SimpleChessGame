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
    static AbstractPiece temp;
   private boolean checked = false;
    

    
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
        //System.out.println(temp.getPossibleMoves() + "iz makeMOve");
       temp.move(temp, endingX, endingY);
    }

    public void setStartingPosition() {
        player1.setTurn(true);
        player2.setTurn(false);
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
         unhighlight();
         board.repaint();
         board.validate();
    }
    
    
    public void findPossibleMoves(AbstractPiece temp) {
        
         temp.findPossibleMoves(temp);
        
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

    public void handleClick(BoardPanel square) {
        
        AbstractPiece p = square.getPiece();
        //ArrayList<Coordinates> CheckedPossibleMoves = new ArrayList<Coordinates>();
        //if(p != null) temp = p;
       //System.out.println(Clicked + "clicked");
        
        boolean check = checkForCheck();
        
        if(check) checked = true;
        
        if(Clicked == 0 && check){
            System.out.println("uslo");
            handleCheckedMoves();
            highlightPossibleMoves(p);
            temp = p;
            Clicked = 1;
            return;
        }
        
        
      
        if(Clicked == 1 && checked){
            
            move(square);
            Clicked = 0;
            checked = false;
            swapTurns();
            System.out.println(checkForCheck() + "da li je i dalje u sahu");
            return;
        }
        

        if(p!= null && player1.isTurn() && p.isIsblack() != player1.isWhite() && Clicked == 0){
            //System.out.println("na " + Clicked + "klik je uslo u prvi");
            handlePieceSelection(square);
            
            return;
        }
        
         else if(player1.isTurn() && Clicked == 1){
            //System.out.println("na " + Clicked + "klik je uslo u drugi");
            move(square);
            swapTurns();
            return;
        }
        
         else if(p!=null && player2.isTurn() && p.isIsblack() != player2.isWhite() && Clicked == 0){
             
              System.out.println(temp);
            handlePieceSelection(square);
           
            
            return;
        }
        
         else if(player2.isTurn() && Clicked == 1){
            
            System.out.println(temp);
            move(square);
            swapTurns();
            return;
        }
        
        
        board.repaint();
        
    }

    private void handlePieceSelection(BoardPanel square) {
        
               
                unhighlight();
                temp = square.getPiece();
                temp.setCoordinates(new Coordinates(square.getCoordX(),square.getCoordY()));                
                findPossibleMoves(temp);
                
                if(!(temp instanceof BlackKing) && !(temp instanceof WhiteKing) && isPinned(temp)){
                    
                    pinnedPieceUpdateMoves();
                  
                   
                 
                  
                   
                }
                
                highlightPossibleMoves(temp);
                //System.out.println(temp.getPossibleMoves() + "iz handlepieceselection");
                Clicked = 1;
        
    }

   public void move(BoardPanel square) {
       
        int endingX;
        int endingY;
        endingX = square.getCoordX();
        endingY = square.getCoordY();
        //System.out.println(temp.getPossibleMoves() + "iz prvog moovea");
        makeMove(temp,endingX,endingY);
        unhighlight();
        Clicked = 0;
        board.repaint();
        board.revalidate();
      
    }

    private void checkGameState(ArrayList<Coordinates> CheckedPossibleMoves) {
        if(CheckedPossibleMoves.isEmpty()){
            System.out.println("gameOver sah mat");
        }
        //da li je sah mat
        //da li je pat
    }
    
    private AbstractPiece returnKingsPosition(){//trazi poziciju trenutnog igraca na tabli
        
        Player p = player1.isTurn() ? player1:player2; //
        
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                BoardPanel bg = board.getBoardGrid()[i][j];
                if(bg.getPiece() != null && p.isWhite() && bg.getPiece().getClass().equals(WhiteKing.class)){//provera vracanja belog kralja
                    return bg.getPiece();
                }
                
                if(bg.getPiece() != null && !p.isWhite() && bg.getPiece().getClass().equals(BlackKing.class)){//provera vracanja crnog kralja
                    return bg.getPiece();
                }
            }
        }
        return null;
    }
    
    private AbstractPiece returnOppositeKingsPosition(){
        Player p = player1.isTurn() ? player2 : player1; // Uzimamo suprotnog igrača

for (int i = 0; i < Board.row; i++) {
    for (int j = 0; j < Board.column; j++) {
        BoardPanel bg = board.getBoardGrid()[i][j];
        if (bg.getPiece() != null && p.isWhite() && bg.getPiece().getClass().equals(WhiteKing.class)) {
            return bg.getPiece(); // Vraća belog kralja
        }
        if (bg.getPiece() != null && !p.isWhite() && bg.getPiece().getClass().equals(BlackKing.class)) {
            return bg.getPiece(); // Vraća crnog kralja
        }
    }
}
return null;

    }
   
    private boolean checkForCheck(){
        
        AbstractPiece p = returnKingsPosition();
        
        if(player2.isTurn()){
            BlackKing bk = (BlackKing) p;  
            return bk.isKingChecked();
        }
        
        else if(player1.isTurn()){
            WhiteKing wk = (WhiteKing) p;
            return wk.isKingChecked();
        }
        return false;
    }

    private void handleCheckedMoves() {
        //ArrayList<Coordinates> allPossibleMoves = new ArrayList<>();
        Player p = player1.isTurn() ? player1:player2;
        //AbstractPiece king = returnKingsPosition();
       // Coordinates kingsOriginalCoordinates = king.getCoordinates();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                AbstractPiece piece = board.getBoardGrid()[i][j].getPiece();
               
                if(piece != null && piece.isIsblack() == !p.isWhite()){
                     //System.out.println(piece.getClass());
                     ArrayList<Coordinates> possibleMoves = piece.findPossibleMoves(piece);
                      ArrayList<Coordinates> possibleMovesUpdated = new ArrayList<>(); 
                     for (Coordinates move : possibleMoves) {
                         
//                         if(piece instanceof BlackKing || piece instanceof WhiteKing){
//                             king.setCoordinates(new Coordinates(move.getX(), move.getY()));
//                         }
                         
                         //pomeri ga privremeno
                         //pravimo temp figuru koja ce da vrati prethodnu figuru na mesto ako je to polje postojalo kao moguci potez
                          AbstractPiece tmp = board.getBoardGrid()[move.getX()][move.getY()].getPiece();
                         board.getBoardGrid()[i][j].setPiece(null);
                         board.getBoardGrid()[i][j].removeAll();
                         board.getBoardGrid()[move.getX()][move.getY()].add(piece);
                        
                         boolean stillInCheck = checkForCheck();
                         
                         //vrati na originalno mesto
                         board.getBoardGrid()[move.getX()][move.getY()].removeAll();
                         board.getBoardGrid()[move.getX()][move.getY()].setPiece(null);
                         board.getBoardGrid()[i][j].add(piece);
                         
                         
                         //ako je na tom polju postojala protivnicka figura vrati je na mesto
                         if(tmp != null && tmp.isIsblack() != piece.isIsblack()){
                             board.getBoardGrid()[move.getX()][move.getY()].add(tmp);
                            
                         }
                         
                        //king.setCoordinates(kingsOriginalCoordinates);

                         board.repaint();
                         
                         if(!stillInCheck ){
                            possibleMovesUpdated.add(move);
                           //allPossibleMoves.add(move);
                         }

                    }
                    piece.setPossibleMoves(possibleMovesUpdated);
                }
            }
        }
        
    }

    public void swapTurns() {
        player1.setTurn(!player1.isTurn());
        player2.setTurn(!player2.isTurn());
    }

    public boolean isPinned(AbstractPiece piece) {
        board.getBoardGrid()[piece.getCoordinates().getX()][piece.getCoordinates().getY()].removeAll();
        board.getBoardGrid()[piece.getCoordinates().getX()][piece.getCoordinates().getY()].setPiece(null);
        boolean check = checkForCheck();
        board.getBoardGrid()[piece.getCoordinates().getX()][piece.getCoordinates().getY()].add(piece);
        return check;
    }
    
    //courtesy of chatgpt
    public AbstractPiece getPinner(AbstractPiece piece) {
    Coordinates piecePos = piece.getCoordinates();
    AbstractPiece king = returnKingsPosition();

    // Pronađi pravac između kralja i figure (vertikalno, horizontalno ili dijagonalno)
    int dx = Integer.signum(king.getCoordinates().getX() - piecePos.getX());
    int dy = Integer.signum(king.getCoordinates().getY() - piecePos.getY());
        //System.out.println(dx + " " + dy);
    System.out.println(piece.getCoordinates());
    int x = piecePos.getX() - dx;
    int y = piecePos.getY() - dy;
    
    // Prolazi kroz polja između figure i kralja 
    while (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
        BoardPanel targetSquare = controller.Controller.board.getBoardGrid()[x][y];
         //System.out.println(x + " " + y);
        if (targetSquare.isOcupied()) {
            
            AbstractPiece targetPiece = targetSquare.getPiece();
           
            
            // Ako je figura suprotnog tima koja napada kralja i to je top, lovac ili kraljica
            
            
            if (targetPiece.isIsblack() != piece.isIsblack() &&
                    (targetPiece instanceof BlackRook || targetPiece instanceof BlackBishop || targetPiece instanceof BlackQueen
                    ||targetPiece instanceof WhiteRook || targetPiece instanceof WhiteBishop || targetPiece instanceof WhiteQueen)) {
                return targetPiece; // Vraća figuru koja pinuje
            }
          //  break; // Ako naiđe na prepreku (druga figura), prekini pretragu
        }
        x -= dx;
        y -= dy;
    }
    return null; // Nema pinovanja
}

    private void pinnedPieceUpdateMoves() {
        AbstractPiece pinner = getPinner(temp);
        Coordinates originalTempCoords = temp.getCoordinates();
        ArrayList<Coordinates> pinnerPossibleMoves = pinner.findPossibleMoves(pinner);
        ArrayList<Coordinates> updatedPossibleMoves = new ArrayList<>();
        
        for (Coordinates coord : temp.getPossibleMoves()) {
            if(pinnerPossibleMoves.contains(coord)){
                //pomeri temp i vidi da li ostaje u sahu
                 AbstractPiece tmp = board.getBoardGrid()[coord.getX()][coord.getY()].getPiece();
                         board.getBoardGrid()[originalTempCoords.getX()][originalTempCoords.getY()].setPiece(null);
                         board.getBoardGrid()[originalTempCoords.getX()][originalTempCoords.getY()].removeAll();
                         board.getBoardGrid()[coord.getX()][coord.getY()].add(temp);
                        
                         boolean stillInCheck = checkForCheck();
                         
                         
                         //vrati na originalno mesto
                         board.getBoardGrid()[coord.getX()][coord.getY()].removeAll();
                         board.getBoardGrid()[coord.getX()][coord.getY()].setPiece(null);
                         board.getBoardGrid()[originalTempCoords.getX()][originalTempCoords.getY()].add(temp);
                         if(tmp != null && tmp.isIsblack() != temp.isIsblack()){
                             updatedPossibleMoves.add(coord);
                            
                         }
                         if(!stillInCheck) updatedPossibleMoves.add(coord);
            }
        }
       
        temp.setPossibleMoves(updatedPossibleMoves);
         //System.out.println(temp.getPossibleMoves());
    }
    
    
}
