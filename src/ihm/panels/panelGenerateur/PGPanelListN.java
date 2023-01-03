package ihm.panels.panelGenerateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.panels.PanelNoeud;
import metier.Noeud;


public class PGPanelListN extends JPanel 
{
    private Controleur ctrl;

    private PanelNoeud           panelNoeud;

    private JButton              btnAjouter;
    private JButton              btnCouleur;
    private JButton              btnSupprimer;
    private JList<Noeud>         listNoeuds;
    private JLabel               lblCouleur;
    private JLabel               lblNom;
    private JLabel               lblPosition;
    private JLabel               lblPositionNom;
    private JScrollPane          lstNoeud;
    private JTextField           txtNom;
    private TextFieldOnlyInteger txtPosNomX;
    private TextFieldOnlyInteger txtPosNomY;
    private TextFieldOnlyInteger txtPosX;
    private TextFieldOnlyInteger txtPosY;
    /**
     * Creates new form PGPanelListN
     */
    public PGPanelListN(Controleur ctrl)
    {   
        this.ctrl = ctrl;

		List<Noeud> lstNoeuds = ctrl.getNoeuds();
        this.lstNoeud       = new JScrollPane         (    );
        this.listNoeuds     = new JList<Noeud>        (    );
        this.lblNom         = new JLabel              (    );
        this.lblPosition    = new JLabel              (    );
        this.lblPositionNom = new JLabel              (    );
        this.lblCouleur     = new JLabel              (    );
        this.txtNom         = new JTextField          (    );
        this.txtPosY        = new TextFieldOnlyInteger(ctrl);
        this.txtPosX        = new TextFieldOnlyInteger(ctrl);
        this.txtPosNomX     = new TextFieldOnlyInteger(ctrl);
        this.txtPosNomY     = new TextFieldOnlyInteger(ctrl);
        this.btnCouleur     = new JButton             (    );
        this.btnAjouter     = new JButton             (    );
        this.btnSupprimer   = new JButton             (    );
        this.panelNoeud     = null;


        this.listNoeuds.setModel(new AbstractListModel<Noeud>()
        {
            public int getSize()
            {
                return lstNoeuds.size();
            }

            public Noeud getElementAt(int index)
            {
                return lstNoeuds.get(index);
            }
        });

        this.listNoeuds.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                Noeud noeudSelected = listNoeuds.getSelectedValue();

				if (noeudSelected == null && lstNoeuds.size() != 0) 
					noeudSelected = lstNoeuds.get(0);
				
				if (noeudSelected != null)
				{
					txtNom      .setText      (noeudSelected.getNom    ()   );
					txtPosX     .setText      (noeudSelected.getX      ()+"");
					txtPosY     .setText      (noeudSelected.getY      ()+"");
					txtPosNomX  .setText      (noeudSelected.getXNom   ()+"");
					txtPosNomY  .setText      (noeudSelected.getYNom   ()+"");
					btnCouleur  .setBackground(noeudSelected.getCouleur()   );
				}
				else
				{
					txtNom      .setText      (""   );
					txtPosX     .setText      (""   );
					txtPosY     .setText      (""   );
					txtPosNomX  .setText      (""   );
					txtPosNomY  .setText      (""   );
					btnCouleur  .setBackground(Color.WHITE);
				}
            }
        });

        this.listNoeuds.setSelectedIndex(0);

        this.lstNoeud.setViewportView(listNoeuds);

        this.lblNom.setText("Nom");
        this.lblNom.setFont(new Font("Segoe UI", 1, 12));

        this.lblPosition.setText("Position");
        this.lblPosition.setFont(new Font("Segoe UI", 1, 12));

        this.lblPositionNom.setText("Position Nom");
        this.lblPositionNom.setFont(new Font("Segoe UI", 1, 12));

        this.lblCouleur.setText("Couleur");
        this.lblCouleur.setFont(new Font("Segoe UI", 1, 12));

        this.txtNom.setColumns(9);
        this.txtNom.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                txtNomActionPerformed(evt);
            }
        });

        this.txtPosY.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                txtPosYActionPerformed(evt);
            }
        });

        this.txtPosX.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                txtPosXActionPerformed(evt);
            }
        });

        this.txtPosNomX.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                txtPosNomXActionPerformed(evt);
            }
        });

        this.txtPosNomY.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                txtPosNomYActionPerformed(evt);
            }
        });

        this.btnCouleur.setText("Couleur");
        this.btnCouleur.setOpaque(true);
        this.btnCouleur.setFocusPainted(false);
        this.btnCouleur.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                btnCouleurActionPerformed(evt);
            }
        });

        this.btnAjouter.setText("Ajouter");
        this.btnAjouter.setFocusPainted(false);
        this.btnAjouter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                btnAjouterActionPerformed(evt);
            }
        });

        this.btnSupprimer.setText("Supprimer");
        this.btnSupprimer.setFocusPainted(false);
        this.btnSupprimer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                btnSupprimerActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAjouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lstNoeud, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNom)
                            .addComponent(lblPosition)
                            .addComponent(lblCouleur))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPositionNom)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCouleur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPosNomX, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPosNomY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPosX, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPosY, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNom)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPosition)
                            .addComponent(txtPosX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPosY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPositionNom)
                            .addComponent(txtPosNomX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPosNomY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCouleur)
                            .addComponent(btnCouleur)))
                    .addComponent(lstNoeud, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }

    private void txtNomActionPerformed      (ActionEvent e)
    {
        Noeud noeud = this.listNoeuds.getSelectedValue();
        noeud.setNom(this.txtNom.getText());

		this.majIHM();
        this.ctrl.majIHMPlateau();
    }
    private void txtPosXActionPerformed     (ActionEvent e)
    {
        Noeud noeud = this.listNoeuds.getSelectedValue();
        noeud.setXY(Integer.parseInt(this.txtPosX.getText()),Integer.parseInt(this.txtPosY.getText()));
        this.ctrl.majIHMPlateau();
    }
    private void txtPosYActionPerformed     (ActionEvent e)
    {
        Noeud noeud = this.listNoeuds.getSelectedValue();
        noeud.setXY(Integer.parseInt(this.txtPosX.getText()),Integer.parseInt(this.txtPosY.getText()));
        this.ctrl.majIHMPlateau();
    }
    private void txtPosNomXActionPerformed  (ActionEvent e)
    {
        Noeud noeud = this.listNoeuds.getSelectedValue();
        noeud.setXYNom(Integer.parseInt(this.txtPosNomX.getText()),Integer.parseInt(this.txtPosNomY.getText()));
        this.ctrl.majIHMPlateau();
    }
    private void txtPosNomYActionPerformed  (ActionEvent e)
    {
        Noeud noeud = this.listNoeuds.getSelectedValue();
        noeud.setXYNom(Integer.parseInt(this.txtPosNomX.getText()),Integer.parseInt(this.txtPosNomY.getText()));
        this.ctrl.majIHMPlateau();
    }
    private void btnCouleurActionPerformed  (ActionEvent e)
    {
        JButton button = (JButton) e.getSource();
        Color oldColor = button.getBackground();
        Color newColor = JColorChooser.showDialog(this, "Choisir une couleur",oldColor);
        this.btnCouleur.setBackground(newColor);
        this.listNoeuds.getSelectedValue().setCouleur(newColor);
        this.ctrl.majIHMPlateau();
    }
    private void btnSupprimerActionPerformed(ActionEvent e)
	{
		int index = this.listNoeuds.getSelectedIndex();

        this.ctrl.supprimerNoeud(index);
		this.majIHM();
	}


    private void btnAjouterActionPerformed(ActionEvent e)
    {
        JDialog dialog = new JDialog(this.ctrl.getIHM(),"Ajouter Noeud");
        dialog.setSize(400,200);
        dialog.add(this.panelNoeud = new PanelNoeud(ctrl));

        dialog.setVisible(true);
    }


    public void appliquerTheme()
    {
        if (this.panelNoeud != null) { this.panelNoeud.appliquerTheme(); }

        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background       = theme.get("background").get(0);
        Color labelForeColor   = theme.get("labels"    ).get(0);
		Color labelBackColor   = theme.get("labels"    ).get(1);
        Color saisiForeColor   = theme.get("saisies"   ).get(0);
		Color saisiBackColor   = theme.get("saisies"   ).get(1);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


        this.setBackground(background);

        this.lstNoeud      .setForeground(labelForeColor);
        this.lstNoeud      .setBackground(labelBackColor);

        this.listNoeuds    .setForeground         (saisiForeColor);
        this.listNoeuds    .setBackground         (background    );
        this.listNoeuds    .setSelectionForeground(background    );
        this.lblNom        .setForeground(labelForeColor);
        this.lblNom        .setBackground(labelBackColor);

        this.lblPosition   .setOpaque(false);
        this.lblPosition   .setForeground(labelForeColor);

        this.lblPositionNom.setOpaque(false);
        this.lblPositionNom.setBackground(labelBackColor);

        this.lblCouleur    .setOpaque(false);
        this.lblCouleur    .setBackground(labelBackColor);

        this.btnCouleur    .setForeground(btnForeColor);
        this.btnCouleur    .setBackground(btnBackColor);
        this.btnCouleur    .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.btnAjouter    .setForeground(btnForeColor);
        this.btnAjouter    .setBackground(btnBackColor);
        this.btnAjouter    .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.btnSupprimer  .setForeground(btnForeColor);
        this.btnSupprimer  .setBackground(btnBackColor);
        this.btnSupprimer  .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.txtNom        .setOpaque(true);
        this.txtNom        .setBorder(null);
        this.txtNom        .setForeground(saisiForeColor);
        this.txtNom        .setBackground(saisiBackColor);
        this.txtNom        .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosY       .setOpaque(true);
        this.txtPosY       .setBorder(null);
        this.txtPosY       .setForeground(saisiForeColor);
        this.txtPosY       .setBackground(saisiBackColor);
        this.txtPosY       .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosX       .setOpaque(true);
        this.txtPosX       .setBorder(null);
        this.txtPosX       .setForeground(saisiForeColor);
        this.txtPosX       .setBackground(saisiBackColor);
        this.txtPosX       .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosNomX    .setOpaque(true);
        this.txtPosNomX    .setBorder(null);
        this.txtPosNomX    .setForeground(saisiForeColor);
        this.txtPosNomX    .setBackground(saisiBackColor);
        this.txtPosNomX    .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosNomY    .setOpaque(true);
        this.txtPosNomY    .setBorder(null);
        this.txtPosNomY    .setForeground(saisiForeColor);
        this.txtPosNomY    .setBackground(saisiBackColor);
        this.txtPosNomY    .setDisabledTextColor(new Color(255, 0, 0));
    }

	public void majIHM()
	{
		this.listNoeuds.setModel(new AbstractListModel<Noeud>()
        {
            List<Noeud> lstNoeuds = ctrl.getNoeuds();

            public int getSize()
            {
                return lstNoeuds.size();
            }

            public Noeud getElementAt(int index)
            {
                return lstNoeuds.get(index);
            }
        });
	}

	public void selectNoeud(int index)
	{
		this.listNoeuds.setSelectedIndex(index);
	}
}