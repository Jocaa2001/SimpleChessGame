/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    public void addInfoField() {
           // Create a panel for player info
        JPanel P1Name = new JPanel();
        P1Name.setLayout(new FlowLayout());
        
        // Create and set the player info label
        JLabel playerInfoLabel = new JLabel(controller.Controller.getInstance().getPlayer1().getName() + "");
        //playerInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        P1Name.add(playerInfoLabel);

        // Add the info panel to the top of the frame
        add(P1Name, BorderLayout.CENTER);
        
        
        JPanel vs = new JPanel();
        P1Name.setLayout(new FlowLayout());
        JLabel vsLabel = new JLabel("vs");
        
        vs.add(vsLabel);
        add(vs,BorderLayout.CENTER);
        
        
        JPanel P2Name = new JPanel();
        P2Name.setLayout(new FlowLayout());
        
        // Create and set the player info label
        JLabel player2InfoLabel = new JLabel(controller.Controller.getInstance().getPlayer2().getName() + "");
        //playerInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        P2Name.add(player2InfoLabel);

        // Add the info panel to the top of the frame
        add(P2Name, BorderLayout.CENTER);
        
        
    }
       
    
    
}

