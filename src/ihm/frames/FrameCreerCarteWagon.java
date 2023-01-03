package ihm.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;

public class FrameCreerCarteWagon extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private int indiceCarte;

    private JPanel panelParametrageCarte;
    private JPanel panelVisualisationCarte;
    private JPanel panelVisualisation;
    private JPanel panelActionCarte;

    private List lstCarte;

    private JButton btnAjouter;
    private JButton btnSupprimer;
    private JButton btnQuitter;

    private JButton btnVerso;
    private JButton btnLocomotive;
    private JButton btnChoisirImage;
    private JButton btnChoisirCouleur;

    private JLabel lblCarteWagon;

    private Dimension dimEcran;

    public FrameCreerCarteWagon(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.indiceCarte = 1;

        //Paramètres de la frame
        this.setTitle("Concepteur de cartes wagon");
        this.dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(0, 0);
        this.setSize(dimEcran.width, dimEcran.height); // Définition d'une taille minimum (obligatoire)
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvre la fenêtre en pleine écran
        this.setLayout( new BorderLayout());

        //Creation des composants
        this.panelParametrageCarte = new JPanel();
        this.panelParametrageCarte.setLayout(new GridLayout(5,1));

        this.panelVisualisation = new JPanel();

        this.panelVisualisationCarte = new JPanel();
        this.lblCarteWagon = new JLabel("");

        this.panelActionCarte = new JPanel();

        this.lstCarte = new List();
        this.lstCarte.add("Salut");
        this.lstCarte.add("Salut");
        this.lstCarte.add("Bonjour");

        this.btnAjouter   = new JButton("Ajouter");
        this.btnSupprimer = new JButton("Supprimer");
        this.btnQuitter   = new JButton("Quitter");

        this.btnVerso          = new JButton("Carte verso");
        this.btnLocomotive     = new JButton("Carte Locomotive");
        this.btnChoisirImage   = new JButton("Choisissez l'image de la carte");
        this.btnChoisirCouleur = new JButton ("Choisissez la couleur de la carte");

        //Ajout des composants au ActionListener
        this.lstCarte.addActionListener(this);

        this.btnAjouter.addActionListener(this);
        this.btnSupprimer.addActionListener(this);
        this.btnQuitter.addActionListener(this);

        this.btnVerso.addActionListener(this);
        this.btnLocomotive.addActionListener(this);
        this.btnChoisirImage.addActionListener(this);
        this.btnChoisirCouleur.addActionListener(this);

        //Ajout des composants
        this.panelVisualisation.add(this.panelVisualisationCarte);

        this.panelVisualisationCarte.add(this.lblCarteWagon);

        this.panelParametrageCarte.add(this.lstCarte);
        this.panelParametrageCarte.add(this.btnChoisirImage);
        this.panelParametrageCarte.add(this.btnChoisirCouleur);
        this.panelParametrageCarte.add(this.btnVerso);
        this.panelParametrageCarte.add(this.btnLocomotive);

        this.panelActionCarte.add(this.btnAjouter, BorderLayout.SOUTH);
        this.panelActionCarte.add(this.btnSupprimer, BorderLayout.SOUTH);
        this.panelActionCarte.add(this.btnQuitter, BorderLayout.SOUTH);

        this.add(this.panelParametrageCarte, BorderLayout.WEST);
        this.add(this.panelVisualisation, BorderLayout.CENTER);
        this.add(this.panelActionCarte, BorderLayout.SOUTH);

        this.appliquerTheme();
        this.setVisible(true);
    }

    //Action performed//
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == btnAjouter )
        {
            String nomFichier;

            // Choix du nom du fichier
            nomFichier = JOptionPane.showInputDialog("Entrez le nom du fichier");

            if (nomFichier != null) 
            {
                File dossierWagon = new File(".\\donnees\\images\\cartes_joueurs\\wagon");

                if (!dossierWagon.exists())
                    dossierWagon.mkdir();

                // Importation du panel en image
                //this.lblCarteWagon.getIcon().getIconHeight(), this.lblCarteWagon.getIcon().getIconWidth()
                //this.panelVisualisatonCarte.getHeight(), this.panelVisualisatonCarte.getWidth()
                //this.panelVisualisatonCarte.setSize(this.lblCarteWagon.getIcon().getIconHeight(), this.lblCarteWagon.getIcon().getIconWidth());
                Dimension     d     = new Dimension (this.panelVisualisationCarte.getWidth(),this.panelVisualisationCarte.getHeight());
                BufferedImage image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D    g2d   = image.createGraphics();
                this.panelVisualisationCarte.print(g2d);
                g2d.dispose();

                // Enregistrement du fichier dans le répertoire choisi
                try 
                {
                    ImageIO.write(image, "png", new File(".\\donnees\\images\\cartes_joueurs\\wagon\\" + nomFichier + ".png"));
                    JOptionPane.showMessageDialog(this, "Exportation réussi dans le dossier wagon");
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'exportation");
                }
            }

            //En dessous est le vrai code final pour la méthode ajouter, ne pas toucher !!
            /*
            // Importation du panel en image
            Dimension     d     = new Dimension (this.lblCarteWagon.getIcon().getIconHeight(), this.lblCarteWagon.getIcon().getIconWidth()) ;
            BufferedImage image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D    g2d   = image.createGraphics();
            this.panelVisualisatonCarte.print(g2d);
            g2d.dispose();

            this.lstCarte.add("Carte " + this.indiceCarte );
            this.ctrl.setImageRectoCouleur(indiceCarte, image);
            this.indiceCarte++;*/
        }

        if ( e.getSource() == this.btnSupprimer )
        {
            //Supprime la carte de la liste
            this.lstCarte.remove(this.lstCarte.getSelectedIndex());
            //this.ctrl.supprimerImageRectoCouleur(this.lstCarte.getSelectedIndex());
        }

        if ( e.getSource() == this.btnQuitter )  { this.dispose(); }

        if ( e.getSource() == this.btnChoisirCouleur )
        {
            List lstCouleurs = new List();
            for ( Color couleur : this.ctrl.getCouleurs())
            {
                lstCouleurs.add(couleur.toString());
            }
            this.panelVisualisationCarte.setBackground(this.ctrl.getCouleurs().get(1));
        }

        if ( e.getSource() == this.btnChoisirImage )
        {
            String filePath = "";
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png", "jpeg");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                filePath  = file.getAbsolutePath();
                BufferedImage img;
                try 
                {
                    img = ImageIO.read(new File(filePath));
                    if ( img.getWidth() > 500 ||  img.getHeight() > 500 )
                    {
                        Image image = img.getScaledInstance((int) this.dimEcran.getWidth()* 8/10, (int)this.dimEcran.getHeight()* 8/10, Image.SCALE_DEFAULT);
                        this.lblCarteWagon.setIcon(new ImageIcon(image));
                        this.panelVisualisationCarte.setSize(image.getWidth(fileChooser), image.getHeight(fileChooser));
                    }
                    else
                    {
                        this.lblCarteWagon.setIcon(new ImageIcon(img));
                        this.panelVisualisationCarte.setSize(img.getWidth(), img.getHeight());
                    }
                } catch (IOException ex) {ex.printStackTrace();}
            }
        }
    }


    public void appliquerTheme()
    {
		Color background       = this.ctrl.getTheme().get("background").get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"    ).get(0);
		Color labelBackColor   = this.ctrl.getTheme().get("labels"    ).get(1);
        Color saisiForeColor   = this.ctrl.getTheme().get("saisies"   ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("bottuns"   ).get(0);
		Color btnBackColor     = this.ctrl.getTheme().get("bottuns"   ).get(1);


        this.setForeground(labelForeColor);
        this.setBackground(background);

        this.panelParametrageCarte.setForeground(labelForeColor);
        this.panelParametrageCarte.setBackground(background);

        this.panelVisualisationCarte.setForeground(labelForeColor);
        this.panelVisualisationCarte.setBackground(background);

        this.panelActionCarte.setForeground(labelForeColor);
        this.panelActionCarte.setBackground(background);

        this.lstCarte.setForeground(saisiForeColor);
        this.lstCarte.setBackground(background);

        this.btnAjouter.setForeground(btnForeColor);
        this.btnAjouter.setBackground(btnBackColor);

        this.btnSupprimer.setForeground(btnForeColor);
        this.btnSupprimer.setBackground(btnBackColor);

        this.btnQuitter.setForeground(btnForeColor);
        this.btnQuitter.setBackground(btnBackColor);

        this.btnVerso.setForeground(btnForeColor);
        this.btnVerso.setBackground(btnBackColor);

        this.btnLocomotive.setForeground(btnForeColor);
        this.btnLocomotive.setBackground(btnBackColor);

        this.btnChoisirImage.setForeground(btnForeColor);
        this.btnChoisirImage.setBackground(btnBackColor);

        this.btnChoisirCouleur.setForeground(btnForeColor);
        this.btnChoisirCouleur.setBackground(btnBackColor);

        this.lblCarteWagon.setForeground(labelForeColor);
        this.lblCarteWagon.setBackground(labelBackColor);
    }
}