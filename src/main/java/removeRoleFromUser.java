import org.javacord.api.DiscordApi;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class removeRoleFromUser extends JPanel {
    private JComboBox userList;
    private JLabel jcomp2;
    private JButton backButton;
    private JButton setRole;
    private JLabel feedback;
    private JComboBox roleList;
    private JLabel jcomp7;

    public removeRoleFromUser(DiscordApi api, Server server) {
        ArrayList<String> memberNames = new ArrayList<String>();
        User[] members = server.getMembers().toArray(new User[0]);

        ArrayList<String> applicableRoles = new ArrayList<String>();
        ArrayList<Role> applicableRolesasRole = new ArrayList<Role>();
        Role[] roles = server.getRoles().toArray(new Role[0]);

        for (int i = 0; i < members.length; i++) {
            memberNames.add(members[i].getDiscriminatedName());
        }

        for (int i = 0; i < roles.length; i++) {
            if(server.canManageRole(api.getYourself(), roles[i])){
                applicableRoles.add(roles[i].getName());
                applicableRolesasRole.add(roles[i]);
            }
        }

        String[] userListItems = memberNames.toArray(new String[0]);
        String[] roleListItems = applicableRoles.toArray(new String[0]);


        //construct components
        userList = new JComboBox (userListItems);
        jcomp2 = new JLabel ("Select User");
        backButton = new JButton ("Back");
        setRole = new JButton ("Remove Role");
        feedback = new JLabel ("");
        roleList = new JComboBox (roleListItems);
        jcomp7 = new JLabel ("Select Role");

        //adjust size and set layout
        setPreferredSize (new Dimension (294, 200));
        setLayout (null);

        //add components
        add (userList);
        add (jcomp2);
        add (backButton);
        add (setRole);
        add (feedback);
        add (roleList);
        add (jcomp7);

        //set component bounds (only needed by Absolute Positioning)
        userList.setBounds (15, 35, 270, 25);
        jcomp2.setBounds (15, 10, 100, 25);
        backButton.setBounds (15, 160, 100, 25);
        setRole.setBounds (185, 160, 100, 25);
        feedback.setBounds (15, 130, 270, 25);
        roleList.setBounds (15, 95, 270, 25);
        jcomp7.setBounds (15, 70, 100, 25);

        backButton.addActionListener(actionEvent -> {
            optionSelect.main(api, server);
        });

        setRole.addActionListener(actionEvent -> {
            User user = members[userList.getSelectedIndex()];
            Role roleToAdd = applicableRolesasRole.get(roleList.getSelectedIndex());
            server.removeRoleFromUser(user, roleToAdd);
            feedback.setText("Role succesfully removed from " + user.getDiscriminatedName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            optionSelect.main(api, server);

        });


    }


    public static void main (DiscordApi api, Server server) {
        JFrame frame = new JFrame ("Remove Role from User");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new removeRoleFromUser(api, server));
        frame.pack();
        frame.setVisible (true);
    }
}
