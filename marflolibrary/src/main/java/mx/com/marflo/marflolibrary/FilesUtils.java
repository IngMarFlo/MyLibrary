package mx.com.marflo.marflolibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import java.io.File;

/**
* @author Alejandro Martínez Flores
 * @since 28/08/2018
*/

public class FilesUtils {

    public static void borrarDirectorio(String path){
        if (path != null){
            File f = new File(path);
            deleteFile(f);
        }
    }

    public static void borrarDirectorio(File f){
        deleteFile(f);
    }

    private static void deleteFile(File f){
        if (f != null) {
            if (f.exists()) {

                if (f.isDirectory()) {
                    File[] files = f.listFiles();

                    for (File fi: files) {
                        if (fi.isDirectory()) {
                            deleteFile(fi);
                        }
                        fi.delete();
                    }
                }
                f.delete();
            }
        }
    }

    static Uri getUriFromProvider(Context c, File f, String provider){
        return FileProvider.getUriForFile(c, provider, f);
    }

    public static void visualizarArchivoConChooser(Context context, File file, int codeRequest, String provider){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            uri = getUriFromProvider(context, file, provider);
        } else{
            uri = Uri.fromFile(file);
        }

        intent.setDataAndType(uri,getMimeType(file.getName()));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent chooser = Intent.createChooser(intent, context.getResources().getString(R.string.chooser_open_with));
        Activity activity = (Activity)context;

        try {
            activity.startActivityForResult(chooser,codeRequest);
        } catch (ActivityNotFoundException e) {

            new PersonalDialog().showDialog(context,
                    context.getResources().getString(R.string.chooser_dialog_not_app_title),
                    context.getResources().getString(R.string.chooser_dialog_not_app_message),
                    PersonalDialog.ICON.INFO,null);
        }
    }

    /**
     * Método que devuelve el mime type de un archivo
     * @param fileName  Nombre del archivo incluyendo su extensión
     * @return String mime type
     */
    private static String getMimeType(String fileName){
        String extension = fileName.substring(fileName.length()-4);
        if (!extension.contains(".")){
            extension = "."+extension;
        }
        String mimeExtension = MimeTypeMap.getFileExtensionFromUrl(extension);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeExtension);
    }
}
