/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.withdraw_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.DatabaseAdapter;
import view.SearchEntryWindow;
import view.WithdrawWindow;

/**
 *
 * @author Sandu
 */
public class ClassComboBoxController implements ActionListener{
    private final WithdrawWindow entryWindow;
    private final JComboBox grade;
    private final JComboBox class_type;
    private final JTextField available;
    
    public ClassComboBoxController(WithdrawWindow window) {
        this.entryWindow = window;
        this.grade = window.getGrade_combo_box();
        this.class_type = window.getClass_combo_box();
        this.available = window.getAvailable_field();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String name =  createClassName();
        DatabaseAdapter adapter = new DatabaseAdapter();
        if(adapter.login("root", "")){
            try {
                ResultSet rs = adapter.retrive_cost_data(name);
                double available_amount = 0;
                while (rs.next()) {
                    available_amount = rs.getDouble("total_available");
                }
                
                this.available.setText(available_amount+"");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ClassComboBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{            
                adapter.close_connection();
            }
        }
    }
    
    private String createClassName(){
        String name = this.entryWindow.getGrade_combo_box().getSelectedItem().toString();
        name = name.replace(" ", "_");
        name = name.concat("_"+this.entryWindow.getClass_combo_box().getSelectedItem().toString());
        return name;
    }
}
