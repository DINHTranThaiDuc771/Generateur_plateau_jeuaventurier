package ihm;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(Controleur ctrl) 
	{
		pGPanelParamJeu2 = new PGPanelParamJeu(ctrl);
        pGPanelParamPlateau1 = new PGPanelParamPlateau(ctrl);
        pGPanelAjouterElements1 = new PGPanelAjouterElements();

        pGPanelModifier1 = new PGPanelModifier();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pGPanelParamJeu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pGPanelParamPlateau1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
            .addComponent(pGPanelAjouterElements1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pGPanelModifier1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pGPanelParamPlateau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pGPanelParamJeu2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pGPanelAjouterElements1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pGPanelModifier1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                )
        );

    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private PGPanelParamJeu pGPanelParamJeu2;
    private PGPanelParamPlateau pGPanelParamPlateau1;
	private PGPanelAjouterElements pGPanelAjouterElements1;
    private PGPanelModifier pGPanelModifier1;

    // End of variables declaration                   
}