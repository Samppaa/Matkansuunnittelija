package com.matkansuunnittelija.userinterface;

import com.matkansuunnittelija.StatusCode;
import com.matkansuunnittelija.controllers.TravelPlanController;
import javax.swing.JOptionPane;

/**
 * Käyttöliittymäikkuna uuden matkasuunnitelman lisäämiseen
 * @author Samuli
 */
public class AddNewTravelPlanGUI extends javax.swing.JDialog {

    private TravelPlanController travelPlanController;
    private MatkansuunnittelijaGUI parent;

    /**
     * Creates new form AddNewTravelPlanGUI
     *
     * @param parent
     */
    public AddNewTravelPlanGUI(java.awt.Frame parent, boolean modal, TravelPlanController controller) {
        super(parent, modal);
        this.parent = (MatkansuunnittelijaGUI) parent;
        travelPlanController = controller;
        initComponents();
    }

    public AddNewTravelPlanGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Uusi matkasuunnitelma");

        jLabel1.setText("Matkasuunnitelman nimi:");

        jButton2.setText("Luo uusi matkasuunnitelma");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(51, 51, 0));
        jLabel4.setText("Päivämäärän tulee olla muotoa dd.mm.yyyy(esim. 10.11.2012)");

        jLabel5.setText("Matkan alkamispäivämäärä:");

        jLabel6.setText("Matkan päättymispäivämäärä:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                    .addComponent(jTextField2))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        StatusCode code = travelPlanController.createNewTravelPlan(jTextField1.getText(), jTextField2.getText(), jTextField3.getText());
        switch (code) {
            case STATUS_TRAVEL_PLAN_CREATE_FAIL_ALREADY_EXISTS:
                JOptionPane.showMessageDialog(null, "Matkasuunnitelma samalla nimellä on jo olemassa!");
                break;
            case STATUS_TRAVEL_PLAN_CREATE_FAIL_FILE_NOT_FOUND:
                JOptionPane.showMessageDialog(null, "Vakava virhe: Tietokanta tiedostoa ei löytynyt");
                break;
            case STATUS_TRAVEL_PLAN_CREATE_FAIL_DATE_DIFFERENCE_TOO_LONG:
                JOptionPane.showMessageDialog(null, "Matkan pituus on liian suuri, maksimipituus on 14 päivää");
                break;
            case STATUS_TRAVEL_PLAN_CREATE_FAIL_START_DATE_AFTER_END_DATE:
                JOptionPane.showMessageDialog(null, "Matkan päättymispäivä on ennen alkamispäivää tai sama päivä");
                break;
            case STATUS_TRAVEL_PLAN_CREATE_SUCCEED:
                // Avaa muokkausikkuna tässä
                this.setVisible(false);
                this.dispose();
                this.parent.openEditTravelPlanMenu(jTextField1.getText());
                break;
            case STATUS_TRAVREL_PLAN_CREATE_FAIL_DATE_WRONG_FORMAT:
                JOptionPane.showMessageDialog(null, "Päivämäärä on väärää muotoa. Oikea muoto on dd.mm.yyyy. Esimerkiksi 12.11.2012");
                break;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddNewTravelPlanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewTravelPlanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewTravelPlanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewTravelPlanGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddNewTravelPlanGUI dialog = new AddNewTravelPlanGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
