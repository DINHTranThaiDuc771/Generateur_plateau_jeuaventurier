package ihm.panels;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import java.awt.Color;

import controleur.Controleur;
import ihm.panels.panelGenerateur.PGPanelAjouterElements;
import ihm.panels.panelGenerateur.PGPanelExplorer;
import ihm.panels.panelGenerateur.PGPanelParamJeu;
import ihm.panels.panelGenerateur.PGPanelParamPlateau;


public class PanelGenerateur extends JPanel 
{
    private PGPanelParamJeu        pGPanelParamJeu;
    private PGPanelParamPlateau    pGPanelParamPlateau;
	private PGPanelAjouterElements pGPanelAjouterElements;
    private PGPanelExplorer        pGPanelExplorer;


	/**
	 * Constructeur de la classe PanelGenerateur
	 * @param ctrl
	 */
    public PanelGenerateur(Controleur ctrl)
    {
        this.pGPanelParamPlateau    = new PGPanelParamPlateau   (ctrl);
        this.pGPanelParamJeu        = new PGPanelParamJeu       (ctrl);
        this.pGPanelAjouterElements = new PGPanelAjouterElements(ctrl);
        this.pGPanelExplorer        = new PGPanelExplorer       (ctrl);


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, true)
                        .addComponent(this.pGPanelParamPlateau, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(this.pGPanelParamJeu, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                    .addComponent(this.pGPanelAjouterElements, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.pGPanelExplorer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(this.pGPanelParamPlateau, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                .addComponent(this.pGPanelParamJeu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(this.pGPanelAjouterElements, GroupLayout.PREFERRED_SIZE, 189, Short.MAX_VALUE)
                .addComponent(this.pGPanelExplorer, GroupLayout.PREFERRED_SIZE, 210, Short.MAX_VALUE)
                )
        );
    }

    public void appliquerTheme()
    {
        this.pGPanelParamPlateau   .appliquerTheme();
        this.pGPanelParamJeu       .appliquerTheme();
        this.pGPanelAjouterElements.appliquerTheme();
        this.pGPanelExplorer       .appliquerTheme();
    }

	public void majListes()
	{
		this.pGPanelExplorer.majListes();
	}

	public void selectNoeud(int index)
	{
		this.pGPanelExplorer.selectNoeud(index);
	}

	public void selectArete(int index)
	{
		this.pGPanelExplorer.selectArete(index);
	}
	
	public void selectObjectif(int index)
	{
		this.pGPanelExplorer.selectObjectif(index);
	}

	public void envoyerCouleur(Color c, String nomPanel)
	{
		this.pGPanelExplorer.envoyerCouleur(c, nomPanel);
	}
}