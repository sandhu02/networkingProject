package gui;

import networking.*;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientServerPage extends JFrame {
    JLabel questionlabel;
    JButton clientButton;
    JButton serverButton;
    JButton exitButton;

    public ClientServerPage () {
        setTitle("Client Server Page");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(4,1));

        questionlabel = new JLabel("Who are you acting as?");
        clientButton = new JButton("Client");
        serverButton = new JButton("Server");
        exitButton = new JButton("Exit");

        MyActionListener listener = new MyActionListener();

        clientButton.addActionListener(listener);
        serverButton.addActionListener(listener);
        exitButton.addActionListener(listener);


        add(questionlabel);
        add(clientButton);
        add(serverButton);
        add(exitButton);
    }

    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (ae.getActionCommand().equals("Client")){
                dispose();
                Client client = new Client("192.168.0.124", 6666);

                State.setClient(client);
                ChatPage cp = new ChatPage();
            }
            else if (ae.getActionCommand().equals("Server")){
                dispose();
                Server server = new Server(6666);

                State.setServer(server);
                ChatPage cp = new ChatPage();
            }
            else if (ae.getActionCommand().equals("Exit")){
                System.exit(0);
            }
        }
    }        

}
