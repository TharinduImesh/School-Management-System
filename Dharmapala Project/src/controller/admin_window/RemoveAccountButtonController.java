/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin_window;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.AdminWindow;
import view.RemoveAccountWindow;

/**
 *
 * @author Sandu
 */
public class RemoveAccountButtonController implements ActionListener{

    private final AdminWindow adminWindow;
    public RemoveAccountButtonController(AdminWindow adminWindow) {
        this.adminWindow = adminWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RemoveAccountWindow instance = RemoveAccountWindow.getInstance();
        instance.setVisible(true);
        
        if(instance.getState() == Frame.ICONIFIED){
            instance.setState(Frame.NORMAL);
        }
    }
    
}
