package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo;

/**
 * Created by Jorge on 22/02/2018.
 * valenbisi.es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.
 */

public final class Constants {
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
    ///< Codes
    // 000 - 099 -> Request Codes
    public static final String geolocation_uri = "geo:0,0?q=__,__,(__)";
    // 100 - 199 -> Result Codes
    public static final int RESULT_OK = 100;    ///< Basic result code.
    // 200 - 299
    // 300 - 399
    // 400 - 499 -> Error Codes
    public static final int RESULT_ER = 400;    ///< Basic error code.
}
