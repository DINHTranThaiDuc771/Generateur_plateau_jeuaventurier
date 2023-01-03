package ihm.customComponent;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class PanelWithImg extends JPanel
{
    private BufferedImage img;

    public PanelWithImg (BufferedImage img)
    {
        super();
        this.setSize(200,200);
        this.img = img;
    }
    public void setImage (BufferedImage img)
    {
        this.img = img;
    }
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        if (img != null)
		{
			// on redimensionne l'image de fond pour qu'elle corresponde à la taille du plateau
			BufferedImage imgPlateau = new BufferedImage(this.getWidth(), this.getHeight(), img.getType());
			Graphics2D gImg = imgPlateau.createGraphics();
			gImg.drawImage(img, 0, 0, null);

			g2.drawImage(imgPlateau, 0, 0, this);
		}
    }
}
