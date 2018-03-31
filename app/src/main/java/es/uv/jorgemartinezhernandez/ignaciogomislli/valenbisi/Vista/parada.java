package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.GeneralMethods;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Parada_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class parada extends AppCompatActivity {

    private Parada_class p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), partes.class);
                i.putExtra(Constants.DATA_RECOVER,Constants.NO_DATA);
                startActivity(i);
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri;
                String[] ur = {p.getCoords(),p.getName()};
                gmmIntentUri = Uri.parse(GeneralMethods.Replace(Constants.geolocation_uri,ur,Constants.regex));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapIntent);
                }
            }
        });

        p = getIntent().getParcelableExtra(Constants.CLASS_PARADA);

        ((TextView)findViewById(R.id.datosParada)).setText(getString(R.string.datos) + " " + p.getName());
        ((TextView)findViewById(R.id.numParada)).setText(Long.toString(p.getNumber()));
        ((TextView)findViewById(R.id.dirParada)).setText(p.getAddress());
        ((TextView)findViewById(R.id.totParada)).setText(Integer.toString(p.getTotal()));
        ((TextView)findViewById(R.id.dispParada)).setText(Integer.toString(p.getDispon()));
        ((TextView)findViewById(R.id.libParada)).setText(Integer.toString(p.getLibres()));
        ((TextView)findViewById(R.id.coordParada)).setText("Lat: " + p.getLat() + " / Lon: " + p.getLon());

    }

}
