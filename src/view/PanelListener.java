/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.pieces.AbstractPiece;
import model.pieces.Coordinates;
import model.player.Player;

/**
 *
 * @author Win 10
 */
public class PanelListener implements MouseListener {
    //Osluskivac akcija nad panelima. Svaki panel ima svoj oslusluskivac
    BoardPanel square;
    //static AbstractPiece temp;
    
    public PanelListener() {  
    }

    public PanelListener(BoardPanel square) {
        this.square = square;
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
      //System.out.println("clicked" + square.getCoordX() + " " + square.getCoordY());
      Player p1 =controller.Controller.getInstance().getPlayer1();
      Player p2 =controller.Controller.getInstance().getPlayer2();
      AbstractPiece p = square.getPiece();
      
        if(p!= null && p1.isTurn() && p.isIsblack() != p1.isWhite()){
            System.out.println("potez igraca 1");
            move();
            return;
        }
        
        if(p1.isTurn() && Controller.Clicked == 1){
            move();
            p1.setTurn(false);
            p2.setTurn(true);
            return;
        }
        
        if(p!=null && p2.isTurn() && p.isIsblack() != p2.isWhite()){
            System.out.println("potez igraca 2");
            move();
            return;
        }
        
        if(p2.isTurn() && Controller.Clicked == 1){
            move();
            p1.setTurn(true);
            p2.setTurn(false);
            return;
        }
             controller.Controller.getInstance().p1Move(square);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("pressed" + square.getCoordX() + " " + square.getCoordY());
        
     
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        //System.out.println("released" + square.getCoordX() + " " + square.getCoordY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println(square.isOcupied());
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // System.out.println("exited");
    }

    public BoardPanel getSquare() {
        return square;
    }

    public void setSquare(BoardPanel square) {
        this.square = square;
    }

    private void move() {
        controller.Controller.getInstance().move(square);
    }
    
}
