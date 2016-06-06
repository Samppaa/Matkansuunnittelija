/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matkansuunnittelija.userinterface;

import com.matkansuunnittelija.controllers.TravelPlanController;
import com.matkansuunnittelija.travelplanobjects.DayPlan;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import javax.swing.DefaultListModel;

/**
 *
 * @author Samuli
 */
public class EditTravelPlanGUI extends javax.swing.JDialog {

    private DefaultListModel listViewModel;
    private final TravelPlanController controller;
    private TravelPlan plan;

    private void initListView() {
        listViewModel = (DefaultListModel) jList1.getModel();
        listViewModel.removeAllElements();
        for (DayPlan d : plan.getDayPlansAsList()) {
            listViewModel.addElement(d.getName());
        }
    }
    
    private void updateTravelPlan() {
        plan = controller.getTravelPlan(plan.getName());
    }

    /**
     * Creates new form EditTravelPlanGUI
     * @param parent
     * @param modal
     * @param travelPlanName
     */
    public EditTravelPlanGUI(java.awt.Frame parent, boolean modal, String travelPlanName) {
        super(parent, modal);
        initComponents();
        MatkansuunnittelijaGUI temp = (MatkansuunnittelijaGUI) parent;
        controller = temp.getTravelPlanController();
        plan = controller.getTravelPlan(travelPlanName);
        this.setTitle(travelPlanName + " - muokkaus");
        initListView();
    }
    
    private String getSelectedDayName() {
        int index = jList1.getSelectedIndex();
        if (index == -1) {
            return null;
        }
        String dayName = (String) listViewModel.getElementAt(index);
        return dayName;
    }
    
    private void openEditActivitiesWindow() {
        String selectedDayName = getSelectedDayName();
        if (selectedDayName != null) {
            updateTravelPlan();
            EditDayActivitiesGUI editDayActivities = new EditDayActivitiesGUI(this, true, plan, selectedDayName);
            editDayActivities.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Matkasuunnitelman muokkaus");

        jList1.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Muokkaa valitun päivän suunnitelmaa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openEditActivitiesWindow();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
