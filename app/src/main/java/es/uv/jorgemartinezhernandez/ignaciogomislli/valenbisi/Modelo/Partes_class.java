package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jorge on 28/03/2018.
 * Valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class Partes_class implements Parcelable{
    private String nombre, descripcion,parada;
    private long paradaID;
    private int estado;
    private int tipo;

    public Partes_class() {
    }


    public Partes_class(Cursor c){
        if(c.moveToFirst()){
            do{
                this.nombre         = c.getString(0);
                this.descripcion    = c.getString(1);
                this.paradaID       = c.getLong(2);
                this.parada         = c.getString(3);
                this.estado         = c.getInt(4);
                this.tipo           = c.getInt(5);
            }while(c.moveToNext());
        }
    }

    public Partes_class(String nombre, String descripcion, String parada, long paradaID, int estado, int tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.parada = parada;
        this.paradaID = paradaID;
        this.estado = estado;
        this.tipo = tipo;
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
