package ihm;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.TransferHandler.TransferSupport;

import controleur.Controleur;

import metier.Arete;
import metier.Metier;
import metier.Noeud;

import java.awt.datatransfer.*;
import java.awt.dnd.*; 

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
import java.awt.Component;
public class PanelPlateau extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener
{
    Controleur ctrl;
    JPanel panelPlateau;
    JLabel lblImagePlateau;
    List<Noeud> lstNoeudOfIHM;
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

    public PanelPlateau(Controleur ctrl, BufferedImage image, int longueurFrame, int hauteurFrame)
    {
        this.ctrl = ctrl;

        lstNoeudOfIHM =  this.ctrl.getMetier().getNoeuds();

        this.image = image;
		this.setBackground(new Color(255, 183, 110));
        initComponent();
        this.lblImagePlateau = new JLabel("");
        this.add(lblImagePlateau);
        
        //Drag and Drop features
        initTransferHandle();
        new MonDropTargetListener(this);
    }

    private void initTransferHandle() 
    {
        TransferHandler dnd = new TransferHandler() {
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
                Icon ico;
                try {
                    ico = (Icon) tansferable.getTransferData(DataFlavor.imageFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        };

        this.setTransferHandler(dnd);
    }

    public void setCouleur(Color color) {
		this.setBackground(color);
	}

    public BufferedImage getImage()
    {
        return this.image;
    }

    public void setImageFond(BufferedImage img)
    {
        this.image = img;
        repaint();
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

        if (dragger) 
        {
            AffineTransform at = new AffineTransform();
			//System.out.println("------------------\n" + xOffset + " " + yOffset + " | " + xDiff + " " + yDiff);
            at.translate(xOffset + xDiff, yOffset + yDiff);
			//System.out.println(xOffset + " " + yOffset + " | " + xDiff + " " + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) 
            {
				xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }
        }

        // All drawings go here

        g2.drawImage(image, (int)xOffset, (int)yOffset, this);


        Metier metier = this.ctrl.getMetier();
        g2.setFont(metier.getPolicePlateau());

        for (Noeud noeud : lstNoeudOfIHM)
        {
            g2.setColor(noeud.getCouleur());
            g2.fillOval(noeud.getX(), noeud.getY(), 10, 10);
            g2.drawString(noeud.getNom(), noeud.getXNom(), noeud.getYNom());
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
		if (SwingUtilities.isRightMouseButton(e))
		{
			Point curPoint = e.getLocationOnScreen();
			double prevXOffset = xOffset + curPoint.x - startPoint.x;
			double prevYOffset = yOffset + curPoint.y - startPoint.y;

			if (prevXOffset < 400 && prevXOffset > (-this.image.getWidth()  /2 - 400)*zoomFactor &&
				prevYOffset < 300 && prevYOffset > (-this.image.getHeight() /2 - 300)*zoomFactor    )
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
		if (SwingUtilities.isRightMouseButton(e))
		{
			released = false;
			startPoint = MouseInfo.getPointerInfo().getLocation();
		}
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
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
        private DropTarget dropTarget;
        private JPanel p;
        public MonDropTargetListener (JPanel panel)
        {
            p = panel;
            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
        }
        @Override
        public void drop(DropTargetDropEvent event) 
        {
            try {
                DropTarget test = (DropTarget) event.getSource();
                Component ca = (Component) test.getComponent();
                Point dropPoint = ca.getMousePosition();
                Transferable tr = event.getTransferable();
    
                if (event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    Icon ico = (Icon) tr.getTransferData(DataFlavor.imageFlavor);
    
                    if (ico != null) {
                        //TODO Action à faire quand on drop 
                        int x, y;
                        x = (int) event.getLocation().getX();
                        y = (int) event.getLocation().getY();  
                        lstNoeudOfIHM.add(new Noeud("Test Drop", x, y, x-10, y-10, Color.BLACK)) ;                 
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
