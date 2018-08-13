package mx.com.marflo.marflolibrary;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Date;

/**
 * Clase que almacena diversos métodos para su uso general
 *
 * @autor   Ing Alejandro Martínez Flores
 * @since   09/07/2018
 * @version 1
 */
public class utils {

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
     * @return
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
                        Toast.makeText(c, "No pudo cargarse la imagen", Toast.LENGTH_SHORT).show();
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
}
