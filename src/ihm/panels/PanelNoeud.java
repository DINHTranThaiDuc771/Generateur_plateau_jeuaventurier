package ihm.panels;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import java.awt.BorderLayout;
import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.customComponent.TextFieldWithHint;

public class PanelNoeud extends JPanel
{
    private Controleur ctrl;
    
    private JPanel               panelListe;
    private JPanel               panelInfos;
    private JPanel               panelBoutons;

	private JTextField           txtNom;
    private TextFieldOnlyInteger txtPosX;
    private TextFieldOnlyInteger txtPosY;
    private TextFieldOnlyInteger txtPosNomX;
    private TextFieldOnlyInteger txtPosNomY;
    private JButton              btnCouleur;
    private JButton              btnAjouter;
    private JLabel               labelPos;
    private JLabel               labelNom;
    private JLabel               labelPosNom;
    private JLabel               labelCouleur;

    private Color                couleur;
    private Color                CouleurAncienTheme;


    public PanelNoeud(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());

        /*Panel liste villes */
        this.panelListe = new JPanel();      
        
        /*Panel infos villes */
        this.panelInfos = new JPanel();
        
        GroupLayout layout = new GroupLayout(panelInfos);
        panelInfos.setLayout(layout);

		this.txtNom     = new TextFieldWithHint   ("Nouveau Noeud", this.ctrl);
        this.txtPosX    = new TextFieldOnlyInteger("X"            , this.ctrl);
        this.txtPosY    = new TextFieldOnlyInteger("Y"            , this.ctrl);
        this.txtPosNomX = new TextFieldOnlyInteger("PosNomX"      , this.ctrl);
        this.txtPosNomY = new TextFieldOnlyInteger("PosNomY"      , this.ctrl);


        this.btnCouleur = new JButton("Couleur");
        this.btnCouleur.setBackground(this.ctrl.getTheme().get("bottuns").get(1));
        this.add(this.btnCouleur);
        this.btnCouleur.addActionListener(e -> {
            selectColor();
        });

        this.labelPos     = new JLabel("Position"    );
        this.labelNom     = new JLabel("Nom"         );
        this.labelPosNom  = new JLabel("Position Nom");
        this.labelCouleur = new JLabel("Couleur"     );

        this.CouleurAncienTheme = null;

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup().
            addComponent(this.labelNom).addComponent(this.labelPos).addComponent(this.labelPosNom).addComponent(this.labelCouleur));

        hGroup.addGroup(layout.createParallelGroup().
            addComponent(this.txtNom).addComponent(this.txtPosX).addComponent(this.txtPosNomX).addComponent(this.btnCouleur));
        
        hGroup.addGroup(layout.createParallelGroup().addComponent(this.txtNom).
            addComponent(this.txtPosY).addComponent(this.txtPosNomY));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(this.labelNom).addComponent(this.txtNom));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(this.labelPos).addComponent(this.txtPosX).addComponent(this.txtPosY));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(this.labelPosNom).addComponent(this.txtPosNomX).addComponent(this.txtPosNomY));
        
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(this.labelCouleur).addComponent(this.btnCouleur));
        
        layout.setVerticalGroup(vGroup);

        /*Panel boutons */
        this.panelBoutons = new JPanel();
        
        this.btnAjouter = new JButton("Ajouter");
        this.btnAjouter.addActionListener(e -> {
            ajouterNoeud();
        });

        panelBoutons.add(this.btnAjouter);

        /*Ajout des panels*/
        this.add(panelListe  , BorderLayout.WEST  );
        this.add(panelInfos  , BorderLayout.CENTER);
        this.add(panelBoutons, BorderLayout.SOUTH );

        this.appliquerTheme();
    }

    /**
     * efface le formulaire suite à l'ajout ou la suppression d'un noeud
     */
    private void effacerForm()
    {
        this.txtNom    .setText("");
        this.txtPosX   .setText("");
        this.txtPosY   .setText("");
        this.txtPosNomX.setText("");
        this.txtPosNomY.setText("");
    }

    /**
     * Methode permettant d'ajouter un noeud
     */
    private void ajouterNoeud() 
    {
		boolean erreur  = false;
		String  nom     = "";
		int     posX    = 0;
		int     posY    = 0;
		int     posNomX = 0; 
		int     posNomY = 0;;

		// test nom
		if(this.txtNom.getText().equals(""))
		{
			this.txtNom.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez entrer un nom", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}
		else
		{
			nom = this.txtNom.getText();
		}
    
		// test position X
		try 
		{
			posX = Integer.parseInt(this.txtPosX.getText());
		} 
		catch (NumberFormatException e) 
		{
			JOptionPane.showMessageDialog(this, "Veuillez entrer une position X valide", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		// test position Y
		try
		{
			posY = Integer.parseInt(this.txtPosY.getText());
		}
		catch (NumberFormatException e)
		{
			this.txtPosY.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez entrer une position Y valide", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		// test position X du nom
		try
		{
			posNomX = Integer.parseInt(this.txtPosNomX.getText());
		}
		catch (NumberFormatException e)
		{
			this.txtPosNomX.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez entrer une position X du nom valide", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		// test position Y du nom
		try
		{
			posNomY = Integer.parseInt(this.txtPosNomY.getText());
		}
		catch (NumberFormatException e)
		{
			this.txtPosNomY.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez entrer une position Y du nom valide", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		// test couleur
		if(this.couleur == null)
		{
			this.btnCouleur.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Veuillez choisir une couleur", "Erreur", JOptionPane.ERROR_MESSAGE);
			erreur = true;
		}

		if(!erreur)
		{
			this.ctrl.ajouterNoeud(nom, posX, posY, posNomX, posNomY, this.couleur);
       		this.effacerForm();
		}  
    }

    private void selectColor() 
    {
        this.couleur = JColorChooser.showDialog(this, "Choisir une couleur", Color.BLACK);
        this.btnCouleur.setBackground(couleur);
    }

    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

		Color background       = theme.get("background").get(0);
        Color labelForeColor   = theme.get("labels"    ).get(0);
		Color saisiBackColor   = theme.get("saisies"   ).get(1);
        Color placeholderColor = theme.get("saisies"   ).get(2);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


        this.setBackground(background);
        this.setForeground(labelForeColor);

        this.panelListe  .setForeground(labelForeColor);
        this.panelListe  .setBackground(background);

        this.panelInfos  .setForeground(labelForeColor);
        this.panelInfos  .setBackground(background);

        this.panelBoutons.setForeground(labelForeColor);
        this.panelBoutons.setBackground(background);

        this.txtNom      .setBorder(null);
        this.txtNom      .setForeground(placeholderColor);
        this.txtNom      .setBackground(saisiBackColor);
        this.txtNom      .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosX     .setBorder(null);
        this.txtPosX     .setForeground(placeholderColor);
        this.txtPosX     .setBackground(saisiBackColor);
        this.txtPosX     .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosY     .setBorder(null);
        this.txtPosY     .setForeground(placeholderColor);
        this.txtPosY     .setBackground(saisiBackColor);
        this.txtPosY     .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosNomX  .setBorder(null);
        this.txtPosNomX  .setForeground(placeholderColor);
        this.txtPosNomX  .setBackground(saisiBackColor);
        this.txtPosNomX  .setDisabledTextColor(new Color(255, 0, 0));

        this.txtPosNomY  .setBorder(null);
        this.txtPosNomY  .setForeground(placeholderColor);
        this.txtPosNomY  .setBackground(saisiBackColor);
        this.txtPosNomY  .setDisabledTextColor(new Color(255, 0, 0));

        this.btnCouleur  .setForeground(btnForeColor);
        this.btnCouleur  .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        if (this.btnCouleur.getBackground().equals(this.CouleurAncienTheme))
        {
            this.btnCouleur.setBackground(btnBackColor);
        }

        this.btnAjouter  .setForeground(btnForeColor);
        this.btnAjouter  .setBackground(btnBackColor);
        this.btnAjouter  .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.labelPos    .setOpaque(false);
        this.labelPos    .setForeground(labelForeColor);

        this.labelNom    .setOpaque(false);
        this.labelNom    .setForeground(labelForeColor);

        this.labelPosNom .setOpaque(false);
        this.labelPosNom .setForeground(labelForeColor);

        this.labelCouleur.setOpaque(false);
        this.labelCouleur.setForeground(labelForeColor);

        this.CouleurAncienTheme = btnBackColor;
    }
}
