package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.kml.KmlLayer;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Geolocalisation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn_create;
    private Button btn_valide;
    private Button btn_delete;
    private boolean b_create_mod = false;
    private List<LatLng> list_point;
    private List<PolygonOptions> list_polygon;
    private List<Marker> list_marker;
    private Activity context;
    private Marker marker;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (Activity) this;

        setContentView(R.layout.activity_geolocalisation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Bouton afin de crée les polygones

        btn_create = (Button) findViewById(R.id.btn_create_area);
        btn_valide = (Button) findViewById(R.id.btn_add_poly);
        btn_valide.setEnabled(false);
        btn_delete = (Button) findViewById(R.id.btn_del);
        btn_delete.setEnabled(false);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (b_create_mod == true) {
                    b_create_mod = false;
                    btn_valide.setEnabled(false);
                    btn_delete.setEnabled(false);
                    btn_create.setText("Mode Création : Off");
                    for (int i = 0; i < list_marker.size(); i++) {
                        list_marker.get(i).remove();
                    }
                    list_marker.clear();
                    list_point.clear();
                    //btn_create.setBackgroundColor(Color.RED);
                } else {
                    b_create_mod = true;
                    btn_valide.setEnabled(true);
                    btn_delete.setEnabled(true);
                    btn_create.setText("Mode Création : On");
                    //btn_create.setBackgroundColor(Color.GREEN);
                }
            }
        });
        btn_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list_point.size() >= 3) {
                /*    for (int j=0;j<list_marker.size();j++){
                        list_marker.get(j);
                    }*/
                    final PolygonOptions polygon = new PolygonOptions().addAll(list_point);
                    polygon.strokeColor(Color.RED);
                    //polygon.fillColor(Color.GREEN);
                    polygon.fillColor(0x5500ff00);

                    polygon.strokeWidth(2);
                    //


                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.activity_insert, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setView(promptsView);

                    builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // on crée  le polygon
                            list_polygon.add(polygon);
                            for (int i = 0; i < list_marker.size(); i++) {
                                list_marker.get(i).remove();
                            }
                            for (int i = 0; i < list_polygon.size(); i++) {
                                mMap.addPolygon(list_polygon.get(i));

                            }
                            list_point.clear();
                        }
                    })
                            .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Annulation on supprime le triangle en cours
                                    for (int i = 0; i < list_marker.size(); i++) {
                                        list_marker.get(i).remove();
                                    }
                                    list_point.clear();
                                }
                            });
                    builder.create();
                    builder.show();


                    //


                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list_point.size() > 0) {
                    list_marker.get(list_marker.size() - 1).remove();
                    list_marker.remove(list_marker.size() - 1);
                    list_point.remove(list_point.size() - 1);
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        list_point = new ArrayList<LatLng>();
        list_polygon = new ArrayList<PolygonOptions>();
        list_marker = new ArrayList<Marker>();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (b_create_mod == true) {
                    list_point.add(latLng);
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(latLng));
                    list_marker.add(marker);
                }
            }
        });

    }

}
