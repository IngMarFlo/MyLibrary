package mx.com.marflo.marflolibrary.pdf_creator;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import java.io.File;

import mx.com.marflo.marflolibrary.R;
import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
public class pdfMaker {
    private pdfCreator pdfCreator;
    private makeFile task;
    private ProgressDialog pd;
    private boolean cancelable;

    public pdfMaker(pdfCreator pdfCreator, boolean cancelable, final pdfCreatorCallback callback){
        this.pdfCreator = pdfCreator;
        this.cancelable = cancelable;

        if (androidPermits.verificarPermiso(pdfCreator.getContext(), AndroidRuntimePermits.WRITE_EXTERNAL_STORAGE)){
            task = new makeFile(pdfCreator, new pdfCreatorCallback() {
                @Override
                public void onFinish(File f) {
                    pd.dismiss();
                    callback.onFinish(f);
                }

                @Override
                public void onException(Exception e) {
                    pd.dismiss();
                    callback.onException(e);
                }
            });

            initDialog();
            task.execute();
        }
    }

    private void initDialog(){
        pd = new ProgressDialog(pdfCreator.getContext());
        pd.setMessage(pdfCreator.getContext().getResources().getString(R.string.pdf_maker_creating_file));
        pd.setTitle(pdfCreator.getContext().getResources().getString(R.string.pdf_maker_progres_message));
        pd.setCancelable(cancelable);
        pd.setIndeterminate(true);
        pd.show();
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                task.cancel(true);
            }
        });
    }
}
