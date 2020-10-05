import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.event.*;

public class sendMessageInChannel extends JPanel {
    private JLabel jcomp1;
    private JComboBox textChannelList;
    private JTextArea messageField;
    private JLabel jcomp4;
    private JLabel feedback;
    private JButton backButton;
    private JButton sendButton;

    public sendMessageInChannel(DiscordApi api, Server server) {
        ArrayList<String> channels = new ArrayList<String>();
        for (int i = 0; i < server.getTextChannels().size(); i++) {
            channels.add(server.getTextChannels().get(i).getName());
        }
        String[] textChannelListItems = channels.toArray(new String[0]);

        //construct components
        jcomp1 = new JLabel ("Select Text Channel");
        textChannelList = new JComboBox (textChannelListItems);
        messageField = new JTextArea (5, 5);
        jcomp4 = new JLabel ("Message:");
        feedback = new JLabel ("");
        backButton = new JButton ("Back");
        sendButton = new JButton ("Send");

        //adjust size and set layout
        setPreferredSize (new Dimension (265, 321));
        setLayout (null);

        //add components
        add (jcomp1);
        add (textChannelList);
        add (messageField);
        add (jcomp4);
        add (feedback);
        add (backButton);
        add (sendButton);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (5, 10, 150, 25);
        textChannelList.setBounds (5, 35, 255, 25);
        messageField.setBounds (5, 170, 255, 75);
        jcomp4.setBounds (5, 145, 100, 25);
        feedback.setBounds (5, 245, 255, 25);
        backButton.setBounds (5, 275, 100, 25);
        sendButton.setBounds (160, 275, 100, 25);

        backButton.addActionListener(actionEvent -> {
            optionSelect.main(api, server);
        });

        sendButton.addActionListener(actionEvent -> {
            server.getTextChannels().get(textChannelList.getSelectedIndex()).sendMessage(messageField.getText());
            feedback.setText("Message succesfully sent!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            optionSelect.main(api, server);
        });
    }


    public static void main (DiscordApi api, Server server) {
        JFrame frame = new JFrame ("Send Message in Channel");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new sendMessageInChannel(api, server));
        frame.pack();
        frame.setVisible (true);
    }
}
