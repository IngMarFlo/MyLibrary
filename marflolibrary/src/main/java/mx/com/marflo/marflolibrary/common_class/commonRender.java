package mx.com.marflo.marflolibrary.common_class;

import android.view.View;
import android.view.ViewGroup;

/**
 * @version 1
 * @autor Ing Alejandro Martínez Flores
 * @since 09/07/2018
 */
public abstract class commonRender<M extends dataModels> {
    private int resource;

    public commonRender(int resource){
        this.resource = resource;
    }

    public int getResource() {
        return resource;
    }

    public abstract View getView(M model, View convert, ViewGroup parent);
    public abstract View getDropDownView(M model, View convert, ViewGroup parent);
}
