package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Jorge on 28/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class DatabaseConnector extends SQLiteOpenHelper{

    private SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Constants.BD;


    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = GeneralMethods.generateCreateTableString(Constants.tabla,new String[]{
                /*"varchar(255) " + */Constants.nombre +" TEXT NOT NULL",
                /*"varchar(255) " + */Constants.descripcion+" TEXT NOT NULL",
                /*"number " + */Constants.paradaID+" INTEGER PRIMARY KEY AUTOINCREMENT",
                /*"varchar(255) "  + */Constants.parada+" TEXT NOT NULL",
                /*"number " + */Constants.estado+" INTEGER NOT NULL",
                /*"number " + */Constants.tipo+" INTEGER NOT NULL"
        });
        db.execSQL(create);

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void InsertarComunicado(String tabla, String[] campos, String[] valores){
        String insert = GeneralMethods.generateInsertString(tabla,campos,valores);
        db.execSQL(insert);
    }

    public void InsertarComunicado(String tabla, String campos, String valores){
        String insert = GeneralMethods.generateInsertString(tabla,campos.split(","),valores.split(","));
        db.execSQL(insert);
    }

    public void ActualizarComunicado(String tabla, String[] campos, String where){
        String update = GeneralMethods.generateUpdateString(tabla,campos,where);
        db.execSQL(update);
    }

    public void BorrarComunicado(String tabla, String where){
        String delete = GeneralMethods.generateDeleteString(tabla,where);
        db.execSQL(delete);
    }

    public ArrayList<Partes_class> ObtenerComunicadoPorParada(String parada){
        String select = GeneralMethods.generateSelectString("*",Constants.tabla,Constants.parada + " = '" + parada + "'");
        Cursor c = getReadableDatabase().rawQuery(select,null);
        ArrayList<Partes_class> partes_classes = new ArrayList<>();

        partes_classes = Partes_class.getParteFromCursor(c);

        return partes_classes;
    }

    public ArrayList<Partes_class> ObtenerComunicadoPorID(long ID){
        String select = GeneralMethods.generateSelectString("*",Constants.tabla,Constants.paradaID + " = '" + ID + "'");
        Log.d("Isnull?",select);
        System.out.println(db);
        Cursor c = getReadableDatabase().rawQuery(select,null);

        ArrayList<Partes_class> partes_classes = new ArrayList<>();

        partes_classes = Partes_class.getParteFromCursor(c);

        return partes_classes;
    }
}