/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.navigation_window;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.NavigationWindow;
import view.WithdrawWindow;

/**
 *
 * @author Sandu
 */
public class WithdrawButtonController implements ActionListener{
    private final NavigationWindow navigationWindow;
    
    public WithdrawButtonController(NavigationWindow navigationWindow) {
        this.navigationWindow = navigationWindow;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        WithdrawWindow withdrawWindow = WithdrawWindow.getInstance();
        withdrawWindow.setVisible(true);
        
        if(withdrawWindow.getState() == Frame.ICONIFIED){
            withdrawWindow.setState(Frame.NORMAL);
        }
    }
    
}
