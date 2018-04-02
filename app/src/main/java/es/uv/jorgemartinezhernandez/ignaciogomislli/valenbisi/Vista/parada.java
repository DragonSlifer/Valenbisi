package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.DatabaseConnector;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.GeneralMethods;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Parada_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Partes_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class parada extends AppCompatActivity {

    private Parada_class p;
    private ListView l;

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
                i.putExtra(Constants.DATA_RECOVER,Constants.NO_DATA);           ///< Parte nuevo
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

        actualiza_Partes();

    }

    private void actualiza_Partes() {
        DatabaseConnector db = new DatabaseConnector(this);
        long id = p.getNumber();
        final ArrayList<Partes_class> partes = db.ObtenerComunicadoPorID(id); ///< Obtenemos todos los comunicados por ID de parada
        AdapterPartes adapterPartes = new AdapterPartes(partes, this);
        l = findViewById(R.id.listView);
        l.setAdapter(adapterPartes);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), partes.class);
                i.putExtra(Constants.DATA_RECOVER,Constants.DATA);           ///< Parte existente
                i.putExtra(Constants.CLASS_PARTES,partes.get(position));
                startActivity(i);
            }
        });
    }

    private class AdapterPartes extends BaseAdapter{

        private ArrayList<Partes_class> items;
        private Context context;
        public AdapterPartes (ArrayList<Partes_class> items, Context context){
            this.items = items;
            this.context = context;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            if(items.size() > position)
                return items.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            if(items.size() > position)
                return items.get(position).getParadaID();
            return -1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null){
                LayoutInflater.from(context).inflate(R.layout.custom_parte_row, null, false);
            }

            ((TextView)view.findViewById(R.id.asunto_parte)).setText(items.get(position).getNombre());
            ((TextView)view.findViewById(R.id.tipo_parte)).setText(items.get(position).getTipoString());

            //Log.d(Constants.CLASS_PARTES, "Imprimiendo parte en posición: " + position);

            return view;
        }
    }

}
