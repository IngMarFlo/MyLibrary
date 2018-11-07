package mx.com.marflo.marflolibrary.rcv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * @author : Ing Alejandro Martínez Flores
 * @since : 02/05/2018
 * @version : 1
 */

public abstract class ViewHolderTemplate<M extends dataModels> extends Render<M>{
    private int resId;
    private ViewHolderCallback<M> callback;

    public ViewHolderTemplate(Class<M> type, int resId) {
        super(type);
        this.resId = resId;
    }

    public ViewHolderTemplate(Class<M> type, int resId, ViewHolderCallback<M> callback) {
        super(type);
        this.resId = resId;
		this.callback = callback;
	}

	@Override
    public void bind(final M model, VH h) {
        finder finder = new FinderImplement(h.itemView);
        if (callback != null) {
			finder.getRootView().setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					callback.onItemClick(model);
				}
			});

			finder.getRootView().setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					callback.onItemLongClick(model);
					return true;
				}
			});
		}

        setInfo(model, finder);
    }

    @Override
    public VH createViewHolder(ViewGroup parent) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    protected abstract void setInfo(final M model, finder finder);
}
