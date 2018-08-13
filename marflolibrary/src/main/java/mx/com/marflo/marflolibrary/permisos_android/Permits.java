package mx.com.marflo.marflolibrary.permisos_android;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 13-08-2018
*/
public interface Permits {
	/**
	 * Request code del Intent
	 */
	int getRequestCode();

	/**
	 * @return Mensaje a mostrar en el diálogo para solicitar el permiso
	 */
	String getDialogRequestMessageResource();

	/**
	 * @return Nombre del permiso
	 */
 	android.Manifest.permission getName();
}
