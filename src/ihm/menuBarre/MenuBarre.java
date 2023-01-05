package ihm.menuBarre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;


public class MenuBarre extends JMenuBar implements ActionListener 
{
	private Controleur ctrl;

	private JMenu menuFichier;
	private JMenu menuExporterSous;
	private JMenu menuPreferences;
	private JMenu menuAide;

	private JMenuItem menuiFichierNouveau;
	private JMenuItem menuiFichierOuvrir;
	private JMenuItem menuiFichierEnregistrer;
	private JMenuItem menuiFichierEnregistrerSous;

	private JMenuItem menuiFichierExporterSousGif;
	private JMenuItem menuiFichierExporterSousPng;
	private JMenuItem menuiFichierExporterSousJpeg;
	private JMenuItem menuiFichierExporterSousJpg;

	private JMenuItem menuiFichierFermer;

	private JMenuItem menuiPreferencesThemes;
	private JMenuItem menuiPreferencesThemesClair;
	private JMenuItem menuiPreferencesThemesSombre;
	private JMenuItem menuiPreferencesThemesDark;


	public MenuBarre(Controleur ctrl) 
	{
		this.ctrl = ctrl;


		/*=========================*/
		/* Création des composants */
		/*=========================*/
		/*---------*/
		/* Fichier */
		/*---------*/
		this.menuFichier = new JMenu("Fichier");
		this.menuFichier.setMnemonic('F');

		/* Nouveau */
		this.menuiFichierNouveau = new JMenuItem("Nouveau");
		this.menuiFichierNouveau.setMnemonic('N');

		/* Ouvrir */
		this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
		this.menuiFichierOuvrir.setMnemonic('O');

		/* Enregistrer */
		this.menuiFichierEnregistrer = new JMenuItem("Enregistrer");
		this.menuiFichierEnregistrer.setMnemonic('S');

		/* Enregistrer sous */
		this.menuiFichierEnregistrerSous = new JMenuItem("Enregistrer Sous");
		this.menuiFichierEnregistrerSous.setMnemonic('S');

		/* Exporter */
		this.menuExporterSous = new JMenu("Exporter");
		this.menuExporterSous.setMnemonic('E');

		/* Gif, png, jpeg, jpg */
		this.menuiFichierExporterSousGif  = new JMenuItem("gif");
		this.menuiFichierExporterSousPng  = new JMenuItem("png");
		this.menuiFichierExporterSousJpeg = new JMenuItem("jpeg");
		this.menuiFichierExporterSousJpg  = new JMenuItem("jpg");

		/* Fermer */
		this.menuiFichierFermer = new JMenuItem("Fermer");
		this.menuiFichierFermer.setMnemonic('W');


		/*------------*/
		/* Préférence */
		/*------------*/
		this.menuPreferences = new JMenu("Préférences");
		this.menuPreferences.setMnemonic('P');

		/* Thèmes */
		this.menuiPreferencesThemes       = new JMenu    ("Thèmes ");

		/* Clair, Sombre, Dark */
		this.menuiPreferencesThemesClair  = new JMenuItem("Clair" );
		this.menuiPreferencesThemesSombre = new JMenuItem("Sombre");
		this.menuiPreferencesThemesDark   = new JMenuItem("Dark"  );


		/*------*/
		/* Aide */
		/*------*/
		this.menuAide = new JMenu("Aide");
		this.menuAide.setMnemonic('A');



		/*===============================*/
		/* Positionnement des composants */
		/*===============================*/
		/*---------*/
		/* Fichier */
		/*---------*/
		this.menuFichier     .add(this.menuiFichierNouveau);
		this.menuFichier     .add(this.menuiFichierOuvrir);
		this.menuFichier     .addSeparator();
		this.menuFichier     .add(this.menuiFichierEnregistrer);
		this.menuFichier     .add(this.menuiFichierEnregistrerSous);
		this.menuExporterSous.add(this.menuiFichierExporterSousGif);
		this.menuExporterSous.add(this.menuiFichierExporterSousPng);
		this.menuExporterSous.add(this.menuiFichierExporterSousJpg);
		this.menuExporterSous.add(this.menuiFichierExporterSousJpeg);
		this.menuFichier     .add(menuExporterSous);
		this.menuFichier     .addSeparator();
		this.menuFichier     .add(this.menuiFichierFermer);
		this.add(menuFichier);

		/*------------*/
		/* Préférence */
		/*------------*/
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesClair);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesSombre);
		this.menuiPreferencesThemes.add(this.menuiPreferencesThemesDark);
		this.menuPreferences.add(this.menuiPreferencesThemes);
		this.add(menuPreferences);

		/*------*/
		/* Aide */
		/*------*/
		this.add(menuAide);


		/*============================*/
		/* Intégration des raccourcis */
		/*============================*/
		this.menuiFichierNouveau        .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+N
		this.menuiFichierOuvrir         .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+O
		this.menuiFichierEnregistrer    .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+S
		this.menuiFichierEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK )); // pour CTRL+SHIFT+S
		this.menuiFichierFermer         .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK)); // pour CTRL+W
		//menuExporterSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK )); // pour CTRL+SHIFT+E


		/*============================*/
		/* Activations des composants */
		/*============================*/
		this.menuiFichierNouveau         .addActionListener(this);
		this.menuiFichierOuvrir          .addActionListener(this);
		this.menuiFichierEnregistrer     .addActionListener(this);
		this.menuiFichierEnregistrerSous .addActionListener(this);
		this.menuExporterSous                 .addActionListener(this);
		this.menuiFichierExporterSousGif .addActionListener(this);
		this.menuiFichierExporterSousPng .addActionListener(this);
		this.menuiFichierExporterSousJpeg.addActionListener(this);
		this.menuiFichierExporterSousJpg .addActionListener(this);
		this.menuiFichierFermer          .addActionListener(this);
		this.menuiPreferencesThemesClair .addActionListener(this);
		this.menuiPreferencesThemesSombre.addActionListener(this);
		this.menuiPreferencesThemesDark  .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem) 
		{
			if (e.getSource() == this.menuiFichierNouveau) 
			{
				int choix = JOptionPane.showConfirmDialog(this.ctrl.getIHM(), "Les modification non enregistrées seront perdu, etes-vous sûr de votre choix ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
				if (choix == JOptionPane.YES_OPTION)
					this.ctrl.nouveau();
			}

			if (e.getSource() == this.menuiFichierOuvrir) 
			{
				JFileChooser chooser = new JFileChooser(".");
				chooser.setFileFilter(new FileNameExtensionFilter("Fichier XML", "xml"));

				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath() != null)
				{
					File fichier = chooser.getSelectedFile();
					String extention = fichier.getName().substring(fichier.getName().lastIndexOf('.') + 1);

					if (extention.equals("xml"))
						this.ctrl.ouvrir(fichier);
					else
						JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format XML", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
					
			}

			if (e.getSource() == this.menuiFichierEnregistrer) 
				this.ctrl.enregistrer();

			if (e.getSource() == this.menuiFichierEnregistrerSous) 
				this.ctrl.enregistrerSous();

			if (e.getSource() == this.menuiFichierExporterSousGif)
				this.ctrl.exporterSous("gif");

			if (e.getSource() == this.menuiFichierExporterSousPng) 
				this.ctrl.exporterSous("png");

			if (e.getSource() == this.menuiFichierExporterSousJpeg)
				this.ctrl.exporterSous("jpeg");

			if (e.getSource() == this.menuiFichierExporterSousJpg) 
				this.ctrl.exporterSous("jpg");

			if (e.getSource() == this.menuiFichierFermer) 
				this.ctrl.frameDispose();

			if (e.getSource() == this.menuiPreferencesThemesClair)
				this.ctrl.changerTheme("clair");
			
			if (e.getSource() == this.menuiPreferencesThemesSombre)
				this.ctrl.changerTheme("sombre");

			if (e.getSource() == this.menuiPreferencesThemesDark)
				this.ctrl.changerTheme("dark");
		}
	}

	
	/**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
	{
		Color foregroundColor = this.ctrl.getTheme().get("menuBar").get(0);
		Color backgroundColor = this.ctrl.getTheme().get("menuBar").get(1);

		/*-------------------------*/
		/* La Menu Barre elle même */
		/*-------------------------*/
		this.setForeground(foregroundColor);
		this.setBackground(backgroundColor);


		/*---------*/
		/* Fichier */
		/*---------*/
		this.menuFichier.setForeground(foregroundColor);
		this.menuFichier.setBackground(backgroundColor);

		/* Nouveau */
		this.menuiFichierNouveau.setForeground(foregroundColor);
		this.menuiFichierNouveau.setBackground(backgroundColor);

		/* Ouvrir */
		this.menuiFichierOuvrir.setForeground(foregroundColor);
		this.menuiFichierOuvrir.setBackground(backgroundColor);

		/* Enregistrer */
		this.menuiFichierEnregistrer.setForeground(foregroundColor);
		this.menuiFichierEnregistrer.setBackground(backgroundColor);

		/* Enregistrer sous */
		this.menuiFichierEnregistrerSous.setForeground(foregroundColor);
		this.menuiFichierEnregistrerSous.setBackground(backgroundColor);

		/* Exporter */
		this.menuExporterSous.setOpaque(true);
		this.menuExporterSous.setForeground(foregroundColor);
		this.menuExporterSous.setBackground(backgroundColor);

		/* Gig */
		this.menuiFichierExporterSousGif.setForeground(foregroundColor);
		this.menuiFichierExporterSousGif.setBackground(backgroundColor);

		/* Png */
		this.menuiFichierExporterSousPng.setForeground(foregroundColor);
		this.menuiFichierExporterSousPng.setBackground(backgroundColor);

		/* Jpeg */
		this.menuiFichierExporterSousJpeg.setForeground(foregroundColor);
		this.menuiFichierExporterSousJpeg.setBackground(backgroundColor);

		/* Jpg */
		this.menuiFichierExporterSousJpg.setForeground(foregroundColor);
		this.menuiFichierExporterSousJpg.setBackground(backgroundColor);

		/* Fermer */
		this.menuiFichierFermer.setForeground(foregroundColor);
		this.menuiFichierFermer.setBackground(backgroundColor);

		/*------------*/
		/* Préférence */
		/*------------*/
		/* Préférence */
		this.menuPreferences.setForeground(foregroundColor);
		this.menuPreferences.setBackground(backgroundColor);

		/* Thèmes */
		this.menuiPreferencesThemes      .setOpaque(true);
		this.menuiPreferencesThemes      .setForeground(foregroundColor);
		this.menuiPreferencesThemes      .setBackground(backgroundColor);

		/* Clair */
		this.menuiPreferencesThemesClair .setForeground(foregroundColor);
		this.menuiPreferencesThemesClair .setBackground(backgroundColor);

		/* Sombre */
		this.menuiPreferencesThemesSombre.setForeground(foregroundColor);
		this.menuiPreferencesThemesSombre.setBackground(backgroundColor);

		/* Dark */
		this.menuiPreferencesThemesDark.setForeground(foregroundColor);
		this.menuiPreferencesThemesDark.setBackground(backgroundColor);

		/*------*/
		/* Aide */
		/*------*/
		this.menuAide.setForeground(foregroundColor);
		this.menuAide.setBackground(backgroundColor);
	}
}