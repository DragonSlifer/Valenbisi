package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.HTTPConnector;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Parada_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class ListaParadas extends AppCompatActivity {

    private ListView l;
    private ArrayList<Parada_class> paradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_paradas);
        Init();
        refreshScreen(paradas);


    }

    /**
     * Metodo para mostrar por pantalla y almacenar las paradas de un arraylist de paradas.
     * Se llama desde HTTP conector
     * @param nuevaListaparada lista de paradas que va a ser guardada en la aplicacion.
     */
    public void refreshScreen(ArrayList<Parada_class> nuevaListaparada)
    {
        try {
            Collections.sort(nuevaListaparada, new Parada_class.ParadaComparator());
            l = findViewById(R.id.list);
            AdapterParadas adapterParadas = new AdapterParadas(this, nuevaListaparada.toArray());
            l.setAdapter(adapterParadas);
            adapterParadas.notifyDataSetChanged();
            Context ctx = getApplicationContext();
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                             Log.d("ListaParadas","Parada seleccionada en posición " + position);
                                             goToParadaInfo(position);
                                         }
                                     });
                    paradas = nuevaListaparada;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void goToParadaInfo(int pos) {
        Intent i = new Intent(this, parada.class);

        i.putExtra(Constants.CLASS_PARADA, paradas.get(pos));

        startActivity(i);
    }

    public void Init() {
        //// Obtener JSON de HTTP
        HTTPConnector conector = new HTTPConnector(this);
        conector.execute();

        //// Obtener JSON de archivo
        /*
            String s = "";
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
                s = writer.toString();
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
            System.out.println(s);
            JSONObject json = new JSONObject(s);
            JSONArray jsonArray = (JSONArray) json.get(Constants.JSON_Parada_Lista);
            Parada_class p = new Parada_class();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonChild = (JSONObject) jsonObject.get(Constants.JSON_Parada_Datos);
                p.setNumber(Long.parseLong((jsonChild.getString(Constants.JSON_Parada_Number))));
                p.setAddress(jsonChild.getString(Constants.JSON_Parada_Addres));
                p.setPartes(Integer.parseInt(jsonChild.getString(Constants.JSON_Parada_Availa)));
                paradas.add(p);
                //System.out.println("Parada_class " + p.toSring());
                p = new Parada_class();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }



    public class AdapterParadas extends BaseAdapter {
        private Context context;
        private Parada_class[] items;

        class ViewHolder {
            TextView number;
            TextView address;
            TextView partes;
        }

        public AdapterParadas(Context c, Object[] i) {
            this.context = c;
            this.items = new Parada_class[i.length];
            for (int x = 0; x < i.length; x++) {
                items[x] = (Parada_class) i[x];
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
            Parada_class p = items[position];
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.custom_list_row, null, false);
            }
            TextView t1 = view.findViewById(R.id.id_text);
            TextView t2 = view.findViewById(R.id.num_paradas);
            TextView t3 = view.findViewById(R.id.nombre_parada);

            t1.setText(getString(R.string.n_parada) + " " + Integer.toString((int) p.getNumber()));
            t2.setText(getString(R.string.partes) + " " + Integer.toString(p.getPartes()));
            t3.setText(p.getAddress());

            Log.d(Constants.CLASS_LISTA_PARADAS, "Imprimiendo parada en posición: " + position);

            return view;
        }
    }
}
