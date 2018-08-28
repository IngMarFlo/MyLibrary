package mx.com.marflo.marflolibrary.permisos_android;

import android.Manifest;
import android.content.Context;

import mx.com.marflo.marflolibrary.R;

/**
 * Enumeración con los permisos configurados
 * @author Alejandro Martínez Flores
 * @version 1
 * @since 28/08/2018
 */
public enum AndroidRuntimePermits{

    WRITE_EXTERNAL_STORAGE(2000, R.string.permit_write_external_storage, R.string.permit_write_external_storage_denied, Manifest.permission.WRITE_EXTERNAL_STORAGE),
    ACCESS_NETWORK_STATE(2001, R.string.permit_access_network_state, R.string.permit_access_network_state_denied, Manifest.permission.ACCESS_NETWORK_STATE),
    CALL_PHONE(2001, R.string.permit_call_phone, R.string.permit_call_phone_denied, Manifest.permission.CALL_PHONE),
    CAMERA(2002, R.string.permit_camera, R.string.permit_camera_denied, Manifest.permission.CAMERA);

    private int code, messageResource, deniedMessage;
    private String name;

    AndroidRuntimePermits(int code, int messageResource, int deniedMessage, String name) {
        this.code               = code;
        this.messageResource    = messageResource;
        this.deniedMessage      = deniedMessage;
        this.name               = name;
    }

    public int getRequestCode() {
        return code;
    }

    public String getDeniedMessage(Context context){
        return context.getResources().getString(deniedMessage);
    }

    String getDialogRequestMessageResource(Context context) {
        return context.getResources().getString(messageResource);
    }

    String getName() {
        return name;
    }
}
