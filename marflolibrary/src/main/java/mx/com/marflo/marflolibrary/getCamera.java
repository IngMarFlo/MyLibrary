package mx.com.marflo.marflolibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;

/**
* @author   Alejandro Martínez Flores
* @since    02/02/2017
*/

public class getCamera {

    private File directory;
    private String provider;

    public getCamera(){}

    public getCamera setProvider(String provider){
        this.provider = provider;
        return this;
    }

    public getCamera setDirectory(File directory){
        this.directory = directory;
        return this;
    }

    public File getDirectory(Context c) {
        if (directory == null){
            return c.getCacheDir();
        }else {
            return directory;
        }
    }

    public void tomarFoto(Context context, String photoName, int requestCode) {

        if (androidPermits.verificarPermiso(context, AndroidRuntimePermits.CAMERA)){
            if (androidPermits.verificarPermiso(context, AndroidRuntimePermits.WRITE_EXTERNAL_STORAGE)){

                Intent data = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri fileUri = FilesUtils.getUriFromProvider(context, getOutputMediaFile(context, photoName), provider);
                data.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                data.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                if (data.resolveActivity(context.getPackageManager()) != null) {
                    Activity ac = (Activity) context;
                    ac.startActivityForResult(data, requestCode);
                }else{
                    /*new PersonalDialog()
                    .showDialog(
                            context,
                            context.getResources().getString(R.string.tomar_foto_title_no_app),
                            context.getResources().getString(R.string.tomar_foto_message_no_app),
                            PersonalDialog.ICON.WARNING,
                            null);*/
                    Toast.makeText(context,
                            context.getResources().getString(R.string.tomar_foto_message_no_app),
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private File getOutputMediaFile(Context c, String photoName){

        if (directory == null){
            return new File(c.getCacheDir(), photoName);
        }

        if (!directory.exists()){
            if(!directory.mkdirs()){
                return null;
            }
        }

        return new File(directory.getPath()+File.separator+photoName);
    }
}
