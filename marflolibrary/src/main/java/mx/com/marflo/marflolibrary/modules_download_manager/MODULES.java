package mx.com.marflo.marflolibrary.modules_download_manager;


/**
 * Interface for download module
 * @author  Alejandro Martínez Flores
 * @since   12-08-2018
 * @version 1
 */
public interface MODULES {
	/**
	 * @return Package of module
	 */
	String getPackage();

	/**
	 * @return Name of module
	 */
	String getModuleName();

	/**
	 * @return Activity name, if is in a package, include package.ActivityName
	 */
	String getActivityName();
}
