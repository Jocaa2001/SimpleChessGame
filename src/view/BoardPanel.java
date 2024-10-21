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
public class BoardPanel extends JPanel{
    //BoardPanel klasa nasledjuje JPanel ali dodajem x i y koordinate koje ce da pamte na kojoj se poziciji taj board nalazi
    private int x;
    private int y;
    private AbstractPiece piece;
    private boolean Ocupied;
    
    
    public BoardPanel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
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

    public AbstractPiece getPiece() {
        return piece;
    }

    public void setPiece(AbstractPiece piece) {
        this.piece = piece;
    }

    @Override
    public Component add(Component comp) {
        piece = (AbstractPiece) comp;
        return super.add(comp); 
    }

    public void setOcupied(boolean isOcupied) {
        this.Ocupied = isOcupied;
    }

    public boolean isOcupied() {
        return !(piece == null);
    }
    
    
    
}
