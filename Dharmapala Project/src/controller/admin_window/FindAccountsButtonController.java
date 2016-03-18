/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin_window;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.DatabaseAdapter;
import view.ExistAccountsWindow;

/**
 *
 * @author Sandu
 */
public class FindAccountsButtonController implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseAdapter adapter = new DatabaseAdapter();
        if(adapter.login("root", "")){
            Object[] retrive_accounts_data = adapter.retrive_accounts_data();
            adapter.close_connection();
            if(retrive_accounts_data[0] != null && ((ArrayList<String>)retrive_accounts_data[0]).size() > 0){
                ExistAccountsWindow instance = ExistAccountsWindow.getInstance();
                instance.setAccounts((ArrayList<String>)retrive_accounts_data[0]);
                
                if(((ArrayList<String>)retrive_accounts_data[1]).size() > 0){
                    instance.setHolders((ArrayList<String>)retrive_accounts_data[1]);
                }
                
                instance.setVisible(true);
                if(instance.getState() == Frame.ICONIFIED){
                    instance.setState(Frame.NORMAL);
                }
            }
            else{
                
            }
        }
        
    }
    
}
