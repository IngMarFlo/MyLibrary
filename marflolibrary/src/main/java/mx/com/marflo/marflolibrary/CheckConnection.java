package mx.com.marflo.marflolibrary;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;

/**
 * @version : 1
 * @author Ing Alejandro Martínez Flores
 * @since 13/08/2018
 */
public class CheckConnection {

    /**
     * Método con el que se valida que el dispositivo cuente con conexión a internet para ejecutar las consultas al servidor
     * @param context   Contexto que llama al método
     * @return          Valor boleano con la validación
     */

    @RequiresPermission (Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean conexionEstablecida(Context context){
        return conectadoPorDatos(context) || conectadoPorWiFi(context);
    }

    @RequiresPermission (Manifest.permission.ACCESS_NETWORK_STATE)
    private static boolean conectadoPorDatos(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null){
                return info.isConnected();
            }
        }

        return false;
    }

	@RequiresPermission (Manifest.permission.ACCESS_NETWORK_STATE)
	private static boolean conectadoPorWiFi(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null){
                return info.isConnected();
            }
        }

        return false;
    }
}
