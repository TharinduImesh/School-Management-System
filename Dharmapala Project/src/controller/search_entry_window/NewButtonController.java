/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.search_entry_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.NewEntryWindow;
import view.SearchEntryWindow;

/**
 *
 * @author Sandu
 */
public class NewButtonController  implements ActionListener{

    private final SearchEntryWindow entryWindow;
    public NewButtonController(SearchEntryWindow window) {
        this.entryWindow = window;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        NewEntryWindow instance = NewEntryWindow.getInstance();
        instance.setGrade_index(this.entryWindow.getGrade_combo_box().getSelectedIndex());
        instance.setClass_index(this.entryWindow.getClass_combo_box().getSelectedIndex());
        instance.setVisible(true);
    }
    
}
