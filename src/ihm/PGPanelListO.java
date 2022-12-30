package ihm;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import controleur.Controleur;


public class PGPanelListO extends  JPanel
{
    /**
     * Creates new form PGPanelListO
     */
    public PGPanelListO(Controleur ctrl)
    {
        lstNoeud = new  JScrollPane();
        jList1 = new  JList<>();
        btnAjouter = new  JButton();
        btnSupprimer = new  JButton();
        comboBoxListNoeudB = new  JComboBox<>();
        lblNoeudA = new  JLabel();
        lblNoeudB = new  JLabel();
        comboBoxListNoeudA = new  JComboBox<>();
        txtPoint = new  JTextField();
        lblPoint = new  JLabel();
        lblRecto = new  JLabel();
        lblVerso = new  JLabel();
        btnImgRecto = new  JButton();
        btnImgVerso = new  JButton();

        setBackground(new java.awt.Color(68, 71, 90));

        jList1.setModel(new  AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstNoeud.setViewportView(jList1);

        btnAjouter.setBackground(new java.awt.Color(40, 42, 54));
        btnAjouter.setForeground(new java.awt.Color(255, 255, 255));
        btnAjouter.setText("Ajouter");
        btnAjouter.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED));
        btnAjouter.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        btnSupprimer.setBackground(new java.awt.Color(40, 42, 54));
        btnSupprimer.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer.setText("Supprimer");
        btnSupprimer.setBorder( BorderFactory.createBevelBorder(BevelBorder.RAISED));
        btnSupprimer.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        comboBoxListNoeudB.setBackground(new java.awt.Color(40, 42, 54));
        comboBoxListNoeudB.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxListNoeudB.setModel(new  DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxListNoeudB.setBorder(null);
        comboBoxListNoeudB.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                comboBoxListNoeudBActionPerformed(evt);
            }
        });

        lblNoeudA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNoeudA.setForeground(new java.awt.Color(255, 255, 255));
        lblNoeudA.setText("Noeud A");

        lblNoeudB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNoeudB.setForeground(new java.awt.Color(255, 255, 255));
        lblNoeudB.setText("Noeud B");

        comboBoxListNoeudA.setBackground(new java.awt.Color(40, 42, 54));
        comboBoxListNoeudA.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxListNoeudA.setModel(new  DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxListNoeudA.setBorder(null);
        comboBoxListNoeudA.setFocusable(false);
        comboBoxListNoeudA.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                comboBoxListNoeudAActionPerformed(evt);
            }
        });

        txtPoint.setBackground(new java.awt.Color(40, 42, 54));
        txtPoint.setForeground(new java.awt.Color(255, 255, 255));
        txtPoint.setBorder(null);
        txtPoint.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                txtPointActionPerformed(evt);
            }
        });

        lblPoint.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPoint.setForeground(new java.awt.Color(255, 255, 255));
        lblPoint.setText("Point");

        lblRecto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRecto.setForeground(new java.awt.Color(255, 255, 255));
        lblRecto.setText("Recto");

        lblVerso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVerso.setForeground(new java.awt.Color(255, 255, 255));
        lblVerso.setText("Verso");

        btnImgRecto.setBackground(new java.awt.Color(40, 42, 54));
        btnImgRecto.setForeground(new java.awt.Color(255, 255, 255));
        btnImgRecto.setText("Image");
        btnImgRecto.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED));
        btnImgRecto.setFocusPainted(false);
        btnImgRecto.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnImgRectoActionPerformed(evt);
            }
        });

        btnImgVerso.setBackground(new java.awt.Color(40, 42, 54));
        btnImgVerso.setForeground(new java.awt.Color(255, 255, 255));
        btnImgVerso.setText("Image");
        btnImgVerso.setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED));
        btnImgVerso.setFocusPainted(false);
        btnImgVerso.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnImgVersoActionPerformed(evt);
            }
        });

         GroupLayout layout = new  GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSupprimer,  GroupLayout.PREFERRED_SIZE, 80,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(lstNoeud,  GroupLayout.PREFERRED_SIZE, 148,  GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.TRAILING, false)
                    .addGroup( GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNoeudA)
                        .addGap(32, 32, 32)
                        .addComponent(comboBoxListNoeudA, 0,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup( GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNoeudB)
                        .addGap(33, 33, 33)
                        .addComponent(comboBoxListNoeudB,  GroupLayout.PREFERRED_SIZE, 82,  GroupLayout.PREFERRED_SIZE))
                    .addGroup( GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblPoint)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPoint,  GroupLayout.PREFERRED_SIZE, 82,  GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(lblRecto)
                            .addComponent(lblVerso))
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(btnImgVerso,  GroupLayout.PREFERRED_SIZE, 50,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImgRecto,  GroupLayout.PREFERRED_SIZE, 50,  GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAjouter,  GroupLayout.PREFERRED_SIZE, 80,  GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addComponent(lstNoeud,  GroupLayout.PREFERRED_SIZE, 100,  GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoeudA)
                            .addComponent(comboBoxListNoeudA,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRecto)
                            .addComponent(btnImgRecto))
                        .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoeudB)
                            .addComponent(comboBoxListNoeudB,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVerso)
                            .addComponent(btnImgVerso))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(txtPoint,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPoint))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupprimer,  GroupLayout.PREFERRED_SIZE, 20,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjouter,  GroupLayout.PREFERRED_SIZE, 20,  GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void btnAjouterActionPerformed( ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void btnSupprimerActionPerformed( ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void comboBoxListNoeudBActionPerformed( ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void comboBoxListNoeudAActionPerformed( ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void txtPointActionPerformed( ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnImgRectoActionPerformed( ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnImgVersoActionPerformed( ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           


    // Variables declaration - do not modify                     
    private  JButton btnAjouter;
    private  JButton btnImgRecto;
    private  JButton btnImgVerso;
    private  JButton btnSupprimer;
    private  JComboBox<String> comboBoxListNoeudA;
    private  JComboBox<String> comboBoxListNoeudB;
    private  JList<String> jList1;
    private  JLabel lblNoeudA;
    private  JLabel lblNoeudB;
    private  JLabel lblPoint;
    private  JLabel lblRecto;
    private  JLabel lblVerso;
    private  JScrollPane lstNoeud;
    private  JTextField txtPoint;
    // End of variables declaration                   
}