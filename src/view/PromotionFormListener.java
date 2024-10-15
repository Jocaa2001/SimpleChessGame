/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Win 10
 */
public class PromotionFormListener implements MouseListener {

    private PromotionBoardPanel panel;
    
    public PromotionFormListener() {
    }

    public PromotionFormListener(PromotionBoardPanel panel) {
        this.panel = panel;
    }

    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        controller.Controller.getInstance().setPromotionPiece(panel.getPiece());
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("usao");
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public PromotionBoardPanel getPanel() {
        return panel;
    }

    public void setPanel(PromotionBoardPanel panel) {
        this.panel = panel;
    }
    
    
    
    
}
