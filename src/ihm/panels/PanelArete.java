package ihm.panels;

import java.awt.Color;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;

import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.frames.FrameCouleurChooser;
import metier.Arete;
import metier.Noeud;


public class PanelArete extends JPanel
{
    
    private Controleur        ctrl;
    
    private JComboBox<String> cbA;
    private JComboBox<String> cbB;
	private JCheckBox         cbVD;
    private JButton           btnCoul1;
    private JButton           btnCoul2;
    private TextFieldOnlyInteger txtDistance;

    private JButton           btnAjouter;

    private Color             couleur1 = null;
    private Color             couleur2 = null;

    private ListModel<String> listModel;
    private List<Arete>       aretes;

    private List<Noeud>       lstNoeudA;
    private List<Noeud>       lstNoeudB;

	private int couleurAttendu = 1;

    public PanelArete(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setBackground(new Color(68, 71, 90));
        this.setLayout(new BorderLayout());

        /*Panel liste aretes */
        JPanel panelListe = new JPanel();
        panelListe.setBackground(new Color(68, 71, 90));

        this.aretes = this.ctrl.getAretes();
        this.listModel = new DefaultListModel<String>();

        for (Arete a : this.aretes) {
            ((DefaultListModel<String>) this.listModel).addElement(a.getNoeud1().getNom() + "-" + a.getNoeud2().getNom());
        }

        /*Panel info aretes */
        JPanel panelInfos = new JPanel();
        panelInfos.setBackground(new Color(68, 71, 90));

        GroupLayout layout = new GroupLayout(panelInfos);
        panelInfos.setLayout(layout);

        JLabel lblNoeudA, lblNoeudB, lblVD, lblCoul1, lblCoul2, lblDistance;
        lblNoeudA = new JLabel("Noeud A");
        lblNoeudA.setForeground(Color.WHITE);
        lblNoeudB = new JLabel("Noeud B");
        lblNoeudB.setForeground(Color.WHITE);
		lblVD = new JLabel("Voix double");
        lblVD.setForeground(Color.WHITE);
        lblCoul1 = new JLabel("Couleur1");
        lblCoul1.setForeground(Color.WHITE);
        lblCoul2 = new JLabel("Couleur2");
        lblCoul2.setForeground(Color.WHITE);
        lblDistance = new JLabel("Distance");
        lblDistance.setForeground(Color.WHITE);

        this.txtDistance = new TextFieldOnlyInteger(ctrl);
        this.txtDistance.setBackground(new Color(58, 60, 76));
        this.txtDistance.setForeground(Color.GRAY);


        lstNoeudA = this.ctrl.getNoeuds();
        lstNoeudB = this.ctrl.getNoeuds();
        
        String[] tabNoeudA = new String[lstNoeudA.size()];
        String[] tabNoeudB = new String[lstNoeudB.size()];
        
        for(int cpt =0; cpt < lstNoeudA.size(); cpt++)
        {
            tabNoeudA[cpt] = lstNoeudA.get(cpt).getNom();
            tabNoeudB[cpt] = lstNoeudB.get(cpt).getNom();
        }

        cbA = new JComboBox<String>(tabNoeudA);
        cbB = new JComboBox<String>(tabNoeudB);

        cbA.setVisible(true);
        cbB.setVisible(true);

		this.cbVD = new JCheckBox();
		this.cbVD.setBackground(new Color(68, 71, 90));
		this.cbVD.setForeground(Color.WHITE);
		this.cbVD.setSelected(false);
        this.cbVD.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                cbVDActionPerformed(evt);
            }
        });

        this.btnCoul1 = new JButton("Couleur");
        this.btnCoul1.setBackground(null);
        add(this.btnCoul1);
        this.btnCoul1.addActionListener(e -> {
            selectColor1();
        });

        this.btnCoul2 = new JButton("Couleur");
        this.btnCoul2.setBackground(null);
		this.btnCoul2.setEnabled(false);
        add(this.btnCoul2);
        this.btnCoul2.addActionListener(e -> {
            selectColor2();
        });

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup().
                addComponent(lblNoeudA).addComponent(lblVD).addComponent(lblCoul1).addComponent(lblDistance)).addContainerGap(10,10);
        
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(cbA).addComponent(cbVD).addComponent(btnCoul1).addComponent(txtDistance)).addContainerGap(10,10);

		hGroup.addGroup(layout.createParallelGroup().
				addComponent(lblNoeudB).addComponent(lblCoul2)).addContainerGap(10,10);

		hGroup.addGroup(layout.createParallelGroup().
				addComponent(cbB).addComponent(this.btnCoul2)).addContainerGap(10,10);

        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblNoeudA).addComponent(cbA).addComponent(lblNoeudB).addComponent(cbB)).addContainerGap(10,10);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblVD).addComponent(this.cbVD)).addContainerGap(10,10);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblCoul1).addComponent(this.btnCoul1).addComponent(lblCoul2).addComponent(this.btnCoul2)).addContainerGap(10,10);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblDistance).addComponent(txtDistance)).addContainerGap(10,10);

        layout.setVerticalGroup(vGroup);

        /*Panel boutons */
        JPanel panelBoutons = new JPanel();
        panelBoutons.setBackground(new Color(68, 71, 90));

        this.btnAjouter = new JButton("Ajouter");
        this.btnAjouter.setBackground(new Color(58, 60, 76));
        this.btnAjouter.setForeground(Color.WHITE);
        this.btnAjouter.addActionListener(e -> {
            ajouterArete();
        });

        panelBoutons.add(this.btnAjouter);


        /*Ajout des panels*/
        this.add(panelListe, BorderLayout.WEST);
        this.add(panelInfos, BorderLayout.CENTER);
        this.add(panelBoutons, BorderLayout.SOUTH);

            
    }

	private void cbVDActionPerformed(ActionEvent e)
	{
		if (this.cbVD.isSelected())
		{
			this.btnCoul2.setEnabled(true);
		}
		else
		{
			this.btnCoul2.setEnabled(false);
			this.btnCoul2.setBackground(null);
		}
	}

	private void selectColor1() 
    {
        new FrameCouleurChooser(this.ctrl, this.getClass().getName());
        this.couleurAttendu = 1;
    }

    private void selectColor2() 
    {
        new FrameCouleurChooser(this.ctrl, this.getClass().getName());
        this.couleurAttendu = 2;
    }

    /**
     * Methode permettant d'ajouter une arete
     */
    private void ajouterArete() 
    {
		boolean erreur = false;
        List<Noeud> lstNoeud = this.ctrl.getNoeuds();
		List<Arete> lstArete = this.ctrl.getAretes();

		Noeud noeud1 = lstNoeud.get(this.cbA.getSelectedIndex());
		Noeud noeud2 = lstNoeud.get(this.cbB.getSelectedIndex());
		int distance = 0;

		if (noeud1.equals(noeud2))
		{
			JOptionPane.showMessageDialog(this, "Les 2 noeuds doivent-êtres différents", "Erreur", JOptionPane.ERROR_MESSAGE);
			this.cbA.setBackground(Color.RED);
			this.cbB.setBackground(Color.RED);
			erreur = true;
		}
		else
		{
			for (Arete a : lstArete)
			{
				if ((a.getNoeud1().equals(noeud1) && a.getNoeud2().equals(noeud2)) ||
				    (a.getNoeud1().equals(noeud2) && a.getNoeud2().equals(noeud1))   )
				{
					JOptionPane.showMessageDialog(this, "L'arête existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.cbA.setBackground(Color.RED);
					this.cbB.setBackground(Color.RED);
					erreur = true;
					break;
				}
			}

			if (!erreur)
			{
				this.cbA.setBackground(new Color(68, 71, 90));
				this.cbB.setBackground(new Color(68, 71, 90));
			}
		}

		try
		{
			distance = Integer.parseInt(this.txtDistance.getText());

			if (distance > 0)
				this.txtDistance.setBackground(new Color(58, 60, 76));
			else
			{
				this.txtDistance.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Veuillez entrer une distance positive", "Erreur", JOptionPane.ERROR_MESSAGE);
				erreur = true;
			}
		}
		catch (NumberFormatException e)
		{
			this.txtDistance.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez entrer une distance valide", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		if (this.couleur1 == null)
		{
			this.btnCoul1.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez choisir une couleur", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		if (this.cbVD.isSelected() && this.couleur2 == null)
		{
			this.btnCoul2.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez choisir une couleur pour la deuxieme voie", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		if (!erreur)
		{
			if (this.cbVD.isSelected())
				this.ctrl.ajouterArete(noeud1, noeud2, distance, couleur1, couleur2);
			else
				this.ctrl.ajouterArete(noeud1, noeud2, distance, couleur1, null    );
		
			this.effacerForm();
		}        
    }


    private void effacerForm() 
    {
        this.cbA.setSelectedIndex(0);
        this.cbB.setSelectedIndex(0);
		this.cbVD.setSelected(false);
        this.btnCoul1.setBackground(null);
		this.couleur1 = null;
        this.btnCoul2.setBackground(null);
		this.btnCoul2.setEnabled(false);
		this.couleur2 = null;
        this.txtDistance.setText("");
    }

	public void envoyerCouleur(Color c)
	{
		if (this.couleurAttendu == 1)
		{
			this.couleur1 = c;
			this.btnCoul1.setBackground(c);
		}
		else
		{
			this.couleur2 = c;
			this.btnCoul2.setBackground(c);
		}
	}
}
