package fr.u_strasbg.tetramaster.tetramasterclientandroid.Geolocalisation;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.BreakIterator;

/**
 * Created by ydevroux on 21/04/2016.
 */
public class GeoSetEvent extends AsyncTask<JSONObject, Void, String> {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://tetramaster.u-strasbg.fr/create_event.php";
    private Geolocalisation g_map;
    private ProgressDialog progress;
    public GeoSetEvent(Geolocalisation g_map) {
        this.g_map = g_map;
    }

    public void setEvent(JSONObject json_event) {
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
            int responseCode = con.getResponseCode();

            final StringBuilder output = new StringBuilder("Request URL " + POST_URL);
            output.append(System.getProperty("line.separator") + "Request Parameters " + json_event.toString());
            output.append(System.getProperty("line.separator")  + "Response Code " + responseCode);
            output.append(System.getProperty("line.separator")  + "Type " + "POST");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
            System.out.println("output===============" + br);
            while((line = br.readLine()) != null ) {
                responseOutput.append(line);
            }
            br.close();

            output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

            g_map.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    progress.dismiss();
                }
            });


            //wr.flush();
            //wr.close();
            //os.close();
            //con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    protected void onPreExecute(){
        progress= new ProgressDialog(g_map);
        progress.setMessage("Loading");
        progress.show();
    }

    @Override
    protected String doInBackground(JSONObject... params) {
        JSONObject lol = params[0];
        setEvent(lol);

        return "";
    }
}
