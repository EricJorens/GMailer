
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Eric Jorens
 */
@SuppressWarnings("serial")
public class MailGui extends JFrame implements KeyListener{

    public MailGui(){
        super("GMailer");
        buildMe();
        mainMail = new SendMyMail(); 
    }
 
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== 10 && passwordPanel.isVisible()){
            //mainBlock.setLocation(mainBlock.getX()-20, mainBlock.getY());
            //mainBlock.setSize(new Dimension(mainBlock.getWidth()+20, mainBlock.getHeight()+20));
            //this.mainBlock.setVisible(false);
            submit.doClick();
        }     
    }
       
        // static method to test the GUI
    public void keyReleased(KeyEvent e){
      
    }
        
    public void keyTyped(KeyEvent e){
                
    }
    private void resetAction(ActionEvent a){
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to Reset?", "Reset Dialogue",DISPOSE_ON_CLOSE);
	if(result == JOptionPane.YES_OPTION){
            inputText.setText("");
        }
    }
    private void sendAction(ActionEvent a){
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to Send?", "Send Dialogue",DISPOSE_ON_CLOSE);
	if(result == JOptionPane.YES_OPTION){
           
            
           mainMail.setReciever(textArray[0].getText());
           mainMail.setSubject(textArray[1].getText());
           mainMail.setMessageText(inputText.getText());
           mainMail.setSmtp("smtp.gmail.com", "465", "true");
           int err = mainMail.SendIt();
           if(err == 0){
               JOptionPane.showMessageDialog(null, "Email Sent Successfully");
           }else{
               JOptionPane.showMessageDialog(null, "Error Sending Email");
           }
        }
        
    }
    public void submitAction(ActionEvent a){
        if(emailValidate(userField.getText())&& passField.getPassword().length != 0){      
           passwordPanel.setVisible(false);
           imagePanel.setVisible(false);
           mainMail.setUserName(userField.getText());
           //System.out.println(passField.getPassword());
           mainMail.setPassword(new String(passField.getPassword()));
           labelArray[5].setText(userField.getText());
           //labelArray[6].setText("********");
        
           //set the action for the password click
           PasswordMouse m = new PasswordMouse(labelArray[6],new String(passField.getPassword()));
           labelArray[6].addMouseListener(m);
           // set the ***s
           
           StringBuilder str = new StringBuilder();
           for(int i = 1; i<= new String (passField.getPassword()).length();i++){
            str.append("*");
           }
           
           labelArray[6].setText(str.toString());
           
           
           
            
        }else{
           JOptionPane.showMessageDialog(this, "Invalid Login Information", "GMailer", INFORMATION_MESSAGE);
        }

        
    }
    
    private boolean emailValidate(String email){
        //email validation
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        if(m.find()){
            return true;
        }else{
            return false;
        }
    }
    private void buildMe(){
        Container c = this.getContentPane();
        c.setLayout(new GridLayout());
        GridBagConstraints set = new GridBagConstraints();
        GridBagConstraints setPass = new GridBagConstraints();
        c.setBackground(Color.white);
        
        
        
        //make the panels
        layeredPane = new JLayeredPane();
        messagePanel = new JPanel(new GridBagLayout());
        passwordPanel = new JPanel(new GridBagLayout());
        
        //-------------IMAGE PANEL----------------   
        imagePanel = new JPanel();
        //imagePanel.setBackground(Color.white);
        
        
        //------------MESSAGE PANEL----------------
        
        
        //make the button array
        buttonArray = new JButton[]{new JButton("Reset"),new JButton("Send"),new JButton("Logout")};
        
        //add button actions
        buttonArray[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                resetAction(ae);
                
            }
        });
        buttonArray[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                sendAction(ae);
                
            }
        });
        
        //make the label array
        //last two labels will hold user name and password
        labelArray = new JLabel[]{new JLabel("to:"),new JLabel("subject:"),
            new JLabel("message:"),new JLabel("username:"), new JLabel("password:"), new JLabel(), new JLabel(), new JLabel("-")};
        
        
        //for password
        labelArray[6].setToolTipText("click to show");
        labelArray[6].setForeground(Color.blue);
        labelArray[6].setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        
        
        //double click the email field
        UserMouse um = new UserMouse(labelArray[5],passwordPanel,imagePanel); //this is the mouse action for the label
        labelArray[5].addMouseListener(um);
        labelArray[5].setToolTipText("double click to edit");
        labelArray[5].setForeground(Color.blue);
        labelArray[5].setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //make the text field array
        textArray = new JTextField[]{new JTextField(),new JTextField()};
        
        //make text area
        inputText = new JTextArea();
	inputText.setWrapStyleWord(true);
	inputText.setLineWrap(true);
        scrollArea = new JScrollPane(inputText);
        
        //add components
        set.fill = GridBagConstraints.HORIZONTAL;
        set.insets = new Insets(10,10,0,10);  //top padding
        set.gridx = 0;
        set.gridy = 0;
        set.gridwidth = 10;
        messagePanel.add(labelArray[0],set);
        
        set.gridx = 0;
        set.gridy = 1;
        messagePanel.add(textArray[0],set);
        
        set.gridx = 0;
        set.gridy = 2;
        messagePanel.add(labelArray[1],set);
        
        set.gridx = 0;
        set.gridy = 3;
        messagePanel.add(textArray[1],set);
        
        set.gridx = 0;
        set.gridy = 4;
        messagePanel.add(labelArray[2],set);
        
        set.gridx = 0;
        set.gridy = 5;
        set.ipady = 1000; 
        set.weighty = 1.0;
        set.weightx = 1.0;
        set.anchor = GridBagConstraints.NORTH;
        messagePanel.add(scrollArea,set);
        
        
        //the buttons
        set.insets = new Insets(0,10,0,0); 
        set.ipady = 0;
        set.ipadx = 0;
        set.gridwidth = 1;
        //set.gridheight = 1;
        set.weighty = 0;
        set.weightx = 0;
        set.gridx = 1;
        set.gridy = 6;
        messagePanel.add(buttonArray[0],set); 
        set.gridx = 2;
        set.gridy = 6;
        messagePanel.add(buttonArray[1],set);
        
        set.gridx = 3;
        set.gridy = 6;
        messagePanel.add(labelArray[3],set);
        set.gridx = 4;
        set.gridy = 6;
        messagePanel.add(labelArray[5],set);
        
        set.gridx = 5;
        set.gridy = 6;
        messagePanel.add(labelArray[4],set);
        set.gridx = 6;
        set.gridy = 6;
        messagePanel.add(labelArray[6],set);
        set.gridx = 1;
        set.gridy = 7;
        messagePanel.add(labelArray[7],set);
                        
        //-----------------PASSWORD PANEL --------------
        
        //make the label array
        labelArrayPass = new JLabel[]{new JLabel("username:"),new JLabel("password:")};
        
        //make the text field array
        
        userField = new JTextField();
        passField = new JPasswordField();
        passField.addKeyListener(this);
       
        setPass.fill = GridBagConstraints.HORIZONTAL;
        
        //setPass.weightx = 0.5;
        setPass.insets = new Insets(2, 2, 2, 2);
        
        setPass.gridx = 0;
        setPass.gridy = 0;
        passwordPanel.add(labelArrayPass[0],setPass);
        setPass.gridx = 0;
        setPass.gridy = 1;
        passwordPanel.add(labelArrayPass[1],setPass);
        
        
        setPass.weightx = 1.0;
        setPass.gridx = 1;
        setPass.gridy = 0;
        //setPass.fill = GridBagConstraints.CENTER;
        //setPass.weightx = 0.5;
        passwordPanel.add(userField,setPass);
        setPass.gridx = 1;
        setPass.gridy = 1;
        passwordPanel.add(passField,setPass);
        
        
        submit = new JButton("accept");
        setPass.weightx = 0;
        setPass.gridx = 2;
        setPass.gridy = 1;
        passwordPanel.add(submit,setPass);
        
        //add button actions
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                submitAction(ae);
                
            }
        });
        
        
        
        
        //------------ADD TO CONTAINER-------------------
        //layeredPane.add(passwordPanel,new Integer(1),0);
       
        messagePanel.setBounds(0, 0, 1000, 500);
        passwordPanel.setBounds(350, 300, 350, 200);
        imagePanel.setBounds(0,0,1000,500);
        layeredPane.add(passwordPanel, new Integer(2),0);
        layeredPane.add(messagePanel,new Integer(0),0);
        layeredPane.add(imagePanel,new Integer(1),0);
        c.add(layeredPane);
        
        
        
        addKeyListener(this);
        //setMaximumSize(new Dimension(1000,500));
        setMinimumSize(new Dimension(1000,520));
        setResizable(false);
        setVisible(true);
    }
    
    //objects
    private JLabel[] labelArray;
    private JButton[] buttonArray;
    private JScrollPane scrollArea;
    private JTextArea inputText;
    private JTextField[] textArray;
    
    private JTextField userField;
    private JPasswordField passField;
         
    private JLabel[] labelArrayPass;
    private JLayeredPane layeredPane;
    private JPanel messagePanel, passwordPanel, imagePanel;
    private JButton submit;
    private SendMyMail mainMail;
    private int INFORMATION_MESSAGE;
    
    public static void main( String[] agrs){
        MailGui c = new MailGui();
        c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
}
