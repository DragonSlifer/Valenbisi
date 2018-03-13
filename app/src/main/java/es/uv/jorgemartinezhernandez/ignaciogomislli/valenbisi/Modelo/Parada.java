package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by Jorge on 22/02/2018.
 * valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public class Parada implements Parcelable {
    private long number;
    private String address;
    private int partes;

    public Parada() {
    }

    public Parada(long number, String address, int partes) {
        this.number = number;
        this.address = address;
        this.partes = partes;
    }

    protected Parada(Parcel in) {
        number = in.readLong();
        address = in.readString();
        partes = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(number);
        dest.writeString(address);
        dest.writeInt(partes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parada> CREATOR = new Creator<Parada>() {
        @Override
        public Parada createFromParcel(Parcel in) {
            return new Parada(in);
        }

        @Override
        public Parada[] newArray(int size) {
            return new Parada[size];
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parada parada = (Parada) o;

        if (getNumber() != parada.getNumber()) return false;
        if (getPartes() != parada.getPartes()) return false;
        return getAddress().equals(parada.getAddress());
    }

    @Override
    public int hashCode() {
        int result = (int) (getNumber() ^ (getNumber() >>> 32));
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPartes();
        return result;
    }

    @Override
    public String toString() {
        return "Parada{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", partes=" + partes +
                '}';
    }

    public static class ParadaComparator implements Comparator<Parada> {
        @Override
        public int compare(Parada o1, Parada o2) {
            return o1.getAddress().compareTo(o2.getAddress());
        }
    }
}
