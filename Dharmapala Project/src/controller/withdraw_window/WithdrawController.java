/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.withdraw_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.DatabaseAdapter;
import view.WithdrawWindow;

/**
 *
 * @author Sandu
 */
public class WithdrawController implements ActionListener{
    private final WithdrawWindow withdrawWindow;
    private final JTextField available_field;
    private final JTextField return_amount_field;
    private final JTextField balance;
    private final JTextArea description;
    public WithdrawController(WithdrawWindow window) {
        this.withdrawWindow = window;
        this.available_field = window.getAvailable_field();
        this.return_amount_field = window.getReturn_field();
        this.balance = window.getBalance_field();
        this.description = window.getDescription_area();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        Double available = Double.parseDouble(this.available_field.getText());
        Double return_amount = Double.parseDouble(this.return_amount_field.getText());
        if(return_amount > 0){
            if(available >= return_amount){
                this.balance.setText((available - return_amount)+"");
                String name = createClassName();
                DatabaseAdapter adapter = new DatabaseAdapter();
                if(adapter.login("root", "")){
                    adapter.update_cost_table(name , (-1*return_amount),description.getText());
                    JOptionPane.showMessageDialog(null, 
                                "Withdraw is completed","Withdraw",
                                JOptionPane.INFORMATION_MESSAGE);
                    
                }
                else{
                    //error
                }
            }
            else{
                JOptionPane.showMessageDialog(null, 
                                "Available amount is not sufficient","Withdraw",
                                JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, 
                                "Please enter amount to withrdaw","Withdraw",
                                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private String createClassName(){
        String name = this.withdrawWindow.getGrade_combo_box().getSelectedItem().toString();
        name = name.replace(" ", "_");
        name = name.concat("_"+this.withdrawWindow.getClass_combo_box().getSelectedItem().toString());
        return name;
    }
}
