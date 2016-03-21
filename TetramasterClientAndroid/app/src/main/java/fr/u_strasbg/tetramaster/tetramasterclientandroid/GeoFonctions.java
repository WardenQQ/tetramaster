package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.location.Location;

/**
 * Created by ydevroux on 15/03/2016.
 */
public class GeoFonctions {
    public static String locationStringFromLocation(final Location location) {
        return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + " " + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
    }
}
