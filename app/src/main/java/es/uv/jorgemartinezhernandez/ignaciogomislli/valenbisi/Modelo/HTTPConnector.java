package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista.ListaParadas;


/**
 * Created by Ignacio on 20/03/2018.
 */

public class HTTPConnector extends AsyncTask<String,String,ArrayList<Parada>> {

    private ListaParadas padre;
    public HTTPConnector(ListaParadas arg)
    {
        this.padre=arg;
    }

    @Override
    protected ArrayList<Parada> doInBackground(String... params) {
            String s="";
            ArrayList<Parada> paradas = new ArrayList();

            String url = "http://mapas.valencia.es/lanzadera/opendata/Valenbisi/JSON";
            try {

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                //add request header
                con.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                con.setRequestProperty("accept", "application/json;");
                con.setRequestProperty("accept-language", "es");

                con.connect();  //Peta aqui!

                int responseCode = con.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK)
                {
                    throw new IOException("HTTP error code: " + responseCode);
                }


                BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                StringBuilder cadena= new StringBuilder();

                String linea="";
                while ((linea=buffer.readLine()) != null)
                {
                    cadena.append(linea+"\n");
                }

                buffer.close();
                con.disconnect();
                s = cadena.toString();

            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
                System.out.println("Error en lectura de http, leyendo de archivo");
            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("Error en lectura de http, leyendo de archivo");
            }

        try {
            System.out.println(s);
            JSONObject json = new JSONObject(s);
            JSONArray jsonArray = (JSONArray) json.get(Constants.JSON_Parada_Lista);
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


            return paradas;
        }

    @Override
    protected void onPostExecute(ArrayList<Parada> paradas)
    {
        padre.refreshScreen(paradas);
    }

    }


