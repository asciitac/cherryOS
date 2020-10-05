import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class optionSelect extends JPanel {
    private JLabel serverName;
    private JLabel serverOwnerName;
    private JLabel memberCount;
    private JButton backButton;
    private JButton banUser;
    private JButton kickUser;
    private JButton addRoleToUser;
    private JButton removeRoleFromUser;
    private JButton sendMessageToChannel;
    private JButton sendMessageToUser;

    public optionSelect(DiscordApi api, Server server) {
        //construct components
        serverName = new JLabel ("Server Name: ");
        serverOwnerName = new JLabel ("Server Owner:");
        memberCount = new JLabel ("Member Count:");
        backButton = new JButton ("Back");
        banUser = new JButton ("Ban User");
        kickUser = new JButton ("Kick User");
        addRoleToUser = new JButton ("Add Role to User");
        removeRoleFromUser = new JButton ("Remove Role from User");
        sendMessageToChannel = new JButton ("Send Message to Channel");
        sendMessageToUser = new JButton ("Send Message to User");

        //adjust size and set layout
        setPreferredSize (new Dimension (240, 285));
        setLayout (null);

        //add components
        add (serverName);
        add (serverOwnerName);
        add (memberCount);
        add (backButton);
        add (banUser);
        add (kickUser);
        add (addRoleToUser);
        add (removeRoleFromUser);
        add (sendMessageToChannel);
        add (sendMessageToUser);

        //set component bounds (only needed by Absolute Positioning)
        serverName.setBounds (5, 5, 230, 25);
        serverOwnerName.setBounds (5, 25, 230, 25);
        memberCount.setBounds (5, 45, 230, 25);
        backButton.setBounds (5, 250, 100, 25);
        banUser.setBounds (5, 70, 100, 25);
        kickUser.setBounds (5, 100, 100, 25);
        addRoleToUser.setBounds (5, 130, 130, 25);
        removeRoleFromUser.setBounds (5, 160, 185, 25);
        sendMessageToChannel.setBounds (5, 190, 185, 25);
        sendMessageToUser.setBounds (5, 220, 210, 25);
    }


    public static void main (DiscordApi api, Server server) {
        JFrame frame = new JFrame ("optionSelect");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new optionSelect(api, server));
        frame.pack();
        frame.setVisible (true);
    }
}
