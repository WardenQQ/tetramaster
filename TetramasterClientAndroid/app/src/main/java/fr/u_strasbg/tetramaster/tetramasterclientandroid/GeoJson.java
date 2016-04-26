package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydevroux on 21/04/2016.
 * Fonctions relative au Json
 */
public class GeoJson {
    public JSONObject setJsonEvent(String nom, String description,PolygonOptions polygon)
    {
        JSONObject json_event = new JSONObject();
        return json_event;
    }
    public List<PolygonOptions> getJsonEvent(){
        List<PolygonOptions> listEvent = new ArrayList<PolygonOptions>();
        return listEvent;
    }
}
