package mx.com.marflo.marflolibrary.customs_views;

/**
 * Interfáz para ejecutar en los views personalizados
 * @author  Ing Alejandro Martínez Flores
 * @since   06/07/2018
 * @version : 1
 */
public interface customView {
    String  getField();
    String  getInvalidMessage();
    boolean isObligatorio();
    void setObligatorio(boolean ob);
}
