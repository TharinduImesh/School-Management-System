/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.create_account_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DatabaseAdapter;
import view.CreateAccountWindow;

/**
 *
 * @author Sandu
 */
public class CreateButtonController implements ActionListener{

    private final CreateAccountWindow createAccountWindow;
    public CreateButtonController(CreateAccountWindow createAccountWindow) {
        this.createAccountWindow = createAccountWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = this.createAccountWindow.getUsername_field().getText();
        String holder_name = this.createAccountWindow.getHolder_name_field().getText();
        String password = new String(this.createAccountWindow.getPassword_field().getPassword());
        String confirm_password = new String(this.createAccountWindow.getConfirm_password_field().getPassword());
        
        if(username != null && !username.equals("")){
            if(holder_name != null && !holder_name.equals("")){
                if(!password.equals("")){
                    if(!confirm_password.equals("")){
                        if(password.equals(confirm_password)){
                            DatabaseAdapter adapter = new DatabaseAdapter();
                            if(adapter.login("root", "")){
                                String create_account = adapter.create_account(username, this.createAccountWindow.getHolder_name_field().getText(),password);
                                adapter.close_connection();

                                if(create_account.equals("success")){
                                    this.createAccountWindow.getUsername_field().setText("");
                                    this.createAccountWindow.getHolder_name_field().setText("");
                                    this.createAccountWindow.getPassword_field().setText("");
                                    this.createAccountWindow.getConfirm_password_field().setText("");
                                    this.createAccountWindow.dispose();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, 
                                                create_account,"Account Creating Error",
                                                JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, 
                                                "Passwords are not match","Password Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, 
                                            "Please enter the password again","Confirm the Password",
                                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, 
                                        "Please enter the password","No Password",
                                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, 
                                "Please enter the name of the account holder","No Holder",
                                JOptionPane.INFORMATION_MESSAGE);
            }            
        }
        else{
            JOptionPane.showMessageDialog(null, 
                            "Please enter a User Name","Empty User Name",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
}
