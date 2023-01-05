package ihm.frames;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import controleur.Controleur;
import ihm.customComponent.GrillePoints;


public class FramePoint extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JPanel      panelPoint;
	private JPanel      panelButton;

	private JTable      tblPoint;
	private JScrollPane spPoint;
	private JButton     btnAjouter;
	private JButton     btnSupprimer;
	private JButton     btnOk;

	
	public FramePoint(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Modifier les points");
		this.setSize(500, 200);
		this.setLocation(500, 300);
		this.setLayout(new BorderLayout());

		// Création des panels
		this.panelPoint  = new JPanel(new BorderLayout());
		this.panelButton = new JPanel();

		// Création des composants
		this.tblPoint = new JTable(new GrillePoints(this.ctrl));
		this.tblPoint.setFillsViewportHeight(true);

		this.spPoint = new JScrollPane(this.tblPoint);
		this.spPoint.setVerticalScrollBar(new JScrollBar());
		this.spPoint.setWheelScrollingEnabled(true);
		this.spPoint.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		/* Création des boutons */
		this.btnAjouter   = new JButton("Ajouter"  );
		this.btnSupprimer = new JButton("Supprimer");
		this.btnOk        = new JButton("Ok"       );

		// Positionnement des composants
		this.panelPoint.add(this.spPoint, BorderLayout.CENTER);

		this.panelButton.add(this.btnAjouter);
		this.panelButton.add(this.btnSupprimer);
		this.panelButton.add(this.btnOk);

		this.add(panelPoint , BorderLayout.CENTER);
		this.add(panelButton, BorderLayout.SOUTH );

		// Activation des composants
		this.btnAjouter  .addActionListener(this);
		this.btnSupprimer.addActionListener(this);
		this.btnOk       .addActionListener(this);

		this.appliquerTheme();
		this.setVisible(true);        
	}

	public void appliquerTheme()
	{
		HashMap<String, List<Color>> theme = this.ctrl.getTheme();

		Color background       = theme.get("background").get(0);
		Color titleForeColor   = theme.get("titles"    ).get(0);
		Color titleBackColor   = theme.get("titles"    ).get(1);
        Color labelForeColor   = theme.get("labels"    ).get(0);
        Color btnForeColor     = theme.get("bottuns"   ).get(0);
		Color btnBackColor     = theme.get("bottuns"   ).get(1);


		this.setBackground(background);

		this.panelPoint .setBackground(background);
		this.panelButton.setBackground(background);

		this.tblPoint	 .setForeground (labelForeColor);
		this.tblPoint    .setBackground (background    );
		this.tblPoint    .getTableHeader().setForeground(titleForeColor);
		this.tblPoint    .getTableHeader().setBackground(titleBackColor);

		this.spPoint     .setForeground(labelForeColor);
		this.spPoint     .setBackground(background);

		this.btnAjouter  .setForeground(btnForeColor);
		this.btnAjouter  .setBackground(btnBackColor);
		this.btnAjouter  .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		this.btnSupprimer.setForeground(btnForeColor);
		this.btnSupprimer.setBackground(btnBackColor);
		this.btnSupprimer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		this.btnOk       .setForeground(btnForeColor);
		this.btnOk       .setBackground(btnBackColor);
		this.btnOk       .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnAjouter)
		{   
			this.ctrl.ajouterPoint();
			this.tblPoint.setModel(new GrillePoints(ctrl));
		}
		if (e.getSource() == this.btnSupprimer) 
		{
			this.ctrl.supprimerPoint();
			this.tblPoint.setModel(new GrillePoints(ctrl));
		}

		if (e.getSource() == this.btnOk) 
		{
			this.dispose();
		}

	}
}

