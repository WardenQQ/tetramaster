package fr.u_strasbg.tetramaster.tetramasterclientandroid.Geolocalisation;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ydevroux on 20/04/2016.
 */
public class GeoGetEvent extends AsyncTask<Void, Void, JSONArray> {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL ="http://tetramaster.u-strasbg.fr/get_event.php";
    private Geolocalisation geo_main;
    public GeoGetEvent(Geolocalisation geo_main){
        this.geo_main = geo_main;
    }
    public JSONArray getEvent(){
        URL obj;
        JSONArray json_event =null;
        try {
            obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int connection = con.getResponseCode();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line = in.readLine())!=null){
                    sb.append(line);
                }

                try {
                    json_event = new JSONArray(sb.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json_event;
    }
    @Override
    protected JSONArray doInBackground(Void... params) {
        return getEvent();
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        geo_main.afficheMap(result);
    }
}
