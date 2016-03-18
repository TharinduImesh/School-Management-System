/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author Sandu
 */
public class Test {
    public static void main(String[] args) {
        String message = "Withdraw is completed.";
        String header = "Withdraw";
        JFrame frame = new JFrame();
        frame.setSize(300,125);
        frame.setUndecorated(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel headingLabel = new JLabel(header);

//headingLabel .setIcon(headingIcon); // --- use image icon you want to be as heading image.
//
//headingLabel.setOpaque(false);

        frame.add(headingLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;

        JButton closeButton = new JButton("X");
        closeButton.setMargin(new Insets(1, 4, 1, 4));
        closeButton.setFocusable(false);

        frame.add(closeButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel messageLabel = new JLabel("<HtMl>"+message);

        frame.add(messageLabel, constraints);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);

        closeButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
    
        });
    }
}
