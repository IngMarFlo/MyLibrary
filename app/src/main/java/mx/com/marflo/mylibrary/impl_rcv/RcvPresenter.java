package mx.com.marflo.mylibrary.impl_rcv;

import mx.com.marflo.marflolibrary.rcv.RecyclerViewAdapter;

/**
 * Interfaz presentadora entre la actividad y el controlador
 * @author : Alejandro Martínez Flores
 * @since :	06/11/2018
 * @version : 1
 */
public interface RcvPresenter {
	/**
	 * Método para obtener el adaptador para el RecyclerView
	 * @param adapter Adaptador genérico configurado
	 */
	void setAdapter(RecyclerViewAdapter adapter);

	/**
	 * Método para detener el SwipeRefreshLayout y ocultar el TextView
	 */
	void stopRefresh();

	/**
	 * Método para mostrar un mensaje tipo {@link android.widget.Toast}
	 * @param text	Mensaje a mostrar
	 */
	void showToastOnItemClick(String text);
}
