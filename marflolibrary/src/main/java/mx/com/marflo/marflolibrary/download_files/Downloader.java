package mx.com.marflo.marflolibrary.download_files;

import android.content.Context;

import java.io.File;

import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;
import mx.com.marflo.marflolibrary.progress_bar_view.ProgressBarMannager;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 29/08/2018
 */
public class Downloader implements DownloadFileCallback{

    private Context context;
    private DownloadFileCallback callback;
    private ProgressBarMannager pbMannager;
    private boolean showDialog;
    private File dest;
    private String URL;

    /**
     * Constructor de la clase, para correr la descarga debe usar el método {@link #run()}
     * @param context       Contexto que llama a la clase
     * @param showDialog    Indicador para mostrar o no el cuadro de descarga
     * @param URL           Dirección del archivo a descargar, debe contener el nombre del archivo así como su extensión
     * @param dest          Directorio de destino en el dispositivo
     * @param callback      Interfaz de comunicación con la clase
     */
    public Downloader(Context context, boolean showDialog, String URL, File dest, DownloadFileCallback callback){
        this.callback   = callback;
        this.context    = context;
        this.dest       = dest;
        this.URL        = URL;
        this.showDialog = showDialog;
    }

    /**
     * Método para ejecutar la descarga del archivo
     */
    public void run(){
        if (androidPermits.verificarPermiso(context, AndroidRuntimePermits.WRITE_EXTERNAL_STORAGE)){
            if (showDialog) {
                pbMannager = new ProgressBarMannager(context, R.string.downloader_pb_title, R.string.downloader_tv_init_title);
                pbMannager.init();
            }

            new DownloadFile(this, dest, new Callback() {
                @Override
                public void onProgress(long total, long download) {
                    if (showDialog) {
                        pbMannager.setProgres(total, download);
                    }
                }
            }).execute(URL);

        }
    }

    @Override
    public void onFinish(File f) {
        if (showDialog) {
            pbMannager.close();
        }
        callback.onFinish(f);
    }

    @Override
    public void onException(Exception e) {
        if (showDialog) {
            pbMannager.close();
        }
        callback.onException(e);
    }

    interface Callback{
        void onProgress(long total, long download);
    }
}
