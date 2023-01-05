package ihm.panels.panelGenerateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;
import ihm.customComponent.TextFieldOnlyInteger;
import ihm.panels.PanelAjoutObjectif;
import metier.CarteObjectif;
import metier.Noeud;


public class PGPanelListO extends  JPanel
{
    private Controleur        ctrl;
    private PanelAjoutObjectif panelAjoutObj;
    private JButton           btnAjouter;
    private JButton           btnImgRecto;
    private JButton           btnImgVerso;
    private JButton           btnSupprimer;
    private JComboBox<Noeud>  comboBoxListNoeudA;
    private JComboBox<Noeud>  comboBoxListNoeudB;
    private JList<CarteObjectif> jListObj;
    private JLabel            lblNoeudA;
    private JLabel            lblNoeudB;
    private JLabel            lblPoint;
    private JLabel            lblRecto;
    private JLabel            lblVerso;
    private JScrollPane       jspObjectif;
    private TextFieldOnlyInteger txtPoint;

    private List<Noeud>       lstNoeuds;
    private List<CarteObjectif> lstObj;
	private boolean estUneMaj = false;
    

    /**
     * Creates new form PGPanelListO
     */
    public PGPanelListO(Controleur ctrl)
    {
        this.ctrl               = ctrl;
        this.panelAjoutObj      = null;
        this.jspObjectif        = new  JScrollPane      ();
        this.jListObj           = new  JList<CarteObjectif>();
        this.btnAjouter         = new  JButton          ();
        this.btnSupprimer       = new  JButton          ();
        this.lblNoeudA          = new  JLabel           ();
        this.lblNoeudB          = new  JLabel           ();
        this.comboBoxListNoeudA = new  JComboBox<Noeud> ();
        this.comboBoxListNoeudB = new  JComboBox<Noeud> ();
        this.txtPoint           = new  TextFieldOnlyInteger(ctrl);
        this.lblPoint           = new  JLabel           ();
        this.lblRecto           = new  JLabel           ();
        this.lblVerso           = new  JLabel           ();
        this.btnImgRecto        = new  JButton          ();
        this.btnImgVerso        = new  JButton          ();
        this.lstNoeuds          = ctrl.getNoeuds();
        this.lstObj             = ctrl.getCarteObjectif();
        
        /*JListObj */
        this.jListObj.setModel(new AbstractListModel<CarteObjectif>() 
        {
            public int getSize() { return lstObj.size(); }
            public CarteObjectif getElementAt(int i) { return lstObj.get(i); }
        });
		if (lstObj.size() != 0)
			this.jListObj.setSelectedIndex(0);

        this.jListObj.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
				CarteObjectif objSelected = null;
				lstObj = ctrl.getCarteObjectif();

				try {
					objSelected = jListObj.getSelectedValue();
				} catch (Exception ex) {}

				if (objSelected == null && lstObj.size() != 0) 
					objSelected = lstObj.get(0);
				
				if (objSelected != null)
				{
					estUneMaj = true;
        			comboBoxListNoeudA.setSelectedIndex(lstNoeuds.indexOf(objSelected.getNoeud1()));
        			comboBoxListNoeudB.setSelectedIndex(lstNoeuds.indexOf(objSelected.getNoeud2()));
					estUneMaj = false;

					txtPoint.setText("" + objSelected.getPoints());
				}
				else
				{
					effacerForm();
				}
			}
		});

        /*Ajout de la JlistObjectif dans un JScrollPane */
        this.jspObjectif.setViewportView(jListObj);

        //btn Ajouter
        this.btnAjouter.setText("Ajouter");
        this.btnAjouter.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        //btn Supprimer
        this.btnSupprimer.setText("Supprimer");
        this.btnSupprimer.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        
        //lbl Noeud A et B
        this.lblNoeudA.setText("Noeud A");
        this.lblNoeudA.setFont(new Font("Segoe UI", 1, 12));

        this.lblNoeudB.setText("Noeud B");
        this.lblNoeudB.setFont(new Font("Segoe UI", 1, 12));

        /* comboBoxListNoeud */
        Noeud[] tabNoeudA = lstNoeuds.toArray(new Noeud[0]);
        Noeud[] tabNoeudB = lstNoeuds.toArray(new Noeud[0]);

        this.comboBoxListNoeudA.setModel(new DefaultComboBoxModel<>(tabNoeudA));
		if (lstObj.size() != 0)
			this.comboBoxListNoeudA.setSelectedIndex(lstNoeuds.indexOf(lstObj.get(0).getNoeud1()));
        this.comboBoxListNoeudA.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                comboBoxListNoeudActionPerformed(evt);
            }
        });

        this.comboBoxListNoeudB.setModel(new DefaultComboBoxModel<>(tabNoeudB));
		if (lstObj.size() != 0)
			this.comboBoxListNoeudB.setSelectedIndex(lstNoeuds.indexOf(lstObj.get(0).getNoeud2()));
        this.comboBoxListNoeudB.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                comboBoxListNoeudBctionPerformed(evt);
            }
        });

		if (lstObj.size() != 0)
			this.txtPoint.setText("" + lstObj.get(0).getPoints());
        this.txtPoint.addActionListener(new  ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                txtPointActionPerformed(evt);
            }
        });

        this.lblPoint.setText("Point");
        this.lblPoint.setFont(new Font("Segoe UI", 1, 12));

        this.lblRecto.setText("Recto");
        this.lblRecto.setFont(new Font("Segoe UI", 1, 12));

        this.lblVerso.setText("Verso");
        this.lblVerso.setFont(new Font("Segoe UI", 1, 12));

        this.btnImgRecto.setText("Image");
        this.btnImgRecto.setFocusPainted(false);
        this.btnImgRecto.addActionListener(new ActionListener()
        {
            public void actionPerformed( ActionEvent evt)
            {
                btnImgRectoActionPerformed(evt);
            }
        });

        this.btnImgVerso.setText("Image");
        this.btnImgVerso.setFocusPainted(false);
        this.btnImgVerso.addActionListener(new ActionListener()
        {
            public void actionPerformed( ActionEvent evt)
            {
                btnImgVersoActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspObjectif, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNoeudA)
                        .addGap(32, 32, 32)
                        .addComponent(comboBoxListNoeudA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNoeudB)
                        .addGap(33, 33, 33)
                        .addComponent(comboBoxListNoeudB,0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRecto)
                    .addComponent(lblVerso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImgVerso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImgRecto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10,10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoeudA)
                            .addComponent(comboBoxListNoeudA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRecto)
                            .addComponent(btnImgRecto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoeudB)
                            .addComponent(comboBoxListNoeudB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVerso)
                            .addComponent(btnImgVerso))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPoint)))
                    .addComponent(jspObjectif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                )
        );

        this.appliquerTheme();
    }                 

    /**
     * Effacer les champs du formulaire suite à l'ajout d'un objectif
     */
    protected void effacerForm() 
    {
        this.comboBoxListNoeudA.setSelectedIndex(0);
        this.comboBoxListNoeudB.setSelectedIndex(0);
        this.txtPoint.setText("");
    }
    
    private void btnAjouterActionPerformed (ActionEvent evt)
    {
        JDialog dialog = new JDialog(this.ctrl.getIHM(), "Ajouter un objectif");
        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(this.ctrl.getIHM());
        this.panelAjoutObj = new PanelAjoutObjectif(this.ctrl);
        dialog.add(this.panelAjoutObj);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void btnSupprimerActionPerformed(ActionEvent evt)
    {
        CarteObjectif objSelected = this.jListObj.getSelectedValue();
        this.ctrl.supprimerObjectif(objSelected);
        this.jListObj.updateUI();
        this.ctrl.majIHMPlateau();
    }

    public void majIHM() 
    {    
		CarteObjectif selectedItem = this.jListObj.getSelectedValue();
        this.jListObj.setModel(new AbstractListModel<CarteObjectif>()
        {
            List<CarteObjectif> lstObj = ctrl.getCarteObjectif();
            public int getSize() { return lstObj.size(); }
            public CarteObjectif getElementAt(int i) { return lstObj.get(i); }
        });

		jListObj.setSelectedValue(selectedItem, true);
    }

    private void comboBoxListNoeudActionPerformed(ActionEvent evt)
    {
		if (this.ctrl.getCarteObjectif().size() != 0)
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
					for (CarteObjectif c : this.ctrl.getCarteObjectif())
					{
						if ((c.getNoeud1().equals(noeud1) && c.getNoeud2().equals(noeud2)) ||
							(c.getNoeud1().equals(noeud2) && c.getNoeud2().equals(noeud1))   )
						{
							JOptionPane.showMessageDialog(this, "La carte objectif existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
							erreur = true;
							break;
						}
					}
				}

				if (!erreur)
					this.jListObj.getSelectedValue().setNoeud1(noeud1);
				else
					this.comboBoxListNoeudA.setSelectedItem(this.jListObj.getSelectedValue().getNoeud1());
				
				this.majIHM();
			}
		}
        
    }

    private void comboBoxListNoeudBctionPerformed(ActionEvent evt)
    {
        if (this.ctrl.getCarteObjectif().size() != 0)
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
					for (CarteObjectif c : this.ctrl.getCarteObjectif())
					{
						if ((c.getNoeud1().equals(noeud1) && c.getNoeud2().equals(noeud2)) ||
							(c.getNoeud1().equals(noeud2) && c.getNoeud2().equals(noeud1))   )
						{
							JOptionPane.showMessageDialog(this, "La carte objectif existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
							erreur = true;
							break;
						}
					}
				}

				if (!erreur)
					this.jListObj.getSelectedValue().setNoeud2(noeud2);
				else
					this.comboBoxListNoeudB.setSelectedItem(this.jListObj.getSelectedValue().getNoeud2());
				
				this.majIHM();
			}
		}
    }

    private void txtPointActionPerformed(ActionEvent evt)
    {
        if (this.txtPoint.getText().length() > 0)
		{
			try
			{
				int point = Integer.parseInt(this.txtPoint.getText());
				if (point < 0)
				{
					JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre supérieur à 0", "Erreur", JOptionPane.ERROR_MESSAGE);
					this.txtPoint.setText("" + this.jListObj.getSelectedValue().getPoints());
				}
				else
				{
					this.jListObj.getSelectedValue().setPoints(point);
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
				this.txtPoint.setText("" + this.jListObj.getSelectedValue().getPoints());
			}
		}
    }

    private void btnImgRectoActionPerformed(ActionEvent evt)
    {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("JPG & JPEG & GIF & PNG Images", "jpg", "gif", "png", "jpeg"));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);

		int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION  && fc.getSelectedFile().getPath() != null)
		{
			try
			{
				File fichier = fc.getSelectedFile();
				String extention = fichier.getName().substring(fichier.getName().lastIndexOf('.') + 1);

				if (extention.equals("jpg") || extention.equals("gif") || 
				    extention.equals("png") || extention.equals("jpeg")  )
				{
					BufferedImage img = ImageIO.read(fichier);
					this.jListObj.getSelectedValue().setImageRecto(img);
				}
				else
					JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format JPG, GIF, PNG ou JPEG", "Erreur", JOptionPane.ERROR_MESSAGE);         
			}
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Le fichier choisi n'est pas une image supportée", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnImgVersoActionPerformed(ActionEvent evt)
    {
        JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("JPG & JPEG & GIF & PNG Images", "jpg", "gif", "png", "jpeg"));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);

		int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION  && fc.getSelectedFile().getPath() != null)
		{
			try
			{
				File fichier = fc.getSelectedFile();
				String extention = fichier.getName().substring(fichier.getName().lastIndexOf('.') + 1);

				if (extention.equals("jpg") || extention.equals("gif") || 
				    extention.equals("png") || extention.equals("jpeg")  )
				{
					BufferedImage img = ImageIO.read(fichier);
					this.ctrl.setImageVersoObjectif(img);
				}
				else
					JOptionPane.showMessageDialog(this, "Le fichier choisi doit-être au format JPG, GIF, PNG ou JPEG", "Erreur", JOptionPane.ERROR_MESSAGE);         
			}
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Le fichier choisi n'est pas une image supportée", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void appliquerTheme()
    {
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

        Color background       = theme.get("background").get(0);
        Color title            = theme.get("titles"    ).get(0);
        Color labelForeColor   = theme.get("labels"    ).get(0);
        Color saisiForeColor   = theme.get("saisies"   ).get(0);
		Color saisiBackColor   = theme.get("saisies"   ).get(1);
        Color placeholderColor = theme.get("saisies"   ).get(2);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


        /* Couleur de fond du panel */
        this.setBackground(background);

        /* ScrollPane contenant la liste des noeuds */
        this.jListObj.setForeground(title);
        this.jListObj.setBackground(background);
        

        /* Bouton ajouter */
        this.btnAjouter        .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnAjouter        .setForeground(btnForeColor);
        this.btnAjouter        .setBackground(btnBackColor);

        /* Bouton supprimer */
        this.btnSupprimer      .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.btnSupprimer      .setForeground(btnForeColor);
        this.btnSupprimer      .setBackground(btnBackColor);

        /* Liste des noeuds A */
        this.comboBoxListNoeudB.setBorder(null);
        this.comboBoxListNoeudB.setForeground(saisiForeColor);
        this.comboBoxListNoeudB.setBackground(saisiBackColor);

        /* Label noeuds A */
        this.lblNoeudA         .setBorder(null);
        this.lblNoeudA         .setOpaque(false);
        this.lblNoeudA         .setForeground(labelForeColor);

        /* Label noeuds B */
        this.lblNoeudB         .setBorder(null);
        this.lblNoeudB         .setOpaque(false);
        this.lblNoeudB         .setForeground(labelForeColor);

        /* Liste des noeuds B */
        this.comboBoxListNoeudA.setBorder(null);
        this.comboBoxListNoeudA.setForeground(saisiForeColor);
        this.comboBoxListNoeudA.setBackground(saisiBackColor);

        /* TexteField des points */
        this.txtPoint          .setBorder(null);
        this.txtPoint          .setForeground(placeholderColor);
        this.txtPoint          .setBackground(saisiBackColor  );
        this.txtPoint          .setForegroundColor (saisiForeColor  );
        this.txtPoint          .setPlaceholderColor(placeholderColor);

        /* Label des points */
        this.lblPoint          .setBorder(null);
        this.lblPoint          .setOpaque(false);
        this.lblPoint          .setForeground(labelForeColor);
        

        /* Label recto */
        this.lblRecto          .setBorder(null);
        this.lblRecto          .setOpaque(false);
        this.lblRecto          .setForeground(labelForeColor);

        /* Label verso */
        this.lblVerso          .setBorder(null);
        this.lblVerso          .setOpaque(false);
        this.lblVerso          .setForeground(labelForeColor);

        /* Bouton image recto */
        this.btnImgRecto       .setForeground(btnForeColor);
        this.btnImgRecto       .setBackground(btnBackColor);
        this.btnImgRecto       .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        /* Bouton image verso */
        this.btnImgVerso       .setForeground(btnForeColor);
        this.btnImgVerso       .setBackground(btnBackColor);
        this.btnImgVerso       .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    public void selectObjectif(int index) 
    {
        this.jListObj.setSelectedIndex(index);
    }
}