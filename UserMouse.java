
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


/**
 *
 * @author Eric Jorens
 */
public class UserMouse implements MouseListener {
    @SuppressWarnings("unused")
	private JLabel mail;
    private JPanel panel;
    private JPanel image;
    
    public UserMouse(JLabel m, JPanel p, JPanel i){
        mail = m;
        panel = p;
        image = i;
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
            panel.setVisible(true);
            image.setVisible(true);
        }
    }
        // mouse entered the JLabel increment count and display it
    public void mouseEntered(MouseEvent arg0) {
        
    }
    public void mouseExited(MouseEvent arg0) {
        
    }
        // mouse was pressed (clicked and released)
        // increment counter and display it
    public void mousePressed(MouseEvent arg0) {
             
    }
    public void mouseReleased(MouseEvent arg0) {
        
    }
}
