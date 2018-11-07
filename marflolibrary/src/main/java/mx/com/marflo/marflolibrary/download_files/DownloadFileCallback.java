package mx.com.marflo.marflolibrary.download_files;

import java.io.File;

/**
 * @author Alejandro Martínez Flores
 * @version : 1
 * @since 28/08/2018
 */
public interface DownloadFileCallback {
    /**
     * Método que se ejecuta en cuanto el archivo sea descargado
     * @param f     Archivo descargado
     */
    void onFinish(File f);

    /**
     * Método que se ejecuta si sucede una excepción durante el proceso
     * @param e Excepción ejecutada
     */
    void onException(Exception e);
}
