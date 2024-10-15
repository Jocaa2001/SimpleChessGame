/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.Border;
import model.pieces.BlackBishop;
import model.pieces.BlackKnight;
import model.pieces.BlackQueen;
import model.pieces.BlackRook;
import model.pieces.WhiteBishop;
import model.pieces.WhiteKnight;
import model.pieces.WhiteQueen;
import model.pieces.WhiteRook;

/**
 *
 * @author Win 10
 */
public class PromotionForm extends JDialog {

    boolean black;
    PromotionBoardPanel bp1,bp2,bp3,bp4;
    
    
    public PromotionForm(boolean black) {
        setSize(400, 150);
       
        setLayout(new GridLayout(1,4));
        setPanels();
        this.black = black;
        populatePanels();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(750,400);
        setVisible(true);
        System.out.println("ovde");
    }

    private void setPanels() {
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
       
       bp1 = new PromotionBoardPanel();
       add(bp1);
       //bp1.setBackground(Color.red);
       bp1.setVisible(true);
       bp1.setBorder(blackBorder);
       
       
       
       bp2 = new PromotionBoardPanel();
       add(bp2);
       //bp2.setBackground(Color.BLACK);
       bp2.setVisible(true);
       bp2.setBorder(blackBorder);
       
       
       bp3 = new PromotionBoardPanel();
       add(bp3);
       //bp3.setBackground(Color.BLUE);
       bp3.setVisible(true);
       bp3.setBorder(blackBorder);
       
       
       bp4 = new PromotionBoardPanel();
       add(bp4);
       //bp4.setBackground(Color.PINK);
       bp4.setVisible(true);
       bp4.setBorder(blackBorder);
        
    }
    
    public void populatePanels(){
        
        if(black){
            bp1.add(new BlackBishop());
            bp2.add(new BlackKnight());
            bp3.add(new BlackQueen());
            bp4.add(new BlackRook());
            
        }else{
            bp1.add(new WhiteBishop());
            bp2.add(new WhiteKnight());
            bp3.add(new WhiteQueen());
            bp4.add(new WhiteRook());
        }
       
        repaint();
        revalidate();
        
    }
    
}
