package ihm.panels.panelGenerateur;

import java.awt.dnd.*; //Drag and Drop pakcage
import java.awt.dnd.DragGestureListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.datatransfer.*;

import controleur.Controleur;


public class PGPanelAjouterElements extends JPanel 
{
    private Controleur ctrl;

    private JLabel imgNoeud;
    private JLabel lblAjouterElements;
    private JLabel lblNoeud;


    public PGPanelAjouterElements(Controleur ctrl)
    {
        this.ctrl = ctrl;

        initComponents();
        dragAndDropSettings();
    }

    private void dragAndDropSettings() 
    {
        DragSource ds = new DragSource();
        MonDragGestureListener dlistener = new MonDragGestureListener();
        ds.createDefaultDragGestureRecognizer(imgNoeud, DnDConstants.ACTION_COPY, dlistener);
    }


    private void initComponents()
    {
        this.lblAjouterElements = new JLabel ();
        this.imgNoeud           = new JLabel ();
        this.lblNoeud           = new JLabel ();

        this.setPreferredSize(new Dimension(403, 215));

        this.lblAjouterElements.setText(" Ajouter éléments");

        this.imgNoeud.setIcon(new ImageIcon(getClass().getResource("/donnees/images/noeud.png")));

        this.lblNoeud.setText("Noeud");
        this.lblNoeud.setFont(new Font("Segoe UI", 1, 12));


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.lblAjouterElements, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.imgNoeud, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addComponent(this.lblNoeud, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))

            )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.lblAjouterElements, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(this.imgNoeud, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                .addGap(18,18,18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(this.lblNoeud))
                )
        );


        this.appliquerTheme();
    }



    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        /* Couleurs */
        Color initialColor = g.getColor();
        
        /* Noeud */
        g.setColor(this.ctrl.getTheme().get("labels").get(0));
        g.fillOval(29, 40, 69, 69);


        g.setColor(initialColor);
    }

    
    class MonDragGestureListener implements DragGestureListener
    {
        public void dragGestureRecognized(DragGestureEvent event) 
        {
            JLabel label = (JLabel) event.getComponent();
            final Icon ico = label.getIcon();


            Transferable transferable = new Transferable()
            {
                @Override
                public DataFlavor[] getTransferDataFlavors()
                {
                    return new DataFlavor[]{DataFlavor.imageFlavor};
                }

                @Override
                public boolean isDataFlavorSupported(DataFlavor flavor)
                {
                    if (!isDataFlavorSupported(flavor))
                    {
                        return false;
                    }
                    return true;
                }

                @Override
                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
                {
                    return ico;
                }
            };
            event.startDrag(null, transferable);
        }
    }


    /**
     * Applique le thème à tout les composants du panel
     */
    public void appliquerTheme()
	{
        HashMap<String, List<Color>> theme = this.ctrl.getTheme();

		Color background     = theme.get("background").get(0);
        Color titleForeColor = theme.get("titles"    ).get(0);
		Color titleBackColor = theme.get("titles"    ).get(1);
        Color labelForeColor = theme.get("labels"    ).get(0);


        this.setBackground(background);
        
        this.lblAjouterElements.setOpaque(true);
        this.lblAjouterElements.setForeground(titleForeColor);
        this.lblAjouterElements.setBackground(titleBackColor);

        this.lblNoeud.setBorder(null);
        this.lblNoeud.setOpaque(false);
        this.lblNoeud.setForeground(labelForeColor);
        this.lblNoeud.setHorizontalAlignment(JLabel.CENTER);

        this.imgNoeud.setHorizontalAlignment(JLabel.CENTER);
	}
}

