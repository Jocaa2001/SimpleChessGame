/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Win 10
 */
public class PanelListener implements MouseListener {
    //Osluskivac akcija nad panelima. Svaki panel ima svoj prisluskivac
    BoardPanel square;
    public PanelListener() {
        
        
        
    }

    public PanelListener(BoardPanel square) {
        this.square = square;
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
       // System.out.println("kliknut");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed" + square.getCoordX() + " " + square.getCoordY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        System.out.println("released" + square.getCoordX() + " " + square.getCoordY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // System.out.println("exited");
    }
    
}
