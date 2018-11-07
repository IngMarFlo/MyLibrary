package mx.com.marflo.marflolibrary.spinner_adapter;


import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * Clase de la cual deben heredar todas las clases que fungiran como modelo de datos para el SpinnerApdater
 * @author   Ing Alejandro Martínez Flores
 * @since   09/07/2018
 * @version : 1
 * @see SpinnerAdapter
 */
public abstract class spinnersModels extends dataModels implements spinnersInterface{
    public boolean isSelectionValid(){
        return getId() != -1 || (getIdRef() != null && !getIdRef().equals("-1"));
    }
}
