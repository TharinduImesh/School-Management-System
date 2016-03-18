/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.CreateInitialTables;
import model.DatabaseAdapter;
import view.AdminWindow;
import view.LoginWindow;
import view.NavigationWindow;

/**
 *
 * @author Sandu
 */
public class Logincontroller implements ActionListener{

    private final LoginWindow loginWindow;
    public Logincontroller(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
//        CreateInitialTables createInitialTables = new CreateInitialTables();
//        createInitialTables.login("root", "");
        DatabaseAdapter adapter = new DatabaseAdapter();
        String username = this.loginWindow.getUsername().getText();
        char [] password = this.loginWindow.getPassword().getPassword();
        if(username != null && !username.equals("")){            
            if(password != null && password.length > 0){
                if(adapter.login("root", "")){
                    String login_into_account = adapter.login_into_account(username, password);
                    adapter.close_connection();
                    if(!login_into_account.equals("")){
                        if(login_into_account.equals("admin")){
                            AdminWindow.start();
                        }
                        else{
                            NavigationWindow.start();
                        }                
                        this.loginWindow.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, 
                                        "User Name or Password is incorrect","Login Error",
                                        JOptionPane.ERROR_MESSAGE);
                    }
                }
            } 
            else{
                JOptionPane.showMessageDialog(null, 
                                "Please enter your password","Empty Password",
                                JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, 
                            "Please enter your User Name","Empty User Name",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
}
