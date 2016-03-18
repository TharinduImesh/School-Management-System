/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.navigation_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.LoginWindow;
import view.NavigationWindow;
import view.NewEntryWindow;
import view.SearchEntryWindow;
import view.WithdrawWindow;

/**
 *
 * @author Sandu
 */
public class LogOutButtonController implements ActionListener{

    private final NavigationWindow navigationWindow;
    public LogOutButtonController(NavigationWindow navigationWindow) {
        this.navigationWindow = navigationWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        NewEntryWindow newEntryWindow = NewEntryWindow.getInstance();
        newEntryWindow.dispose();
        
        SearchEntryWindow searchEntryWindow = SearchEntryWindow.getInstance();
        searchEntryWindow.dispose();
        
        WithdrawWindow withdrawWindow = WithdrawWindow.getInstance();
        withdrawWindow.dispose();
        
        LoginWindow.start();
        this.navigationWindow.dispose();
    }
    
}
