/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin_window;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import view.AdminWindow;
import view.CreateAccountWindow;

/**
 *
 * @author Sandu
 */
public class CreateAccountButtonController implements ActionListener{

    private final AdminWindow adminWindow;
    public CreateAccountButtonController(AdminWindow adminWindow) {
        this.adminWindow = adminWindow;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        final CreateAccountWindow instance = CreateAccountWindow.getInstance();
        instance.setVisible(true);
        
        if(instance.getState() == Frame.ICONIFIED){
            instance.setState(Frame.NORMAL);
        }
        
        
    }
    
}
