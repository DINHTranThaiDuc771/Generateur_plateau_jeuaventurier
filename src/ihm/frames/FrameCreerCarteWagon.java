package ihm.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.List;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import controleur.Controleur;

public class FrameCreerCarteWagon extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private int indiceMaxlstCouleur;

    private JPanel panelParametrageCarte;
    private JPanel panelVisualisationCarte;
    private JPanel panelVisualisation;
    private JPanel panelActionCarte;
    private JPanel panelCouleur;

    private List lstCarte;

    private JButton btnEnregistrer;
    private JButton btnQuitter;
    private JButton btnChoisirImage;

    private JLabel lblCarteWagon;

    private Dimension dimEcran;

    public FrameCreerCarteWagon(Controleur ctrl)
    {
        this.ctrl = ctrl;

        //Paramètres de la frame
        this.setTitle("Concepteur de cartes wagon");
        this.dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimEcran.width/10, dimEcran.height/10);
        this.setSize(dimEcran.width* 8/10, dimEcran.height* 8/10); // Définition d'une taille minimum (obligatoire)
        this.setLayout( new BorderLayout());

        //Creation des composants
        this.lstCarte = new List();
        
        this.panelParametrageCarte = new JPanel();
        this.panelParametrageCarte.setLayout(new GridLayout(3,1));

        this.panelVisualisation = new JPanel();

        this.panelVisualisationCarte = new JPanel();
        this.lblCarteWagon = new JLabel("");

        this.panelCouleur = new JPanel();

        this.panelActionCarte = new JPanel();

        try 
        {
            BufferedImage img = ImageIO.read(new File(".\\donnees\\images\\cartes_joueurs\\verso.png"));
            this.ctrl.setImageVersoCouleur(img);
            img = ImageIO.read(new File(".\\donnees\\images\\cartes_joueurs\\locomotive.png"));
            this.ctrl.setImageRectoLocomotive(img);
        } catch (IOException ex) {ex.printStackTrace();}

        this.indiceMaxlstCouleur = this.ctrl.getCouleurs().size();
        
        int indiceCarte = 1;
        for(Color c : this.ctrl.getCouleurs())
        {
            this.lstCarte.add("Carte " + indiceCarte++);
        }

        this.lstCarte.add("Verso");
        this.lstCarte.add("Locomotive");

        this.btnEnregistrer  = new JButton("Enregistrer");
        this.btnQuitter      = new JButton("Quitter");
        this.btnChoisirImage = new JButton("Choisissez l'image de la carte");

        //Ajout des composants au ActionListener
        this.lstCarte.addActionListener(this);

        this.btnEnregistrer.addActionListener(this);
        this.btnQuitter.addActionListener(this);

        this.btnChoisirImage.addActionListener(this);

        //Ajout des composants
        this.panelVisualisation.add(this.panelVisualisationCarte);

        this.panelVisualisationCarte.add(this.lblCarteWagon);

        this.panelParametrageCarte.add(this.lstCarte);
        this.panelParametrageCarte.add(this.panelCouleur);
        this.panelParametrageCarte.add(this.btnChoisirImage);

        this.panelActionCarte.add(this.btnEnregistrer, BorderLayout.SOUTH);
        this.panelActionCarte.add(this.btnQuitter, BorderLayout.SOUTH);

        this.add(this.panelParametrageCarte, BorderLayout.WEST);
        this.add(this.panelVisualisation, BorderLayout.CENTER);
        this.add(this.panelActionCarte, BorderLayout.SOUTH);

        this.appliquerTheme();
        this.setVisible(true);
    }

    //Action performed//*
    public void actionPerformed(ActionEvent e) 
    {
        if ( e.getSource() == lstCarte )
        {
            System.out.println("ligne " + this.lstCarte.getSelectedIndex());
            System.out.println("ligne max " + this.indiceMaxlstCouleur);

            if ( this.lstCarte.getSelectedIndex() == this.indiceMaxlstCouleur ) 
            { 
                BufferedImage img = this.ctrl.getImageVersoCouleur();
                this.lblCarteWagon.setIcon(new ImageIcon(img));
                this.panelVisualisationCarte.setBackground(null);
                this.panelCouleur.setBackground(null);
            }

            if ( this.lstCarte.getSelectedIndex() == this.indiceMaxlstCouleur+1 ) 
            { 
                BufferedImage img = this.ctrl.getImageRectoLocomotive();
                this.lblCarteWagon.setIcon(new ImageIcon(img));
                this.panelVisualisationCarte.setBackground(null);
                this.panelCouleur.setBackground(null);
            }

            if ( this.lstCarte.getSelectedIndex() != this.indiceMaxlstCouleur && this.lstCarte.getSelectedIndex() != this.indiceMaxlstCouleur+1 ) 
            { 
                if ( this.ctrl.getImagesRectoCouleur().get(this.lstCarte.getSelectedIndex()) != null )
                {
                    BufferedImage img = this.ctrl.getImagesRectoCouleur().get(this.lstCarte.getSelectedIndex());
                    this.lblCarteWagon.setIcon(new ImageIcon(img));
                    this.panelCouleur.setBackground(this.ctrl.getCouleurs().get(this.lstCarte.getSelectedIndex()));
                }
                this.panelCouleur.setBackground(this.ctrl.getCouleurs().get(this.lstCarte.getSelectedIndex()));
            }
        }

        if ( e.getSource() == btnEnregistrer )
        {
            // Importation du panel en image
            Dimension     d     = new Dimension (this.panelVisualisationCarte.getWidth(),this.panelVisualisationCarte.getHeight());
            BufferedImage image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D    g2d   = image.createGraphics();
            this.panelVisualisationCarte.print(g2d);
            g2d.dispose();

            if ( this.lstCarte.getSelectedIndex() == this.indiceMaxlstCouleur )
                this.ctrl.setImageVersoCouleur(image);

            else if ( this.lstCarte.getSelectedIndex() == this.indiceMaxlstCouleur+1 )
                this.ctrl.setImageRectoLocomotive(image);

            else
                this.ctrl.setImageRectoCouleur(this.lstCarte.getSelectedIndex(), image);
        }

        if ( e.getSource() == this.btnQuitter )  { this.dispose(); }

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
                        Image image = img.getScaledInstance((int) this.dimEcran.getWidth()* 9/10, (int)this.dimEcran.getHeight()* 9/10, Image.SCALE_DEFAULT);
                        this.lblCarteWagon.setIcon(new ImageIcon(image));
                        this.panelVisualisationCarte.setSize(image.getWidth(fileChooser), image.getHeight(fileChooser));
                    }
                    else if ( img.getWidth() < 100 ||  img.getHeight() < 100 )
                    {
                        Image image = img.getScaledInstance((int) this.dimEcran.getWidth()* 3/10, (int)this.dimEcran.getHeight()* 3/10, Image.SCALE_DEFAULT);
                        this.lblCarteWagon.setIcon(new ImageIcon(image));
                        this.panelVisualisationCarte.setSize(image.getWidth(fileChooser), image.getHeight(fileChooser));
                    }
                    else
                    {
                        this.lblCarteWagon.setIcon(new ImageIcon(img));
                        this.panelVisualisationCarte.setSize(img.getWidth(), img.getHeight());
                    }
                } catch (IOException ex) {ex.printStackTrace();}
                this.panelVisualisationCarte.setBackground(this.ctrl.getCouleurs().get(this.lstCarte.getSelectedIndex()));
            }
        }
    }


    public void appliquerTheme()
    {
		Color background       = this.ctrl.getTheme().get("background").get(0);
        Color labelForeColor   = this.ctrl.getTheme().get("labels"    ).get(0);
        Color saisiForeColor   = this.ctrl.getTheme().get("saisies"   ).get(0);
        Color btnForeColor     = this.ctrl.getTheme().get("bottuns"   ).get(0);
		Color btnBackColor     = this.ctrl.getTheme().get("bottuns"   ).get(1);


        this.setForeground(labelForeColor);
        this.setBackground(background);

        this.panelParametrageCarte.setForeground(labelForeColor);
        this.panelParametrageCarte.setBackground(background);

        this.panelCouleur.setForeground(labelForeColor);
        this.panelCouleur.setBackground(background);

        this.panelActionCarte.setForeground(labelForeColor);
        this.panelActionCarte.setBackground(background);

        this.lstCarte.setForeground(saisiForeColor);
        this.lstCarte.setBackground(background);

        this.btnEnregistrer.setForeground(btnForeColor);
        this.btnEnregistrer.setBackground(btnBackColor);
        this.btnEnregistrer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.btnQuitter.setForeground(btnForeColor);
        this.btnQuitter.setBackground(btnBackColor);
        this.btnQuitter.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.btnChoisirImage.setForeground(btnForeColor);
        this.btnChoisirImage.setBackground(btnBackColor);
        this.btnChoisirImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.lblCarteWagon.setOpaque(false);
        this.lblCarteWagon.setForeground(labelForeColor);

        this.panelVisualisationCarte.setForeground(labelForeColor);
        this.panelVisualisationCarte.setBackground(background);

        this.panelVisualisation.setForeground(labelForeColor);
        this.panelVisualisation.setBackground(background);
    }
}