package mx.com.marflo.marflolibrary;

import android.content.Context;
import android.support.v7.app.AlertDialog;


/**
 * Class to display a dialog with the type of exception and message of the same, unless a title and personalized message is indicated
 * @author  :   Ing Alejandro Martínez Flores
 * @since   :   08/06/2018
 * @version :   1
 */
public class HandleException {
    private Context c;
    private String title, message;
    private Callback callback;

    /**
     * Public constructor to create an instance of the class
     * @param c Context
     */
    public HandleException(Context c){
        this.c = c;
    }

    /**
     * Method to set the dialog title
     * @param title Dialog title
     * @return Instance of the class
     */
    public HandleException setDialogTitle(String title){
        this.title = title;
        return this;
    }

    /**
     * Method to set the dialog message
     * @param message Dialog message
     * @return Instance of the class
     */
    public HandleException setDialogMessage(String message){
        this.message = message;
        return this;
    }

    /**
     * Interface to excecute some code after the dialog show
     * @param callback  Interface
     * @return  Instance of the class
     */
    public HandleException setCallback(Callback callback){
        this.callback = callback;
        return this;
    }

    /**
     * Method to create and show the dialog
     * @param e Exception
     */
    public void show(Exception e){
        e.printStackTrace();
        if (title == null){
            title = c.getResources().getString(R.string.handle_exception_title);
        }
        if (message == null){
            message = c.getResources().getString(R.string.handle_exception_type)+" "+e.getClass().getSimpleName()+
                            "\n"+c.getResources().getString(R.string.handle_exception_message)+" "+e.getMessage();
        }

        PersonalDialog dialog = new PersonalDialog();
        dialog.showDialog(c, title, message, PersonalDialog.ICON.WARNING, new PersonalDialog.Callback() {
                    @Override
                    public void onPositiveClick(AlertDialog ad) {
                        ad.dismiss();
                        if (callback != null){
                            callback.onDialogAcepted();
                        }
                    }

                    @Override
                    public void onNeutralClick(AlertDialog ad) {

                    }

                    @Override
                    public void onNegativeClick(AlertDialog ad) {

                    }
                });
    }

    /**
     * Interface of the class
     */
    public interface Callback{
        /**
         * It is executed when the user presses the positive button of the dialog
         */
        void onDialogAcepted();
    }
}
