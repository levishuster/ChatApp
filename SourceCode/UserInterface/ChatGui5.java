package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class ChatGui5 extends JFrame implements GuiClient
{
    // GUI items
    private JLabel userLabel = new JLabel("Connected Users");
    private JButton sendButton;
    private JTextField message;
    private JTextArea history;
    private JPanel usersPanel;
    private JPanel buttonsPanel;
    private JPanel chatPanel;
    private JPanel bottomPanel;
    private String username;
    private JCheckBox allButton;
    private Vector<JCheckBox> userCheckButtonsList;
    private Vector<Boolean> chattingTo;
    
    // set up GUI
    public ChatGui5(String username) {
        super("Echo Client");
        
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.username = username;
        
        // The users panel will contain a list of users to the left side
        usersPanel = new JPanel();
        usersPanel.setLayout(new BorderLayout());
        
        userCheckButtonsList = new Vector<JCheckBox>();
        
        addUser("Nigel");
        addUser("Levi");
        addUser("Mike");
        
        allButton = new JCheckBox("all");
        allButton.addActionListener(ae ->{
            for(JCheckBox box : userCheckButtonsList)
                if(box.isSelected()) 
                    box.setEnabled(true);
                else 
                    box.setEnabled(false);
        });
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        for(JCheckBox user : userCheckButtonsList)
            buttonsPanel.add(user);
        
        buttonsPanel.add(allButton);
        
        usersPanel.add(userLabel, "North");
        usersPanel.add(buttonsPanel, "Center");
        
        // The message field and text area will be enclosed in the chat panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        
        history = new JTextArea();
        history.setEditable(false);
        
        history.setFont(new Font("Arial", Font.PLAIN, 16));
        chatPanel.add(history, "Center");
        
        // Create all of the members of the bottom panel: the message area and send
        // button
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        message = new JTextField(30);
        message.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Make "Enter" the default key to send text to the chat
        message.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !message.getText().equals(""))
                    addMessage(message.getText());
            }
        });
        
        sendButton = new JButton("Send");
        sendButton.setEnabled(true);
        
        // We will set the "default button" to the send button, so whenever the user presses
        // enter, the message that is stored in the message field will be sent
        getRootPane().setDefaultButton(sendButton);
              
        bottomPanel.add(message);
        bottomPanel.add(sendButton);        
        
        chatPanel.add(bottomPanel, "South");

        // Now add all of the panels into the whole frame
        add(usersPanel, "West");
        add(chatPanel, "Center");
        
        
        setVisible(true);
    } // end CountDown constructor

    public void addUser(String username)
    {
        JCheckBox newUser = new JCheckBox(username);
        newUser.setSelected(true);
        userCheckButtonsList.add(newUser);
        
    }
    
    public void addMessage(String message)
    {
        history.append("<" + username  + " to: ");
        
        for(JCheckBox box : userCheckButtonsList)
        {
            if(box.isSelected())
                history.append(box.getText() + " ");
        }
        
        history.append(" > " + message + "\n");
        
        this.message.setText("");
    }
 
    public static void main(String args[]) {
		String username = JOptionPane.showInputDialog(null, "Please enter your user name: ");
		ChatGui5 application = new ChatGui5(username);
    }
}
