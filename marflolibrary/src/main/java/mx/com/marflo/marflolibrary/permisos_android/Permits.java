package mx.com.marflo.marflolibrary.permisos_android;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

/**
 * Alejandro Martinez Flores on 12/08/2018.
 */
public interface Permits {
	int getRequestCode();
	@StringRes
	int getMessageResorce();
	@StringRes
	int getDialogRequestMessageResource();
 	String getName();
}
