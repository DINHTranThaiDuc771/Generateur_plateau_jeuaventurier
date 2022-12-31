package ihm.customComponent;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldWithHint extends JTextField implements FocusListener
{
    private final String hint;
    private boolean showingHint;
    private Color foregroundColor;
    private Color placeholderColor;

  
    public TextFieldWithHint(final String hint, Controleur ctrl)
    {
        super(hint);
        this.setCaretColor     (ctrl.getTheme().get("saisies").get(0));
        this.foregroundColor  = ctrl.getTheme().get("saisies").get(0) ;
        this.placeholderColor = ctrl.getTheme().get("saisies").get(2) ;

        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }
  
    @Override
    public void focusGained(FocusEvent e)
    {
        if(this.getText().isEmpty())
        {
            super.setText("");
            this.setForeground(this.foregroundColor);
            this.showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        if(this.getText().isEmpty())
        {
            super.setText(hint);
            this.setForeground(this.placeholderColor);
            this.showingHint = true;
        }
    }
  
    @Override
    public String getText()
    {
        return this.showingHint ? "" : super.getText();
    }

    public void setPlaceholderColor(Color placeholderColor)
    {
        this.placeholderColor = placeholderColor;

        /* Permet de mettre à jour la couleur des éléments */
        this.setCaretColor(this.foregroundColor);
        this.focusGained(null);
        this.focusLost  (null);
    }

    public void setForegroundColor(Color foregroundColor)
    {
        this.foregroundColor = foregroundColor;

        /* Permet de mettre à jour la couleur des éléments */
        this.setCaretColor(this.foregroundColor);
        this.focusGained(null);
        this.focusLost  (null);
    }
}
