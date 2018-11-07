package mx.com.marflo.marflolibrary.rcv;

import mx.com.marflo.marflolibrary.common_class.dataModels;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 07/11/2018.
 */
public abstract class ViewHolderCallback<M extends dataModels> {
	public void onItemClick(M model){};
	public void onItemLongClick(M model){};
}
