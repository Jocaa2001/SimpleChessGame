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
     
     controller.Controller.getInstance().handleClick(square);
             
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

    
}
