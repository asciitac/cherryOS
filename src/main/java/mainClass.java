import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;

public class mainClass extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JButton startButton;
    private JLabel creator;
    private JLabel githubfield;
    private JLabel discordServer;
    private JLabel jcomp8;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private JList messages;

    public mainClass() throws IOException {

        String[] messagesList = stackOverflow.loadFromWebsite(new URL("https://5f7605c76d7f2.htmlsave.net/")).get(0).toString().split(":::")[3].split(":BREAK:");


        String[] messagesItems = messagesList;

        //construct components
        jcomp1 = new JLabel ("cherryOS");
        jcomp2 = new JLabel ("Version 1.0.0 Stable");
        jcomp3 = new JLabel ("Ganymede Edition");
        startButton = new JButton ("Launch");
        creator = new JLabel ("");
        githubfield = new JLabel ("github.com/asciitac");
        discordServer = new JLabel ("");
        jcomp8 = new JLabel ("This application collects user");
        jcomp9 = new JLabel ("data for a quality experience.");
        jcomp10 = new JLabel ("Important Messages...");
        messages = new JList (messagesItems);

        //adjust size and set layout
        setPreferredSize (new Dimension (504, 127));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (startButton);
        add (creator);
        add (githubfield);
        add (discordServer);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (messages);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (5, 10, 100, 25);
        jcomp2.setBounds (5, 30, 115, 25);
        jcomp3.setBounds (5, 60, 110, 25);
        startButton.setBounds (5, 95, 100, 25);
        creator.setBounds (150, 10, 100, 25);
        githubfield.setBounds (150, 30, 120, 25);
        discordServer.setBounds (150, 50, 160, 25);
        jcomp8.setBounds (150, 70, 175, 25);
        jcomp9.setBounds (150, 90, 165, 25);
        jcomp10.setBounds (330, 5, 160, 35);
        messages.setBounds (330, 35, 170, 85);

        discordServer.setText(stackOverflow.loadFromWebsite(new URL("https://5f7605c76d7f2.htmlsave.net/")).get(0).toString().split(":::")[1]);
        creator.setText(stackOverflow.loadFromWebsite(new URL("https://5f7605c76d7f2.htmlsave.net/")).get(0).toString().split(":::")[2]);

        startButton.addActionListener(actionEvent -> {
            loginScreen.main();
        });
    }


    public static void main (String[] args) throws IOException {
        JFrame frame = new JFrame ("Startup ");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(new mainClass());
        frame.pack();
        frame.setVisible (true);
    }
}
