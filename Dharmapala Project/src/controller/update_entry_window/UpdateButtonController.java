/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.update_entry_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.DatabaseAdapter;
import view.UpdateEntryWindow;

/**
 *
 * @author Sandu
 */
public class UpdateButtonController implements ActionListener{

    private final UpdateEntryWindow updateEntryWindow;
    private final JTable data_table;
    private final JTable update_table;
    private String name;
    private final int [] selectedRows;
    public UpdateButtonController(UpdateEntryWindow window) {
        this.updateEntryWindow = window;
        this.data_table = window.getData_table();
        this.update_table = window.getUpdate_table();
        this.name = window.getClass_name();
        this.selectedRows = UpdateEntryWindow.getSelectedRows();
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseAdapter adapter = new DatabaseAdapter();
        if(adapter.login("root", "")){
            
            this.name = this.name.replace(" ", "_");            
            System.out.println(this.name); 
            double remain = 0;
            int count = 0;
            for(int i : this.selectedRows){
                remain += Float.parseFloat(this.update_table.getValueAt(count, 6).toString())-Float.parseFloat(this.data_table.getValueAt(i,6).toString());
            }
            boolean state = adapter.update_cost_table(this.name, remain,"");
            if(state){
                adapter.update_class_table(this.update_table, this.name);
                JOptionPane.showMessageDialog(null, 
                                "Successfully updated","Update",
                                JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, 
                                "Error in updating enties, Please try again","Update Error",
                                JOptionPane.ERROR_MESSAGE);
            }
            adapter.close_connection();
        }else{
            System.out.println("data base login error");
        }
    }
    
    
}
