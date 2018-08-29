package mx.com.marflo.marflolibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Alejandro Martínez Flores
 * @since 28/08/2018
 */

public class ConnectivityMannager {

    public static boolean isInternalWifi(String wifi, String... internalNames){
        for (String s : internalNames){
            if (wifi.contains(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Método con el que se valida que el dispositivo cuente con conexión a internet
     * @param context   Contexto que llama al método
     * @return          Valor boleano con la validación
     */
    public static boolean conexionEstablecida(Context context){
        return isMobile(context) || isWifi(context);
    }

    public static boolean isMobile(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null){
                return info.isConnected();
            }
        }

        return false;
    }

    public static boolean isWifi(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null){
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null){
                return info.isConnected();
            }
        }

        return false;
    }

    public static String getWifiName(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivity != null;
        NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info.getExtraInfo();
    }
}
