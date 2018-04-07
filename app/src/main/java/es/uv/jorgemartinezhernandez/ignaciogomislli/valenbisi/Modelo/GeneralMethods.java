package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.util.Log;

import java.util.Objects;

/**
 * Created by Jorge on 21/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class GeneralMethods {
    /**
     * Funcion para reemplazar en una cadena distintos campos
     * @param cad Cadena en la que reemplazar
     * @param rep Valores para ser emplazados
     * @param reg Elemento a reemplazar, habitualmente '__'
     * @return
     */
    public static String Replace (String cad, String[] rep, String reg) {
        for(String s : rep){
            cad = cad.replaceFirst(reg,s);
            Log.d("replace","reg -> " + reg + " rep -> "+ s);
        }
        return cad;
    }

    /**
     * Compactador de arrays en strings.
     * @param array Array a ser convertido.
     * @param separator Elemento de separación del array
     * @return String final
     */
    public static String arrayToString(String[] array,String separator){
        String string = "";

        for(int i = 0; i < array.length; i++){
            string += array[i];
            if(i+1 < array.length){
                string += separator;
            }
        }

        return string;
    }

    /**
     * Generador de consultas Select de la base de datos
     */
    public static String generateSelectString(String seleccion, String from, String where){
        String select = Constants.select + " " + Constants.from_t;
        select = select.replaceFirst(Constants.regex,seleccion);
        select = select.replaceFirst(Constants.regex,from);
        select = addWhere(select,where);
        Log.d("BD: Select",select);
        return select;
    }

    /**
     * Generador de comandos CreateTable de la base de datos
     */
    public static String generateCreateTableString(String tabla, String[] campos){
        String createTable = Constants.create;
        String campos_s;
        createTable = createTable.replaceFirst(Constants.regex,tabla);
        campos_s = arrayToString(campos,",");
        createTable = createTable.replaceFirst(Constants.regex,campos_s);
        Log.d("BD: CreateTable",createTable);
        return createTable;
    }

    /**
     * Generador de consultas Update de la base de datos
     */
    public static String generateUpdateString(String tabla, String[] campos, String where){
        String update = Constants.update;
        String campos_s;

        update = update.replaceFirst(Constants.regex, tabla);
        campos_s = arrayToString(campos,",");
        update = update.replaceFirst(Constants.regex,campos_s);
        update = addWhere(update,where);
        Log.d("BD: Update",update);
        return update;
    }

    /**
     * Generador de consultas Insert a la base de datos
     */
    public static String generateInsertString(String tabla, String[] campos, String[] valores){
        String insert = Constants.insert;
        String campos_s;

        insert = insert.replaceFirst(Constants.regex, tabla);

        if(campos.length == valores.length){
            campos_s = arrayToString(campos,",");
            insert = insert.replaceFirst(Constants.regex,campos_s);
            campos_s = arrayToString(valores,",");
            insert = insert.replaceFirst(Constants.regex,campos_s);
        } else {
            insert = null;
        }
        Log.d("BD: Insert",insert);
        return insert;
    }

    /**
     * Generador de comandos Delete de la base de datos
     */
    public static  String generateDeleteString(String tabla, String where){
        String delete = Constants.delete;

        if (!Objects.equals(where, "") && !Objects.equals(where, null)){
            delete = delete.replaceFirst(Constants.regex,tabla);
            delete = delete.replaceFirst(Constants.regex,where);
            if(delete.contains("where  ;")){
                delete = null;
            } else{
                delete = delete.split(";")[0];
            }
        } else {
            delete = null;
        }
        Log.d("BD: Delete",delete);
        return delete;
    }

    /**
     * Generador de clausulas Where para operaciones sobre la base de datos
     */
    private static String addWhere(String cad, String where){
        if(where != null){
            cad += " " + Constants.where;
            cad = cad.replaceFirst(Constants.regex,where);
        }
        return cad;
    }
}
