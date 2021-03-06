package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jorge on 28/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class Partes_class implements Parcelable {
    private String nombre, descripcion, parada;
    private long paradaID;
    private int estado;
    private int tipo;
    private Date date;

    public Partes_class() {
    }

    protected Partes_class(Parcel in) {
        nombre = in.readString();
        descripcion = in.readString();
        parada = in.readString();
        paradaID = in.readLong();
        estado = in.readInt();
        tipo = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(parada);
        dest.writeLong(paradaID);
        dest.writeInt(estado);
        dest.writeInt(tipo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Partes_class> CREATOR = new Creator<Partes_class>() {
        @Override
        public Partes_class createFromParcel(Parcel in) {
            return new Partes_class(in);
        }

        @Override
        public Partes_class[] newArray(int size) {
            return new Partes_class[size];
        }
    };

    /**
     * Constructor de partes a partir de un cursor
     *
     * @param c Cursor apuntando a una fila en la tabla de tipo parte
     * @return ArrayList de partes
     */
    public static ArrayList<Partes_class> getParteFromCursor(Cursor c) {
        ArrayList<Partes_class> partes_classArrayList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                Partes_class aux = new Partes_class();
                aux.nombre = c.getString(0);
                aux.descripcion = c.getString(1);
                aux.paradaID = c.getLong(2);
                aux.parada = c.getString(3);
                aux.estado = c.getInt(4);
                aux.tipo = c.getInt(5);
                aux.date = GeneralMethods.stringToDate(c.getString(6));
                partes_classArrayList.add(aux);
            } while (c.moveToNext());
        }

        return partes_classArrayList;
    }

    /**
     * Constructor a partir de datos
     *
     * @param nombre      Nombre del parte
     * @param descripcion Información del parte
     * @param parada      Nombre de parada
     * @param paradaID    ID de parada
     * @param estado      Estado del parte
     * @param tipo        Tipo de parte
     */
    public Partes_class(String nombre, String descripcion, String parada, long paradaID, int estado, int tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.parada = parada;
        this.paradaID = paradaID;
        this.estado = estado;
        this.tipo = tipo;
    }

    //Funciones para implementar parcelable

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParada() {
        return parada;
    }

    public void setParada(String parada) {
        this.parada = parada;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public long getParadaID() {
        return paradaID;
    }

    public void setParadaID(long paradaID) {
        this.paradaID = paradaID;
    }

    public String getTipoString() {
        String s = "";

        switch (tipo) {
            case 0:
                s = "Mecánico";
                break;
            case 1:
                s = "Eléctrico";
                break;
            case 2:
                s = "Pintura";
                break;
            case 3:
                s = "Obra de Paleta";
                break;
        }

        return s;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String[] getCampos() {
        return new String[]{
                Constants.paradaID,
                Constants.date,
                Constants.parada,
                Constants.descripcion,
                Constants.nombre,
                Constants.estado,
                Constants.tipo
        };
    }

    public String[] getValores() {
        return new String[]{
                Long.toString(this.paradaID),
                "'" + GeneralMethods.dateToString(this.date) + "'",
                "'" + this.parada + "'",
                "'" + this.descripcion + "'",
                "'" + this.nombre + "'",
                Integer.toString(this.estado),
                Integer.toString(this.tipo)
        };
    }

    @Override
    public String toString() {
        return "Partes_class{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", parada='" + parada + '\'' +
                ", paradaID=" + paradaID +
                ", estado=" + estado +
                ", tipo=" + tipo +
                '}';
    }
}
