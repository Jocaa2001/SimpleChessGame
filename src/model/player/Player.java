/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Win 10
 */
public class Player{
    
    private String name;
    private boolean turn;
    private boolean white;
    

    public Player() {
    }

    public Player(boolean turn, boolean white) {
        this.turn = turn;
        this.white = white;
    }


    public Player(String name, boolean turn) {
        this.name = name;
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
    
    public void takeTurn(){
        
    }

    public boolean isWhite() {
        return white;
    }

    
    
}
