import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class kickUser extends JPanel {
    private JComboBox userList;
    private JLabel jcomp2;
    private JTextArea jcomp3;
    private JButton backButton;
    private JButton banButton;
    private JLabel reasonField;
    private JLabel feedback;

    public kickUser(DiscordApi api, Server server) throws InterruptedException {
        //construct preComponents
        ArrayList<String> applicableUsers = new ArrayList<String>();
        ArrayList<User> applicableUsersasUsers = new ArrayList<User>();
        User[] members = server.getMembers().toArray(new User[0]);
        for (int i = 0; i < members.length; i++) {
            if(server.canBanUser(api.getYourself(), members[i])){
                applicableUsers.add(members[i].getDiscriminatedName());
                applicableUsersasUsers.add(members[i]);
            }
        }
        String[] userListItems = applicableUsers.toArray(new String[0]);

        //construct components
        userList = new JComboBox (userListItems);
        jcomp2 = new JLabel ("Select User");
        jcomp3 = new JTextArea (2, 2);
        backButton = new JButton ("Back");
        banButton = new JButton ("Kick");
        reasonField = new JLabel ("Reason:");
        feedback = new JLabel ("");

        //adjust size and set layout
        setPreferredSize (new Dimension (294, 360));
        setLayout (null);

        //add components
        add (userList);
        add (jcomp2);
        add (jcomp3);
        add (backButton);
        add (banButton);
        add (reasonField);
        add (feedback);

        //set component bounds (only needed by Absolute Positioning)
        userList.setBounds (15, 35, 270, 25);
        jcomp2.setBounds (15, 10, 100, 25);
        jcomp3.setBounds (15, 150, 270, 110);
        backButton.setBounds (10, 330, 100, 25);
        banButton.setBounds (185, 330, 100, 25);
        reasonField.setBounds (15, 125, 100, 25);
        feedback.setBounds (15, 275, 270, 25);

        backButton.addActionListener(actionEvent -> {
            optionSelect.main(api, server);
        });

        banButton.addActionListener(actionEvent -> {
            User user = applicableUsersasUsers.get(userList.getSelectedIndex());
            server.kickUser(user, reasonField.getText());
            feedback.setText(user.getDiscriminatedName() + " kicked successfully!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            optionSelect.main(api, server);
        });
    }


    public static void main (DiscordApi api, Server server) throws InterruptedException {
        JFrame frame = new JFrame ("Kick User");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new kickUser(api, server));
        frame.pack();
        frame.setVisible (true);
    }
}
