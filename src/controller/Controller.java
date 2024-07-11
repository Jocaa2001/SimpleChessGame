/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Win 10
 */
public class Controller {
    
    
    private Controller instance;

    private Controller() {
    }

    public Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }
    
    
    
}
