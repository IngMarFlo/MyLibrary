package mx.com.marflo.marflolibrary.modules_download_manager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus;
import com.google.android.play.core.tasks.OnFailureListener;

import java.util.Set;

import mx.com.marflo.marflolibrary.HandleException;
import mx.com.marflo.marflolibrary.PersonalDialog;
import mx.com.marflo.marflolibrary.R;

/**
 * @version 1
 * @author Ing Alejandro Martínez Flores
 * @since 02/08/2018
 */
public class modulesDownloadMannager {
    private Context context;

    private SplitInstallManager manager;

    private MODULES module;

    private int mySession = 0;

    private ProgressBar progressBar;
    private AlertDialog dialog;
    private TextView tv;
    private boolean showDialogDownload;

    public modulesDownloadMannager(MODULES module, Context context){
        this.module = module;
        this.context= context;

        manager             = SplitInstallManagerFactory.create(context.getApplicationContext());
        showDialogDownload  = true;
    }

    public void download(){

        if (isInstaled()){
            goModule(false);
        }else {

            if (showDialogDownload) {
                dialogDescarga();
            }else{
                initDownload();
            }

        }
    }

    private void dialogDescarga(){
        new PersonalDialog()
                .setShowNo(context.getResources().getString(R.string.CANCELAR))
                .showDialog(context,
                        context.getResources().getString(R.string.download_content_title),
                        context.getResources().getString(R.string.download_content_message),
                        PersonalDialog.ICON.INFO, new PersonalDialog.Callback() {
                            @Override
                            public void onPositiveClick(AlertDialog ad) {
                                ad.dismiss();
                                showDialogDownload = false;
                                initDownload();
                            }

                            @Override
                            public void onNeutralClick(AlertDialog ad) {

                            }

                            @Override
                            public void onNegativeClick(AlertDialog ad) {
                                ad.dismiss();
                            }
                        });
    }

    private void initDownload(){
        initDialog();

        SplitInstallRequest request = SplitInstallRequest.newBuilder()
                .addModule(module.getModuleName())
                .build();

        manager.registerListener(stateUpdatedListener);
        manager.startInstall(request)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        SplitInstallException sie = (SplitInstallException) e;
                        manageErrors(0, sie);
                    }
                });
    }

    private boolean isInstaled(){
        Set<String> installedModules = manager.getInstalledModules();

        for (String s : installedModules){
            if (s.equals(module.getModuleName())){
                return true;
            }
        }
        return false;
    }

    private void cancelInstall(){
        manager.cancelInstall(mySession);
    }

    /*METODOS PRIVADOS*/

    private SplitInstallStateUpdatedListener stateUpdatedListener = new SplitInstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(SplitInstallSessionState state) {
            mySession = state.sessionId();

            switch (state.status()){
                case SplitInstallSessionStatus.PENDING:
                    break;
                case SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION:
                    try {
                        context.startIntentSender(state.resolutionIntent().getIntentSender(), null, 0,0,0);
                    } catch (IntentSender.SendIntentException e) {
                        new HandleException(context).show(e);
                    }
                    break;
                case SplitInstallSessionStatus.DOWNLOADING:
                    long total      = state.totalBytesToDownload();
                    long progres    = state.bytesDownloaded();

                    setProgres(total, progres);
                    break;
                case SplitInstallSessionStatus.DOWNLOADED:
                    tv.setText(context.getResources().getString(R.string.download_downloaded));
                    break;
                case SplitInstallSessionStatus.INSTALLING:
                    progressBar.setIndeterminate(true);
                    tv.setText(context.getResources().getString(R.string.download_instaling_module));
                    break;
                case SplitInstallSessionStatus.INSTALLED:
                    tv.setText(context.getResources().getString(R.string.download_module_installed));
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    goModule(true);
                    break;
                case SplitInstallSessionStatus.FAILED:
                    manageErrors(state.errorCode(), null);
                    break;
                case SplitInstallSessionStatus.CANCELING:
                    progressBar.setProgress(0);
                    progressBar.setIndeterminate(true);
                    tv.setText(context.getResources().getString(R.string.download_canceling));
                    break;
                case SplitInstallSessionStatus.CANCELED:
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    Toast.makeText(context,context.getResources().getString(R.string.download_download_cancel), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    private void showCancelDialog(String error){

        new PersonalDialog()
            .setShowNo(context.getResources().getString(R.string.CANCELAR))
            .setYesTitle(context.getResources().getString(R.string.TRY))
            .showDialog(context, context.getResources().getString(R.string.ERROR),
                    error +"\n\n"+context.getResources().getString(R.string.QUE_HACER),
                PersonalDialog.ICON.INFO, new PersonalDialog.Callback() {
                    @Override
                    public void onPositiveClick(AlertDialog cancelDialog) {
                        cancelDialog.dismiss();
                        download();
                    }

                    @Override
                    public void onNeutralClick(AlertDialog cancelDialog) {
                    }

                    @Override
                    public void onNegativeClick(AlertDialog cancelDialog) {

                        cancelDialog.dismiss();
                        cancelInstall();
                    }
                });
    }

    private void initDialog(){

        View v = LayoutInflater.from(context).inflate(R.layout.progress_bar, null, false);
        v.setPadding(20,20,20,20);

        progressBar = v.findViewById(R.id.pgProgressBar);
        progressBar.setIndeterminate(false);

        tv          = v.findViewById(R.id.tvProgressBar);

        tv.setText(context.getResources().getString(R.string.download_init));

        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle(context.getResources().getString(R.string.download_downloaded_module));
        b.setCancelable(false);
        b.setView(v);

        dialog = b.create();
        dialog.show();
    }

    private void setProgres(long total, long downloaded){
        double div = (double)downloaded / total;
        int P = (int) (div * 100);

        String t = P+" %";

        tv.setText(t);
        progressBar.setProgress(P);

    }

    private void goModule(boolean restart){

        Intent i;
        if (restart) {
            i = context.getApplicationContext().getPackageManager()
                    .getLaunchIntentForPackage(context.getApplicationContext().getPackageName());
            if (i != null) {
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        }else{

            i = new Intent().setClassName(context.getPackageName(), module.getActivityName());
        }

        if (i != null){
            context.startActivity(i);
        }
    }

    private void manageErrors(int error, SplitInstallException e){
        manager.cancelInstall(mySession);
        if (dialog != null) {
            dialog.dismiss();
        }

        //Para cualquier error considerar un diálogo que indique el error y las opciones de reintentar y cancelar
        String eM = "";
        if (e != null){
            error   = e.getErrorCode();
            eM      = e.getMessage();
        }
        switch (error){
            case SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED:
                showCancelDialog("ERROR ACTIVE_SESSIONS_LIMIT_EXCEEDED"+"\n"+eM);
                break;

            case SplitInstallErrorCode.MODULE_UNAVAILABLE:
                showCancelDialog("MODULE_UNAVAILABLE"+"\n"+eM);
                break;

            case SplitInstallErrorCode.INVALID_REQUEST:
                showCancelDialog("INVALID_REQUEST"+"\n"+eM);
                break;

            case SplitInstallErrorCode.SESSION_NOT_FOUND:
                showCancelDialog("SESSION_NOT_FOUND"+"\n"+eM);
                break;

            case SplitInstallErrorCode.API_NOT_AVAILABLE:
                showCancelDialog("API_NOT_AVAILABLE"+"\n"+eM);
                break;

            case SplitInstallErrorCode.ACCESS_DENIED:
                showCancelDialog("ACCESS_DENIED"+"\n"+eM);
                break;

            case SplitInstallErrorCode.NETWORK_ERROR:
                showCancelDialog("NETWORK_ERROR"+"\n"+eM);
                break;

            case SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION:
                showCancelDialog("INCOMPATIBLE_WITH_EXISTING_SESSION"+"\n"+eM);
                break;

            case SplitInstallErrorCode.SERVICE_DIED:
                if (dialog != null){
                    tv.setText(context.getResources().getString(R.string.TRYING));
                }
                download();
                break;
        }
    }
}
