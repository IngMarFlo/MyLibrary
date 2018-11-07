package mx.com.marflo.mylibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import mx.com.marflo.marflolibrary.FilesUtils;
import mx.com.marflo.marflolibrary.basic_camera.CameraActivity;
import mx.com.marflo.marflolibrary.getCamera;
import mx.com.marflo.marflolibrary.permisos_android.AndroidRuntimePermits;
import mx.com.marflo.marflolibrary.permisos_android.androidPermits;
import mx.com.marflo.mylibrary.impl_rcv.ImplementRcv;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMain(View v){
    	Intent i = null;
    	switch (v.getId()){
			case R.id.btnActivityMain_customViews:
				break;

			case R.id.btnActivityMain_rcvImpl:
				i = new Intent(this, ImplementRcv.class);
				break;
		}

		if (i != null){
			startActivity(i);
		}
	}
}
