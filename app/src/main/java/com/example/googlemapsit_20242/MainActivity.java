package com.example.googlemapsit_20242;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity
        extends FragmentActivity
        implements OnMapReadyCallback {
    GoogleMap Mapa;

    int contador;

    ArrayList<LatLng> puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        contador = 0;
        puntos = new ArrayList<LatLng>();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Mapa = googleMap;

        Mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        Mapa.getUiSettings().setZoomControlsEnabled(true);

        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0124126681187626, -79.46950741447908), 20);

        Mapa.moveCamera(camUpd1);


        Mapa.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null; //
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info, null);
                ImageView imageView = view.findViewById(R.id.fcauteq);
                TextView titleTextView = view.findViewById(R.id.facult);
                TextView descriptionTextView = view.findViewById(R.id.detalle);

                switch (marker.getTitle()) {
                    case "Facultad de Ciencias de la Ingeniería":
                        imageView.setImageResource(R.drawable.fingenieria);
                        titleTextView.setText("Facultad de Ciencias de la Ingeniería");
                        descriptionTextView.setText(Html.fromHtml("Ubicada frente a la FCE"));

                        break;
                    case "Facultad de Ciencias de la Enfermeria":
                        imageView.setImageResource(R.drawable.efermeria);
                        titleTextView.setText("Facultad de Ciencias de la Enfermeria");
                        descriptionTextView.setText(Html.fromHtml("Ubicada frente a laboratorio de FCI"));
                        break;

                    case "Centro Medico":
                        imageView.setImageResource(R.drawable.centromedico);
                        titleTextView.setText("Centro Medico");
                        descriptionTextView.setText(Html.fromHtml("Ubicada  a lado de la biblioteca y frente al Rectorado"));


                        break;
                    case "Biblioteca":
                        imageView.setImageResource(R.drawable.biblioteca);
                        titleTextView.setText("Biblioteca");
                        descriptionTextView.setText(Html.fromHtml("Ubicada a lado del parqueadero"));
                        break;
                }
                return view;
            }
        });
        //FCI
        LatLng punto1 = new LatLng(-1.0125271589737213, -79.47050566896054);
        Mapa.addMarker(new MarkerOptions()
                .position(punto1)
                .title("Facultad de Ciencias de la Ingeniería"));

        //Enfermeria
        LatLng punto2= new LatLng(-1.0128436101960268, -79.46933086142937);
        Mapa.addMarker(new MarkerOptions()
                .position(punto2)
                .title("Facultad de Ciencias de la Enfermeria"));

        //Centro Medico
        LatLng punto3 = new LatLng(-1.012291161431891, -79.46931476817552);
        Mapa.addMarker(new MarkerOptions()
                .position(punto3)
                .title("Centro Medico"));

        //Bibliotéca)
        LatLng punto4 = new LatLng(-1.0124145237854407, -79.46838672387008);
        Mapa.addMarker(new MarkerOptions()
                .position(punto4)
                .title("Biblioteca"));
    }

}

   /* @Override
    public void onMapClick(@NonNull LatLng latLng) {

        TextView lblLat = findViewById(R.id.lblLatitud);
        lblLat.setText(String.format("%.4f", latLng.latitude));

        TextView lblLong = findViewById(R.id.lblLongitud);
        lblLong.setText(String.format("%.4f", latLng.longitude));

        Mapa.addMarker(new MarkerOptions().position(latLng)
                .title("Marcador"));
        contador = contador + 1;
        puntos.add(latLng);
        if (contador == 4) {
            PolylineOptions lineas = new PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));

            lineas.width(8);
            lineas.color(Color.RED);
            Mapa.addPolyline(lineas);
            contador = 0;
            puntos.clear();
        }
    }

    */
