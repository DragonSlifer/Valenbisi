package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Jorge on 22/02/2018.
 * valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class Parada_class implements Parcelable {
    private long number;        ///< Numero de parada
    private String name;        ///< Nombre de la parada
    private String address;     ///< Dirección de la parada
    private int partes;         ///< Numero de partes
    private int total;          ///< Total de bicicletas
    private int libres;         ///< Bicis libres
    private int dispon;         ///< Bicis disponibles
    private double lat;           ///< Latitud
    private double lon;           ///< Longitud
    private boolean open;       ///< Abierto
    private boolean ticket;     ///< Ticket
    private String updated;     ///<

    public Parada_class() {
    }

    public Parada_class(JSONObject jsonObject){
        try {
            ///< Properties
            JSONObject properties = (JSONObject) jsonObject.get(Constants.JSON_Parada_Datos);
            this.setName(properties.getString(Constants.JSON_Parada_Name));
            Log.d("Parada_class","Name: " + getName());
            this.setNumber(Long.parseLong((properties.getString(Constants.JSON_Parada_Number))));
            this.setAddress(properties.getString(Constants.JSON_Parada_Addres));
            if(Objects.equals(properties.getString(Constants.JSON_Parada_Open), "T"))
                open = true;
            else
                open = false;
            this.setDispon(properties.getInt(Constants.JSON_Parada_Availa));
            this.setLibres(properties.getInt(Constants.JSON_Parada_Free));
            if(Objects.equals(properties.getString(Constants.JSON_Parada_Ticket), "T"))
                ticket = true;
            else
                ticket = false;
            this.setUpdated(properties.getString(Constants.JSON_Parada_UpDate));
            this.setPartes(0);
            ///< Geometry
            JSONArray coords = ((JSONObject) jsonObject.get(Constants.JSON_Parada_Geomet)).getJSONArray(Constants.JSON_Parada_Coords);
            this.setLat(coords.getDouble(0));
            this.setLon(coords.getDouble(1));
            Log.d("Parada_class","Coords: " + getCoords());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Parada_class(long number, String address, int partes) {
        this.number = number;
        this.address = address;
        this.partes = partes;
    }

    public Parada_class(long number, String name, String address, int partes, int total, int libres, int dispon, double lat, double lon, boolean open, boolean ticket, String updated) {
        this.number = number;
        this.name = name;
        this.address = address;
        this.partes = partes;
        this.total = total;
        this.libres = libres;
        this.dispon = dispon;
        this.lat = lat;
        this.lon = lon;
        this.open = open;
        this.ticket = ticket;
        this.updated = updated;
    }

    protected Parada_class(Parcel in) {
        number = in.readLong();
        name = in.readString();
        address = in.readString();
        partes = in.readInt();
        total = in.readInt();
        libres = in.readInt();
        dispon = in.readInt();
        lat = in.readDouble();
        lon = in.readDouble();
        open = in.readByte() != 0;
        ticket = in.readByte() != 0;
        updated = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(number);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(partes);
        dest.writeInt(total);
        dest.writeInt(libres);
        dest.writeInt(dispon);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeByte((byte) (ticket ? 1 : 0));
        dest.writeString(updated);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parada_class> CREATOR = new Creator<Parada_class>() {
        @Override
        public Parada_class createFromParcel(Parcel in) {
            return new Parada_class(in);
        }

        @Override
        public Parada_class[] newArray(int size) {
            return new Parada_class[size];
        }
    };

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPartes() {
        return partes;
    }

    public void setPartes(int partes) {
        this.partes = partes;
    }

    public int getTotal() {
        return total;
    }

    public int getLibres() {
        return libres;
    }

    public void setLibres(int libres) {
        this.libres = libres;
        this.total = dispon + libres;
    }

    public int getDispon() {
        return dispon;
    }

    public void setDispon(int dispon) {
        this.dispon = dispon;
        this.total = dispon + libres;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isTicket() {
        return ticket;
    }

    public void setTicket(boolean ticket) {
        this.ticket = ticket;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCoords (){
        return "Lat: " + Double.toString(getLat()) + " / Lon: " + Double.toString(getLon());
    }

    public static class ParadaComparator implements Comparator<Parada_class> {
        @Override
        public int compare(Parada_class o1, Parada_class o2) {
            return o1.getAddress().compareTo(o2.getAddress());
        }
    }
}
