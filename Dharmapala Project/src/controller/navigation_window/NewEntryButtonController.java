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
import view.NewEntryWindow;

/**
 *
 * @author Sandu
 */
public class NewEntryButtonController implements ActionListener{

    private final NavigationWindow navigationWindow;
    public NewEntryButtonController(NavigationWindow navigationWindow) {
        this.navigationWindow = navigationWindow;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        NewEntryWindow instance = NewEntryWindow.getInstance();
        instance.setVisible(true);
        
        if(instance.getState() == Frame.ICONIFIED){
            instance.setState(Frame.NORMAL);
        }
    }
    
}
