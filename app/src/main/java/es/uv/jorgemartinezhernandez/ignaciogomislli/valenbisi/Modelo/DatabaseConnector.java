package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Jorge on 28/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class DatabaseConnector extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Constants.BD;


    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = GeneralMethods.generateCreateTableString(Constants.tabla, new String[]{
                Constants.nombre + " TEXT NOT NULL",
                Constants.descripcion + " TEXT NOT NULL",
                Constants.paradaID + " INTEGER NOT NULL",
                Constants.parada + " TEXT NOT NULL",
                Constants.estado + " INTEGER NOT NULL",
                Constants.tipo + " INTEGER NOT NULL",
                Constants.date + " TEXT NOT NULL",
                "PRIMARY KEY (" + Constants.date + ", " + Constants.paradaID + ")"
        });
        db.execSQL(create);

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + Constants.tabla);

    }

    public ArrayList<Map<String, String>> generalSelect_1Table(String camps, String from, String where) {
        ArrayList<Map<String, String>> retval = new ArrayList<>();

        String select = GeneralMethods.generateSelectString(camps, from, where);
        Cursor cursor = getReadableDatabase().rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> map = new HashMap<>();
                int columns = cursor.getColumnCount();
                for (int i = 0; i < columns; i++) {
                    switch (cursor.getType(i)) {
                        case Cursor.FIELD_TYPE_INTEGER:
                            map.put(cursor.getColumnName(i) + Constants.regex + "I", Integer.toString(cursor.getInt(i)));
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            map.put(cursor.getColumnName(i) + Constants.regex + "F", Float.toString(cursor.getFloat(i)));
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            map.put(cursor.getColumnName(i) + Constants.regex + "S", cursor.getString(i));
                            break;
                    }
                }
                retval.add(map);
            } while (cursor.moveToNext());
        }
        return retval;
    }


    public void InsertarComunicado(String tabla, String[] campos, String[] valores) {
        String insert = GeneralMethods.generateInsertString(tabla, campos, valores);
        getReadableDatabase().execSQL(insert);
    }

    public void InsertarComunicado(String tabla, String campos, String valores) {
        String insert = GeneralMethods.generateInsertString(tabla, campos.split(","), valores.split(","));
        getReadableDatabase().execSQL(insert);
    }

    public void ActualizarComunicado(String tabla, String[] campos, String[] valores, String where) {
        String update = GeneralMethods.generateUpdateString(tabla, campos, valores, where);
        getReadableDatabase().execSQL(update);
    }

    public void BorrarComunicado(String tabla, String where) {
        String delete = GeneralMethods.generateDeleteString(tabla, where);
        Log.d(Constants.CLASS_DATABASE, "Delete: " + delete);
        getReadableDatabase().execSQL(delete);
    }

    public ArrayList<Partes_class> ObtenerComunicadoPorParada(String parada) {
        String select = GeneralMethods.generateSelectString("*", Constants.tabla, Constants.parada + " = '" + parada + "'");
        Cursor c = getReadableDatabase().rawQuery(select, null);
        ArrayList<Partes_class> partes_classes = new ArrayList<>();

        partes_classes = Partes_class.getParteFromCursor(c);

        return partes_classes;
    }

    public ArrayList<Partes_class> ObtenerComunicadoPorID(long ID) {
        String select = GeneralMethods.generateSelectString("*", Constants.tabla, Constants.paradaID + " = '" + ID + "'");
        //Log.d("Isnull?",select);
        //System.out.println(db);
        Cursor c = getReadableDatabase().rawQuery(select, null);

        ArrayList<Partes_class> partes_classes = new ArrayList<>();

        partes_classes = Partes_class.getParteFromCursor(c);

        return partes_classes;
    }
}