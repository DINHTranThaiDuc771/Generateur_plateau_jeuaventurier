package ihm.frames;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controleur.Controleur;


public class FrameCouleur extends JFrame implements ActionListener
{
	private Controleur    ctrl;
	
	private JPanel        panelCouleur;
	private JPanel        panelOk;

	private List<JButton> lstBtnCoul;
	private JButton       btnPlus;
	private JButton       btnOK;

	/**
	 * 
	 * @param ctrl
	 */
	public FrameCouleur(Controleur ctrl)
	{
		this.ctrl = ctrl;

		List<Color> lstCoul = this.ctrl.getCouleurs();
		int nbLig = (int) Math.ceil((lstCoul.size() + 1.0) / 5);

		/*----------------------------*/
		/* Initialisation de la frame */
		/*----------------------------*/
		this.setTitle("Choix des couleurs disponibles dans la partie (arêtes et cartes wagon)");
		this.setSize(300, nbLig * 65 + 50);
		this.setLocation(500, 300);
		this.setLayout(new BorderLayout());

		/*----------------------------*/
		/* Création des panels        */
		/*----------------------------*/
		this.panelCouleur = new JPanel();
		this.panelCouleur.setLayout(new GridLayout(nbLig,5, 20, 20));
		this.panelCouleur.setBackground(new Color(68, 71, 90));

		this.panelOk = new JPanel();
		this.panelOk.setBackground(new Color(68, 71, 90));

		/*----------------------------*/
		/* Création des Composants    */
		/*----------------------------*/
		this.lstBtnCoul = new ArrayList<JButton>();
		for (Color c : lstCoul)
		{
			JButton btnCoul = new JButton();
			btnCoul.setBackground(c);

			this.lstBtnCoul.add(btnCoul);
		}

		this.btnPlus = new JButton("+" );
		this.btnOK   = new JButton("OK");

		/*-------------------------------*/
		/* Positionnement des Composants */
		/*-------------------------------*/
		this.add(panelCouleur, BorderLayout.CENTER);
		this.add(panelOk     , BorderLayout.SOUTH );

		// ajout des boutons de couleurs
		for (JButton btnCoul : this.lstBtnCoul)
		{
			this.panelCouleur.add(btnCoul);
		}

		// ajout du bouton ajouter
		this.panelCouleur.add(this.btnPlus);

		// ajout des espaces manquant
		for (int cpt = this.lstBtnCoul.size() + 1; cpt < nbLig * 5 ; cpt++)
			this.panelCouleur.add(new JLabel());

		this.panelOk.add(this.btnOK);
		
		/*----------------------------*/
		/* Activation des Composants  */
		/*----------------------------*/
		for (JButton btnCoul : this.lstBtnCoul)
		{
			btnCoul.addActionListener(this);
		}

		this.btnPlus.addActionListener(this);
		this.btnOK.addActionListener(this);


		this.appliquerTheme();
		this.setVisible(true);
	}

	public void majIHM()
	{
		new FrameCouleur(ctrl);
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for (JButton btnCoul : this.lstBtnCoul)
			if ( e.getSource() == btnCoul && this.lstBtnCoul.size() > 1 )
			{
				this.ctrl.supprimerCouleur(btnCoul.getBackground());
				this.majIHM();
			}

		if(e.getSource() == this.btnPlus)
		{
			Color color = JColorChooser.showDialog(this, "Choisir une couleur", Color.BLACK);

			if (color != null)
			{
				this.ctrl.ajouterCouleur(color);
				this.majIHM();
			}
		}

		if(e.getSource() == this.btnOK)
		{
			this.dispose();
		}
	}


	public void appliquerTheme()
	{
		HashMap<String, List<Color>> theme = this.ctrl.getTheme();

		Color background       = theme.get("background").get(0);
        Color labelForeColor   = theme.get("labels"    ).get(0);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


		this.setBackground(background);
		this.setForeground(labelForeColor);

		this.panelCouleur.setForeground(labelForeColor);
		this.panelCouleur.setBackground(background);

		this.panelOk     .setForeground(labelForeColor);
		this.panelOk     .setBackground(background);

		this.btnPlus     .setForeground(btnForeColor);
		this.btnPlus     .setBackground(btnBackColor);

		this.btnOK       .setForeground(btnForeColor);
		this.btnOK       .setBackground(btnBackColor);
	}
}
