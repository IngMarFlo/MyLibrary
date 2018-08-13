package mx.com.marflo.marflolibrary.permisos_android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;

/**
 * Autor:        Ing Alejandro Martínez Flores
 * Fecha:        30/05/2018
 * Descripción:
 */
public class androidPermits {

    /**
     * Método para verificar si se cuenta con el permiso
     * @param context   Contexto que llama al método
     * @param permiso   Nombre del periso, utilizar los objetos String estaticos de la clase
     * @return          Valor boleano indicando si se cuenta con el permiso
     */
    public static boolean verificarPermiso(Context context, Permits permiso){
        if (ContextCompat.checkSelfPermission(context, permiso.getName().toString()) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            solicitarPermiso((Activity) context, permiso);
            return false;
        }
    }

    private static void solicitarPermiso(Activity activity, Permits permiso){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permiso.getName().toString())){
            mostrarDialogo(activity, permiso);
        }else{
            ActivityCompat.requestPermissions(activity, new String[]{permiso.getName().toString()}, permiso.getRequestCode());
        }
    }

    private static void mostrarDialogo(final Activity activity, final Permits permiso){
        PersonalDialog dialog = new PersonalDialog();
        dialog.showDialog(activity,
                activity.getResources().getString(R.string.permiso_faltante),
                permiso.getDialogRequestMessageResource(),
                PersonalDialog.ICON.INFO,
                new PersonalDialog.Callback() {
                    @Override
                    public void onPositiveClick(AlertDialog ad) {
                        ad.dismiss();
                        ActivityCompat.requestPermissions(activity, new String[]{permiso.getName().toString()}, permiso.getRequestCode());
                    }

                    @Override
                    public void onNeutralClick(AlertDialog ad) {
ad.dismiss();
                    }

                    @Override
                    public void onNegativeClick(AlertDialog ad) {
                        ad.dismiss();
                    }
                });
    }
}
