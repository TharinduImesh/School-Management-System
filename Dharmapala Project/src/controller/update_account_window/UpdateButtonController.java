/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.update_account_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DatabaseAdapter;
import view.UpdateAccountWindow;

/**
 *
 * @author Sandu
 */
public class UpdateButtonController implements ActionListener{

    private final UpdateAccountWindow updateAccountWindow;
    public UpdateButtonController(UpdateAccountWindow createAccountWindow) {
        this.updateAccountWindow = createAccountWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String current_username = this.updateAccountWindow.getCurrent_username_field().getText();
        
        if(current_username != null && !current_username.equals("")){
            String new_username = this.updateAccountWindow.getNew_username_field().getText();
            String password = new String(this.updateAccountWindow.getPassword_field().getPassword());
            String confirm_password = new String(this.updateAccountWindow.getConfirm_password_field().getPassword());
            
            if(!password.equals("")){
                if(!confirm_password.equals("")){
                    if(password.equals(confirm_password)){
                        DatabaseAdapter adapter = new DatabaseAdapter();
                        if(adapter.login("root", "")){
                            boolean update_account = adapter.update_account(current_username, new_username, password);
                            adapter.close_connection();
                            if(!update_account){
                                JOptionPane.showMessageDialog(null, 
                                            "Error in account updating. "
                                                    + "Please check current user name and try again","Update Error",
                                            JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                this.updateAccountWindow.getCurrent_username_field().setText("");
                                this.updateAccountWindow.getNew_username_field().setText("");
                                this.updateAccountWindow.getPassword_field().setText("");
                                this.updateAccountWindow.getConfirm_password_field().setText("");
                                this.updateAccountWindow.dispose();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, 
                                            "Please try again","Database Error",
                                            JOptionPane.ERROR_MESSAGE);
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
                                "Please enter the New Password","No Password",
                                JOptionPane.INFORMATION_MESSAGE);
            }            
        }
        else{
            JOptionPane.showMessageDialog(null, 
                        "Please enter current User Name","Input Error",
                        JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
