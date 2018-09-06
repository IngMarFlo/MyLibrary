package mx.com.marflo.marflolibrary.download_files;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import java.io.File;

import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 29/08/2018
 */
public class Downloader implements DownloadFileCallback{

    private Context context;
    private DownloadFileCallback callback;
    private boolean showDialog;
    private File dest, file;
    private String URL;

    /**
     * Constructor de la clase, para correr la descarga debe usar el método {@link #run()}
     * @param context       Contexto que llama a la clase
     * @param showDialog    Indicador para mostrar o no el cuadro de descarga
     * @param URL           Dirección del archivo a descargar, debe contener el nombre del archivo así como su extensión
     * @param dest          Directorio de destino en el dispositivo, en caso que el parámetro sea nulo se guarda en el cache
     * @param callback      Interfaz de comunicación con la clase
     */
    public Downloader(Context context, boolean showDialog, String URL, @Nullable File dest, DownloadFileCallback callback){
        this.callback   = callback;
        this.context    = context.getApplicationContext();
        this.dest       = (dest == null) ? context.getCacheDir() : dest;
        this.URL        = URL;
        this.showDialog = showDialog;
    }

    /**
     * Método para ejecutar la descarga del archivo
     */
    public void run(){
        if (androidPermits.verificarPermiso(context, AndroidRuntimePermits.WRITE_EXTERNAL_STORAGE)){
            file = new File(dest, DownloadFile.getFileName(URL));
            if (file.exists()){
                fileExistDialog();
            }else{
                download();
            }

        }
    }

    private void fileExistDialog(){
        new PersonalDialog()
                .setYesTitle(context.getResources().getString(R.string.DOWNLOAD))
                .setShowNo(context.getResources().getString(R.string.OPEN))
                .setShowNeutral(context.getResources().getString(R.string.CANCELAR))
                .showDialog(context,
                        context.getResources().getString(R.string.dialog_downloader_title),
                        context.getResources().getString(R.string.dialog_downloader_message),
                        PersonalDialog.ICON.INFO, new PersonalDialog.Callback() {
                            @Override
                            public void onPositiveClick(AlertDialog ad) {
                                download();
                                ad.dismiss();
                            }

                            @Override
                            public void onNeutralClick(AlertDialog ad) {
                                ad.dismiss();
                            }

                            @Override
                            public void onNegativeClick(AlertDialog ad) {
                                ad.dismiss();
                                callback.onFinish(file);
                            }
                        });
    }

    private void download(){
        new DownloadFile(this, context, showDialog, dest).execute(URL);
    }

    @Override
    public void onFinish(File f) {
        callback.onFinish(f);
    }

    @Override
    public void onException(Exception e) {
        callback.onException(e);
    }
}
