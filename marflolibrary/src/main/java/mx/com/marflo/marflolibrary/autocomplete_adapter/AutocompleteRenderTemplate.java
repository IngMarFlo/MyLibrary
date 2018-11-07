package mx.com.marflo.marflolibrary.autocomplete_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.marflo.marflolibrary.common_interfaces.FinderCommonImplement;
import mx.com.marflo.marflolibrary.common_interfaces.finderCommon;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
public abstract class AutocompleteRenderTemplate<M extends autocompletesModels> extends autocompleteRender<M> {
    private int resource;

    public AutocompleteRenderTemplate(int resource) {
        super(resource);
        this.resource = resource;
    }

    @Override
    public View getView(M model, View convert, ViewGroup parent) {
        if (convert == null){
            convert = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        }
        setInfo(model, new FinderCommonImplement(convert));
        return convert;
    }

    @Override
    public View getDropDownView(M model, View convert, ViewGroup parent) {
        return getView(model, convert, parent);
    }

    /**
     * Método que debe implementarse en la clase concreta para mostrar la información requerida en el spinner
     * @param model     Modelo de datos que extiende de la clase spinnersModels
     * @param finder    Objeto de apoyo para mayor facilidad para mostrar información
     */
    public abstract void setInfo(M model, finderCommon finder);
}
