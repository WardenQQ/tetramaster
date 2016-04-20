package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by ydevroux on 25/03/2016.
 */
public class GeoConnect {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://tetramaster.u-strasbg.fr/create_event.php";
    private static final String GET_URL ="http://tetramaster.u-strasbg.fr/get_event.php";
    private static JSONObject jsonInsert = null;
    private static JSONObject jsonGet = null;
    public static void getMap() throws  IOException, JSONException{
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        //int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        jsonGet = new JSONObject();
        //String test = con.getContentEncoding();
        jsonGet.put("lol","lol");

    }
    public static void sendPOST(String nom, String description,PolygonOptions polygon) throws IOException, JSONException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        jsonInsert = new JSONObject();
        jsonInsert.put("nameEvent",nom);
        jsonInsert.put("descEvent", description);
        List<LatLng> points = polygon.getPoints();
        String pointLat = "";
        String pointLong = "";
        for (int i =0;i<points.size();i++){
            pointLat += String.valueOf(points.get(i).latitude)+"|";
            pointLong += String.valueOf(points.get(i).longitude)+"|";
        }
        jsonInsert.put("pointLat",pointLat);
        jsonInsert.put("pointLong",pointLong);

        con.connect();
        OutputStream os = con.getOutputStream();
        OutputStreamWriter wr = new OutputStreamWriter(os);
        String jsontext = jsonInsert.toString();
        wr.write(jsontext);
        wr.flush();
        wr.close();
        os.close();
        // For POST only - EN
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

}
