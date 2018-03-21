package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.util.Log;

/**
 * Created by Jorge on 21/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class GeneralMethods {
    public static String Replace (String cad, String[] rep, String reg) {
        for(String s : rep){
            cad = cad.replaceFirst(reg,s);
            Log.d("replace","reg -> " + reg + " rep -> "+ s);
        }
        return cad;
    }
}
