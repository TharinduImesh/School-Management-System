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
import javax.swing.table.DefaultTableModel;
import model.DatabaseAdapter;
import view.SearchEntryWindow;

/**
 *
 * @author Sandu
 */
public class DeleteButtonController  implements ActionListener{

    private final SearchEntryWindow entryWindow;
    private final JTable data_table;
    public DeleteButtonController(SearchEntryWindow window) {
        this.entryWindow = window;
        this.data_table = window.getTable();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        int [] selectedRows = this.data_table.getSelectedRows();
        String message = "";
        if(selectedRows.length > 0){
            if(selectedRows.length > 1){
                message = "Do you want to delete this entries";
            }
            else{
                message = "Do you want to delete this entry";
            }

            int showConfirmDialog = JOptionPane.showConfirmDialog(null, 
                                    message,"Delete",
                                    JOptionPane.YES_NO_OPTION);

            if(showConfirmDialog == JOptionPane.YES_OPTION){
                DatabaseAdapter adapter = new DatabaseAdapter();
                if(adapter.login("root", "")){
                    String name = createClassName();
                    DefaultTableModel tablemodel = (DefaultTableModel) this.data_table.getModel();
                    for(int i:selectedRows){
                        boolean state = adapter.delete_entry(name,this.data_table.getValueAt(i, 0).toString() , this.data_table.getValueAt(i, 1).toString(), this.data_table.getValueAt(i, 3).toString(), Float.parseFloat(this.data_table.getValueAt(i, 6).toString()));
                        if(state){
                            tablemodel.removeRow(i);
                            tablemodel.fireTableDataChanged();
                        }
                    }

                    adapter.close_connection();
                }
                else{
                    System.out.println("data base logging error");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, 
                                "Please select a entry","Select Entry",
                                JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    private String createClassName(){
        String name = this.entryWindow.getGrade_combo_box().getSelectedItem().toString();
        name = name.replace(" ", "_");
        name = name.concat("_"+this.entryWindow.getClass_combo_box().getSelectedItem().toString());
        return name;
    }
}
