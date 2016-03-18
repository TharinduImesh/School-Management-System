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
public class ClassComboBoxController implements ActionListener{
    private final SearchEntryWindow entryWindow;
    private final JTable output;
    private final JTextField total_amount;
    private final JTextField total_public;
    private final JTextField total_class;
    private final JTextField total_sport;
    private final JTextField total_member;
    private final JTextField balance;
    
    public ClassComboBoxController(SearchEntryWindow window) {
        this.entryWindow = window;
        this.output = window.getTable();
        this.total_amount = window.getTotal_amount();
        this.total_public = window.getTotal_public_amount();
        this.total_class = window.getTotal_class_amount();
        this.total_sport = window.getTotal_sport_amount();
        this.total_member = window.getTotal_membership_amount();
        this.balance = window.getBalance_field();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String name =  createClassName();
        DatabaseAdapter adapter = new DatabaseAdapter();
        if(adapter.login("root", "")){
            ResultSet rs = adapter.retrive_class_data(name);

            if(rs != null){            
                try {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int row = 0;
                    int columnCount = metaData.getColumnCount();
                    DefaultTableModel tablemodel = (DefaultTableModel) this.output.getModel();                    
                    while (rs.next()) {
                        for (int i = 1; i < columnCount+1; i++) {
                            tablemodel.setRowCount(row+1);
                            this.output.setValueAt(rs.getObject(i), row, i-1);
                        }
                        row++;
    //                    String amount = rs.getObject(columnCount).toString();

    //                    switch(amount){
    //                        case "2440":
    //                            this.output.setValueAt(1437, row, columnCount+1);
    //                            this.output.setValueAt(600, row, columnCount+2);
    //                            this.output.setValueAt(100, row, columnCount+3);
    //                            this.output.setValueAt(303, row, columnCount+4);
    //                            break;
    //                        case "2464":
    //                            this.output.setValueAt(1461, row, columnCount+1);
    //                            this.output.setValueAt(600, row, columnCount+2);
    //                            this.output.setValueAt(100, row, columnCount+3);
    //                            this.output.setValueAt(303, row, columnCount+4);
    //                            break;
    //                        case "700":
    //                            this.output.setValueAt(0, row, columnCount+1);
    //                            this.output.setValueAt(600, row, columnCount+2);
    //                            this.output.setValueAt(100, row, columnCount+3);
    //                            this.output.setValueAt(0, row, columnCount+4);
    //                            break;
    //                        case "1740":
    //                            this.output.setValueAt(1437, row, columnCount+1);
    //                            this.output.setValueAt(0, row, columnCount+2);
    //                            this.output.setValueAt(0, row, columnCount+3);
    //                            this.output.setValueAt(303, row, columnCount+4);
    //                            break;
    //                        case "1764":
    //                            this.output.setValueAt(1461, row, columnCount+1);
    //                            this.output.setValueAt(0, row, columnCount+2);
    //                            this.output.setValueAt(0, row, columnCount+3);
    //                            this.output.setValueAt(303, row, columnCount+4);
    //                            break;
    //                    }
                    }
                    this.entryWindow.getTable().setEnabled(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ClassComboBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                //show error
            }
            
            ResultSet resultSet = adapter.retrive_total_data(name);
            if(resultSet != null){
                try {
                    while (resultSet.next()) {
                        this.total_amount.setText(resultSet.getObject(1).toString());
                        this.total_public.setText(resultSet.getObject(2).toString());
                        this.total_class.setText(resultSet.getObject(3).toString());
                        this.total_sport.setText(resultSet.getObject(4).toString());
                        this.total_member.setText(resultSet.getObject(5).toString());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClassComboBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            resultSet = adapter.retrive_cost_data(name);
            if(resultSet != null){
                try {
                    while (resultSet.next()) {
                        this.balance.setText(resultSet.getDouble("total_available")+"");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClassComboBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
