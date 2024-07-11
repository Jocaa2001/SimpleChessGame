/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Win 10
 */
public class Board extends JFrame{
   
    ArrayList<BoardPanel> boardGrid = new ArrayList<>();
    public final int row = 8;
    public final int column = 8;
    
    public Board() throws HeadlessException {
        setSize(720,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 8));
        
        initBoard();
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
                boardGrid.add(square);
                add(square);
                
            }
         }
          
        }
       
    }

        


