import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class stackOverflow {
    public static ArrayList loadFromWebsite(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line = null;
        ArrayList<String> data = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        return data;
    }
}
