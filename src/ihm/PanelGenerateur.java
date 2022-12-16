package ihm;
import javax.swing.*;


import controleur.Controleur;
public class PanelGenerateur extends JPanel 
{	
	private Controleur ctrl;
	/**
	 * Constructeur de la classe PanelGenerateur
	 * @param ctrl
	 */
    public PanelGenerateur(Controleur ctrl) {
        this.ctrl = ctrl;
		initComponents(ctrl);
    }
                     
    private void initComponents(Controleur ctrl) 
	{
        pGPanelParamPlateau = new PGPanelParamPlateau(ctrl);
        pGPanelParamJeu = new PGPanelParamJeu(ctrl);
        pGPanelAjouterElements = new PGPanelAjouterElements();
        pGPanelModifier = new PGPanelModifier();


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pGPanelParamPlateau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(pGPanelParamJeu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                    .addComponent(pGPanelAjouterElements, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pGPanelModifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pGPanelParamPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pGPanelParamJeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pGPanelAjouterElements, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pGPanelModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private PGPanelParamJeu pGPanelParamJeu;
    private PGPanelParamPlateau pGPanelParamPlateau;
	private PGPanelAjouterElements pGPanelAjouterElements;
    private PGPanelModifier pGPanelModifier;

    // End of variables declaration                   
}