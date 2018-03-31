package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.util.Log;

import java.util.Objects;

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

    public static String arrayToString(String[] array,String separator){
        String string = "";

        for(int i = 0; i < array.length; i++){
            string += array[i];
            if(i++ < array.length){
                string += separator;
            }
        }

        return string;
    }

    /**
     *
     * @param tabla
     * @param from
     * @param where
     * @return
     */
    public static String generateSelectString(String tabla, String from, String where){
        String select = Constants.select + " " + Constants.from_t;
        select = select.replaceFirst(Constants.regex,tabla);
        select = select.replaceFirst(Constants.regex,from);
        select = addWhere(select,where);
        return select;
    }

    /**
     *
     * @param tabla
     * @param campos
     * @return
     */
    public static String generateCreateTableString(String tabla, String[] campos){
        String createTable = Constants.create;
        String campos_s;
        createTable = createTable.replaceFirst(Constants.regex,tabla);
        campos_s = arrayToString(campos,",");
        createTable = createTable.replaceFirst(Constants.regex,campos_s);

        return createTable;
    }

    /**
     *
     * @param tabla
     * @param campos
     * @param where
     * @return
     */
    public static String generateUpdateString(String tabla, String[] campos, String where){
        String update = Constants.update;
        String campos_s;

        update = update.replaceFirst(Constants.regex, tabla);
        campos_s = arrayToString(campos,",");
        update = update.replaceFirst(Constants.regex,campos_s);
        update = addWhere(update,where);

        return update;
    }

    public static String generateInsertString(String tabla, String[] campos, String[] valores){
        String insert = Constants.update;
        String campos_s;

        insert = insert.replaceFirst(Constants.regex, tabla);

        if(campos.length == valores.length){
            campos_s = arrayToString(campos,",");
            insert = insert.replaceFirst(Constants.regex,campos_s);
            campos_s = arrayToString(campos,",");
            insert = insert.replaceFirst(Constants.regex,campos_s);
        } else {
            insert = null;
        }
        return insert;
    }

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

        return delete;
    }

    private static String addWhere(String cad, String where){
        if(where != null){
            cad += " " + Constants.where;
            cad = cad.replaceFirst(Constants.regex,where);
        }
        return cad;
    }
}
