/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Win 10
 */
public class NameInputDialog extends JDialog {
    
    
     private JTextField player1TextField;
    private JTextField player2TextField;
    private String player1Name;
    private String player2Name;
    private boolean confirmed;

    public NameInputDialog(Board parent) {
         super(parent, "Enter Player Names", true);

        // Set layout
        setLayout(new GridLayout(3, 2, 10, 10));

        // Initialize text fields
        player1TextField = new JTextField();
        player2TextField = new JTextField();

        // Create components
        JLabel player1Label = new JLabel("Player 1 Name:");
        JLabel player2Label = new JLabel("Player 2 Name:");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // Add components to dialog
        add(player1Label);
        add(player1TextField);
        add(player2Label);
        add(player2TextField);
        add(okButton);
        add(cancelButton);

        // Button action listeners
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player1Name = player1TextField.getText().trim();
                player2Name = player2TextField.getText().trim();
                confirmed = true;
                controller.Controller.getInstance().setPlayerNames(player1Name,player2Name);
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                setVisible(false);
            }
        });

        // Dialog settings
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    
    
    
}
