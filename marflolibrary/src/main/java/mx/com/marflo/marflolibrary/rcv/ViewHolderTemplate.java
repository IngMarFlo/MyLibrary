package mx.com.marflo.marflolibrary.rcv;

import android.view.ViewGroup;

import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * Autor:        Ing Alejandro Martínez Flores
 * Fecha:        02/05/2018
 * Descripción:
 */

public abstract class ViewHolderTemplate<M extends dataModels> extends Render<M>{
    private int resId;

    public ViewHolderTemplate(Class type, int resId) {
        super(type);
        this.resId = resId;
    }

    @Override
    public void bind(M model, VH h) {
        setInfo(model, new FinderImplement(h.itemView));
    }

    @Override
    public VH createViewHolder(ViewGroup parent) {
        return new VH(RecyclerViewAdapter.inflate(resId, parent));
    }

    protected abstract void setInfo(final M model, finder finder);
}
