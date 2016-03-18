/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.search_entry_window.ClassComboBoxController;
import controller.search_entry_window.DeleteButtonController;
import controller.search_entry_window.GradeComboBoxController;
import controller.search_entry_window.NewButtonController;
import controller.search_entry_window.StudentIndexFieldController;
import controller.search_entry_window.UpdateButtonController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Sandu
 */
public class SearchEntryWindow extends javax.swing.JFrame {

    private static SearchEntryWindow searchEntryWindow = new SearchEntryWindow();
    /**
     * Creates new form MainWindow
     */
    private SearchEntryWindow() {
        initComponents();
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        ((JLabel)this.grade_combo_box.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)this.class_combo_box.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        this.input_data.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        postInit();
    }

    public void init(){
        
        
//        input_data.addKeyListener(new KeyListener(){
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getID() == KeyEvent.KEY_PRESSED){
//                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
//                        double amount = 0;
//                        double total_public = 0;
//                        double total_cost = 0;
//                        double total_sport = 0;
//                        double total_member = 0;
//                        for(int i=0; i<input_data.getRowCount();i++){
//                            if(input_data.getValueAt(i, 0)!= null){
//                                amount += (double) input_data.getValueAt(i, 3);
//                                total_public += (double) input_data.getValueAt(i, 4);
//                                total_cost += (double) input_data.getValueAt(i, 5); 
//                                total_sport += (double) input_data.getValueAt(i, 6);
//                                total_member += (double) input_data.getValueAt(i, 7);
//                            }
//                            else{
//                                break;
//                            }
//                        }
//                        
//                        total_amount.setText(amount+"");
//                        total_public_amount.setText(total_public+"");
//                        total_class_amount.setText(total_cost+"");
//                        cost_field.setText(total_cost+"");
//                        total_sport_amount.setText(total_sport+"");
//                        total_membership_amount.setText(total_member+"");
//                        
////                        DefaultTableModel model = (DefaultTableModel)input_data.getModel();
////                        model.addRow(new Object[]{null, null,null,null,null,null,null,null}); 
//                  }
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
//                double amount = 0;
//                double total_public = 0;
//                double total_cost = 0;
//                double total_sport = 0;
//                double total_member = 0;
//                for(int i=0; i<input_data.getRowCount();i++){
//                    if(input_data.getValueAt(i, 0)!= null){
//                        if(input_data.getValueAt(i, 4) != null){
//                            amount += (double) input_data.getValueAt(i, 4);
//                        }
//                        
//                        if(input_data.getValueAt(i, 5) != null){
//                            total_public += (double) input_data.getValueAt(i, 5);
//                        }
//                        
//                        if(input_data.getValueAt(i, 6) != null){
//                            total_cost += (double) input_data.getValueAt(i, 6);
//                        }
//                         
//                        if(input_data.getValueAt(i, 7) != null){
//                            total_sport += (double) input_data.getValueAt(i, 7);
//                        }
//                        
//                        if(input_data.getValueAt(i, 8) != null){
//                            total_member += (double) input_data.getValueAt(i, 8);
//                        }
//                    }
//                    else{
//                        break;
//                    }
//                }
//
//                total_amount.setText(amount+"");
//                total_public_amount.setText(total_public+"");
//                total_class_amount.setText(total_cost+"");
//                cost_field.setText(total_cost+"");
//                total_sport_amount.setText(total_sport+"");
//                total_membership_amount.setText(total_member+"");
                        
//                        DefaultTableModel model = (DefaultTableModel)input_data.getModel();
//                        model.addRow(new Object[]{null, null,null,null,null,null,null,null}); 
//                int row = input_data.getSelectedRow();
//                int column = input_data.getSelectedColumn();
//                
//                if(column == 4 ){//&& input_data.getValueAt(row, column)!= null){
//                    System.out.println(input_data.getValueAt(row, column));
//                    float public_amount = 0;
//                    float class_amount = 0;
//                    float sport_amount = 0;
//                    float member_amount = 0;
//
//                    switch(input_data.getValueAt(row, column).toString()){
//                        case "2440": 
//                            public_amount = 1437;
//                            class_amount = 600;
//                            sport_amount = 100;
//                            member_amount = 303;
//                            break;
//                        case "2464":
//                            public_amount = 1437;
//                            class_amount = 600;
//                            sport_amount = 100;
//                            member_amount = 303;
//                            break;
//                        case "700":
//                            class_amount = 600;
//                            sport_amount = 100;
//                            break;
//                        case "1740":
//                            public_amount = 1437;
//                            member_amount = 303;
//                            break;
//                        case "1764":
//                            public_amount = 1461;
//                            member_amount = 303;
//                            break;
//                    }
//
//                    input_data.setValueAt(public_amount, row, column+1);
//                    input_data.setValueAt(class_amount, row, column+2);
//                    input_data.setValueAt(sport_amount, row, column+3);
//                    input_data.setValueAt(member_amount, row, column+4);
//                }
//            }

//            @Override
//            public void keyTyped(KeyEvent e) {
//                int row = input_data.getSelectedRow();
//                int column = input_data.getSelectedColumn();
//                
//                if(column == 4 ){//&& input_data.getValueAt(row, column)!= null){
//                    System.out.println(input_data.getValueAt(row, column));
//                    float public_amount = 0;
//                    float class_amount = 0;
//                    float sport_amount = 0;
//                    float member_amount = 0;
//
//                    switch(input_data.getValueAt(row, column).toString()){
//                        case "2440": 
//                            public_amount = 1437;
//                            class_amount = 600;
//                            sport_amount = 100;
//                            member_amount = 303;
//                            break;
//                        case "2464":
//                            public_amount = 1437;
//                            class_amount = 600;
//                            sport_amount = 100;
//                            member_amount = 303;
//                            break;
//                        case "700":
//                            class_amount = 600;
//                            sport_amount = 100;
//                            break;
//                        case "1740":
//                            public_amount = 1437;
//                            member_amount = 303;
//                            break;
//                        case "1764":
//                            public_amount = 1461;
//                            member_amount = 303;
//                            break;
//                    }
//
//                    input_data.setValueAt(public_amount, row, column+1);
//                    input_data.setValueAt(class_amount, row, column+2);
//                    input_data.setValueAt(sport_amount, row, column+3);
//                    input_data.setValueAt(member_amount, row, column+4);
//                }
//            }

//        });
        
//        return_field.addCaretListener(new CaretListener() {
//
//            @Override
//            public void caretUpdate(CaretEvent e) {
//                if(cost_field.getText() != null && !return_field.getText().isEmpty()){
//                    double cost = Double.parseDouble(cost_field.getText());
//                    double return_cost = Double.parseDouble(return_field.getText());
//                    if(cost >= return_cost){
//                        balance_field.setText((cost-return_cost)+"");
//                    }
//                    else{
//                        balance_field.setText("");
//                    }
//                }
//            }
//
//        });

    }
    
    public void postInit(){
        ClassComboBoxController combo_box_listener = new ClassComboBoxController(this);
        class_combo_box.addActionListener(combo_box_listener);
        
        GradeComboBoxController gradeComboBoxController = new GradeComboBoxController(this);
        grade_combo_box.addActionListener(gradeComboBoxController);
        
        StudentIndexFieldController studentIndexFieldController = new StudentIndexFieldController(this);
        student_index_field.addActionListener(studentIndexFieldController);
        
        UpdateButtonController updateButtonController = new UpdateButtonController(this);
        update_button.addActionListener(updateButtonController);
        
        DeleteButtonController deleteButtonController = new DeleteButtonController(this);
        delete_button.addActionListener(deleteButtonController);
        
        NewButtonController newEntryButtonController = new NewButtonController(this);
        new_button.addActionListener(newEntryButtonController);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        input_data = new javax.swing.JTable();
        total_amount = new javax.swing.JTextField();
        total_public_amount = new javax.swing.JTextField();
        total_class_amount = new javax.swing.JTextField();
        total_sport_amount = new javax.swing.JTextField();
        total_membership_amount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        balance_field = new javax.swing.JTextField();
        delete_button = new javax.swing.JButton();
        grade_combo_box = new javax.swing.JComboBox();
        class_combo_box = new javax.swing.JComboBox();
        update_button = new javax.swing.JButton();
        new_button = new javax.swing.JButton();
        student_index_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1450, 735));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 0, 18)); // NOI18N
        jLabel1.setText("Student Index");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(370, 20, 160, 30);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        input_data.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        input_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Index No.", "Student Name", "Receipt No.", "Amount", "Public", "Class", "Sport", "Memebership"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        input_data.setEnabled(false);
        input_data.setRowHeight(20);
        jScrollPane1.setViewportView(input_data);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 1300, 440);

        total_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        total_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_amount.setMinimumSize(new java.awt.Dimension(6, 35));
        total_amount.setPreferredSize(new java.awt.Dimension(20, 35));
        total_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_amountActionPerformed(evt);
            }
        });
        jPanel1.add(total_amount);
        total_amount.setBounds(600, 510, 143, 30);

        total_public_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        total_public_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(total_public_amount);
        total_public_amount.setBounds(735, 510, 150, 30);

        total_class_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        total_class_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_class_amount.setPreferredSize(new java.awt.Dimension(140, 20));
        jPanel1.add(total_class_amount);
        total_class_amount.setBounds(880, 510, 145, 30);

        total_sport_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        total_sport_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_sport_amount.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel1.add(total_sport_amount);
        total_sport_amount.setBounds(1020, 510, 150, 30);

        total_membership_amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        total_membership_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(total_membership_amount);
        total_membership_amount.setBounds(1160, 510, 170, 30);

        jLabel2.setFont(new java.awt.Font("Trajan Pro", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(490, 510, 70, 30);

        jLabel5.setFont(new java.awt.Font("Trajan Pro", 0, 18)); // NOI18N
        jLabel5.setText("Remaining balance");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(960, 560, 200, 40);

        balance_field.setEditable(false);
        balance_field.setBackground(new java.awt.Color(255, 255, 255));
        balance_field.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        balance_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(balance_field);
        balance_field.setBounds(1180, 560, 150, 40);

        delete_button.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        delete_button.setForeground(new java.awt.Color(255, 0, 0));
        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });
        jPanel1.add(delete_button);
        delete_button.setBounds(820, 630, 160, 40);

        grade_combo_box.setFont(new java.awt.Font("Trajan Pro", 0, 18)); // NOI18N
        grade_combo_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 13" }));
        grade_combo_box.setMinimumSize(new java.awt.Dimension(96, 30));
        grade_combo_box.setPreferredSize(new java.awt.Dimension(96, 30));
        jPanel1.add(grade_combo_box);
        grade_combo_box.setBounds(30, 20, 160, 30);

        class_combo_box.setFont(new java.awt.Font("Trajan Pro", 0, 18)); // NOI18N
        class_combo_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
        class_combo_box.setEnabled(false);
        class_combo_box.setMinimumSize(new java.awt.Dimension(0, 0));
        class_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                class_combo_boxActionPerformed(evt);
            }
        });
        jPanel1.add(class_combo_box);
        class_combo_box.setBounds(220, 20, 90, 30);

        update_button.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        update_button.setText("Update");
        jPanel1.add(update_button);
        update_button.setBounds(1010, 630, 150, 40);

        new_button.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        new_button.setText("New");
        jPanel1.add(new_button);
        new_button.setBounds(1180, 630, 150, 40);

        student_index_field.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        student_index_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                student_index_fieldActionPerformed(evt);
            }
        });
        jPanel1.add(student_index_field);
        student_index_field.setBounds(540, 20, 220, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void total_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_amountActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
//        EnterDataButtonController data_controller = new EnterDataButtonController();
        
        
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void class_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_class_combo_boxActionPerformed
        input_data.setEnabled(true);
    }//GEN-LAST:event_class_combo_boxActionPerformed

    private void student_index_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_student_index_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_student_index_fieldActionPerformed

    public JTable getTable(){
        return input_data;
    }

    public JTextField getBalance_field() {
        return balance_field;
    }

    public JComboBox getClass_combo_box() {
        return class_combo_box;
    }

    public JComboBox getGrade_combo_box() {
        return grade_combo_box;
    }

    public JTextField getStudent_index_field() {
        return student_index_field;
    }

    public JTextField getTotal_amount() {
        return total_amount;
    }

    public JTextField getTotal_class_amount() {
        return total_class_amount;
    }

    public JTextField getTotal_membership_amount() {
        return total_membership_amount;
    }

    public JTextField getTotal_public_amount() {
        return total_public_amount;
    }

    public JTextField getTotal_sport_amount() {
        return total_sport_amount;
    }
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        
//    }
//    
    public static SearchEntryWindow getInstance() {
        searchEntryWindow.setLocationRelativeTo(null);
        searchEntryWindow.setResizable(false);
        return searchEntryWindow;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balance_field;
    private javax.swing.JComboBox class_combo_box;
    private javax.swing.JButton delete_button;
    private javax.swing.JComboBox grade_combo_box;
    private javax.swing.JTable input_data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton new_button;
    private javax.swing.JTextField student_index_field;
    private javax.swing.JTextField total_amount;
    private javax.swing.JTextField total_class_amount;
    private javax.swing.JTextField total_membership_amount;
    private javax.swing.JTextField total_public_amount;
    private javax.swing.JTextField total_sport_amount;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables
}
