import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class serverSelectorScreen extends JPanel {
    private JLabel botName;
    private JComboBox serverDropdown;
    private JLabel jcomp3;
    private JButton okayButton;

    public serverSelectorScreen(DiscordApi api) {
        Server[] servers = api.getServers().toArray(new Server[0]);
        ArrayList<String> applicableServers = new ArrayList<String>();
        ArrayList<Server> applicableasServers = new ArrayList<Server>();
        for (int i = 0; i < api.getServers().size(); i++) {
            if(servers[i].isAdmin(api.getYourself())){
                applicableServers.add(servers[i].getName());
                applicableasServers.add(servers[i]);
            }
        }
        String[] serverNames = applicableServers.toArray(new String[0]);

        //construct components
        botName = new JLabel ("Logged in as " + api.getYourself().getDiscriminatedName());
        serverDropdown = new JComboBox (serverNames);
        jcomp3 = new JLabel ("Select Server");
        okayButton = new JButton ("OK");

        //adjust size and set layout
        setPreferredSize (new Dimension (220, 171));
        setLayout (null);

        //add components
        add (botName);
        add (serverDropdown);
        add (jcomp3);
        add (okayButton);

        //set component bounds (only needed by Absolute Positioning)
        botName.setBounds (5, 0, 210, 30);
        serverDropdown.setBounds (5, 50, 210, 25);
        jcomp3.setBounds (5, 25, 100, 25);
        okayButton.setBounds (160, 140, 55, 25);
         okayButton.addActionListener(actionEvent -> {
             Server server = applicableasServers.get(serverDropdown.getSelectedIndex());
             optionSelect.main(api, server);
         });
    }



    public static void main (DiscordApi api) {
        JFrame frame = new JFrame ("Select a Server");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new serverSelectorScreen(api));
        frame.pack();
        frame.setVisible (true);
    }
}
