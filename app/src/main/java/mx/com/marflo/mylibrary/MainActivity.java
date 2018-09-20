package mx.com.marflo.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import mx.com.marflo.marflolibrary.FilesUtils;
import mx.com.marflo.marflolibrary.basic_camera.CameraActivity;
import mx.com.marflo.marflolibrary.getCamera;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCamera camera = new getCamera();
        camera.setDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        camera.tomarFoto(this, "test.jpg", 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            if (resultCode == RESULT_OK){
                if (data.getExtras() != null) {
                    File f = (File) data.getExtras().getSerializable(CameraActivity.RESULT);
                    FilesUtils.visualizarArchivoConChooser(MainActivity.this, f, 1, "mx.com.marflo.mylibrary.provider");
                }
            }
        }
    }
}
