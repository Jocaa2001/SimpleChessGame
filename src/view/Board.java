/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;


/**
 *
 * @author Win 10
 */
public class Board extends JFrame{
   
    public static final int row = 8;
    public static final int column = 8;
    BoardPanel[][] boardGrid = new BoardPanel[row][column];
    
    public Board() throws HeadlessException {
        setSize(720,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new GridLayout(9, 9));
        
        initBoard();
        addResetButton();
    }

    private void initBoard() {
         
        Color lightColor = new Color(255, 255, 255);  
        Color darkColor = new Color(139, 69, 19);     
         
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardPanel square = new BoardPanel(row,col);
                
               
                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }
                boardGrid[row][col] = square;
                add(square);
                
            }
         }
         
        }

    public BoardPanel[][] getBoardGrid() {
        return boardGrid;
    }

    public void setBoardGrid(BoardPanel[][] boardGrid) {
        this.boardGrid = boardGrid;
    }

    private void addResetButton() {
        JButton jb = new JButton();
        this.add(jb);
        jb.setText("Reset");
        jb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                controller.Controller.getInstance().setStartingPosition();
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
       
    
    
}

