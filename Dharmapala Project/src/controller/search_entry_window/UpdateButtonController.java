/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.search_entry_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.SearchEntryWindow;
import view.UpdateEntryWindow;

/**
 *
 * @author Sandu
 */
public class UpdateButtonController  implements ActionListener{

    private final SearchEntryWindow entryWindow;
    private final JTable data_table;
    public UpdateButtonController(SearchEntryWindow window) {
        this.entryWindow = window;
        this.data_table = window.getTable();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        int [] selectedRows = this.data_table.getSelectedRows();
        String name = createClassName();
        if(selectedRows.length > 0){
            UpdateEntryWindow.start(name, selectedRows, this.data_table);
            this.entryWindow.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, 
                                "Please select a entry","Select Entry",
                                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private String createClassName(){
        String name = this.entryWindow.getGrade_combo_box().getSelectedItem().toString();
        name = name.concat(" "+this.entryWindow.getClass_combo_box().getSelectedItem().toString());
        return name;
    }
}
