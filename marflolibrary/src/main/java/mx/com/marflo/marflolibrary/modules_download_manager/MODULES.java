package mx.com.marflo.marflolibrary.modules_download_manager;


/**
 * Interface for download module
 * @author  Alejandro Martínez Flores
 * @since   12-08-2018
 * @version 1
 */
public interface MODULES {

	/**
	 * @return Name of module
	 */
	String getModuleName();

	/**
	 * @return Activity name include all package name
	 */
	String getActivityName();
}
