package mx.com.marflo.marflolibrary;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase que almacena diversos métodos para su uso general
 *
 * @author   Ing Alejandro Martínez Flores
 * @since   09/07/2018
 * @version 1
 */
public class utils {

    public static SimpleDateFormat getSimpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    }

    public enum TIME_FACTORS{

        SEGS_MIL(1000),
        MINS_MIL(SEGS_MIL.getValue() * 60),
        HRS_MIL(MINS_MIL.getValue() * 60),
        DAYS_MIL(HRS_MIL.getValue() * 24);

        long value;

        TIME_FACTORS(long value){
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }

    /**
     * Devuelve la diferencia de tiempo (dependiende de fc) entre dos fechas
     * @param fi    Fecha inicial
     * @param ff    Fecha final
     * @param fc    Factor de tiempo
     * @return Diferencia de tiempo
     */
    public static long getDifference(Date fi, Date ff, TIME_FACTORS  fc){
        return (ff.getTime() - fi.getTime()) / fc.getValue();
    }

    public static boolean isLargeDevice(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return false;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Valida que el path no sea nulo, vacío, un solo espacio, o la palabra null
     * @param p Path recibido
     * @return  Resultado de la validación
     */
    public static boolean isValidPath(String p){
        return p != null && !p.isEmpty() && !p.equals(" ") && !p.equals("null");
    }

    public static void putImageWithGlide(String path, final ImageView imv, @DrawableRes int placeHolder){
        final Context c = imv.getContext();
        Glide.with(c).load(path).asBitmap().placeholder(placeHolder).centerCrop().
                listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        //Toast.makeText(c, "No pudo cargarse la imagen", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        imv.setImageBitmap(resource);
                        return true;
                    }
                }).into(imv);
    }

    public static void visualizarArchivoConChooser(Context context, File file, int codeRequest){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            uri = FileProvider.getUriForFile(context, "mx.com.marflo.marflolibrary.provider",file);
        } else{
            uri = Uri.fromFile(file);
        }

        String abrirCon		= context.getResources().getString(R.string.utils_visualizar_archivo_con_chooser_abrir_con);
        String noAppTitle	= context.getResources().getString(R.string.utils_visualizar_archivo_con_chooser_no_app_title);
        String noAppMessage	= context.getResources().getString(R.string.utils_visualizar_archivo_con_chooser_no_app_message);

        intent.setDataAndType(uri, getMimeType(file.getName()));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent chooser = Intent.createChooser(intent, abrirCon);
        Activity activity = (Activity)context;

        try {
            activity.startActivityForResult(chooser,codeRequest);
        } catch (ActivityNotFoundException e) {
            PersonalDialog dialog = new PersonalDialog();
            dialog.showDialog(context, noAppTitle, noAppMessage, PersonalDialog.ICON.INFO,null);
        }
    }

    private static String getMimeType(String fileName){
        String extension = fileName.substring(fileName.length()-4,fileName.length());
        if (!extension.contains(".")){
            extension = "."+extension;
        }
        String mimeExtension = MimeTypeMap.getFileExtensionFromUrl(extension);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeExtension);
    }
}
