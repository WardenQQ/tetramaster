package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ydevroux on 21/04/2016.
 */
public class GeoSetEvent {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://tetramaster.u-strasbg.fr/create_event.php";
    public static void setEvent(JSONObject json_event) {
        URL obj;
        try {
            //Ouverture de connexion
            obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setDoOutput(true);
            con.connect();
            //écriture dans unn Buffer
            OutputStream os = con.getOutputStream();
            OutputStreamWriter wr = new OutputStreamWriter(os);
            String jsontext = json_event.toString();
            wr.write(jsontext);
            wr.flush();
            wr.close();
            os.close();
            con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
