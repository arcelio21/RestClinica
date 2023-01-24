package com.example.util;

import com.google.gson.Gson;

/**
 *@apiNote Clase que se encargar de la Serializacion
 * */
public class GsonUtil {

    /**
     *@apiNote Convierte aun objeto a Json
     * */
    public static String serializae(Object src){

        Gson gson = new Gson();

        return gson.toJson(src);
    }

    /**
     *Convierte String Json al DTO que se defina
     *@param json : Json String que se quiere convertir
     *@param dClass : Clase a la que se quiere convertir el Json
     * */
    public static <D> D toObject(String json,Class<D> dClass){
        Gson gson = new Gson();
        return gson.fromJson(json,dClass);
    }

    /**
     *@apiNote Metodo se encargar de convertir un objeto a Json, y luego a la clase que especificamos por parametro
     *@param src : Objeto que se desea convertir
     *@param dClass: Clase a la que se desea convertir el objeto
     * */
    public static <D> D toObject(Object src,Class <D> dClass){
        Gson gson = new Gson();
        String srcJson = gson.toJson(src);
        return gson.fromJson(srcJson,dClass);
    }
}
