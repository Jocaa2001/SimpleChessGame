/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import model.pieces.BlackPawn;
import model.pieces.BlackQueen;
import model.pieces.BlackRook;
import model.pieces.WhitePawn;
import model.pieces.WhiteQueen;
import view.Board;
import view.PromotionForm;


/**
 *
 * @author Win 10
 */
public class Main {
    public static void main(String[] args) {
     
     Board b = new Board();
     controller.Controller.getInstance().setBoard(b);
     controller.Controller.getInstance().setStartingPosition();

     //new PromotionForm(false);
     
     
    b.setVisible(true);
    }
    
    
}