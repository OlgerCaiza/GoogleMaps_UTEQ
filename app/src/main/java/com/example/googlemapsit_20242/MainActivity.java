package com.example.googlemapsit_20242;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity
        extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    GoogleMap mapa;
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
        mapa = googleMap;

        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);

        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0124126681187626, -79.46950741447908), 17);

        mapa.moveCamera(camUpd1);
        mapa.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        TextView lblLat = findViewById(R.id.lblLatitud);
        lblLat.setText(String.format("%.4f", latLng.latitude));

        TextView lblLong = findViewById(R.id.lblLongitud);
        lblLong.setText(String.format("%.4f", latLng.longitude));

        // Agregar un marcador personalizado
        agregarMarcadorPersonalizado(latLng, "Marcador " + contador, BitmapDescriptorFactory.HUE_BLUE);

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
            mapa.addPolyline(lineas);
            contador = 0;
            puntos.clear();
        }
    }

    public void PintarRectUTEQ(View view) {
        // Agregar marcadores personalizados
        agregarMarcadorPersonalizado(new LatLng(-1.012440745584145, -79.47061135682758), "Facultad de Ciencias de la Ingeniería ", BitmapDescriptorFactory.HUE_RED );
        agregarMarcadorPersonalizado(new LatLng(-1.0130882477722807, -79.4705275084588), "Facultad de Ciencias Empresariales UTEQ", BitmapDescriptorFactory.HUE_RED);
        agregarMarcadorPersonalizado(new LatLng(-1.013189334949394, -79.47050337193856), "Club Economía UTEQ | QUEVEDO | LOS RIOS | ECUADOR", BitmapDescriptorFactory.HUE_YELLOW);
        agregarMarcadorPersonalizado(new LatLng(-1.0121890702011316, -79.4696299782475), "Instituto de Infomática\n", BitmapDescriptorFactory.HUE_CYAN);
        agregarMarcadorPersonalizado(new LatLng(-1.0118255068382742, -79.47107931224551), "Cyber Universitario\n", BitmapDescriptorFactory.HUE_CYAN);
        agregarMarcadorPersonalizado(new LatLng(-1.0127262531896748, -79.46985569479489), "Parqueadero UTEQ\n", BitmapDescriptorFactory.HUE_CYAN);


        // Dibujar la línea
        PolylineOptions lineas = new PolylineOptions()
                .add(new LatLng(-1.0119593066306347, -79.47154808373672))
                .add(new LatLng(-1.012855024574039, -79.47163391442182))
                .add(new LatLng(-1.0130749313366685, -79.46731019365936))
                .add(new LatLng(-1.012329393715087, -79.46727800715244))
                .add(new LatLng(-1.0119593066306347, -79.47154808373672));

        lineas.width(8);
        lineas.color(Color.GREEN);

        mapa.addPolyline(lineas);
    }

    private void agregarMarcadorPersonalizado(LatLng latLng, String titulo, float colorMarcador) {
        mapa.addMarker(new MarkerOptions()
                .position(latLng)
                .title(titulo)
                .icon(BitmapDescriptorFactory.defaultMarker(colorMarcador)));
    }
}
