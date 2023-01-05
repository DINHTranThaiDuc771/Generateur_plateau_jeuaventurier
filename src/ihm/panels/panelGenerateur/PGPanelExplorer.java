package ihm.panels.panelGenerateur;

import controleur.Controleur;

import javax.swing.JTabbedPane;
import java.awt.Color;


public class PGPanelExplorer extends javax.swing.JPanel
{
    private Controleur ctrl;

    private JTabbedPane  jTabbedPane1;
    private PGPanelListA pGPanelListA;
    private PGPanelListN pGPanelListN;
    private PGPanelListO pGPanelListO;
    /**
     * Creates new form PGPanelExplorer
     */
    public PGPanelExplorer(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.jTabbedPane1 = new JTabbedPane();
        this.pGPanelListN = new PGPanelListN(ctrl);
        this.pGPanelListA = new PGPanelListA(ctrl);
        this.pGPanelListO = new PGPanelListO(ctrl);


        this.jTabbedPane1.setName("");
        this.jTabbedPane1.addTab("Noeud"    , this.pGPanelListN);
        this.jTabbedPane1.addTab("Arêtes"   , this.pGPanelListA);
        this.jTabbedPane1.addTab("Objectifs", this.pGPanelListO);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this.jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(this.jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        this.jTabbedPane1.getAccessibleContext().setAccessibleName("List Noeud");

        this.appliquerTheme();
    }

    public void appliquerTheme()
    {
        this.setBackground(this.ctrl.getTheme().get("background").get(0));

        this.jTabbedPane1.setForeground(this.ctrl.getTheme().get("titles").get(0));
        this.jTabbedPane1.setBackground(this.ctrl.getTheme().get("titles").get(1));

        this.pGPanelListA.appliquerTheme();
        this.pGPanelListN.appliquerTheme();
        this.pGPanelListO.appliquerTheme();
    }

	public void majListes()
	{
		this.pGPanelListA.majIHM();
		this.pGPanelListN.majIHM();
		this.pGPanelListO.majIHM();
	}

	public void selectNoeud(int index)
	{
		this.jTabbedPane1.setSelectedIndex(0);
		this.pGPanelListN.selectNoeud(index);
	}

	public void selectArete(int index)
	{
		this.jTabbedPane1.setSelectedIndex(1);
		this.pGPanelListA.selectArete(index);
	}

    public void selectObjectif(int index)
    {
        this.jTabbedPane1.setSelectedIndex(2);
        this.pGPanelListO.selectObjectif(index);
    }

	public void envoyerCouleur(Color c, String nomPanel)
	{
		this.pGPanelListA.envoyerCouleur(c, nomPanel);
	}
}