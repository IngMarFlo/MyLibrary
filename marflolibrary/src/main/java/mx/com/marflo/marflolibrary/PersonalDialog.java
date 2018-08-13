package mx.com.marflo.marflolibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Clase que crea un cuadro de diálogo de una forma más compacta
 * @version 1
 * @author Ing Alejandro Martínez Flores
 * @since 11/08/2018
 */
public class PersonalDialog {
    private AlertDialog mAlertDialog;
    private boolean     showNo,showNeutral, cancelabe;
    private String      yesTitle,noTitle,neutralTitle;
    private View view;

    public PersonalDialog(){
        showNo      = false;
        showNeutral = false;
        cancelabe   = false;

        yesTitle     = null;
        noTitle      = null;
        neutralTitle = null;
        view         = null;
    }

    /**
     * Método que agrega un view al cuadro de diálogo, esto para crear cuadros con formulario, radios, etc.
     * @param view Elemento a mostrar
     */
    public PersonalDialog setView(View view){
        this.view = view;
        return this;
    }

    /**
     * Método que establece la aparición del botón negativo en el cuadro de diálogo
     * @param title Texto para mostrar en el botón
     */
    public PersonalDialog setShowNo(String title){
        showNo          = true;
        this.noTitle    = title;
        if(mAlertDialog!=null){
            mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setText(title);
        }
        return this;
    }

    /**
     * Método que establece la aparición del botón neutral en el cuadro de diálogo
     * @param title Texto para mostrar en el botón
     */
    public PersonalDialog setShowNeutral(String title){
        showNeutral         = true;
        this.neutralTitle   = title;
        if(mAlertDialog!=null){
            mAlertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setText(title);
        }
        return this;
    }

    /**
     * Método para establecer el título del botón
     * @param title Texto para mostrar en el botón
     */
    public PersonalDialog setYesTitle (String title){
        this.yesTitle = title;
        if(mAlertDialog!=null){
            mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(title);
        }
        return this;
    }

    /**
     * Método con el cual se crea el cuadro de diálogo de acuerdo a la configuración anterior y se muestra una vez creado
     * @param context   Contexto que llama al método para mostrar el cuadro de diálogo
     * @param title     Título a mostrar en el cuadro de diálogo
     * @param mensaje   Mensaje a mostrar en el cuadro de diálogo
     * @param icon      Icono que se mostrará en el cuadro de diálogo
     * @param callback  Interfaz que se ejecuta al dar click en algún botón
     */
    public void showDialog(@NonNull Context context, @Nullable String title, @Nullable String mensaje,
                           @Nullable ICON icon, @Nullable final Callback callback){
        AlertDialog.Builder b = new AlertDialog.Builder(context);

        b.setTitle(title);
        b.setMessage(mensaje);
        if (icon!=null){
            b.setIcon(icon.getResource());
        }else {
            b.setIcon(null);
        }
        b.setCancelable(cancelabe);
        if (view!=null){
            b.setView(view);
        }

        String yes;
        if (yesTitle == null){
            yes = context.getResources().getString(R.string.ACEPTAR);
        }else {
            yes = yesTitle;
        }

        b.setPositiveButton(yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        if (showNo){
            b.setNegativeButton(noTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

        if (showNeutral){
            b.setNeutralButton(neutralTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

        final AlertDialog d = b.create();
        d.show();
        mAlertDialog = d;

        d.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback!=null){
                    callback.onPositiveClick(mAlertDialog);
                }else{
                    mAlertDialog.dismiss();
                }
            }
        });

        d.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback!=null){
                    callback.onNegativeClick(mAlertDialog);
                }else{
                    mAlertDialog.dismiss();
                }
            }
        });

        d.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback!=null){
                    callback.onNeutralClick(mAlertDialog);
                }else{
                    mAlertDialog.dismiss();
                }
            }
        });
    }

    /**
     * Interface para controlar el click de los botones del diálogo
     */
    public interface Callback{
        /**
         * Se ejecuta cuando el botón positivo del cuadro de diálogo es presionado
         * @param ad Cuadro de diálogo
         */
        void onPositiveClick(AlertDialog ad);


        /**
         * Se ejecuta cuando el botón neutral del cuadro de diálogo es presionado
         * @param ad Cuadro de diálogo
         */
        void onNeutralClick(AlertDialog ad);


        /**
         * Se ejecuta cuando el botón negativo del cuadro de diálogo es presionado
         * @param ad Cuadro de diálogo
         */
        void onNegativeClick(AlertDialog ad);
    }

    /**
     * Enumeración para establecer el ícono del diálogo
     */
    public enum ICON{

        /**
         * Utilizar para diálogos que solicitan confirmación del usuario así como en
         * diálogos que muestren algún mensaje de adverdencia
         */
        INFO(R.drawable.ic_alert),

        /**
         * Utilizar para diálogos que indiquen la ejecución correcta de alguna operación o
         * confirmación de algo
         */
        CORRECT(R.drawable.ic_correct),

        /**
         * Utilizar para diálogos que indiquen errores
         */
        WARNING(R.drawable.ic_warning);

        private int resource;
        ICON(int resource){
            this.resource = resource;
        }

        public int getResource() {
            return resource;
        }
    }
}
