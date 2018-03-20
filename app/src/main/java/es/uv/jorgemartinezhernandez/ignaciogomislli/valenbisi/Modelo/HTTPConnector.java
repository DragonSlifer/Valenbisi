package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista.ListaParadas;


/**
 * Created by Ignacio on 20/03/2018.
 */

public class HTTPConnector extends AsyncTask<String,String,String> {
    @Override
    public String doInBackground(String... params) {
            String result="";

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
                result = cadena.toString();




            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
                System.out.println("Error en lectura de http, leyendo de archivo");
                return "";
            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("Error en lectura de http, leyendo de archivo");
                return "";
            }

            return result;
        }

    }

