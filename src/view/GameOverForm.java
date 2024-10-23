/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.player.Player;


/**
 *
 * @author Win 10
 */
public class GameOverForm extends JDialog {

    private Player winner;
    
    public GameOverForm(JFrame parent, Player winner) {
        super(parent);
        setVisible(true);
        this.winner = winner;
        initForm();
        
    }

    private void initForm() {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Use BorderLayout for layout flexibility
        
        JLabel text = new JLabel();
        text.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        text.setFont(new Font("Arial", Font.BOLD, 24)); // Make the text larger and bold
        
        if (winner != null) {
            text.setText("Winner is " + winner.getName());
        } else {
            text.setText("Stalemate");
        }
        
        add(text, BorderLayout.CENTER); // Add the text to the center of the form
        
        // OK Button to close the form
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Set button font
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        
        add(buttonPanel, BorderLayout.SOUTH); // Place the button at the bottom
    }
    
    
    
    
}
