package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Parada;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class ListaParadas extends AppCompatActivity {

    private ListView l;
    private ArrayList<Parada> paradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paradas);
        Init();
        Collections.sort(paradas,new Parada.ParadaComparator());
        l = findViewById(R.id.list);
        AdapterParadas adapterParadas = new AdapterParadas(this, paradas.toArray());
        l.setAdapter(adapterParadas);
        adapterParadas.notifyDataSetChanged();
        Context ctx = getApplicationContext();
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = l.getSelectedItemPosition();
                goToParadaInfo(i);
            }
        });
    }

    public void goToParadaInfo(int pos){
        Intent i = new Intent(this,parada.class);

        i.putExtra(Constants.CLASS_PARADA, paradas.get(pos));

        startActivity(i);
    }

    public void Init() {
        paradas = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.valenbisi);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;

            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String s = writer.toString();

            System.out.println(s);
            JSONObject json = new JSONObject(s);
            JSONArray jsonArray = (JSONArray) json.get(Constants.JSON_Parada_Lista); //<-Esta interpretando S como un JSON OBJECT
            Parada p = new Parada();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonChild = (JSONObject) jsonObject.get(Constants.JSON_Parada_Datos);
                p.setNumber(Long.parseLong((jsonChild.getString(Constants.JSON_Parada_Number))));
                p.setAddress(jsonChild.getString(Constants.JSON_Parada_Addres));
                p.setPartes(Integer.parseInt(jsonChild.getString(Constants.JSON_Parada_Availa)));
                paradas.add(p);
                //System.out.println("Parada " + p.toSring());
                p = new Parada();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class AdapterParadas extends BaseAdapter {
        private Context context;
        private Parada[] items;

        class ViewHolder {
            TextView number;
            TextView address;
            TextView partes;
        }

        public AdapterParadas(Context c, Object[] i) {
            this.context = c;
            this.items = new Parada[i.length];
            for(int x = 0; x < i.length; x++) {
                items[x] = (Parada) i[x];
            }
        }

        @Override
        public int getCount() {
            return items.length;
        }

        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return items[position].getNumber();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Parada p = items[position];
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.custom_list_row,null,false);
            }
            TextView t1 = view.findViewById(R.id.id_text);
            TextView t2 = view.findViewById(R.id.num_paradas);
            TextView t3 = view.findViewById(R.id.nombre_parada);

            t1.setText(Integer.toString((int)p.getNumber()));
            t2.setText(Integer.toString(p.getPartes()));
            t3.setText(p.getAddress());

            Log.d(Constants.CLASS_LISTA_PARADAS,"Imprimiendo parada en posición: " + position);

            return view;
        }
    }
}
