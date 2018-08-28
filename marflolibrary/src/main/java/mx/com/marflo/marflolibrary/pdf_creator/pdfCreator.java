package mx.com.marflo.marflolibrary.pdf_creator;

import android.content.Context;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;

import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
public abstract class pdfCreator {
    public Context context;

    public pdfCreator(Context context){
        this.context = context;
    }

    Context getContext(){
        return context;
    }

    void onCancel(){
        new PersonalDialog()
                .showDialog(context,
                        context.getResources().getString(R.string.CANCELADO),
                        context.getResources().getString(R.string.pdf_maker_cancelled),
                        PersonalDialog.ICON.INFO, null);
    }

    public static File getFichero(Context c, String name){
        if (!name.contains(".pdf")){
            name += ".pdf";
        }
        return new File(c.getCacheDir(), name);
    }

    public abstract File create() throws DocumentException, FileNotFoundException;
}
