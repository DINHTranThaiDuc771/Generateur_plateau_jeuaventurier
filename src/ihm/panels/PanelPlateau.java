package ihm.panels;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import controleur.Controleur;

import metier.Arete;
import metier.Noeud;

import java.awt.datatransfer.*;
import java.awt.dnd.*; 
import java.awt.Shape;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;


public class PanelPlateau extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener
{
    private Controleur ctrl;
    private JLabel lblImagePlateau;
	private int[] taillePlateau;

	private Ellipse2D[]   tabNoeud;
	private Integer       idNoeudDrag;
	private Rectangle2D[] tabNomNoeud;
	private Integer       idNomNoeudDrag;

    private BufferedImage image;

    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;

    private Noeud selectionNoeud;


    public PanelPlateau(Controleur ctrl)
    {
        this.ctrl = ctrl;
		
		this.idNoeudDrag = null;

        this.initComponent();

        this.lblImagePlateau = new JLabel("");
        this.add(lblImagePlateau);
        
        //Drag and Drop features
        this.initTransferHandle();
        new MonDropTargetListener(this);
    }

    public void appliquerTheme()
    {
        this.setBackground(this.ctrl.getTheme().get("background").get(0));
    }

    public void selectNoeud(int noeud)
    {
        for(Noeud n : this.ctrl.getNoeuds())
            if(n.getId() == noeud + 1)
                this.selectionNoeud = n;
        this.repaint();
    }

    private void initTransferHandle() 
    {
        TransferHandler dnd = new TransferHandler()
        {
            @Override
            public boolean canImport(TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                //only Strings
                if (!support.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable tansferable = support.getTransferable();
                try {
                    tansferable.getTransferData(DataFlavor.imageFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        };

        this.setTransferHandler(dnd);
    }

    public BufferedImage getImage()
    {
        return this.image;
    }

    public void setImageFond(BufferedImage img)
    {
        this.image = img;
        this.repaint();
    }

    private void initComponent() 
    {
        this.addMouseWheelListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

		// affichage du zoom
        if (zoomer) 
        {
			AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = false;
        }

		// affichage du déplacement
        if (dragger) 
        {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) 
            {
				xOffset += xDiff;
                yOffset += yDiff;
				
                dragger = false;
            }
        }

        //      affichage du plateau

		// récupération des variables et initialisation des tableaux
		this.taillePlateau   = this.ctrl.getTaillePlateau();
		List<Noeud> lstNoeud = this.ctrl.getNoeuds();
		this.tabNoeud    = new Ellipse2D  [lstNoeud.size()];
		this.tabNomNoeud = new Rectangle2D[lstNoeud.size()];

		// affichage de la couleur de fond
		g2.setColor(this.ctrl.getCouleurPlateau());
		g2.fillRect(0, 0, taillePlateau[0], taillePlateau[1]);

		// affichage de l'image de fond
		BufferedImage img = this.ctrl.getImagePlateau();
		if (img != null && taillePlateau[0] > 0 && taillePlateau[1] > 0)
		{
			// on redimensionne l'image de fond pour qu'elle corresponde à la taille du plateau
			BufferedImage imgPlateau = new BufferedImage(taillePlateau[0], taillePlateau[1], img.getType());
			Graphics2D gImg = imgPlateau.createGraphics();
			gImg.drawImage(img, 0, 0, null);

			g2.drawImage(imgPlateau, 0, 0, this);
		}

		// définition de la police d'écriture
		Font police = this.ctrl.getPolicePlateau();
		if (police != null)
       		g2.setFont(police);

		// affichage des aretes
		for (Arete arete : this.ctrl.getAretes())
		{
			Point n1, n2;
			// on calcul l'angle de rotation à partir de la tangante de notre angle
			double angle = Math.atan((double) (arete.getNoeud2().getY() - arete.getNoeud1().getY()) / 
			                                  (arete.getNoeud2().getX() - arete.getNoeud1().getX())  );

			// si couleur 2 est null alors nous somme sur une arete simple
			// sinon nous sommes sur une arete double
			if (arete.getCouleur2() == null)
			{
				n1 = new Point(arete.getNoeud1().getX(), arete.getNoeud1().getY());
				n2 = new Point(arete.getNoeud2().getX(), arete.getNoeud2().getY());
				this.paintArete(g2, n1, n2, arete.getDistance(), arete.getCouleur1(), angle);
			}
			else
			{
				int adj = (int) (12 * Math.cos(angle + 1.57)); //90° = 1.57
				int opp = (int) (12 * Math.sin(angle + 1.57));

				n1 = new Point(arete.getNoeud1().getX() + adj, arete.getNoeud1().getY() + opp);
				n2 = new Point(arete.getNoeud2().getX() + adj, arete.getNoeud2().getY() + opp);
				this.paintArete(g2, n1, n2, arete.getDistance(), arete.getCouleur1(), angle);

				n1 = new Point(arete.getNoeud1().getX() - adj, arete.getNoeud1().getY() - opp);
				n2 = new Point(arete.getNoeud2().getX() - adj, arete.getNoeud2().getY() - opp);
				this.paintArete(g2, n1, n2, arete.getDistance(), arete.getCouleur2(), angle);
			}
		}
		
		// affichage des noeuds
		int i = 0;
        for (Noeud noeud : lstNoeud)
        {
			int midX = noeud.getX();
			int midY = noeud.getY();

			// contour du noeud
			g2.setColor(Color.BLACK);
			g2.fillOval(midX-12, midY-12, 24, 24);

            if (noeud == selectionNoeud)
            {
                g2.setColor(Color.RED);
                g2.drawOval(midX-14, midY-14, 28, 28);
            }

			// noeud
            g2.setColor(noeud.getCouleur());
            g2.fillOval(midX-10, midY-10, 20, 20);

			this.tabNoeud[i] = new Ellipse2D.Double(midX-12, midY-12, 24, 24);

			// contour du nom du noeud
			FontMetrics metrics = g.getFontMetrics();
			int width = metrics.stringWidth(noeud.getNom());

			g2.setColor(Color.WHITE);
			g2.fillRect(midX + noeud.getXNom() - (noeud.getNom().length() * 3), 
			            midY + noeud.getYNom() - 7, 
			            width, 14);

			this.tabNomNoeud[i++] = new Rectangle2D.Double(midX + noeud.getXNom() - (noeud.getNom().length() * 3), 
			                                               midY + noeud.getYNom() - 7, 
			                                               width, 14);

			// nom du noeud	
			g2.setColor(Color.BLACK);
            g2.drawString(noeud.getNom(), 
			              midX + noeud.getXNom() - (noeud.getNom().length() * 3), 
			              midY + noeud.getYNom() + 4);

        }
    }

	private void paintArete(Graphics2D g2, Point n1, Point n2, int d, Color c, double angle)
	{
		for (double cpt = 1 ; cpt < d + 1 ; cpt++)
		{
			// on récupère les coordonnées centrale de notre tronçon
			int x = (int) (n1.getX() + ((n2.getX() - n1.getX()) * (cpt / (d + 1))));
			int y = (int) (n1.getY() + ((n2.getY() - n1.getY()) * (cpt / (d + 1))));

			// on créer notre tronçon sans son angle
			RoundRectangle2D fig1 = new RoundRectangle2D.Double(x - 25, y - 10, 50, 20, 25, 25);

			// on créer un autre tronçon mais avec son angle cette fois-ci
			AffineTransform t = new AffineTransform();
			t.rotate(angle, fig1.getX()+25, fig1.getY()+10);
			Shape fig2 = t.createTransformedShape(fig1);

			// on dessine notre troncon
			g2.setColor(c);
			g2.fill(fig2);
			g2.setColor(Color.BLACK);
			g2.draw(fig2);
		}
	}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    {

        zoomer = true;

        //Zoom in
        if (e.getWheelRotation() < 0 && zoomFactor < 3)
        {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0 && zoomFactor > 0.5)
        {
            zoomFactor /= 1.1;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
		if (SwingUtilities.isLeftMouseButton(e))
		{
			if (this.idNoeudDrag != null)
				this.ctrl.setPositionNoeud(this.idNoeudDrag, e.getX(), e.getY());

			if (this.idNomNoeudDrag != null)
			{
				int x = e.getX() - this.ctrl.getNoeuds().get(this.idNomNoeudDrag).getX(); 
				int y = e.getY() - this.ctrl.getNoeuds().get(this.idNomNoeudDrag).getY(); 

				this.ctrl.setPositionNomNoeud(this.idNomNoeudDrag, x, y);
			}
				
			
			this.repaint();
		}

		if (SwingUtilities.isRightMouseButton(e))
		{
			Point curPoint = e.getLocationOnScreen();
			double prevXOffset = xOffset + curPoint.x - startPoint.x;
			double prevYOffset = yOffset + curPoint.y - startPoint.y;

			if (prevXOffset < 400 && prevXOffset > -(this.taillePlateau[0] / 2 + 400) * zoomFactor &&
				prevYOffset < 300 && prevYOffset > -(this.taillePlateau[1] / 2 + 300) * zoomFactor    )
			{
				xDiff = curPoint.x - startPoint.x;
				yDiff = curPoint.y - startPoint.y;
				repaint();
			}

			dragger = true;
		}
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) 
    {
		if (SwingUtilities.isLeftMouseButton(e))
		{
			for (int i = 0 ; i < this.tabNoeud.length ; i++)
				if (this.tabNoeud[i].contains(e.getPoint()))
				{
					this.idNoeudDrag = i;
					this.ctrl.selectNoeud(i);
				}

			for (int i = 0 ; i < this.tabNomNoeud.length ; i++)
				if (this.tabNomNoeud[i].contains(e.getPoint()))
				{
					this.idNomNoeudDrag = i;
					this.ctrl.selectNoeud(i);
				}
		}

		if (SwingUtilities.isRightMouseButton(e))
		{
			released = false;
			startPoint = MouseInfo.getPointerInfo().getLocation();
		}
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
		if (SwingUtilities.isLeftMouseButton(e))
		{
			this.idNoeudDrag = null;
			this.idNomNoeudDrag = null;
		}
			

		if (SwingUtilities.isRightMouseButton(e))
		{
			released = true;
			repaint();
		}
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e){}

    class MonDropTargetListener extends DropTargetAdapter
    {
        private JPanel p;
        public MonDropTargetListener (JPanel panel)
        {
            p = panel;
            new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
        }
        @Override
        public void drop(DropTargetDropEvent event) 
        {
            try
            {
                ((Component) ((DropTarget) event.getSource()).getComponent()).getMousePosition();
                Transferable tr = event.getTransferable();
    
                if (event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    Icon ico = (Icon) tr.getTransferData(DataFlavor.imageFlavor);
    
                    if (ico != null) {
                        /* Action à faite quand on drop */
                        int x, y;
                        x = (int) event.getLocation().getX();
                        y = (int) event.getLocation().getY();  
						PanelPlateau.this.ctrl.ajouterNoeud("", x, y, 0, -20, Color.BLACK);            
                        p.revalidate();
                        p.repaint();
                        event.dropComplete(true);
                    }
                } else {
                    event.rejectDrop();
                }
            } catch (Exception e) {
                e.printStackTrace();
                event.rejectDrop();
            }
        }
    }
}
