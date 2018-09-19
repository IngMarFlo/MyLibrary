package mx.com.marflo.marflolibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.content.FileProvider;
import android.util.Log;
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

        /*Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            uri = getUriFromProvider(context, file, provider);
        } else{
            uri = Uri.fromFile(file);
        }*/
		Uri uri = FileProvider.getUriForFile(context, provider, file);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri,context.getContentResolver().getType(uri));
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
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

    /**
     * @param context   Contexto que llama al método
     * @param uri       Uri que contiene el archivo
     * @return          Nombre del archivo
     */
    public static String getFileNameByUri(Context context, Uri uri){
        String fileName="unknown";//default fileName

        try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
        }
        return fileName;
    }

    /**
     * @param context   Contexto que llama al método
     * @param uri       Uri que contiene el archivo
     * @return          Path del archivo
     */
    public static String getPath(Context context, Uri uri) {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                switch (type) {
                    case "image":
                        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "video":
                        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "audio":
                        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        break;
                }
                selection = "_id=?";
                selectionArgs = new String[]{ split[1] };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor ;
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                if (cursor != null) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (cursor.moveToFirst()) {
                        return cursor.getString(column_index);
                    }
                    cursor.close();
                }

            } catch (Exception ignored) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * @param fname     File name
     * @param formatos  Accepted formats
     * @return          result of validation
     */
    public static boolean validateFile(String fname, String... formatos){
        fname = fname.toLowerCase();
        String extension = fname.substring(fname.length()-4);

        for (String ext: formatos) {
            if (extension.contains(ext)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
