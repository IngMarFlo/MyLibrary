package mx.com.marflo.marflolibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.widget.Toast;

import java.io.File;
import java.security.acl.Permission;

import mx.com.marflo.marflolibrary.basic_camera.CameraActivity;
import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;

/**
* @author   Alejandro Martínez Flores
* @since    02/02/2017
*/

public class getCamera {

    private File directory;
    //private String provider;

    public getCamera(){}

    /*public getCamera setProvider(String provider){
        this.provider = provider;
        return this;
    }*/

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

    @RequiresPermission(allOf = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void tomarFoto(Context context, String photoName, int requestCode) {

        if (androidPermits.verificarPermiso(context, AndroidRuntimePermits.CAMERA)){
            if (androidPermits.verificarPermiso(context, AndroidRuntimePermits.WRITE_EXTERNAL_STORAGE)){

                File f = getOutputMediaFile(context, photoName);

                if (f == null){
                    Toast.makeText(context, context.getResources().getString(R.string.tomar_foto_file_null), Toast.LENGTH_LONG).show();
                    return;
                }

                //Intent data = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //data.putExtra(MediaStore.EXTRA_OUTPUT, FilesUtils.getUriFromProvider(context, f, provider));
                //data.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                //if (data.resolveActivity(context.getPackageManager()) == null) {
                Intent data = new Intent(context, CameraActivity.class);
                data.putExtra(CameraActivity.DESTINY, f);
                //}

                Activity ac = (Activity) context;
                ac.startActivityForResult(data, requestCode);
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

        return new File(directory, photoName);
    }
}
