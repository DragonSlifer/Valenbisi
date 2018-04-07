package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

/**
 * Created by Jorge on 22/02/2018.
 * valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public final class Constants {
    /**
     * Clase con todos los valores constantes a lo largo de la aplicacion
     */

    public static final String regex = "__";
    ///< JSON strings
    public static final String JSON_Parada_Lista = "features";
    public static final String JSON_Parada_Datos = "properties";
    public static final String JSON_Parada_Number = "number";
    public static final String JSON_Parada_Addres = "address";
    public static final String JSON_Parada_Availa = "available";
    public static final String JSON_Parada_Geomet = "geometry";
    public static final String JSON_Parada_Coords = "coordinates";
    public static final String JSON_Parada_Free = "free";
    public static final String JSON_Parada_Available = "available";
    public static final String JSON_Parada_Name = "name";
    public static final String JSON_Parada_UpDate = "updated_at";
    public static final String JSON_Parada_Open = "open";
    public static final String JSON_Parada_Ticket = "ticket";
    ///< Class identifiers
    public static final String CLASS_LISTA_PARADAS = "LIST_PAR";
    public static final String CLASS_PARADA = "PARADA";
    public static final String CLASS_PARTES = "PARTES";
    public static final String CLASS_DATABASE = "DATABASE_CONNECTOR";
    public static final String DATE = "DATE";
    ///< Request codes
    public static final String DATA_RECOVER = "DATA_RECOVER";
    public static final String geolocation_uri = "geo:0,0?q=" + regex + ",( " + regex + ")";
    ///< Codes
    // 000 - 099
    // 100 - 199 -> Result Codes
    public static final int RESULT_OK = 100;    ///< Basic result code.
    // 200 - 299
    public static final int NO_DATA = 200;
    public static final int DATA = 201;
    // 300 - 399
    // 400 - 499 -> Error Codes
    public static final int RESULT_ER = 400;    ///< Basic error code.
    ///< SQL STRINGS
    public static final String BD = "ValenbisiComunicados";
    public static final String tabla = "comunicados";
    public static final String nombre = "nombre";
    public static final String descripcion = "descripcion";
    public static final String parada = "parada";
    public static final String paradaID = "paradaID";
    public static final String estado = "estado";
    public static final String tipo = "tipo";
    public static final String date = "fecha";
    public static final String select = "select " + regex;
    public static final String from_t = "from " + regex;
    public static final String where = "where " + regex;
    public static final String create = "create table " + regex + "(" + regex + ")";
    public static final String update = "update " + regex + " set " + regex;
    public static final String set = "set ";
    public static final String camps = regex + " = " + regex;
    public static final String insert = "insert into " + regex + " (" + regex + ") values (" + regex + ")";
    public static final String delete = "delete from " + regex + " where " + regex + ";";
}

