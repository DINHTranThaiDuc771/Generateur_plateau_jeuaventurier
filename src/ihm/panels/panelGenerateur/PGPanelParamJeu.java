package ihm.panels.panelGenerateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.customComponent.TextFieldWithHint;
import ihm.frames.FrameCouleur;
import ihm.frames.FrameCreerCarteWagon;
import ihm.frames.FramePoint;


public class PGPanelParamJeu extends JPanel
{
	private Controleur ctrl;

	private TextFieldWithHint txtMaxNbJoueur;
    private TextFieldWithHint txtMinNbJoueur;
    private TextFieldWithHint txtNbCarteParCoul;
    private TextFieldWithHint txtNbJoker;
    private TextFieldWithHint txtJetonsPourFinir;
    private TextFieldWithHint txtJetonsParJoueur;

    private JButton    btnCouleurs;
    private JButton    btnMoinsCoul;
    private JButton    btnMoinsJoker;
    private JButton    btnPlusCoul;
    private JButton    btnPlusJoker;
    private JButton    btnPoints;
    private JButton    btnMoinsJetonsParJoueur;    
    private JButton    btnMoinsJetonsPourFinir;
    private JButton    btnPlusJetonsParJoueur;
    private JButton    btnPlusJetonsPourFinir;
    private JButton    btnCartesWagon;
    private JLabel     lblImgCarte;
    private JLabel     lblModif;
    private JLabel     lblMultiCoul;
    private JLabel     lblNbCarteParCoul;
    private JLabel     lblNbJoueurs;
    private JLabel     lblParamJeu;
    private JLabel     lblCartesWagon;
    private JLabel     lblJetonsPourFinir;
    private JLabel     lblJetonsWagons;
    private JLabel     lblJetonsParJoueur;

    private FrameCouleur         frameCouleur        ;
    private FramePoint           framePoint          ;
    private FrameCreerCarteWagon frameCreerCarteWagon;
    
    private Color                placeholderAncienTheme;


	/**
     * Creates new form PGPanelParamJeu
     * @param ctrl : Controleur faisant le lien avec l'IHM
     */
    public PGPanelParamJeu(Controleur ctrl)
    {
		this.ctrl = ctrl;

        this.lblParamJeu              = new JLabel();
        this.lblNbJoueurs             = new JLabel();
        this.lblModif                 = new JLabel();
        this.lblMultiCoul             = new JLabel();
        this.lblNbCarteParCoul        = new JLabel();
        this.lblImgCarte              = new JLabel();
        this.lblCartesWagon           = new JLabel();
        this.lblJetonsWagons          = new JLabel();
		this.lblJetonsParJoueur       = new JLabel();
        this.lblJetonsPourFinir       = new JLabel();
        this.txtMinNbJoueur           = new TextFieldOnlyInteger("Min", ctrl);
        this.txtMaxNbJoueur           = new TextFieldOnlyInteger("Max", ctrl);
        this.txtNbJoker               = new TextFieldOnlyInteger(""+this.ctrl.getNbCarteLocomotive(), ctrl);
        this.txtNbCarteParCoul        = new TextFieldOnlyInteger(""+this.ctrl.getNbCarteCoul      (), ctrl);
		this.txtJetonsParJoueur       = new TextFieldOnlyInteger(""+this.ctrl.getNbJetonJoueur    (), ctrl);
        this.txtJetonsPourFinir       = new TextFieldOnlyInteger(""+this.ctrl.getNbJetonFin       (), ctrl);
        this.btnPlusJoker             = new JButton();
        this.btnCouleurs              = new JButton();
        this.btnPoints                = new JButton();
        this.btnPlusCoul              = new JButton();
        this.btnMoinsJoker            = new JButton();
        this.btnMoinsCoul             = new JButton();
        this.btnCartesWagon           = new JButton();
        this.btnMoinsJetonsParJoueur  = new JButton();
        this.btnMoinsJetonsPourFinir  = new JButton();
        this.btnPlusJetonsParJoueur   = new JButton();
        this.btnPlusJetonsPourFinir   = new JButton();

        this.frameCouleur             = null;
        this.framePoint               = null;
        this.frameCreerCarteWagon     = null;
        this.placeholderAncienTheme   = null;

        this.lblParamJeu.setText(" Parametre du jeu");

        this.lblNbJoueurs.setText("Nombre de joueurs");
        this.lblNbJoueurs.setFont(new Font("Segoe UI", 1, 12));

        this.lblModif.setText("Modifier");
        this.lblModif.setFont(new Font("Segoe UI", 1, 12));

        this.lblMultiCoul.setText("multicouleurs");
        this.lblMultiCoul.setFont(new Font("Segoe UI", 1, 12));

        this.lblNbCarteParCoul.setText("nb couleurs");
        this.lblNbCarteParCoul.setFont(new Font("Segoe UI", 1, 12));

        this.lblImgCarte.setText("Créer cartes wagon");
        this.lblImgCarte.setFont(new Font("Segoe UI", 1, 12));
        
        this.lblCartesWagon.setText("Cartes wagon");
        this.lblCartesWagon.setFont(new Font("Segoe UI", 1, 12));

        this.lblJetonsPourFinir.setText("pour finir");
        this.lblJetonsPourFinir.setFont(new Font("Segoe UI", 1, 12));

        this.lblJetonsParJoueur.setText("par joueur");
        this.lblJetonsParJoueur.setFont(new Font("Segoe UI", 1, 12));

        this.lblJetonsWagons.setText("Jetons wagon");
        this.lblJetonsWagons.setFont(new Font("Segoe UI", 1, 12));

        this.txtMinNbJoueur.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtMinJoueurActionPerformed(e);
            }
        });

        this.txtMaxNbJoueur.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtMaxNbJoueurActionPerformed(e);
            }
        });


        this.txtNbJoker.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtNbJokerActionPerformed(e);
            }
        });


        this.txtNbCarteParCoul.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtNbCarteParCoulActionPerformed(e);
            }
        });

        this.txtJetonsPourFinir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtJetonsPourFinirActionPerformed(evt);
            }

        });

        this.txtJetonsParJoueur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtJetonsParJoueurActionPerformed(evt);
            }


        });

        this.btnCouleurs.setText("Couleurs");
        this.btnCouleurs.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnCouleursActionPerformed(e);
            }
        });


        this.btnPoints.setText("Points");
        this.btnPoints.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnPointsActionPerformed(e);
            }
        });


        


        this.btnPlusCoul.setText("   +   ");
        this.btnPlusCoul.setToolTipText("+");
        this.btnPlusCoul.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnPlusCoulActionPerformed(e);
            }
        });


        this.btnMoinsJoker.setText("   -   ");
        this.btnMoinsJoker.setToolTipText("+");
        this.btnMoinsJoker.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnMoinsJokerActionPerformed(e);
            }
        });


        this.btnPlusJoker.setText("   +   ");
        this.btnPlusJoker.setToolTipText("+");
        this.btnPlusJoker.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnPlusJokerActionPerformed(e);
            }
        });


        this.btnMoinsCoul.setText("   -   ");
        this.btnMoinsCoul.setToolTipText("+");
        this.btnMoinsCoul.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnMoinsCoulActionPerformed(e);
            }
        });

        this.btnPlusJetonsParJoueur.setText("   +   ");
        this.btnPlusJetonsParJoueur.setToolTipText("+");
        this.btnPlusJetonsParJoueur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPlusJetonsParJoueurActionPerformed(evt);
            }
        });

        this.btnPlusJetonsPourFinir.setText("   +   ");
        this.btnPlusJetonsPourFinir.setToolTipText("+");
        this.btnPlusJetonsPourFinir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPlusJetonsPourFinirActionPerformed(evt);
            }
        });

        this.btnMoinsJetonsParJoueur.setText("   -   ");
        this.btnMoinsJetonsParJoueur.setToolTipText("-");
        this.btnMoinsJetonsParJoueur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMoinsJetonsParJoueurActionPerformed(evt);
            }
        });

        this.btnMoinsJetonsPourFinir.setText("   -   ");
        this.btnMoinsJetonsPourFinir.setToolTipText("-");
        this.btnMoinsJetonsPourFinir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMoinsJetonsPourFinirActionPerformed(evt);
            }
        });

        this.btnCartesWagon.setText("Cartes");
        this.btnCartesWagon.setBorder(null);
        this.btnCartesWagon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCartesWagonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblParamJeu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJetonsPourFinir)
                    .addComponent(lblJetonsWagons)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNbJoueurs)
                            .addComponent(lblCartesWagon))
                        .addGap(15, 15, 15)
                        .addComponent(txtMinNbJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaxNbJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModif)
                            .addComponent(lblJetonsParJoueur)
                            .addComponent(lblNbCarteParCoul)
                            .addComponent(lblMultiCoul))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnMoinsJetonsParJoueur)
                                    .addComponent(btnMoinsJetonsPourFinir))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtJetonsPourFinir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPlusJetonsPourFinir))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtJetonsParJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPlusJetonsParJoueur))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCouleurs, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMoinsJoker)
                                .addGap(18, 18, 18)
                                .addComponent(txtNbJoker, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPlusJoker))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMoinsCoul)
                                .addGap(18, 18, 18)
                                .addComponent(txtNbCarteParCoul, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPlusCoul)))))
                .addGap(18, 18, 18)
                .addComponent(btnCartesWagon, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblParamJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNbJoueurs)
                    .addComponent(txtMinNbJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxNbJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCartesWagon)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNbCarteParCoul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMoinsCoul, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPlusCoul, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNbCarteParCoul))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNbJoker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPlusJoker, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMoinsJoker, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMultiCoul))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblJetonsWagons)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJetonsParJoueur)
                    .addComponent(txtJetonsParJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlusJetonsParJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoinsJetonsParJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJetonsPourFinir)
                    .addComponent(txtJetonsPourFinir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlusJetonsPourFinir, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoinsJetonsPourFinir, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModif)
                    .addComponent(btnCouleurs, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCartesWagon, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        this.appliquerTheme();
    }

	// nb joueurs
    private void txtMinJoueurActionPerformed(ActionEvent e) 
	{
		if (this.txtMinNbJoueur.getText().length() > 0)
		{
			try
			{
				int val = Integer.parseInt(this.txtMinNbJoueur.getText());
				if (val < 0 || val >= this.ctrl.getNbJoueursMax())
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et inférieur au nombre maximum de joueur", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtMinNbJoueur.setText("" + this.ctrl.getNbJoueursMin());
				}
				else
				{
					this.ctrl.setNbJoueursMin(val);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtMinNbJoueur.setText("" + this.ctrl.getNbJoueursMin());
			}
		}
	}

    private void txtMaxNbJoueurActionPerformed(ActionEvent e) 
	{
		if (this.txtMaxNbJoueur.getText().length() > 0)
		{
			try
			{
				int val = Integer.parseInt(this.txtMaxNbJoueur.getText());
				if (val < 0 || val <= this.ctrl.getNbJoueursMin())
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et supérieur au nombre minimum de joueur", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtMaxNbJoueur.setText("" + this.ctrl.getNbJoueursMax());
				}
				else
				{
					this.ctrl.setNbJoueursMax(val);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtMaxNbJoueur.setText("" + this.ctrl.getNbJoueursMax());
			}
		}
	}


	// nb cartes couleurs
    private void txtNbJokerActionPerformed(ActionEvent e) 
	{
		if (this.txtNbJoker.getText().length() > 0)
		{
			try
			{
				int val = Integer.parseInt(this.txtNbJoker.getText());
				if (val < 0 || val <= this.ctrl.getNbCarteCoul())
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et supérieur au nombre de cartes couleurs", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtNbJoker.setText("" + this.ctrl.getNbCarteLocomotive());
				}
				else
				{
					this.ctrl.setNbCarteLocomotive(val);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtNbJoker.setText("" + this.ctrl.getNbCarteLocomotive());
			}
		}
	}

    private void txtNbCarteParCoulActionPerformed(ActionEvent e) 
	{
		if (this.txtNbCarteParCoul.getText().length() > 0)
		{
			try
			{
				int val = Integer.parseInt(this.txtNbCarteParCoul.getText());
				if (val < 0 || val >= this.ctrl.getNbCarteLocomotive())
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et inférieur au nombre de carte multicouleurs", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtNbCarteParCoul.setText("" + this.ctrl.getNbCarteCoul());
				}
				else
				{
					this.ctrl.setNbCarteCoul(val);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtNbCarteParCoul.setText("" + this.ctrl.getNbCarteCoul());
			}
		}
	}

	private void btnMoinsJokerActionPerformed(ActionEvent e)
	{
		int nbJoker = this.ctrl.getNbCarteLocomotive();
		if (nbJoker - 1 > 0 && nbJoker - 1 > this.ctrl.getNbCarteCoul())
		{
			nbJoker--;
			this.txtNbJoker.setText(Integer.toString(nbJoker));
			this.ctrl.setNbCarteLocomotive(nbJoker);
		}
		else
        {
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et supérieur au nombre de cartes couleurs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtNbJoker.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtNbJoker.getForeground().equals(saisiForeColor))
            this.txtNbJoker.setForeground(saisiForeColor);
    }


    private void btnPlusJokerActionPerformed(ActionEvent e)
	{
		int nbJoker = this.ctrl.getNbCarteLocomotive();
		nbJoker++;

		this.txtNbJoker.setText(Integer.toString(nbJoker));
		this.ctrl.setNbCarteLocomotive(nbJoker);

        
        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtNbJoker.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtNbJoker.getForeground().equals(saisiForeColor))
            this.txtNbJoker.setForeground(saisiForeColor);
    }
    


    private void btnMoinsCoulActionPerformed(ActionEvent e) 
	{ 
		int nbCarteWagon = this.ctrl.getNbCarteCoul();

		if (nbCarteWagon - 1 > 0)
		{
			nbCarteWagon--;
			this.txtNbCarteParCoul.setText(Integer.toString(nbCarteWagon));
			this.ctrl.setNbCarteCoul(nbCarteWagon);
		}
		else
        {
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtNbCarteParCoul.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtNbCarteParCoul.getForeground().equals(saisiForeColor))
            this.txtNbCarteParCoul.setForeground(saisiForeColor);
    }
    

    
    private void btnPlusCoulActionPerformed(ActionEvent e)
	{
		int nbCarteWagon = this.ctrl.getNbCarteCoul();
		if (nbCarteWagon + 1 < this.ctrl.getNbCarteLocomotive())
		{
			nbCarteWagon++;
			this.txtNbCarteParCoul.setText(Integer.toString(nbCarteWagon));
			this.ctrl.setNbCarteCoul(nbCarteWagon);
		}
		else
        {
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre inférieur au nombre de carte multicouleurs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtNbCarteParCoul.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtNbCarteParCoul.getForeground().equals(saisiForeColor))
            this.txtNbCarteParCoul.setForeground(saisiForeColor);
    }

	// nb jetons
	private void txtJetonsParJoueurActionPerformed(ActionEvent evt) 
	{
		if (this.txtJetonsParJoueur.getText().length() > 0)
		{
			try
			{
				int val = Integer.parseInt(this.txtJetonsParJoueur.getText());
				if (val < 0 || val <= this.ctrl.getNbJetonFin())
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et supérieur au nombre de jeton pour finir la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtJetonsParJoueur.setText("" + this.ctrl.getNbJetonJoueur());
				}
				else
				{
					this.ctrl.setNbJetonJoueur(val);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtJetonsParJoueur.setText("" + this.ctrl.getNbJetonJoueur());
			}
		}
    }

	private void txtJetonsPourFinirActionPerformed(ActionEvent evt) 
	{                                                   
        if (this.txtJetonsPourFinir.getText().length() > 0)
		{
			try
			{
				int val = Integer.parseInt(this.txtJetonsPourFinir.getText());
				if (val < 0 || val >= this.ctrl.getNbJetonJoueur())
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et inférieur au nombre de jeton par joueur", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtJetonsPourFinir.setText("" + this.ctrl.getNbJetonFin());
				}
				else
				{
					this.ctrl.setNbJetonFin(val);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtJetonsPourFinir.setText("" + this.ctrl.getNbJetonFin());
			}
		}
    }    

	private void btnPlusJetonsParJoueurActionPerformed(ActionEvent evt) 
	{                                                       
        int nbJeton = this.ctrl.getNbJetonJoueur();
		nbJeton++;

		this.txtJetonsParJoueur.setText(Integer.toString(nbJeton));
		this.ctrl.setNbJetonJoueur(nbJeton);

        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtJetonsParJoueur.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtJetonsParJoueur.getForeground().equals(saisiForeColor))
            this.txtJetonsParJoueur.setForeground(saisiForeColor);
	}                                                      

    private void btnPlusJetonsPourFinirActionPerformed(ActionEvent evt) 
	{                                                       
        int nbJeton = this.ctrl.getNbJetonFin();
		if (nbJeton + 1 < this.ctrl.getNbJetonJoueur())
		{
			nbJeton++;
			this.txtJetonsPourFinir.setText(Integer.toString(nbJeton));
			this.ctrl.setNbJetonFin(nbJeton);
		}
		else
        {
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre inférieur au nombre de jeton par joueur", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtJetonsPourFinir.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtJetonsPourFinir.getForeground().equals(saisiForeColor))
            this.txtJetonsPourFinir.setForeground(saisiForeColor);
    }                                                      

    private void btnMoinsJetonsParJoueurActionPerformed(ActionEvent evt) 
	{                                                         
        int nbJeton = this.ctrl.getNbJetonJoueur();
		if (nbJeton - 1 > 0 && nbJeton - 1 > this.ctrl.getNbJetonFin())
		{
			nbJeton--;
			this.txtJetonsParJoueur.setText(Integer.toString(nbJeton));
			this.ctrl.setNbJetonJoueur(nbJeton);
		}
		else
        {
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0 et supérieur au nombre de jeton pour finir la partie", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtJetonsParJoueur.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtJetonsParJoueur.getForeground().equals(saisiForeColor))
            this.txtJetonsParJoueur.setForeground(saisiForeColor);
    }

    private void btnMoinsJetonsPourFinirActionPerformed(java.awt.event.ActionEvent evt) 
	{
        int nbJeton = this.ctrl.getNbJetonFin();

		if (nbJeton - 1 > 0)
		{
			nbJeton--;
			this.txtJetonsPourFinir.setText(Integer.toString(nbJeton));
			this.ctrl.setNbJetonFin(nbJeton);
		}
		else
        {
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
        }


        /* Permet de mettre le texte en vrai texte qui ne disparaît pas quand on clique dessus */
        this.txtJetonsPourFinir.placeholderToTrueTxt();

        Color saisiForeColor   = this.ctrl.getTheme().get("saisies").get(0);
        if (!this.txtJetonsPourFinir.getForeground().equals(saisiForeColor))
            this.txtJetonsPourFinir.setForeground(saisiForeColor);
    }

    private void btnCouleursActionPerformed   (ActionEvent e) { this.frameCouleur         = new FrameCouleur        (this.ctrl); }
    private void btnPointsActionPerformed     (ActionEvent e) { this.framePoint           = new FramePoint          (this.ctrl); }
    private void btnCartesWagonActionPerformed(ActionEvent e) { this.frameCreerCarteWagon = new FrameCreerCarteWagon(this.ctrl); }   
	   
    /**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
	{
        if (this.frameCouleur         != null) { this.frameCouleur        .appliquerTheme(); }
        if (this.framePoint           != null) { this.framePoint          .appliquerTheme(); }
        if (this.frameCreerCarteWagon != null) { this.frameCreerCarteWagon.appliquerTheme(); }

        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

		Color background       = theme.get("background").get(0);
        Color titleForeColor   = theme.get("titles"    ).get(0);
		Color titleBackColor   = theme.get("titles"    ).get(1);
        Color labelForeColor   = theme.get("labels"    ).get(0);
        Color saisiForeColor   = theme.get("saisies"   ).get(0);
		Color saisiBackColor   = theme.get("saisies"   ).get(1);
        Color placeholderColor = theme.get("saisies"   ).get(2);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


        this.setBackground(background);

        this.lblParamJeu.setOpaque(true);
        this.lblParamJeu.setBorder(null);
        this.lblParamJeu.setBackground(titleBackColor);
        this.lblParamJeu.setForeground(titleForeColor);

        this.lblNbJoueurs.setBorder(null);
        this.lblNbJoueurs.setOpaque(false);
        this.lblNbJoueurs.setForeground(labelForeColor);

        this.lblModif.setBorder(null);
        this.lblModif.setOpaque(false);
        this.lblModif.setForeground(labelForeColor);

        this.lblMultiCoul.setBorder(null);
        this.lblMultiCoul.setOpaque(false);
        this.lblMultiCoul.setForeground(labelForeColor);

        this.lblNbCarteParCoul.setBorder(null);
        this.lblNbCarteParCoul.setOpaque(false);
        this.lblNbCarteParCoul.setForeground(labelForeColor);

        this.lblImgCarte.setBorder(null);
        this.lblImgCarte.setOpaque(false);
        this.lblImgCarte.setForeground(labelForeColor);

        this.lblCartesWagon.setBorder(null);
        this.lblCartesWagon.setOpaque(false);
        this.lblCartesWagon.setForeground(labelForeColor);

        this.lblJetonsWagons.setBorder(null);
        this.lblJetonsWagons.setOpaque(false);
        this.lblJetonsWagons.setForeground(labelForeColor);

        this.lblJetonsParJoueur.setBorder(null);
        this.lblJetonsParJoueur.setOpaque(false);
        this.lblJetonsParJoueur.setForeground(labelForeColor);

        this.lblJetonsPourFinir.setBorder(null);
        this.lblJetonsPourFinir.setOpaque(false);
        this.lblJetonsPourFinir.setForeground(labelForeColor);

        this.txtMinNbJoueur.setBorder(null);
        this.txtMinNbJoueur.setBackground(saisiBackColor  );
        this.txtMinNbJoueur.setForeground(placeholderColor);
        this.txtMinNbJoueur.setForegroundColor (saisiForeColor  );
        this.txtMinNbJoueur.setPlaceholderColor(placeholderColor);
        this.txtMinNbJoueur.setHorizontalAlignment(JTextField.RIGHT);

        this.txtMaxNbJoueur.setBorder(null);
        this.txtMaxNbJoueur.setBackground(saisiBackColor  );
        this.txtMaxNbJoueur.setForeground(placeholderColor);
        this.txtMaxNbJoueur.setForegroundColor (saisiForeColor  );
        this.txtMaxNbJoueur.setPlaceholderColor(placeholderColor);
        this.txtMaxNbJoueur.setHorizontalAlignment(JTextField.RIGHT);

        this.txtNbJoker.setBorder(null);
        this.txtNbJoker.setCaretColor(saisiForeColor);
        this.txtNbJoker.setBackground(saisiBackColor  );
        this.txtNbJoker.setHorizontalAlignment(JTextField.CENTER);
        this.verifColorTxt(this.txtNbJoker);

        this.txtNbCarteParCoul.setBorder(null);
        this.txtNbCarteParCoul.setCaretColor(saisiForeColor);
        this.txtNbCarteParCoul.setBackground(saisiBackColor  );
        this.txtNbCarteParCoul.setHorizontalAlignment(JTextField.CENTER);
        this.verifColorTxt(this.txtNbCarteParCoul);

        this.txtJetonsParJoueur.setBorder(null);
        this.txtJetonsParJoueur.setCaretColor(saisiForeColor);
        this.txtJetonsParJoueur.setBackground(saisiBackColor);
        this.txtJetonsParJoueur.setHorizontalAlignment(JTextField.CENTER);
        this.verifColorTxt(this.txtJetonsParJoueur);

        this.txtJetonsPourFinir.setBorder(null);
        this.txtJetonsPourFinir.setCaretColor(saisiForeColor);
        this.txtJetonsPourFinir.setBackground(saisiBackColor  );
        this.txtJetonsPourFinir.setHorizontalAlignment(JTextField.CENTER);
        this.verifColorTxt(this.txtJetonsPourFinir);

        this.btnPlusJoker.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnPlusJoker.setBackground(btnBackColor);
        this.btnPlusJoker.setForeground(btnForeColor);

        this.btnMoinsJoker.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnMoinsJoker.setBackground(btnBackColor);
        this.btnMoinsJoker.setForeground(btnForeColor);

        this.btnCouleurs.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCouleurs.setBackground(btnBackColor);
        this.btnCouleurs.setForeground(btnForeColor);

        this.btnMoinsCoul.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnMoinsCoul.setBackground(btnBackColor);
        this.btnMoinsCoul.setForeground(btnForeColor);

        this.btnPoints.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnPoints.setBackground(btnBackColor);
        this.btnPoints.setForeground(btnForeColor);

        this.btnCartesWagon.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnCartesWagon.setBackground(btnBackColor);
        this.btnCartesWagon.setForeground(btnForeColor);

        this.btnPlusCoul.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnPlusCoul.setBackground(btnBackColor);
        this.btnPlusCoul.setForeground(btnForeColor);

        this.btnPlusJetonsParJoueur.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnPlusJetonsParJoueur.setBackground(btnBackColor);
        this.btnPlusJetonsParJoueur.setForeground(btnForeColor);

        this.btnPlusJetonsPourFinir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnPlusJetonsPourFinir.setBackground(btnBackColor);
        this.btnPlusJetonsPourFinir.setForeground(btnForeColor);

        this.btnMoinsJetonsParJoueur.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnMoinsJetonsParJoueur.setBackground(btnBackColor);
        this.btnMoinsJetonsParJoueur.setForeground(btnForeColor);
        
        this.btnMoinsJetonsPourFinir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnMoinsJetonsPourFinir.setBackground(btnBackColor);
        this.btnMoinsJetonsPourFinir.setForeground(btnForeColor);

        this.placeholderAncienTheme = placeholderColor;
	}

    private void verifColorTxt(TextFieldWithHint TextField)
    {
        Color saisiForeColor   = this.ctrl.getTheme().get("saisies"   ).get(0);
        Color placeholderColor = this.ctrl.getTheme().get("saisies"   ).get(2);
 

        if (this.placeholderAncienTheme == null)
        {
            TextField.setForegroundColor (saisiForeColor);
            TextField.setPlaceholderColor(placeholderColor);
        }
        else
        {
            if (!TextField.getForeground().equals(this.placeholderAncienTheme))
            {
                TextField.setForeground(saisiForeColor);
            }
            else
            {
                TextField.setForegroundColor (saisiForeColor  );
                TextField.setPlaceholderColor(placeholderColor);
            }
        }
    }
}