/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.pieces.AbstractPiece;

/**
 *
 * @author Win 10
 */
public class PromotionBoardPanel extends JPanel {

    private AbstractPiece piece;
    
    public PromotionBoardPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        addMouseListener(new PromotionFormListener(this));
    }

    public AbstractPiece getPiece() {
        return piece;
    }

    @Override
    public Component add(Component comp) {
        piece = (AbstractPiece) comp;
        return super.add(comp); 
    }
  
}
