package mx.com.marflo.marflolibrary.spinner_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.marflo.marflolibrary.common_interfaces.FinderCommonImplement;
import mx.com.marflo.marflolibrary.common_interfaces.finderCommon;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 07 Julio 2018
 * @param <M> Clase que debe extender de spinnersModels
 */
public abstract class SpinnerRenderTemplate<M extends spinnersModels> extends SpinnerRender<M> {
    private int resource;

    public SpinnerRenderTemplate(int resource) {
        super(resource);
        this.resource = resource;
    }

    @Override
    public View getView(M model, View convert, ViewGroup parent) {
        if (convert == null){
            convert = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        }
        setViewInfo(model, new FinderCommonImplement(convert));
        return convert;
    }

    @Override
    public View getDropDownView(M model, View convert, ViewGroup parent) {
        if (convert == null){
            convert = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        }
        setDropDownViewInfo(model, new FinderCommonImplement(convert));
        return convert;
    }

    /**
     * Método que debe implementarse en la clase concreta para mostrar la información requerida en el spinner
     * @param model     Modelo de datos que extiende de la clase spinnersModels
     * @param finder    Objeto de apoyo para mayor facilidad para mostrar información
     */
    public abstract void setViewInfo(M model, finderCommon finder);
    public abstract void setDropDownViewInfo(M model, finderCommon finder);
}
