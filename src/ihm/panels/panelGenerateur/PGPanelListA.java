  package ihm.panels.panelGenerateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.frames.FrameCouleurChooser;
import ihm.panels.PanelArete;
import metier.Arete;
import metier.Noeud;


public class PGPanelListA extends JPanel
{
    private Controleur        ctrl;

    private JButton           btnAjouter;
    private JButton           btnCouleur1;
    private JButton           btnCouleur2;
    private JButton           btnSupprimer;
    private JCheckBox         cb2Sens;
    private JComboBox<Noeud>  comboBoxListNoeudA;
    private JComboBox<Noeud>  comboBoxListNoeudB;
    private JList<Arete>      jListAretes;
    private JScrollPane       jScrollPane1;
    private JLabel            lbl2Sens;
    private JLabel            lblCouleur1;
    private JLabel            lblCouleur2;
    private JLabel            lblDistance;
    private JLabel            lblNoeudA;
    private JLabel            lblNoeudB;
    private TextFieldOnlyInteger txtDistance;
	private PanelArete        panelArete; 

    private List<Noeud>       lstNoeuds;
    private List<Arete>       lstAretes;

	private int couleurAttendu = 1;
	private boolean estUneMaj = false;


    /**
     * Creates new form PGPanelListA
     */
    public PGPanelListA(Controleur ctrl)
    {
        this.ctrl               = ctrl;

        this.jScrollPane1       = new JScrollPane      ();
        this.jListAretes        = new JList<Arete>    ();
        this.lblNoeudA          = new JLabel           ();
        this.lblNoeudB          = new JLabel           ();
        this.lbl2Sens           = new JLabel           ();
        this.lblCouleur1        = new JLabel           ();
        this.lblDistance        = new JLabel           ();
        this.lblCouleur2        = new JLabel           ();
        this.comboBoxListNoeudA = new JComboBox<Noeud>();
        this.comboBoxListNoeudB = new JComboBox<Noeud>();
        this.cb2Sens            = new JCheckBox        ();
        this.txtDistance        = new TextFieldOnlyInteger(ctrl);
		this.panelArete		    = null;
        this.btnCouleur1        = new JButton          ();
        this.btnCouleur2        = new JButton          ();
        this.btnAjouter         = new JButton          ();
        this.btnSupprimer       = new JButton          ();
        this.lstNoeuds          = ctrl.getNoeuds       ();
        this.lstAretes          = ctrl.getAretes       ();
        

        /* jListAretes */       
        this.jListAretes.setModel(new AbstractListModel<Arete>()
        {
            public int getSize(){ return lstAretes.size(); }
            public Arete getElementAt(int index) { return lstAretes.get(index);}
        });

		this.jListAretes.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
				Arete areteSelected = null;
				try {
					areteSelected = jListAretes.getSelectedValue();
				} catch(Exception ex) {}
				

				if (areteSelected == null && lstAretes.size() != 0) 
					areteSelected = lstAretes.get(0);
				
				if (areteSelected != null)
				{
					estUneMaj = true;
        			comboBoxListNoeudA.setSelectedIndex(lstNoeuds.indexOf(areteSelected.getNoeud1()));
        			comboBoxListNoeudB.setSelectedIndex(lstNoeuds.indexOf(areteSelected.getNoeud2()));
					estUneMaj = false;


					txtDistance.setText("" + areteSelected.getDistance());
					btnCouleur1.setBackground(areteSelected.getCouleur1());

					if (areteSelected.getCouleur2() != null)
					{
						cb2Sens.setSelected(true);
						btnCouleur2.setBackground(areteSelected.getCouleur2());
					}
					else
					{
						cb2Sens.setSelected(false);
						btnCouleur2.setBackground(null);
					}
				}
				else
				{
					effacerForm();
				}
			}
		});


        /* Ajout de la list1 dans un JScrollPane */
        this.jScrollPane1.setViewportView(jListAretes);

        /* lblNoeudA */
        this.lblNoeudA.setText("Noeud A");
        this.lblNoeudA.setFont(new Font("Segoe UI", 1, 12));

        /* lblNoeudB */
        this.lblNoeudB.setText("Noeud B");
        this.lblNoeudB.setFont(new Font("Segoe UI", 1, 12));

        /* lbl2Sens */
        this.lbl2Sens.setText("2 sens");
        this.lbl2Sens.setFont(new Font("Segoe UI", 1, 12));

        /* lblCouleurAB */
        this.lblCouleur1.setText("Couleur 1");
        this.lblCouleur1.setFont(new Font("Segoe UI", 1, 12));

        /* lblDistance */
        this.lblDistance.setText("Distance");
        this.lblDistance.setFont(new Font("Segoe UI", 1, 12));

        /* lblCouleurBA */
        this.lblCouleur2.setText("Couleur 2");
        this.lblCouleur2.setFont(new Font("Segoe UI", 1, 12));

        /* comboBoxListNoeudA */
        Noeud[] tabNoeudA = lstNoeuds.toArray(new Noeud[0]);
        Noeud[] tabNoeudB = lstNoeuds.toArray(new Noeud[0]);
  

        this.comboBoxListNoeudA.setModel(new DefaultComboBoxModel<>(tabNoeudA));
		if (lstAretes.size() != 0)
			this.comboBoxListNoeudA.setSelectedIndex(lstNoeuds.indexOf(lstAretes.get(0).getNoeud1()));
        this.comboBoxListNoeudA.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                comboBoxListNoeudAActionPerformed(evt);
            }
        });

        this.comboBoxListNoeudB.setModel(new DefaultComboBoxModel<>(tabNoeudB));
		if (lstAretes.size() != 0)
			this.comboBoxListNoeudB.setSelectedIndex(lstNoeuds.indexOf(lstAretes.get(0).getNoeud2()));
        this.comboBoxListNoeudB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                comboBoxListNoeudBActionPerformed(evt);
            }
        });

        this.cb2Sens.setSelected(false);
        this.cb2Sens.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                cb2SensActionPerformed(evt);
            }
        });

		if (lstAretes.size() != 0)
			this.txtDistance.setText("" + lstAretes.get(0).getDistance());
        this.txtDistance.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                txtDistanceActionPerformed(evt);
            }
        });

        this.btnCouleur1.setText("Couleur");
        this.btnCouleur1.setFocusPainted(false);
        this.btnCouleur1.addActionListener(e ->
        {
            selectColor1();
        });

        this.btnCouleur2.setText("Couleur");
        this.btnCouleur2.setFocusPainted(false);
		this.btnCouleur2.setEnabled(false);
        this.btnCouleur2.addActionListener(e ->
        {
            selectColor2();
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
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(this.btnSupprimer, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.btnAjouter, GroupLayout.DEFAULT_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(this.jScrollPane1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.lbl2Sens)
                        .addGap(47, 47, 47)
                        .addComponent(this.cb2Sens))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(this.lblNoeudA)
                            .addGap(32, 32, 32)
                            .addComponent(this.comboBoxListNoeudA, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(this.lblNoeudB)
                            .addGap(33, 33, 33)
                            .addComponent(this.comboBoxListNoeudB, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.lblDistance)
                        .addGap(25, 25, 25)
                        .addComponent(this.txtDistance, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(this.lblCouleur2)
                            .addGap(18, 18, 18)
                            .addComponent(this.btnCouleur2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(this.lblCouleur1)
                            .addGap(18, 18, 18)
                            .addComponent(this.btnCouleur1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10,10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.lblNoeudA)
                            .addComponent(this.comboBoxListNoeudA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(this.lblDistance)
                            .addComponent(this.txtDistance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(this.lblNoeudB)
                            .addComponent(this.comboBoxListNoeudB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(this.lblCouleur1)
                            .addComponent(this.btnCouleur1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.lbl2Sens)
                                .addComponent(this.cb2Sens, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(this.lblCouleur2))
                            .addComponent(this.btnCouleur2)))
                    .addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.btnAjouter, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.btnSupprimer, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                )
        );

        this.appliquerTheme();
    }                      

    private void selectColor1() 
    {
		new FrameCouleurChooser(ctrl, this.getClass().getName());
		this.couleurAttendu = 1;
    }


    private void selectColor2() 
    {
        new FrameCouleurChooser(ctrl, this.getClass().getName());
		this.couleurAttendu = 2;
    }


    private void btnAjouterActionPerformed(ActionEvent e)
	{
		JDialog dialog = new JDialog(this.ctrl.getIHM(),"Ajouter Arete");
		dialog.setSize(400,200);
        dialog.setLocationRelativeTo(this.ctrl.getIHM());
		this.panelArete = new PanelArete(ctrl);
		dialog.add(this.panelArete);
        dialog.pack();
		dialog.setVisible(true);
	}


    private void btnSupprimerActionPerformed(ActionEvent e)
    {
        Arete areteSelected = this.jListAretes.getSelectedValue();
        this.ctrl.supprimerArete(areteSelected.getNoeud1().getNom(), areteSelected.getNoeud2().getNom());
        this.jListAretes.updateUI();
        this.ctrl.majIHMPlateau();
    }

    private void cb2SensActionPerformed(ActionEvent e)
	{
		if (this.cb2Sens.isSelected())
		{
			this.btnCouleur2.setEnabled(true);
		}
		else
		{
			this.btnCouleur2.setEnabled(false);
			this.btnCouleur2.setBackground(null);
		}
	}

	private void txtDistanceActionPerformed(ActionEvent e)
	{
		if (this.txtDistance.getText().length() > 0)
		{
			try
			{
				int distance = Integer.parseInt(this.txtDistance.getText());
				if (distance < 0)
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtDistance.setText("" + this.jListAretes.getSelectedValue().getDistance());
				}
				else
				{
					this.jListAretes.getSelectedValue().setdistance(distance);
					this.ctrl.majIHMPlateau();
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtDistance.setText("" + this.jListAretes.getSelectedValue().getDistance());
			}
		}
	}
    

	private void comboBoxListNoeudAActionPerformed(ActionEvent e)
	{
		if (this.ctrl.getAretes().size() != 0)
		{
			Noeud noeud1 = (Noeud) this.comboBoxListNoeudA.getSelectedItem();
			Noeud noeud2 = (Noeud) this.comboBoxListNoeudB.getSelectedItem();
			boolean erreur = false;
			
			if (!estUneMaj)
			{
				if (noeud1.equals(noeud2))
				{
					JOptionPane.showMessageDialog(this, "Les 2 noeuds doivent-êtres différents", "Erreur", JOptionPane.ERROR_MESSAGE);
					erreur = true;
				}
				else
				{
					for (Arete a : this.ctrl.getAretes())
					{
						if ((a.getNoeud1().equals(noeud1) && a.getNoeud2().equals(noeud2)) ||
							(a.getNoeud1().equals(noeud2) && a.getNoeud2().equals(noeud1))   )
						{
							JOptionPane.showMessageDialog(this, "L'arête existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
							erreur = true;
							break;
						}
					}
				}

				if (!erreur)
				{
					this.jListAretes.getSelectedValue().setNoeud1(noeud1);
					this.ctrl.majIHMPlateau();
				}
				else
					this.comboBoxListNoeudA.setSelectedItem(this.jListAretes.getSelectedValue().getNoeud1());
			}
		
		}
	}

    private void comboBoxListNoeudBActionPerformed(ActionEvent e)
	{
		if (this.ctrl.getAretes().size() != 0) 
		{
			Noeud noeud1 = (Noeud) this.comboBoxListNoeudA.getSelectedItem();
			Noeud noeud2 = (Noeud) this.comboBoxListNoeudB.getSelectedItem();
			boolean erreur = false;
	
			if (!estUneMaj)
			{
				if (noeud1.equals(noeud2))
				{
					JOptionPane.showMessageDialog(this, "Les 2 noeuds doivent-êtres différents", "Erreur", JOptionPane.ERROR_MESSAGE);
					erreur = true;
				}
				else
				{
					for (Arete a : this.ctrl.getAretes())
					{
						if ((a.getNoeud1().equals(noeud1) && a.getNoeud2().equals(noeud2)) ||
							(a.getNoeud1().equals(noeud2) && a.getNoeud2().equals(noeud1))   )
						{
							JOptionPane.showMessageDialog(this, "L'arête existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
							erreur = true;
							break;
						}
					}
				}
	
				if (!erreur)
				{
					this.jListAretes.getSelectedValue().setNoeud2(noeud2);
					this.ctrl.majIHMPlateau();
				}
				else
					this.comboBoxListNoeudB.setSelectedItem(this.jListAretes.getSelectedValue().getNoeud2());
			}
		}
	}

    

    private void effacerForm() 
    {
        this.txtDistance.setText("");
        this.comboBoxListNoeudA.setSelectedIndex(0);
        this.comboBoxListNoeudB.setSelectedIndex(0);
        this.cb2Sens.setSelected(false);
        this.btnCouleur1.setBackground(this.ctrl.getTheme().get("bottuns").get(1));
        this.btnCouleur2.setBackground(this.ctrl.getTheme().get("bottuns").get(1));
    }



    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background       = theme.get("background"  ).get(0);
        Color titleForeColor   = theme.get("titles"      ).get(0);
        Color labelForeColor   = theme.get("labels"      ).get(0);
		Color labelBackColor   = theme.get("labels"      ).get(1);
        Color saisiForeColor   = theme.get("saisies"     ).get(0);
		Color saisiBackColor   = theme.get("saisies"     ).get(1);
        Color placeholderColor = theme.get("saisies"     ).get(2);
        Color btnForeColor     = theme.get("bottuns"     ).get(0);
		Color btnBackColor     = theme.get("bottuns"     ).get(1);


        this.setForeground(titleForeColor);
        this.setBackground(background);
    
        this.jListAretes            .setForeground         (saisiForeColor);
        this.jListAretes            .setBackground         (background    );
        this.jListAretes            .setSelectionForeground(background    );

        this.lblNoeudA         .setForeground(labelForeColor);
        this.lblNoeudA         .setBackground(labelBackColor);

        this.lblNoeudB         .setForeground(labelForeColor);
        this.lblNoeudB         .setBackground(labelBackColor);
        
        this.lbl2Sens          .setForeground(labelForeColor);
        this.lbl2Sens          .setBackground(labelBackColor);
        
        this.lblCouleur1       .setForeground(labelForeColor);
        this.lblCouleur2       .setBackground(labelBackColor);

        this.lblDistance       .setForeground(labelForeColor);
        this.lblDistance       .setBackground(labelBackColor);
        
        this.lblCouleur2       .setForeground(labelForeColor);
        this.lblCouleur2       .setBackground(labelBackColor);
        
        this.comboBoxListNoeudA.setBorder(null);
        this.comboBoxListNoeudA.setForeground(saisiForeColor);
        this.comboBoxListNoeudA.setBackground(saisiBackColor);
        
        this.comboBoxListNoeudB.setBorder(null);
        this.comboBoxListNoeudB.setForeground(saisiForeColor);
        this.comboBoxListNoeudB.setBackground(saisiBackColor);
        
        this.cb2Sens           .setOpaque    (false);
        this.cb2Sens           .setForeground(saisiForeColor);
        
        this.txtDistance       .setOpaque(true);
        this.txtDistance       .setBorder(null);
        this.txtDistance       .setForeground(placeholderColor);
        this.txtDistance       .setBackground(saisiBackColor  );
        this.txtDistance       .setForegroundColor (saisiForeColor  );
        this.txtDistance       .setPlaceholderColor(placeholderColor);
        this.txtDistance       .setDisabledTextColor(new Color(255, 0, 0));
        
        this.btnCouleur1       .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCouleur1       .setForeground(btnForeColor);
        this.btnCouleur1       .setBackground(btnBackColor);
        
        this.btnCouleur2       .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCouleur2       .setForeground(btnForeColor);
        this.btnCouleur2       .setBackground(btnBackColor);
        
        this.btnAjouter        .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnAjouter        .setForeground(btnForeColor);
        this.btnAjouter        .setBackground(btnBackColor);
        
        this.btnSupprimer      .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnSupprimer      .setForeground(btnForeColor);
        this.btnSupprimer      .setBackground(btnBackColor);
    }

	public void envoyerCouleur(Color c, String nomPanel)
	{
		if (nomPanel.contains("PGPanelListA"))
		{
			if (this.couleurAttendu == 1)
			{
				this.btnCouleur1.setBackground(c);
				this.jListAretes.getSelectedValue().setCouleur1(c);
			}
			else
			{
				this.btnCouleur2.setBackground(c);
				this.jListAretes.getSelectedValue().setCouleur2(c);
			}

			this.ctrl.majIHMPlateau();
		}

		if (nomPanel.contains("PanelArete"))
		{
			this.panelArete.envoyerCouleur(c);
		}
	}

	public void majIHM()
	{
		this.jListAretes.setModel(new AbstractListModel<Arete>()
        {
            List<Arete> lstAretes = ctrl.getAretes();

            public int getSize()
            {
                return lstAretes.size();
            }

            public Arete getElementAt(int index)
            {
                return lstAretes.get(index);
            }
        });
	}

	public void selectArete(int index)
	{
		this.jListAretes.setSelectedIndex(index);
	}
}