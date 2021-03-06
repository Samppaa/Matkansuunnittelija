package com.matkansuunnittelija.userinterface;

import com.matkansuunnittelija.errormanager.StatusCode;
import com.matkansuunnittelija.controllers.TravelPlanController;
import com.matkansuunnittelija.travelplanobjects.DayEvent;
import com.matkansuunnittelija.travelplanobjects.TravelPlan;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Käyttöliittymäikkuna tietyn päivän aktiviteettien muokkaamiseen
 *
 * @author Samuli
 */
public class EditDayActivitiesGUI extends javax.swing.JDialog {

    private final TravelPlanController controller;
    private DefaultListModel listViewModel;
    private final TravelPlan plan;
    private final String dayPlanName;

    private String getListFormattedStringForDayEvent(String name, String time) {
        return name + " - " + time;
    }

    private String getNameFromFormattedStringForDayEvent(String formattedString) {
        return formattedString.split(" - ")[0];
    }

    private void initListViewWithActivities() {
        listViewModel = (DefaultListModel) activitiesList.getModel();
        listViewModel.removeAllElements();

        for (DayEvent e : plan.getDayPlan(dayPlanName).getDayEvents()) {
            listViewModel.addElement(getListFormattedStringForDayEvent(e.getName(), e.getTime()));
        }
    }

    public void addActivityToTheList(String name, String time) {
        listViewModel.addElement(getListFormattedStringForDayEvent(name, time));
    }

    private void openAddNewActivityMenu() {
        AddNewActivityGUI addNewActivity = new AddNewActivityGUI(this, true, plan, dayPlanName);
        addNewActivity.setVisible(true);
    }

    private String generateTitleForWindow() {
        return plan.getName() + " - " + dayPlanName + " - Aktiviteetit";
    }

    /**
     * Creates new form EditDayActivitiesGUI
     * @param parent
     * @param modal
     * @param plan
     * @param dayPlanName
     */
    public EditDayActivitiesGUI(javax.swing.JDialog parent, boolean modal, TravelPlan plan, String dayPlanName) {
        super(parent, modal);
        initComponents();
        this.plan = plan;
        this.dayPlanName = dayPlanName;
        this.setTitle(generateTitleForWindow());
        this.controller = TravelPlanController.getInstance();
        initListViewWithActivities();
    }

    private DayEvent getSelectedEvent() {
        int index = activitiesList.getSelectedIndex();
        DayEvent event = controller.getTravelPlan(plan.getName()).getDayPlan(dayPlanName).getDayEventWithName(getNameFromFormattedStringForDayEvent((String) listViewModel.getElementAt(index)));
        return event;
    }

    private String getDescriptionForSelectedItem() {
        DayEvent event = getSelectedEvent();
        if (event != null) {
            return event.getDescription();
        }

        return "";
    }

    private String getNameForSelectedItem() {
        DayEvent event = getSelectedEvent();
        if (event != null) {
            return event.getName();
        }

        return "";
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
        activitiesList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        activityDescriptionTextArea = new javax.swing.JTextArea();
        activityDescriptionLabel = new javax.swing.JLabel();
        addNewActivityButton = new javax.swing.JButton();
        deleteSelectedActivityButton = new javax.swing.JButton();
        activitiesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        activitiesList.setModel(new DefaultListModel());
        activitiesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                dayActivitySelected(evt);
            }
        });
        jScrollPane1.setViewportView(activitiesList);

        activityDescriptionTextArea.setEditable(false);
        activityDescriptionTextArea.setColumns(20);
        activityDescriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(activityDescriptionTextArea);

        activityDescriptionLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        activityDescriptionLabel.setText("Valitun aktiviteetin kuvaus");

        addNewActivityButton.setText("Lisää uusi aktiviteetti");
        addNewActivityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActivityButtonActionPerformed(evt);
            }
        });

        deleteSelectedActivityButton.setText("Poista valittu aktiviteetti");
        deleteSelectedActivityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedActivityButtonActionPerformed(evt);
            }
        });

        activitiesLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        activitiesLabel.setText("Aktiviteetit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(deleteSelectedActivityButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 337, Short.MAX_VALUE)
                        .addComponent(addNewActivityButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activityDescriptionLabel)
                            .addComponent(activitiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(activitiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(activityDescriptionLabel)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNewActivityButton)
                    .addComponent(deleteSelectedActivityButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNewActivityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActivityButtonActionPerformed
        openAddNewActivityMenu();
    }//GEN-LAST:event_addNewActivityButtonActionPerformed

    private void dayActivitySelected(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_dayActivitySelected
        if (activitiesList.getSelectedIndex() != -1) {
            activityDescriptionTextArea.setText(getDescriptionForSelectedItem());
        } else {
            activityDescriptionTextArea.setText(" ");
        }
    }//GEN-LAST:event_dayActivitySelected

    private void deleteSelectedActivityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectedActivityButtonActionPerformed
        if (activitiesList.getSelectedIndex() != -1) {
            StatusCode code = controller.deleteDayEventFromDayPlan(plan.getName(), dayPlanName, getNameForSelectedItem());
            switch (code) {
                case STATUS_TRAVEL_PLAN_REMOVE_EVENT_SUCCEED:
                    int index = activitiesList.getSelectedIndex();
                    listViewModel.removeElementAt(index);
                    break;
                case STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND:
                    JOptionPane.showMessageDialog(null, "Vakava virhe: Tiedostoa ei löytynyt.");
                    break;
            }
        }
    }//GEN-LAST:event_deleteSelectedActivityButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activitiesLabel;
    private javax.swing.JList activitiesList;
    private javax.swing.JLabel activityDescriptionLabel;
    private javax.swing.JTextArea activityDescriptionTextArea;
    private javax.swing.JButton addNewActivityButton;
    private javax.swing.JButton deleteSelectedActivityButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
