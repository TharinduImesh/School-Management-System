/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.search_entry_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.DatabaseAdapter;
import view.SearchEntryWindow;

/**
 *
 * @author Sandu
 */
public class StudentIndexFieldController implements ActionListener{

    private final SearchEntryWindow entryWindow;
    private final JTable output;
    private final JTextField student_index;
    
    public StudentIndexFieldController(SearchEntryWindow window) {
        this.entryWindow = window;
        this.output = window.getTable();
        this.student_index = window.getStudent_index_field();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name =  createClassName();
        DatabaseAdapter adapter = new DatabaseAdapter();
        if(adapter.login("root", "")){
        ResultSet rs = adapter.retrive_student_data(name, this.student_index.getText());
        
        if(rs != null){            
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                int row = 0;
                int columnCount = metaData.getColumnCount();
                DefaultTableModel tablemodel = (DefaultTableModel) this.output.getModel();
                tablemodel.getDataVector().removeAllElements();
                tablemodel.fireTableDataChanged();
                
//                int row_count = tablemodel.getRowCount();
//                int column_count = tablemodel.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i < columnCount+1; i++) {
                        tablemodel.setRowCount(row+1);
                        this.output.setValueAt(rs.getObject(i), row, i-1);                        
                    }
                    row++;
                }
                this.entryWindow.getTable().setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(ClassComboBoxController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            //show error
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
