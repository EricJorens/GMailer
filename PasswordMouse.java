
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
     
/**
 *
 * @author ericwood
 */
public class PasswordMouse implements MouseListener{
    private JLabel pass;
    private String show;
    public PasswordMouse(JLabel t, String s){
        pass = t;
        show = s;
    }
    public void mouseClicked(MouseEvent e) {
   
    }
        // mouse entered the JLabel increment count and display it
    public void mouseEntered(MouseEvent arg0) {
        
    }
    public void mouseExited(MouseEvent arg0) {
        
    }
        // mouse was pressed (clicked and released)
        // increment counter and display it
    public void mousePressed(MouseEvent arg0) {
        pass.setText(show);      
    }
    public void mouseReleased(MouseEvent arg0) {
        StringBuilder str = new StringBuilder();
        for(int i = 1; i<=pass.getText().length();i++){
            str.append("*");
        }
        pass.setText(str.toString());
    }
}
