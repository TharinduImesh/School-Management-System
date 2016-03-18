/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.NewEntryWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.DatabaseAdapter;
import view.NewEntryWindow;

/**
 *
 * @author Sandu
 */
public class EnterDataButtonController implements ActionListener{
    private final NewEntryWindow entry_window;
    private final JTable input_table;
    private final JComboBox grade;
    private final JComboBox class_type;
    
    public EnterDataButtonController(NewEntryWindow entry_window) {
        this.entry_window = entry_window;
        this.input_table = entry_window.getTable();
        this.grade = entry_window.getGrade_combo_box();
        this.class_type = entry_window.getClass_combo_box();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.input_table.getValueAt(0, 0) != null){
            String name = createClassName();
            DatabaseAdapter db_connector = new DatabaseAdapter();        
            boolean store_data = db_connector.store_data(this.input_table, name);            
            
            if(!store_data){
                JOptionPane.showMessageDialog(null, 
                            "Please try again","Data saving error",
                            JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, 
                            "Data saving completed","Saved",
                            JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel tablemodel = (DefaultTableModel) this.input_table.getModel();
                tablemodel.getDataVector().removeAllElements();
                tablemodel.fireTableDataChanged();
                tablemodel.setRowCount(1);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, 
                            "Please enter data","Data empty",
                            JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    private String createClassName(){
        String name = this.grade.getSelectedItem().toString();
        name = name.replace(" ", "_");
        name = name.concat("_"+this.class_type.getSelectedItem().toString());
        return name;
    }
}
