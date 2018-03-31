package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by Jorge on 28/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class DatabaseConnector extends SQLiteOpenHelper{

    private SQLiteDatabase db;

    public DatabaseConnector(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = GeneralMethods.generateCreateTableString(Constants.tabla,new String[]{"varchar(255) " + Constants.nombre,
                "varchar(255) " + Constants.descripcion,"number " + Constants.paradaID, "varchar(255) "  + Constants.parada,"number " + Constants.estado,"number " + Constants.tipo});

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
        Cursor c = db.rawQuery(select,null);
        ArrayList<Partes_class> partes_classes = new ArrayList<>();

        partes_classes = Partes_class.getParteFromCursor(c);

        return partes_classes;
    }

    public ArrayList<Partes_class> ObtenerComunicadoPorID(long ID){
        String select = GeneralMethods.generateSelectString("*",Constants.tabla,Constants.paradaID + " = '" + ID + "'");
        Cursor c = db.rawQuery(select,null);

        ArrayList<Partes_class> partes_classes = new ArrayList<>();

        partes_classes = Partes_class.getParteFromCursor(c);

        return partes_classes;
    }
}