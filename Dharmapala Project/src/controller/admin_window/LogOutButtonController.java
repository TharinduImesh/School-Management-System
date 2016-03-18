/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AdminWindow;
import view.CreateAccountWindow;
import view.ExistAccountsWindow;
import view.LoginWindow;
import view.RemoveAccountWindow;
import view.UpdateAccountWindow;

/**
 *
 * @author Sandu
 */
public class LogOutButtonController implements ActionListener{

    private final AdminWindow adminWindow;
    public LogOutButtonController(AdminWindow adminWindow) {
        this.adminWindow = adminWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreateAccountWindow createAccountWindow = CreateAccountWindow.getInstance();
        createAccountWindow.dispose();
        
        UpdateAccountWindow updateAccountWindow = UpdateAccountWindow.getInstance();
        updateAccountWindow.dispose();
        
        ExistAccountsWindow existAccountsWindow = ExistAccountsWindow.getInstance();
        existAccountsWindow.dispose();
        
        RemoveAccountWindow removeAccountWindow = RemoveAccountWindow.getInstance();
        removeAccountWindow.dispose();
        
        LoginWindow.start();
        this.adminWindow.dispose();
        
    }
    
}
