package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.GridLayout;

public class ChatPage extends JFrame {
    JButton sendButton;
    JButton exitButton;
    
    JLabel incomingchats;
    JPanel incomingchatsPage;
    JLabel outgoingchats;
    JPanel outgoingchatsPage;

    JLabel sendMessage;
    JTextField messageField;

    public ChatPage () {
        setTitle("Chat Page");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(3,2));

        sendButton = new JButton("Send");
        exitButton = new JButton("Exit");
        sendMessage = new JLabel("Enter message to send ");
        messageField = new JTextField(1000);

        String Incomingmessage = "";
        if (State.onServer == false){
            State.client.getMessage();
            Incomingmessage = State.client.inMessage;
        }
        else if (State.onServer == true) {
            State.server.listenforMessage();
            Incomingmessage = State.server.inMessage;
        }

        incomingchats = new JLabel(Incomingmessage);    
        incomingchatsPage = new JPanel();
        incomingchatsPage.setBackground(new java.awt.Color(100,100,100));
        incomingchatsPage.setBounds(0, 0, 200,400);

        outgoingchats = new JLabel("this is a outgoing chat");
        outgoingchatsPage = new JPanel();
        outgoingchatsPage.setBackground(new java.awt.Color(100,100,100));
        outgoingchatsPage.setBounds(200, 0, 200, 400);

        MyActionListener listener = new MyActionListener();
        sendButton.addActionListener(listener);
        exitButton.addActionListener(listener);
        messageField.addActionListener(listener);
        
        incomingchatsPage.add(incomingchats);
        outgoingchatsPage.add(outgoingchats);
        add(incomingchatsPage);
        add(outgoingchatsPage);
        add(sendMessage);
        add(messageField);
        add(exitButton);
        add(sendButton);
    }

    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (ae.getActionCommand().equals("Send")){
                if (State.onServer == false){
                    State.client.sendMessage(messageField.getText());
                }
                else if (State.onServer == true) {
                    State.server.sendMessage(messageField.getText());
                }
            }
            else if (ae.getActionCommand().equals("Exit")){
                System.exit(0);
            }
        }
    }
}
