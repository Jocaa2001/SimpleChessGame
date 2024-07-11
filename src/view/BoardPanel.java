/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Win 10
 */
public class BoardPanel extends JPanel{
    //BoardPanel klasa nasledjuje JPanel ali dodajem x i y koordinate koje ce da pamte na kojoj se poziciji taj board nalazi
    private int x;
    private int y;

    
    
    public BoardPanel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
       
        addMouseListener(new PanelListener(this));
    }

    public int getCoordX() {
        return x;
    }

    public void setCoordX(int x) {
        this.x = x;
    }

    public int getCoordY() {
        return y;
    }

    public void setCoordY(int y) {
        this.y = y;
    }
    
    
    
    
}
