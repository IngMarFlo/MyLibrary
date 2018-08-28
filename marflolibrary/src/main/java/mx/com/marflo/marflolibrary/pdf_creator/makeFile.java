package mx.com.marflo.marflolibrary.pdf_creator;

import android.os.AsyncTask;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
class makeFile extends AsyncTask<String, Integer, File> {

    private pdfCreator pdfCreator;
    private pdfCreatorCallback callback;

    makeFile(pdfCreator pdfCreator, pdfCreatorCallback callback){
        this.pdfCreator = pdfCreator;
        this.callback   = callback;
    }

    @Override
    protected File doInBackground(String... strings) {
        try {
            return pdfCreator.create();
        } catch (DocumentException | IOException e) {
            callback.onException(e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (file != null){
            callback.onFinish(file);
        }
    }

    @Override
    protected void onCancelled(File file) {
        super.onCancelled(file);
        if (file.exists()){
            file.delete();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        pdfCreator.onCancel();
    }
}
