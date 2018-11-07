package mx.com.marflo.marflolibrary.rcv;

import android.view.ViewGroup;

import java.lang.reflect.Type;
import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * @author :        Ing Alejandro Martínez Flores
 * @since :        02/05/2018
 * @version : 1
 */

abstract class Render<M extends dataModels> {

    private final Type type;
    Render(Class<M> type){
        this.type = type;
    }
    public Type getType(){return type;}

    public abstract void bind(M model, VH h);
    public abstract VH createViewHolder(ViewGroup parent);
}
