package mx.com.marflo.marflolibrary.progress_bar_view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import mx.com.marflo.marflolibrary.R;

/**
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 29/08/2018
 */
public class ProgressBarMannager {

    private Context context;
    private ProgressBar progressBar;
    private AlertDialog dialog;
    private TextView tv;

    public ProgressBarMannager(Context context, int dialogResourceTitle, int tvResourceMessage){
        this.context = context;

        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(context).inflate(R.layout.progress_bar, null, false);
        v.setPadding(20,20,20,20);

        progressBar = v.findViewById(R.id.pgProgressBar);
        setIndeterminate(false);

        tv = v.findViewById(R.id.tvProgressBar);

        setMessage(tvResourceMessage);

        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle(context.getResources().getString(dialogResourceTitle));
        b.setCancelable(false);
        b.setView(v);

        dialog = b.create();
    }

    public void init(){
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    public void setMessage(int resMes){
        tv.setText(context.getResources().getString(resMes));
    }

    public void setMessage(String mes){
        tv.setText(mes);
    }

    public void close(){
        if (!((Activity) context).isFinishing()) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void setProgres(long total, long downloaded){
        double div = (double)downloaded / total;
        int P = (int) (div * 100);
        progressBar.setProgress(P);
    }

    public void setIndeterminate(boolean det){
        progressBar.setIndeterminate(det);
    }
}
