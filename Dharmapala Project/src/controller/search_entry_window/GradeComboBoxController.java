/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.search_entry_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.SearchEntryWindow;

/**
 *
 * @author Sandu
 */
public class GradeComboBoxController implements ActionListener{
    private final SearchEntryWindow entryWindow;
    
    public GradeComboBoxController(SearchEntryWindow window) {
        this.entryWindow = window;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.entryWindow.getClass_combo_box().setEnabled(true);
    }
       
}
