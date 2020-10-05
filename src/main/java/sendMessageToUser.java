import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class sendMessageToUser extends JPanel {
    private JLabel jcomp1;
    private JComboBox memberList;
    private JTextArea messageField;
    private JLabel jcomp4;
    private JLabel feedback;
    private JButton backButton;
    private JButton sendButton;

    public sendMessageToUser(DiscordApi api, Server server) {
        ArrayList<String> users = new ArrayList<String>();
        User[] members = server.getMembers().toArray(new User[0]);
        for (int i = 0; i < members.length; i++) {
            users.add(members[i].getDiscriminatedName());
        }
        String[] memberListItems = users.toArray(new String[0]);

        //construct components
        jcomp1 = new JLabel ("Select User");
        memberList = new JComboBox (memberListItems);
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
        add (memberList);
        add (messageField);
        add (jcomp4);
        add (feedback);
        add (backButton);
        add (sendButton);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (5, 10, 150, 25);
        memberList.setBounds (5, 35, 255, 25);
        messageField.setBounds (5, 170, 255, 75);
        jcomp4.setBounds (5, 145, 100, 25);
        feedback.setBounds (5, 245, 255, 25);
        backButton.setBounds (5, 275, 100, 25);
        sendButton.setBounds (160, 275, 100, 25);

        backButton.addActionListener(actionEvent -> {
            optionSelect.main(api, server);
        });

        sendButton.addActionListener(actionEvent -> {
            members[memberList.getSelectedIndex()].sendMessage(messageField.getText());
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
        frame.getContentPane().add (new sendMessageToUser(api, server));
        frame.pack();
        frame.setVisible (true);
    }
}
