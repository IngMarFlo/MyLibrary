package mx.com.marflo.marflolibrary;

import android.content.Context;
import android.util.Log;


/**
 * Autor:        Ing Alejandro Martínez Flores
 * Fecha:        08/06/2018
 * Descripción:
 */
public class HandleException {
    public static void show(Context c, Exception e){
        e.printStackTrace();
        Log.w("MARFLO_", "Excepción="+e.getClass().getSimpleName()+"\nMessage="+e.getMessage());
        PersonalDialog dialog = new PersonalDialog();
        String message =
                c.getResources().getString(R.string.handle_exception_type)+" "+e.getClass().getSimpleName()+
                "\n"+c.getResources().getString(R.string.handle_exception_message)+" "+e.getMessage();
        dialog.showDialog(c,
                c.getResources().getString(R.string.handle_exception_title),
                message,
                PersonalDialog.ICON.WARNING, null);
    }
}
