/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.remove_account_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DatabaseAdapter;
import view.RemoveAccountWindow;

/**
 *
 * @author Sandu
 */
public class RemoveButtonController implements ActionListener{

    private final RemoveAccountWindow removeAccountWindow;
    public RemoveButtonController(RemoveAccountWindow createAccountWindow) {
        this.removeAccountWindow = createAccountWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         DatabaseAdapter adapter = new DatabaseAdapter();
         
         if(adapter.login("root", "")){
             adapter.remove_account(this.removeAccountWindow.getUsername_field().getText());
             adapter.close_connection();
             this.removeAccountWindow.getUsername_field().setText("");
             this.removeAccountWindow.dispose();
         }
         else{
             
         }
    }
    
}
