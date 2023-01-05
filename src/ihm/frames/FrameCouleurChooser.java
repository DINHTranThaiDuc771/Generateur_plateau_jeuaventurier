package ihm.frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import controleur.Controleur;


public class FrameCouleurChooser extends JFrame implements ActionListener
{
	private Controleur ctrl;
	private String     nomPanel;
	
	private List<JButton> lstBtnCoul;

	/**
	 * 
	 * @param ctrl
	 */
	public FrameCouleurChooser(Controleur ctrl, String nomPanel)
	{
		this.ctrl = ctrl;
		this.nomPanel = nomPanel;

		List<Color> lstCoul = this.ctrl.getCouleurs();
		int nbLig = (int) Math.ceil((double) lstCoul.size() / 5);

		/*----------------------------*/
		/* Initialisation de la frame */
		/*----------------------------*/
		this.setSize(300, nbLig * 70);
		this.setLocation(500, 300);
		this.setLayout(new GridLayout(nbLig,5, 20, 20));

		/*----------------------------*/
		/* CrÃ©ation des Composants    */
		/*----------------------------*/
		this.lstBtnCoul = new ArrayList<JButton>();
		for (Color c : lstCoul)
		{
			JButton btnCoul = new JButton();
			btnCoul.setBackground(c);

			this.lstBtnCoul.add(btnCoul);
		}

		/*-------------------------------*/
		/* Positionnement des Composants */
		/*-------------------------------*/

		// ajout des boutons de couleurs
		for (JButton btnCoul : this.lstBtnCoul)
		{
			this.add(btnCoul);
		}

		// ajout des espaces manquant
		for (int cpt = this.lstBtnCoul.size(); cpt < nbLig * 5 ; cpt++)
			this.add(new JLabel());

		
		/*----------------------------*/
		/* Activation des Composants  */
		/*----------------------------*/
		for (JButton btnCoul : this.lstBtnCoul)
		{
			btnCoul.addActionListener(this);
		}
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for (JButton btnCoul : this.lstBtnCoul)
			if ( e.getSource() == btnCoul )
			{
				this.ctrl.envoyerCouleur(btnCoul.getBackground(), this.nomPanel);
				this.dispose();
			}
	}
}