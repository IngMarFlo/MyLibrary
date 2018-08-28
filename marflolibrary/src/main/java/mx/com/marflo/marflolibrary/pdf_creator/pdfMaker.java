package mx.com.marflo.marflolibrary.pdf_creator;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
public class pdfMaker {
    private pdfCreator pdfCreator;
    private makeFile task;

    public pdfMaker(pdfCreator pdfCreator, pdfCreatorCallback callback){
        this.pdfCreator = pdfCreator;
        task = new makeFile(pdfCreator, callback);
    }

    public void run(){
        initDialog();
        task.execute();
    }

    private void initDialog(){
        new PersonalDialog()
                .setYesTitle(pdfCreator.getContext().getResources().getString(R.string.CANCELAR))
                .setView(getView())
                .showDialog(pdfCreator.getContext(),
                        pdfCreator.getContext().getResources().getString(R.string.pdf_maker_creating_file),
                        null,null, new PersonalDialog.Callback() {
                    @Override
                    public void onPositiveClick(AlertDialog alertDialog) {
                        alertDialog.dismiss();
                        task.cancel(true);
                    }

                    @Override
                    public void onNeutralClick(AlertDialog alertDialog) {

                    }

                    @Override
                    public void onNegativeClick(AlertDialog alertDialog) {

                    }
                });
    }

    @SuppressLint("InflateParams")
    private View getView(){
        return LayoutInflater.from(pdfCreator.getContext()).inflate(R.layout.pdf_creator_progress,
                null, false);
    }
}
